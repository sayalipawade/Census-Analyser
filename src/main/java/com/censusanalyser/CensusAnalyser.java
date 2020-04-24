package com.censusanalyser;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class CensusAnalyser
{
  //  OpenCSVBuilder openCSV=new OpenCSVBuilder();
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
            List<IndianStateCensusClass> censusClassList=icsvBuilder.getFileList(reader,IndianStateCensusClass.class);
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
        return 0;
    }

    //Read State Code CSV file
    public int loadIndianStateCodeData(String csvFilePath) throws CensusAnalyserException
    {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));)
        {
            ICSVBuilder icsvBuilder=CSVBuliderFactory.createCSVBulider();
            List<StateCodePOJO> stateCodePOJOList=icsvBuilder.getFileList(reader,StateCodePOJO.class);
            return stateCodePOJOList.size();
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
        return 0;
    }
}

