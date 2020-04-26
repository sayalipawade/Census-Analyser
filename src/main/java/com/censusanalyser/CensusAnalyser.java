package com.censusanalyser;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class CensusAnalyser<E>
{
    Map<String, StateCensusDAO> csvStateCensusMap=new HashMap<>();
    List<StateCensusDAO> censusDAOList;

    //main method
    public static void main(String[] args)
    {
        System.out.println("Welcome to census analyser");
    }

    // Read State census CSV file
    public Integer loadIndianStateCensusData(String filePath) throws CSVBuilderException, CensusAnalyserException
    {
        try (Reader reader = Files.newBufferedReader(Paths.get(filePath));)
        {
            ICSVBuilder icsvBuilder = CSVBuliderFactory.createCSVBulider();
            Iterator<IndianStateCensusClass> stateIterator = icsvBuilder.getCSVfile(reader,IndianStateCensusClass.class);

            while (stateIterator.hasNext())
            {
                IndianStateCensusClass indiaCensusCsv = stateIterator.next();
                csvStateCensusMap.put(indiaCensusCsv.state,new StateCensusDAO(indiaCensusCsv));
            }
            censusDAOList = csvStateCensusMap.values().stream().collect(Collectors.toList());
            return csvStateCensusMap.size();
        }
        catch (IOException e)
        {
            throw new CensusAnalyserException(CensusAnalyserException.Exception_Type.FILE_NOT_FOUND, e.getMessage());
        }
        catch (RuntimeException e)
        {
            throw new CensusAnalyserException(CensusAnalyserException.Exception_Type.INCORRECT_DELIMETER,e.getMessage());
        }
    }

   //Read State Code CSV file
   public int loadIndianStateCodeData(String csvFilePath) throws CensusAnalyserException, CSVBuilderException
    {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));)
        {
            ICSVBuilder icsvBuilder = CSVBuliderFactory.createCSVBulider();
            Iterator<StateCodePOJO> stateIterator = icsvBuilder.getCSVfile(reader,StateCodePOJO.class);

            while (stateIterator.hasNext())
            {
                StateCodePOJO stateCodePOJO = stateIterator.next();
                csvStateCensusMap.put(stateCodePOJO.state,new StateCensusDAO(stateCodePOJO));
            }
            censusDAOList = csvStateCensusMap.values().stream().collect(Collectors.toList());
            return csvStateCensusMap.size();
        }
        catch (IOException e)
        {
            throw new CensusAnalyserException(CensusAnalyserException.Exception_Type.FILE_NOT_FOUND,
                                                                                    "Enter correct file name and type");
        }
        catch (RuntimeException e)
        {
            throw new CensusAnalyserException(CensusAnalyserException.Exception_Type.INCORRECT_DELIMETER,
                                                                                        "Check delimiter and header");
        }
    }

    //getting state sorted data
    public String getStateWiseData() throws CensusAnalyserException
    {
        if(censusDAOList.size()==0 | censusDAOList==null)
        {
            throw new CensusAnalyserException(CensusAnalyserException.Exception_Type.NO_CENSUS_DATA,"No census data");
        }
        Comparator<StateCensusDAO> censusClassComparator=Comparator.comparing(csvStateCensusMap->csvStateCensusMap.state);
        this.sort(censusClassComparator,censusDAOList);
        String stateSortedCensusJson=new Gson().toJson(censusDAOList);
        return stateSortedCensusJson;
    }

    //count the total records in csv file
    private <E> int getCount(Iterator<E> iterator)
    {
        Iterable<E> iterable = () -> iterator;
        int totalRecords = (int) StreamSupport.stream(iterable.spliterator(), false).count();
        return totalRecords;
    }

    //Sort function to sort the State census data
    public<E> void sort(Comparator<E> censusClassComparator,List<E> censusClassList)
    {
        for(int i=0;i<censusClassList.size();i++)
        {
            for(int j=0;j<censusClassList.size()-i-1;j++)
            {
                E census1=censusClassList.get(j);
                E census2=censusClassList.get(j+1);
                if(censusClassComparator.compare(census1,census2)>0)
                {
                    censusClassList.set(j,census2);
                    censusClassList.set(j+1,census1);
                }
            }
        }
    }
}

