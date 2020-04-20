package com.censusanalyser;
import com.opencsv.bean.CsvBindByName;
public class IndianStateCensusClass
    {
        @CsvBindByName(column="State")
        public String state;

        @CsvBindByName(column="Population")
        public String population;

        @CsvBindByName(column = "AreaInSqKm")
        public String area;

        @CsvBindByName(column = "DensityPerSqKm")
        public String density;

        public String getAreaInSqKm()
        {
            return area;
        }
        public String getDensityPerSqKm()
        {
            return density;
        }
        public String getState()
        {
            return state;
        }
        public String getPopulation()
        {
            return population;
        }
        public void setState(String state)
        {
            this.state=state;
        }
        public void setPopulation(String population)
        {
            this.population=population;
        }
        public void setAreaInSqKm(String area)
        {
            this.area=area;
        }
        public void setDensityPerSqKm(String density)
        {
            this.density=density;
        }
        @Override
        public String toString()
        {
            return "IndianStateCencusClass{" +
                    "state='" + state + '\'' +
                    ", population='" + population + '\'' +
                    ", area='" + area + '\'' +
                    ", density='" + density + '\'' +
                    '}';
        }
    }


