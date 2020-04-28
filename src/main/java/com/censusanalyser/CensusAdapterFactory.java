package com.censusanalyser;

import java.util.Map;

public class CensusAdapterFactory
{
    public static Map<String, StateCensusDAO> getCensusData(CensusAnalyser.Country country, String[] csvFilePath) throws
                                                                            CensusAnalyserException, CSVBuilderException
    {
        if (country.equals(CensusAnalyser.Country.INDIA))
        {
            return new IndianCensusAdapter().loadCensusData(csvFilePath);
        }
        else if (country.equals(CensusAnalyser.Country.US))
        {
            return new USCensusAdapter().loadCensusData(csvFilePath[0]);
        }
        else
        {
            throw new CensusAnalyserException(CensusAnalyserException.Exception_Type.INVALID_COUNTRY,"Invalid country");
        }
    }
}
