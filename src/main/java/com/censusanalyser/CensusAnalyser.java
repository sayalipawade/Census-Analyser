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
            Iterable<IndianStateCensusClass> stateCensusIterable = () -> stateIterator;
            StreamSupport.stream(stateCensusIterable.spliterator(),false).
                    forEach(censusCSV->csvStateCensusMap.put(censusCSV.getState(),new StateCensusDAO(censusCSV)));
            censusDAOList=csvStateCensusMap.values().stream().collect(Collectors.toList());
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
            Iterable<StateCodePOJO> stateCodeIterable = () -> stateIterator;
            StreamSupport.stream(stateCodeIterable.spliterator(),false).
                    forEach(censusCSV->csvStateCensusMap.put(censusCSV.state,new StateCensusDAO(censusCSV)));
            censusDAOList=csvStateCensusMap.values().stream().collect(Collectors.toList());
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

    //Read State Code CSV file
    public int loadUSCensusData(String csvFilePath) throws CensusAnalyserException, CSVBuilderException
    {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));)
        {
            ICSVBuilder icsvBuilder = CSVBuliderFactory.createCSVBulider();
            Iterator<USCensusClass> stateIterator = icsvBuilder.getCSVfile(reader,USCensusClass.class);
            Iterable<USCensusClass> stateCodeIterable = () -> stateIterator;
            StreamSupport.stream(stateCodeIterable.spliterator(),false).
                    forEach(censusCSV->csvStateCensusMap.put(censusCSV.state,new StateCensusDAO(censusCSV)));
            censusDAOList=csvStateCensusMap.values().stream().collect(Collectors.toList());
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
        this.sort(censusClassComparator);
        String stateSortedCensusJson=new Gson().toJson(censusDAOList);
        return stateSortedCensusJson;
    }

    //getting state code sorted data
    public String getStateCodeWiseSortedData() throws CensusAnalyserException
    {
        if(censusDAOList.size()==0 | censusDAOList==null)
        {
            throw new CensusAnalyserException(CensusAnalyserException.Exception_Type.NO_CENSUS_DATA,"No Data");
        }
        Comparator<StateCensusDAO> stateCodeComparator=Comparator.comparing(census->census.stateCode);
        this.sort(stateCodeComparator);
        String sortedStateCode=new Gson().toJson(censusDAOList);
        return sortedStateCode;
    }

    //getting population in sorted order
    public String getPopulationSortedData() throws CensusAnalyserException
    {
        if(censusDAOList.size()==0 || censusDAOList==null)
        {
            throw new CensusAnalyserException(CensusAnalyserException.Exception_Type.NO_CENSUS_DATA, "No Data");
        }
        Comparator<StateCensusDAO> stateCensusDAOComparator = Comparator.comparing(census -> census.population);
        this.sort(stateCensusDAOComparator);
        String sortedCensusJson = new Gson().toJson(censusDAOList);
        return sortedCensusJson;
    }

    //getting density in sorted order
    public String getDensityWiseSortedData() throws CensusAnalyserException
    {
        if(censusDAOList.size()==0 | censusDAOList==null)
        {
            throw new CensusAnalyserException(CensusAnalyserException.Exception_Type.NO_CENSUS_DATA,"No Data");
        }
        Comparator<StateCensusDAO> stateCodeComparator=Comparator.comparing(census->census.densityPerSqKm);
        this.sort(stateCodeComparator);
        String sortedDensity=new Gson().toJson(censusDAOList);
        return sortedDensity;
    }

    //getting Area in descending order
    public String getAreaWiseSortedData() throws CensusAnalyserException
    {
        if(censusDAOList.size()==0 | censusDAOList==null)
        {
            throw new CensusAnalyserException(CensusAnalyserException.Exception_Type.NO_CENSUS_DATA,"No Data");
        }
        Comparator<StateCensusDAO> stateCodeComparator=Comparator.comparing(census->census.areaInSqKm);
        this.descendingSort(stateCodeComparator);
        String sortedArea=new Gson().toJson(censusDAOList);
        return sortedArea;
    }

    //Sort function to sort the State census data
    public void sort(Comparator<StateCensusDAO> stateCensusDAOComparator)
    {
        for(int i=0;i<censusDAOList.size();i++)
        {
            for(int j=0;j<censusDAOList.size()-i-1;j++)
            {
                StateCensusDAO census1=censusDAOList.get(j);
                StateCensusDAO census2=censusDAOList.get(j+1);
                if(stateCensusDAOComparator.compare(census1,census2)>0)
                {
                    censusDAOList.set(j,census2);
                    censusDAOList.set(j+1,census1);
                }
            }
        }
    }

    //Sort function to sort the data in descending order
    public void descendingSort(Comparator<StateCensusDAO> stateCensusDAOComparator)
    {
        for(int i=0;i<censusDAOList.size();i++)
        {
            for(int j=0;j<censusDAOList.size()-i-1;j++)
            {
                StateCensusDAO census1=censusDAOList.get(j);
                StateCensusDAO census2=censusDAOList.get(j+1);
                if(stateCensusDAOComparator.compare(census1,census2)<0)
                {
                    censusDAOList.set(j,census2);
                    censusDAOList.set(j+1,census1);
                }
            }
        }
    }
}

