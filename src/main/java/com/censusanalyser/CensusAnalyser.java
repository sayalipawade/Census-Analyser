package com.censusanalyser;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class CensusAnalyser
{
    List<IndianStateCensusClass> censusClassList=null;
    List<StateCodePOJO> stateCodeList=null;

    Map<String,IndianStateCensusClass> csvStateCensusMap=new HashMap<>();
    Map<String,StateCodePOJO> stateCodeMap=new HashMap<>();

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
            Iterator<IndianStateCensusClass> stateIterator = icsvBuilder.getCSVfile(reader, IndianStateCensusClass.class);
            while (stateIterator.hasNext())
            {
                IndianStateCensusClass state = stateIterator.next();
                this.csvStateCensusMap.put(state.getState(), state);
                censusClassList = csvStateCensusMap.values().stream().collect(Collectors.toList());
            }
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
            ICSVBuilder icsvBuilder=CSVBuliderFactory.createCSVBulider();
            Iterator<StateCodePOJO> stateCodeIterator=icsvBuilder.getCSVfile(reader,StateCodePOJO.class);
            while(stateCodeIterator.hasNext())
            {
                StateCodePOJO stateCode=stateCodeIterator.next();
                this.stateCodeMap.put(stateCode.getStateCode(),stateCode);
                stateCodeList=stateCodeMap.values().stream().collect(Collectors.toList());
            }
            return stateCodeMap.size();
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
        if(censusClassList.size()==0 | censusClassList==null)
        {
            throw new CensusAnalyserException(CensusAnalyserException.Exception_Type.NO_CENSUS_DATA,"No census data");
        }
        Comparator<IndianStateCensusClass> censusClassComparator=Comparator.comparing(census->census.getState());
        this.sort(censusClassComparator,censusClassList);
        String stateSortedCensusJson=new Gson().toJson(censusClassList);
        return stateSortedCensusJson;
    }

    //getting state code sorted data
    public String getStateCodeWiseSortedData() throws CensusAnalyserException
    {
        if(stateCodeList.size()==0 | stateCodeList==null)
        {
            throw new CensusAnalyserException(CensusAnalyserException.Exception_Type.NO_CENSUS_DATA,"No Data");
        }
        Comparator<StateCodePOJO> stateCodeComparator=Comparator.comparing(census->census.getStateCode());
        this.sort(stateCodeComparator,stateCodeList);
        String sortedStateCode=new Gson().toJson(stateCodeList);
        return sortedStateCode;
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

