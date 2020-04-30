package com.censusanalyser;

import java.util.Map;

public class USCensusAdapter extends CensusAdapter
{
    @Override
    public Map<String, StateCensusDAO> loadCensusData(String... csvFilePath) throws CensusAnalyserException,
                                                                                                 CSVBuilderException
    {
        return super.loadCensusData(IndianStateCensusClass.class, csvFilePath[0]);
    }
}
