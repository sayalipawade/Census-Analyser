package com.censusanalyser;

public class StateCensusDAO
{
    public String state;
    public String stateCode;
    public int population;
    public int densityPerSqKm;
    public int areaInSqKm;
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
}
