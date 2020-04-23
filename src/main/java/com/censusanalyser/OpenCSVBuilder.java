package com.censusanalyser;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.Reader;
import java.util.Iterator;

public class OpenCSVBuilder
{
    public<E> Iterator<E> getCSVfile(Reader reader, Class<E> csvClass) throws CensusAnalyserException
    {
        return this.getCSVToBean(reader,csvClass).iterator();
    }

    public<E> CsvToBean<E> getCSVToBean(Reader reader, Class<E> csvClass) throws CensusAnalyserException
    {
        try
        {
            CsvToBeanBuilder<E> csvToBeanBuilder = new CsvToBeanBuilder<E>(reader);
            csvToBeanBuilder.withType(csvClass);
            csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
            CsvToBean<E> csvToBean=csvToBeanBuilder.build();
            return csvToBean;
        }
        catch (RuntimeException e)
        {
            throw new CensusAnalyserException(CensusAnalyserException.Exception_Type.INCORRECT_DELIMETER,"check delimiter and header");
        }
    }
}
