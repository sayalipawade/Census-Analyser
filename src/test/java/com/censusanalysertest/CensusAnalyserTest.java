package com.censusanalysertest;
import com.censusanalyser.CensusAnalyser;
import com.censusanalyser.CensusAnalyserException;
import org.junit.Assert;
import org.junit.Test;

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

    /* TC2.1:Given the State Census CSV File if incorrect Returns a custom Exception*/
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
}
