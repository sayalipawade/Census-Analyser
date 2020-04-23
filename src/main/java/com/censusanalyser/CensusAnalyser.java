package com.censusanalyser;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.StreamSupport;

public class CensusAnalyser
{
    OpenCSVBuilder openCSV=new OpenCSVBuilder();
    public static void main(String[] args)
    {
        System.out.println("Welcome to census analyser");
    }

    // Read State census CSV file
    public Integer readFile(String filePath) throws CensusAnalyserException
    {
        int noOfRecords=0;
        try (Reader reader = Files.newBufferedReader(Paths.get(filePath));)
        {
            Iterator<IndianStateCensusClass> stateCSVIterator = openCSV.getCSVfile(reader,IndianStateCensusClass.class);
            Iterable<IndianStateCensusClass> csvIterable = ()-> stateCSVIterator;
            noOfRecords=(int) StreamSupport.stream(csvIterable.spliterator(),false).count();
            return noOfRecords;
        }
        catch (IOException e)
        {
            throw new CensusAnalyserException(CensusAnalyserException.Exception_Type.FILE_NOT_FOUND, "Enter correct file name and type");
        }
        catch (RuntimeException e)
        {
            throw new CensusAnalyserException(CensusAnalyserException.Exception_Type.INCORRECT_DELIMETER, "Check delimiter and header");
        }
        catch (CSVBuilderException e)
        {
            e.printStackTrace();
        }
        return 0;
    }

    //Read State Code CSV file
    public int loadIndianStateCodeData(String csvFilePath) throws CensusAnalyserException
    {
        int count = 0;
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));)
        {
            Iterator<StateCodePOJO> statesCSVIterator = openCSV.getCSVfile(reader,StateCodePOJO.class);
            Iterable<StateCodePOJO> iterableStateCode= () -> statesCSVIterator;
            int countRecord=(int)StreamSupport.stream(iterableStateCode.spliterator(),false).count();
            return countRecord;
        }
        catch (IOException e)
        {
            throw new CensusAnalyserException(CensusAnalyserException.Exception_Type.FILE_NOT_FOUND, "enter correct file name and type");
        }
        catch (RuntimeException e)
        {
            throw new CensusAnalyserException(CensusAnalyserException.Exception_Type.INCORRECT_DELIMETER, "check delimiter and header");
        }
        catch (CSVBuilderException e)
        {
            e.printStackTrace();
        }
        return 0;
    }
}

