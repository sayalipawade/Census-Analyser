package com.censusanalysertest;
import com.censusanalyser.CensusAnalyser;
import com.censusanalyser.CensusAnalyserException;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class CensusAnalyserTest
{
    CensusAnalyser censusAnalyser=new CensusAnalyser();
    /*TC1.1:Given the States Census CSV file, Check to ensure the Number of Record matches */
    @Test
    public void givenFilePath_WhenNoOfRecordMatch_ThenReturnTrue() throws Exception
    {
        Integer noOfRecords=censusAnalyser.readFile("/home/asus/IdeaProjects/StateCensusAnalyser/src/test/resources/StateCensus.csv");
        Assert.assertEquals((Integer)29,noOfRecords);
    }

    /* TC1.2:Given the State Census CSV File if incorrect Returns a custom Exception*/
    @Test
    public void givenFilePath_WhenIncorrect_ThenThrow_Exception() throws Exception
    {
        try
        {
            censusAnalyser.readFile("/home/asus/IdeaProjects/StateCensusAnalyser/src/test/resources/StateCensus1.csv");
        }
        catch (CensusAnalyserException  e)
        {
            Assert.assertEquals(CensusAnalyserException.Exception_Type.FILE_NOT_FOUND,e.type);
        }
    }

    /*TC 1.3:Given csv file when type is incorrect should throw exception*/
    @Test
    public void givenFilePath_whenTypeIncorrect_thenThrowException() throws Exception
    {
        try
        {
            censusAnalyser.readFile("/home/asus/IdeaProjects/StateCensusAnalyser/src/test/resources/StateCensus1.cs");
        }
        catch (CensusAnalyserException e)
        {
            Assert.assertEquals(CensusAnalyserException.Exception_Type.FILE_NOT_FOUND,e.type);
        }
    }
}
