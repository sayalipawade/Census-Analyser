package com.censusanalyser;
public class CensusAnalyserException extends Exception
{
    public enum Exception_Type
    {
        FILE_NOT_FOUND,INCORRECT_DELIMETER;
    }
    public Exception_Type type;
    public CensusAnalyserException(Exception_Type type,String message)
    {
        super(message);
        this.type=type;
        System.out.println(message);
    }
}
