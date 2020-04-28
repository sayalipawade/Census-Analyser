package com.censusanalyser;
import com.opencsv.bean.CsvBindByName;
public class USCensusClass
{
    @CsvBindByName(column="State Id")
    public String stateId;

    @CsvBindByName(column = "State")
    public String state;

    @CsvBindByName(column = "Population")
    public String population;

    @CsvBindByName(column ="Housing units")
    public String housingUnits;

    @CsvBindByName(column = "Total area")
    public String totalArea;

    @CsvBindByName(column ="Water area")
    public String water;

    @CsvBindByName(column = "Land area")
    public String landArea;

    @CsvBindByName(column = "Population Density")
    public String populationDensity;

    @CsvBindByName(column = "Housing Density")
    public String housingDensity;

}
