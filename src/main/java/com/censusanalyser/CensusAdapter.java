package com.censusanalyser;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.StreamSupport;
import static java.nio.file.Files.newBufferedReader;

public abstract class CensusAdapter
{
    public abstract Map<String,StateCensusDAO> loadCensusData(String... csvFilePath) throws CensusAnalyserException, CSVBuilderException;
    public <E> Map<String,StateCensusDAO> loadCensusData(Class<E> csvClass,String filePath) throws CensusAnalyserException,
            CSVBuilderException
    {
        Map<String, StateCensusDAO> csvStateCensusMap = new HashMap<>();
        try (Reader reader = newBufferedReader(Paths.get(filePath)))
        {
            ICSVBuilder icsvBuilder=CSVBuliderFactory.createCSVBulider();
            Iterator<E> stateIterator=icsvBuilder.getCSVfile(reader,csvClass);
            Iterable<E> stateCensuses = () -> stateIterator;
            if (csvClass.getName().contains("IndianStateCensusClass"))
            {
                StreamSupport.stream(stateCensuses.spliterator(), false)
                        .map(IndianStateCensusClass.class::cast)
                        .forEach(censusCSV -> csvStateCensusMap.put(censusCSV.getState(), new StateCensusDAO(censusCSV)));
            }
            else if (csvClass.getName().contains("USCensusClass"))
            {
                StreamSupport.stream(stateCensuses.spliterator(), false)
                        .map(USCensusClass.class::cast)
                        .forEach(censusCSV -> csvStateCensusMap.put(censusCSV.state, new StateCensusDAO(censusCSV)));
            }
        }
        catch (IOException e)
        {
            throw new CensusAnalyserException(CensusAnalyserException.Exception_Type.FILE_NOT_FOUND,e.getMessage());
        }
        return csvStateCensusMap;
    }
}
