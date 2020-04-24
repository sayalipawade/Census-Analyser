package com.censusanalyser;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

public class CSVStates
{
    //METHOD TO LOAD THE CSV FILE AND GET
    public int loadIndianStateCodeData(String csvFilePath) throws CensusAnalyserException
    {
        int count=0;
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));)
        {
            CsvToBean<StateCodePOJO> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(StateCodePOJO.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            Iterator<StateCodePOJO> statesCSVIterator = csvToBean.iterator();
            while (statesCSVIterator.hasNext())
            {
                StateCodePOJO censusCSV = statesCSVIterator.next();
                count++;
            }
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
        return count;
    }
}
