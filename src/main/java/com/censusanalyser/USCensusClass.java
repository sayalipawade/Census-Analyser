package com.censusanalyser;
import com.opencsv.bean.CsvBindByName;
public class USCensusClass
{
    @CsvBindByName(column="State Id")
    public String stateCode;

    @CsvBindByName(column = "State")
    public String state;

    @CsvBindByName(column = "Population")
    public long population;

    @CsvBindByName(column ="Housing units")
    public String housingUnits;

    @CsvBindByName(column = "Total area")
    public double totalArea;

    @CsvBindByName(column ="Water area")
    public String water;

    @CsvBindByName(column = "Land area")
    public double landArea;

    @CsvBindByName(column = "Population Density")
    public int populationDensity;

    @CsvBindByName(column = "Housing Density")
    public String housingDensity;
}
