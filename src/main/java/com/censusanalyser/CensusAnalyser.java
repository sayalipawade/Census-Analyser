package com.censusanalyser;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.StreamSupport;

public class CensusAnalyser
{
    List<IndianStateCensusClass> censusClassList=null;
    List<StateCodePOJO> stateCodeList=null;

    //main method
    public static void main(String[] args)
    {
        System.out.println("Welcome to census analyser");
    }

    // Read State census CSV file
    public Integer readFile(String filePath) throws CensusAnalyserException
    {
        try (Reader reader = Files.newBufferedReader(Paths.get(filePath));)
        {
            ICSVBuilder icsvBuilder=CSVBuliderFactory.createCSVBulider();
            censusClassList=icsvBuilder.getFileList(reader,IndianStateCensusClass.class);
            return censusClassList.size();
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
        catch (CSVBuilderException e)
        {
            e.printStackTrace();
        }
        return  0;
    }

    //Read State Code CSV file
    public int loadIndianStateCodeData(String csvFilePath) throws CensusAnalyserException, CSVBuilderException
    {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));)
        {
            ICSVBuilder icsvBuilder=CSVBuliderFactory.createCSVBulider();
            stateCodeList=icsvBuilder.getFileList(reader,StateCodePOJO.class);
            return stateCodeList.size();
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

    //getting data of state
    public String getStateWiseData() throws CensusAnalyserException
    {
        if(censusClassList.size()==0 | censusClassList==null)
        {
            throw new CensusAnalyserException(CensusAnalyserException.Exception_Type.NO_CENSUS_DATA,"No census data");
        }
        Comparator<IndianStateCensusClass> censusClassComparator=Comparator.comparing(census->census.getState());
        this.sort(censusClassComparator);
        String stateSortedCensusJson=new Gson().toJson(censusClassList);
        return stateSortedCensusJson;
    }

    public String getStateCodeWiseSortedData() throws CensusAnalyserException
    {
        if(stateCodeList.size()==0 | stateCodeList==null)
        {
            throw new CensusAnalyserException(CensusAnalyserException.Exception_Type.NO_CENSUS_DATA,"No Data");
        }
        Comparator<StateCodePOJO> stateCodeComparator=Comparator.comparing(census->census.getStateCode());
        this.sorting(stateCodeComparator);
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
    public void sort(Comparator<IndianStateCensusClass> censusClassComparator)
    {
        for(int i=0;i<censusClassList.size();i++)
        {
            for(int j=0;j<censusClassList.size()-i-1;j++)
            {
                IndianStateCensusClass census1=censusClassList.get(j);
                IndianStateCensusClass census2=censusClassList.get(j+1);
                if(censusClassComparator.compare(census1,census2)>0)
                {
                    censusClassList.set(j,census2);
                    censusClassList.set(j+1,census1);
                }
            }
        }
    }

    //Sorting function to sort the state code data
    public void sorting(Comparator<StateCodePOJO> stateCodeComparator)
    {
        for(int i=0;i<stateCodeList.size()-1;i++)
        {
            for(int j=0;j<stateCodeList.size()-i-1;j++)
            {
                StateCodePOJO census1=stateCodeList.get(j);
                StateCodePOJO census2=stateCodeList.get(j+1);
                if(stateCodeComparator.compare(census1,census2)>0)
                {
                    stateCodeList.set(j,census2);
                    stateCodeList.set(j+1,census1);
                }
            }
        }
    }
}

