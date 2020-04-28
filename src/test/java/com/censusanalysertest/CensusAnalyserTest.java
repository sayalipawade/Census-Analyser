package com.censusanalysertest;
import com.censusanalyser.*;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

public class CensusAnalyserTest
{
    private static final String STATE_CENSUS_CSV_FILE_PATH="src/test/resources/StateCensus.csv";
    private static final String STATE_CENSUS_WRONG_FILE_PATH="src/test/resources/StateCensus1.csv";
    private static final String STATE_CENSUS_WRONG_FILE_TYPE="src/test/resources/StateCensus.cs";
    private static final String STATE_CENSUS_WRONG_DELIMITER="src/test/resources/StateCensusDataWithWrongDelimiter.csv";

    private static final String STATE_CODE_CSV_FILE="src/test/resources/StateCode.csv";
    private static final String STATE_CODE_WRONG_CSV_FILE="src/test/resources/StateCode1.csv";
    private static final String STATE_CODE_WRONG_FILE_TYPE="src/test/resources/StateCode.cs";
    private static final String STATE_CODE_WITH_WRONG_DELIMITER="src/test/resources/StateCodeWithWrongDelimiter.csv";

    private static final String US_CENSUS_CSV_FILE_PATH="src/test/resources/USCensusData.csv";

    CensusAnalyser censusAnalyser=new CensusAnalyser();

    /*TC1.1:Given the States Census CSV file, Check to ensure the Number of Record matches */
    @Test
    public void givenFilePath_WhenNoOfRecordMatch_ThenReturnTrue() throws CensusAnalyserException, CSVBuilderException
    {
        Integer noOfRecords=censusAnalyser.loadStateCensusCSVData(CensusAnalyser.Country.INDIA,STATE_CENSUS_CSV_FILE_PATH);
        Assert.assertEquals((Integer)29,noOfRecords);
    }

    /* TC1.2:Given the State Census CSV File if incorrect Returns a custom Exception*/
    @Test
    public void givenFilePath_WhenIncorrect_ThenThrow_Exception() throws CSVBuilderException
    {
        try
        {
            censusAnalyser.loadStateCensusCSVData(CensusAnalyser.Country.INDIA,STATE_CENSUS_WRONG_FILE_PATH);
        }
        catch (CensusAnalyserException  e)
        {
            Assert.assertEquals(CensusAnalyserException.Exception_Type.FILE_NOT_FOUND,e.type);
        }
    }

    /*TC1.3:Given csv file when type is incorrect should throw exception*/
    @Test
    public void givenFilePath_WhenTypeIncorrect_thenThrowException() throws CSVBuilderException
    {
        try
        {
            censusAnalyser.loadStateCensusCSVData(CensusAnalyser.Country.INDIA,STATE_CENSUS_WRONG_FILE_TYPE);
        }
        catch (CensusAnalyserException e)
        {
            Assert.assertEquals(CensusAnalyserException.Exception_Type.FILE_NOT_FOUND,e.type);
        }
    }

    /*TC1.4:Given State census csv file when correct but delimiter is incorrect should throw custom exception*/
    @Test
    public void givenFilePath_WhenDelimiterIncorrect_ThenThrowException() throws CSVBuilderException
    {
        try
        {
            censusAnalyser.loadStateCensusCSVData(CensusAnalyser.Country.INDIA,STATE_CENSUS_WRONG_DELIMITER);
        }
        catch (CensusAnalyserException e)
        {
            Assert.assertEquals(CensusAnalyserException.Exception_Type.INCORRECT_DELIMETER,e.type);
        }
    }

    /*TC1.5:Given csv file when header incorrect should throw exception*/
    @Test
    public void givenFilePath_WhenHeaderIncorrect_ThenThrowException() throws CSVBuilderException
    {
        try
        {
            censusAnalyser.loadStateCensusCSVData(CensusAnalyser.Country.INDIA,STATE_CENSUS_WRONG_DELIMITER);
        }
        catch (CensusAnalyserException e)
        {
            Assert.assertEquals(CensusAnalyserException.Exception_Type.INCORRECT_DELIMETER,e.type);
        }
    }

     /*TC2.1:Given State code csv file when no of records matches then return true */
    @Test
    public void givenFilePath_WhenNoOfRecordsMatches_ThenReturnTrue() throws CensusAnalyserException, CSVBuilderException
    {
        Integer count=censusAnalyser.loadIndianStateCodeData(STATE_CODE_CSV_FILE);
        Assert.assertEquals((Integer)37,count);
    }

    /*TC2.2:Given State code csv file when incorrect file path should throw exception*/
    @Test
    public void givenFilePath_WhenFilePathCorrect_ThenThrowException() throws CSVBuilderException
    {
        try
        {
            censusAnalyser.loadIndianStateCodeData(STATE_CODE_WRONG_CSV_FILE);
        }
        catch (CensusAnalyserException e)
        {
            Assert.assertEquals(CensusAnalyserException.Exception_Type.FILE_NOT_FOUND, e.type);
        }
    }

    /*TC2.3:Given state code csv file when type is incorrect should throw exception*/
    @Test
    public void givenFilePath_WhenTypeIncorrect_ThenThrowException() throws CSVBuilderException
    {
        try
        {
            censusAnalyser.loadIndianStateCodeData(STATE_CENSUS_WRONG_FILE_TYPE);
        }
        catch (CensusAnalyserException e)
        {
            Assert.assertEquals(CensusAnalyserException.Exception_Type.FILE_NOT_FOUND,e.type);
        }
    }

