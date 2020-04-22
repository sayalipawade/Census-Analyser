package com.censusanalysertest;
import com.censusanalyser.CSVStates;
import com.censusanalyser.CensusAnalyser;
import com.censusanalyser.CensusAnalyserException;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class CensusAnalyserTest
{
    private static final String STATE_CENSUS_CSV_FILE_PATH="src/test/resources/StateCensus.csv";
    private static final String STATE_CENSUS_WRONG_FILE_PATH="src/test/resources/StateCensus1.csv";
    private static final String STATE_CENSUS_WRONG_FILE_TYPE="src/test/resources/StateCensus.cs";
    private static final String STATE_CENSUS_WRONG_DELIMITER="src/test/resources/StateCensusDataWithWrongDelimiter.csv";
    private static final String STATE_CODE_CSV_FILE="src/test/resources/StateCode.csv";
    private static final String STATE_CODE_WRONG_CSV_FILE="src/test/resources/StateCode1.csv";
    private static final String STATE_CODE_WRONG_FILE_TYPE="src/test/resources/StateCode.txt";
    private static final String STATE_CODE_WITH_WRONG_DELIMITER="src/test/resources/StateCodeWithWrongDelimiter.csv";

    CensusAnalyser censusAnalyser=new CensusAnalyser();


    /*TC1.1:Given the States Census CSV file, Check to ensure the Number of Record matches */
    @Test
    public void givenFilePath_WhenNoOfRecordMatch_ThenReturnTrue() throws CensusAnalyserException
    {
        Integer noOfRecords=censusAnalyser.readFile(STATE_CENSUS_CSV_FILE_PATH);
        Assert.assertEquals((Integer)29,noOfRecords);
    }

    /* TC1.2:Given the State Census CSV File if incorrect Returns a custom Exception*/
    @Test
    public void givenFilePath_WhenIncorrect_ThenThrow_Exception()
    {
        try
        {
            censusAnalyser.readFile(STATE_CENSUS_WRONG_FILE_PATH);
        }
        catch (CensusAnalyserException  e)
        {
            Assert.assertEquals(CensusAnalyserException.Exception_Type.FILE_NOT_FOUND,e.type);
        }
    }

    /*TC 1.3:Given csv file when type is incorrect should throw exception*/
    @Test
    public void givenFilePath_WhenTypeIncorrect_thenThrowException()
    {
        try
        {
            censusAnalyser.readFile(STATE_CENSUS_WRONG_FILE_TYPE);
        }
        catch (CensusAnalyserException e)
        {
            Assert.assertEquals(CensusAnalyserException.Exception_Type.FILE_NOT_FOUND,e.type);
        }
    }

    /*TC 1.4:Given State census csv file when correct but delimiter is incorrect should throw custom exception*/
    @Test
    public void givenFilePath_WhenDelimiterIncorrect_ThenThrowException()
    {
        try
        {
            censusAnalyser.readFile(STATE_CENSUS_WRONG_DELIMITER);
        }
        catch (CensusAnalyserException e)
        {
            Assert.assertEquals(CensusAnalyserException.Exception_Type.INCORRECT_DELIMETER,e.type);
        }
    }

    /* TC1.5:Given csv file when header incorrect should throw exception*/
    @Test
    public void givenFilePath_WhenHeaderIncorrect_ThenThrowException()
    {
        try
        {
            censusAnalyser.readFile(STATE_CENSUS_WRONG_DELIMITER);
        }
        catch (CensusAnalyserException e)
        {
            Assert.assertEquals(CensusAnalyserException.Exception_Type.INCORRECT_DELIMETER,e.type);
        }
    }

     /*TC2.1:Given State code csv file when no of records matches then return true */
    @Test
    public void givenFilePath_WhenNoOfRecordsMatches_ThenReturnTrue() throws CensusAnalyserException
    {
        CSVStates csvStates=new CSVStates();
        Integer count=csvStates.loadIndianStateCodeData(STATE_CODE_CSV_FILE);
        Assert.assertEquals((Integer)37,count);
    }

    /*TC2.2:Given State code csv file when incorrect file path should throw exception*/
    @Test
    public void givenFilePath_WhenFilePathCorrect_ThenThrowException()
    {
        try
        {
            CSVStates csvStates = new CSVStates();
            csvStates.loadIndianStateCodeData(STATE_CODE_WRONG_CSV_FILE);
        }
        catch (CensusAnalyserException e)
        {
            Assert.assertEquals(CensusAnalyserException.Exception_Type.FILE_NOT_FOUND, e.type);
        }
    }

    /*TC2.3:Given state code csv file when type is incorrect should throw exception*/
    @Test
    public void givenFilePath_WhenTypeIncorrect_ThenThrowException()
    {
        try
        {
            CSVStates csvStates=new CSVStates();
            csvStates.loadIndianStateCodeData(STATE_CENSUS_WRONG_FILE_TYPE);
        }
        catch (CensusAnalyserException e)
        {
            Assert.assertEquals(CensusAnalyserException.Exception_Type.FILE_NOT_FOUND,e.type);
        }
    }

    /*TC 2.4:Given state code csv file when delimiter is incorrect should throw exception*/
    @Test
    public void givenFilePath_WhenDelimiterIncorrect_ThenThrowCustomException()
    {
        try
        {
            CSVStates csvStates=new CSVStates();
            csvStates.loadIndianStateCodeData(STATE_CENSUS_WRONG_DELIMITER);
        }
        catch (CensusAnalyserException e)
        {
            Assert.assertEquals(CensusAnalyserException.Exception_Type.INCORRECT_DELIMETER,e.type);
        }
    }
}
