package com.censusanalyser;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.StreamSupport;

public class IndianCensusAdapter extends CensusAdapter
{
    @Override
    public Map<String, StateCensusDAO> loadCensusData(String... filePath) throws CensusAnalyserException,
                                                                                            CSVBuilderException
    {
        Map<String, StateCensusDAO> csvStateCensusMap = super.loadCensusData(IndianStateCensusClass.class, filePath[0]);
        if (filePath.length == 1)
            return csvStateCensusMap;
        return loadStateCodeData(csvStateCensusMap, filePath[1]);
    }


    private Map<String,StateCensusDAO> loadStateCodeData(Map<String,StateCensusDAO> csvStateCensusMap, String filePath)
                                                                                        throws CensusAnalyserException
    {
        try (Reader reader = Files.newBufferedReader(Paths.get(filePath));)
        {
            ICSVBuilder icsvBuilder = CSVBuliderFactory.createCSVBulider();
            Iterator<IndianStateCensusClass> csvStateCensusIterator = icsvBuilder.getCSVfile(reader,
                                                                                        IndianStateCensusClass.class);
            Iterable<IndianStateCensusClass> stateCensusIterable = () -> csvStateCensusIterator;
            StreamSupport.stream(stateCensusIterable.spliterator(), false)
                    .filter(csvStateCensusPojo -> csvStateCensusMap.get(csvStateCensusPojo.getState()) != null)
                    .forEach(csvStateCensusPojo -> csvStateCensusMap.get(csvStateCensusPojo.getState()).state =
                                                                                        csvStateCensusPojo.getState());
        }
        catch (IOException e)
        {
            throw new CensusAnalyserException(CensusAnalyserException.Exception_Type.FILE_NOT_FOUND,e.getMessage());
        }
        catch (CSVBuilderException e) { }
        return csvStateCensusMap;
    }
}