    /*TC 2.4:Given state code csv file when delimiter is incorrect should throw exception*/
    @Test
    public void givenFilePath_WhenDelimiterIncorrect_ThenThrowCustomException() throws CSVBuilderException
    {
        try
        {
            censusAnalyser.loadIndianStateCodeData(STATE_CENSUS_WRONG_DELIMITER);
        }
        catch (CensusAnalyserException e)
        {
            Assert.assertEquals(CensusAnalyserException.Exception_Type.INCORRECT_DELIMETER,e.type);
        }
    }

    /*TC 2.5:Given state code csv file when header is incorrect should throw exception*/
    @Test
    public void givenFilePath_WhenHeaderIncorrect_ThenThrowCustomException() throws CSVBuilderException
    {
        try
        {
            censusAnalyser.loadIndianStateCodeData(STATE_CENSUS_WRONG_DELIMITER);
        }
        catch (CensusAnalyserException e)
        {
            Assert.assertEquals(CensusAnalyserException.Exception_Type.INCORRECT_DELIMETER,e.type);
        }
    }

    /*TC 3.1:Given state census csv data when sorted should return start state of census data*/
    @Test
    public void givenIndiaCensusCsvData_WhenSorted_ThenreturnStartState() throws CSVBuilderException
    {
        try
        {
            censusAnalyser.loadStateCensusCSVData(CensusAnalyser.Country.INDIA,STATE_CENSUS_CSV_FILE_PATH);
            String sortedData = censusAnalyser.getStateWiseData();
            IndianStateCensusClass[] censusData = new Gson().fromJson(sortedData, IndianStateCensusClass[].class);
            Assert.assertEquals("Andhra Pradesh", censusData[0].state);
        }
        catch (CensusAnalyserException e) { }
    }

    /*TC3.2:Given state census csv data when sorted should return End state of census data*/
    @Test
    public void givenIndiaCensusCsvData_WhenSorted_ThenreturnEndState() throws CSVBuilderException
    {
        try
        {
            censusAnalyser.loadStateCensusCSVData(CensusAnalyser.Country.INDIA,STATE_CENSUS_CSV_FILE_PATH);
            String sortedData = censusAnalyser.getStateWiseData();
            IndianStateCensusClass[] censusData = new Gson().fromJson(sortedData, IndianStateCensusClass[].class);
            Assert.assertEquals("West Bengal", censusData[28].state);
        }
        catch (CensusAnalyserException e) { }
    }

    /*TC 4.1:Given State code csv data when sorted should return start state */
    @Test
    public void givenStateCodeCSVData_WhenSorted_ThenReturnStartState() throws CSVBuilderException
    {
        try
        {
            censusAnalyser.loadIndianStateCodeData(STATE_CODE_CSV_FILE);
            String sortData=censusAnalyser.getStateCodeWiseSortedData();
            StateCodePOJO[] csvCode=new Gson().fromJson(sortData,StateCodePOJO[].class);
            Assert.assertEquals("AD",csvCode[0].stateCode);
        }
        catch (CensusAnalyserException e){ }
    }

    /*TC 4.2:Given State code csv data when sorted should return End state */
    @Test
    public void givenStateCodeCSVData_WhenSorted_ThenReturnEndState() throws CSVBuilderException
    {
        try
        {
            censusAnalyser.loadIndianStateCodeData(STATE_CODE_CSV_FILE);
            String sortData=censusAnalyser.getStateCodeWiseSortedData();
            StateCodePOJO[] csvCode=new Gson().fromJson(sortData,StateCodePOJO[].class);
            Assert.assertEquals("WB",csvCode[36].stateCode);
        }
        catch (CensusAnalyserException e){ }
    }

    /*TC 5.1: Given State Census data when sorted should return sorted start population state*/
    @Test
    public void givenIndiaCensusCSVData_WhenSorted_ThenReturnSortedStartPopulationState()
    {
        try
        {
            censusAnalyser.loadStateCensusCSVData(CensusAnalyser.Country.INDIA,STATE_CENSUS_CSV_FILE_PATH);
            String sortedData=censusAnalyser.getPopulationSortedData();
            IndianStateCensusClass[] censusData = new Gson().fromJson(sortedData, IndianStateCensusClass[].class);
            Assert.assertEquals(607688,censusData[0].population);
        }
        catch (CensusAnalyserException | CSVBuilderException e)
        {
            e.printStackTrace();
        }
    }

    /*TC 6.1: Given State Census data when sorted should return sorted start density state*/
    @Test
    public void givenIndiaCensusCSVData_WhenSorted_ThenReturnSortedStartDensity()
    {
        try
        {
            censusAnalyser.loadStateCensusCSVData(CensusAnalyser.Country.INDIA,STATE_CENSUS_CSV_FILE_PATH);
            String sortedData=censusAnalyser.getDensityWiseSortedData();
            IndianStateCensusClass[] censusData = new Gson().fromJson(sortedData, IndianStateCensusClass[].class);
            Assert.assertEquals("Arunachal Pradesh",censusData[0].state);
        }
        catch (CensusAnalyserException | CSVBuilderException e)
        {
            e.printStackTrace();
        }
    }

    /*TC 7.1:Given State census data when sorted should return sorted start Area state*/
    @Test
    public void givenIndiaCensusCSVData_WhenSorted_ThenReturnSortedStartArea()
    {
        try
        {
            censusAnalyser.loadStateCensusCSVData(CensusAnalyser.Country.INDIA,STATE_CENSUS_CSV_FILE_PATH);
            String sortedData=censusAnalyser.getAreaWiseSortedData();
            IndianStateCensusClass[] censusData = new Gson().fromJson(sortedData, IndianStateCensusClass[].class);
            Assert.assertEquals("Rajasthan",censusData[0].state);
        }
        catch (CensusAnalyserException | CSVBuilderException e)
        {
            e.printStackTrace();
        }
    }
}
