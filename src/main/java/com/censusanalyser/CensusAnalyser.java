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
            List<StateCodePOJO> censusClassList=icsvBuilder.getFileList(reader,StateCodePOJO.class);
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

    //count the total records in csv file
    private <E> int getCount(Iterator<E> iterator)
    {
        Iterable<E> iterable = () -> iterator;
        int totalRecords = (int) StreamSupport.stream(iterable.spliterator(), false).count();
        return totalRecords;
    }

    //Sort function to sort the data
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
}

