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

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getPopulation() {
            return population;
        }

        public void setPopulation(String population) {
            this.population = population;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getDensity() {
            return density;
        }

        public void setDensity(String density) {
            this.density = density;
        }
    }


