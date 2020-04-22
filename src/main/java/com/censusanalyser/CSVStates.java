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
    public int loadIndianStateCodeData(String csvFilePath) throws IOException {
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
        return count;
    }
}
