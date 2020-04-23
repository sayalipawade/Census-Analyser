package com.censusanalyser;

public class CSVBuilderException extends Exception
{
    public enum Exception_Type
    {
        CENSUS_FILE_PROBLEM,UNABLE_TO_PARSE;
    }
    Exception_Type type;
    public CSVBuilderException(Exception_Type type,String message)
    {
        super(message);
        this.type=type;
    }
}
