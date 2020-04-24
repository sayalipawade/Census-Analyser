package com.censusanalyser;

public class CSVBuilderException extends Exception
{
    public enum Exception_Type
    {
        UNABLE_TO_PARSE;
    }
    Exception_Type type;
    public CSVBuilderException(Exception_Type type,String message)
    {
        super(message);
        this.type=type;
        System.out.println(message);
    }
}
