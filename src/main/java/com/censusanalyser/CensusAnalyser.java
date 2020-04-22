package com.censusanalyser;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.StreamSupport;

public class CensusAnalyser
{
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

            Iterator<IndianStateCensusClass> stateCSVIterator = this.getCSVfile(reader,IndianStateCensusClass.class);
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
    }

    //Read State Code CSV file
    public int loadIndianStateCodeData(String csvFilePath) throws CensusAnalyserException
    {
        int count = 0;
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));)
        {
            Iterator<StateCodePOJO> statesCSVIterator = this.getCSVfile(reader,StateCodePOJO.class);
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
    }

    //Opencsv code
    private<E> Iterator<E> getCSVfile(Reader reader, Class<E> csvClass) throws CensusAnalyserException
    {
        try
        {
            CsvToBeanBuilder<E> csvToBeanBuilder = new CsvToBeanBuilder<E>(reader);
            csvToBeanBuilder.withType(csvClass);
            csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
            CsvToBean<E> csvToBean=csvToBeanBuilder.build();
            return csvToBean.iterator();
        }
        catch (RuntimeException e)
        {
            throw new CensusAnalyserException(CensusAnalyserException.Exception_Type.INCORRECT_DELIMETER,"check delimiter and header");
        }
    }
}

