package com.censusanalyser;

import java.util.Comparator;

public class StateCensusDAO
{
    public String state;
    public String stateCode;
    public long population;
    public int densityPerSqKm;
    public double areaInSqKm;
    public String StateCode;
    public int tin;
    public int srNo;
    public String stateName;

    public String stateId;
    public StateCensusDAO(IndianStateCensusClass indianStateCensusClass)
    {
        state=indianStateCensusClass.getState();
        population=indianStateCensusClass.getPopulation();
        densityPerSqKm= (int) indianStateCensusClass.getDensity();
        areaInSqKm=indianStateCensusClass.getArea();
    }
   public StateCensusDAO(StateCodePOJO stateCodePOJO)
   {
        stateCode = stateCodePOJO.getStateCode();
        srNo = stateCodePOJO.getSrNo();
        tin = stateCodePOJO.getTin();
        stateName = stateCodePOJO.getState();
   }
   public StateCensusDAO(USCensusClass usCensusClass)
    {
        stateCode=usCensusClass.stateCode;
        state=usCensusClass.state;
        population=usCensusClass.population;
        areaInSqKm=usCensusClass.landArea;
        densityPerSqKm= (int) usCensusClass.populationDensity;
    }




  /*  public static Comparator<IndianStateCensusClass> getSortComparator(CensusAnalyser.SortingMode mode)
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
    }*/


    @Override
    public String toString() {
        return "StateCensusDAO{" +
                "state='" + state + '\'' +
                ", stateCode='" + stateCode + '\'' +
                ", population=" + population +
                ", densityPerSqKm=" + densityPerSqKm +
                ", areaInSqKm=" + areaInSqKm +
                ", StateCode='" + StateCode + '\'' +
                ", tin=" + tin +
                ", srNo=" + srNo +
                ", stateName='" + stateName + '\'' +
                ", stateId='" + stateId + '\'' +
                '}';
    }
}
