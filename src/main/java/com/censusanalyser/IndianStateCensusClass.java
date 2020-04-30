package com.censusanalyser;
import com.opencsv.bean.CsvBindByName;
public class IndianStateCensusClass
    {
        @CsvBindByName(column="State")
        public String state;

        @CsvBindByName(column="Population")
        public long population;

        @CsvBindByName(column = "AreaInSqKm")
        public double area;

        @CsvBindByName(column = "DensityPerSqKm")
        public double density;

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

        public String getState()
        {
            return state;
        }

        public void setState(String state)
        {
            this.state = state;
        }

        public long getPopulation()
        {
            return population;
        }

        public void setPopulation(long population)
        {
            this.population = population;
        }

        public double getArea()
        {
            return area;
        }

        public void setArea(int area)
        {
            this.area = area;
        }

        public double getDensity()
        {
            return density;
        }

        public void setDensity(double density)
        {
            this.density = density;
        }

    }





















