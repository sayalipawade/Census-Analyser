package com.censusanalyser;

public class CSVBuliderFactory
{
    public static ICSVBuilder createCSVBulider()
    {
        return new OpenCSVBuilder();
    }
}
