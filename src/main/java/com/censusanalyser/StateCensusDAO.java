package com.censusanalyser;

import java.util.Comparator;

public class StateCensusDAO
{
    public String state;
    public String stateCode;
    public long population;
    public int densityPerSqKm;
    public int areaInSqKm;

    public String stateId;
    public StateCensusDAO(IndianStateCensusClass indianStateCensusClass)
    {
        state=indianStateCensusClass.getState();
        population=indianStateCensusClass.getPopulation();
        densityPerSqKm=indianStateCensusClass.getDensity();
        areaInSqKm=indianStateCensusClass.getArea();
    }
    public StateCensusDAO(StateCodePOJO indianStateCensusClass)
    {
        stateCode=indianStateCensusClass.getStateCode();
    }

    public StateCensusDAO(USCensusClass usCensusClass)
    {
        stateId=usCensusClass.stateId;
        state=usCensusClass.state;
        population=usCensusClass.population;
        areaInSqKm=usCensusClass.landArea;
        densityPerSqKm=usCensusClass.populationDensity;
    }

    public static Comparator<IndianStateCensusClass> getSortComparator(CensusAnalyser.SortingMode mode)
    {
        if(mode.equals(CensusAnalyser.SortingMode.STATE))
        {
            return Comparator.comparing(census->census.state);
        }
        if(mode.equals(CensusAnalyser.SortingMode.POPULATION))
        {
            return Comparator.comparing(IndianStateCensusClass::getPopulation).reversed();
        }
        if(mode.equals(CensusAnalyser.SortingMode.AREA))
        {
            return Comparator.comparing(IndianStateCensusClass::getArea).reversed();
        }
        if(mode.equals(CensusAnalyser.SortingMode.DENSITY))
        {
            return Comparator.comparing(IndianStateCensusClass::getDensity).reversed();
        }
        return null;
    }
}
