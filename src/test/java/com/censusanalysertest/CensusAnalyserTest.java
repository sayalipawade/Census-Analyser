package com.censusanalysertest;

import com.censusanalyser.CensusAnalyser;
import org.junit.Assert;
import org.junit.Test;

public class CensusAnalyserTest
{
    /*TC1.1:Given the States Census CSV file, Check to ensure the Number of Record matches */
    @Test
    public void givenFilePath_WhenNoOfRecordMatch_ThenReturnTrue() throws Exception
    {
        CensusAnalyser censusAnalyser=new CensusAnalyser();
        Integer noOfRecords=censusAnalyser.readFile("/home/asus/IdeaProjects/StateCensusAnalyser/src/test/resources/StateCensus.csv");
        Assert.assertEquals((Integer)29,noOfRecords);
    }
}
