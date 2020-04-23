package com.censusanalyser;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.Reader;
import java.util.Iterator;

public class OpenCSVBuilder implements ICSVBuilder
{
    public<E> Iterator<E> getCSVfile(Reader reader, Class<E> csvClass) throws CSVBuilderException
    {
        return this.getCSVToBean(reader,csvClass).iterator();
    }

    public<E> CsvToBean<E> getCSVToBean(Reader reader, Class<E> csvClass) throws CSVBuilderException
    {
        try
        {
            CsvToBeanBuilder<E> csvToBeanBuilder = new CsvToBeanBuilder<E>(reader);
            csvToBeanBuilder.withType(csvClass);
            csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
            CsvToBean<E> csvToBean=csvToBeanBuilder.build();
            return csvToBean;
        }
        catch (IllegalStateException e)
        {
            throw new CSVBuilderException(CSVBuilderException.Exception_Type.UNABLE_TO_PARSE,"unable to parse");
        }
    }
}
