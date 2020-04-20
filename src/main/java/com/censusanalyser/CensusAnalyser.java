package com.censusanalyser;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

public class CensusAnalyser
{
    public static void main(String[] args)
    {
        System.out.println("Welcome to census analyser");
    }

    // Read CSV file
    public Integer readFile(String filePath) throws CensusAnalyserException, IOException {
        int count = 0;
        try
        {
            BufferedReader reader = Files.newBufferedReader(Paths.get(filePath));
            CsvToBean<IndianStateCensusClass> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(IndianStateCensusClass.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            Iterator<IndianStateCensusClass> stateIterator = csvToBean.iterator();

            while (stateIterator.hasNext()) {
                IndianStateCensusClass indianStateCensusclass = stateIterator.next();
                System.out.println("State : " + indianStateCensusclass.getState());
                System.out.println("population: " + indianStateCensusclass.getPopulation());
                System.out.println("areaInSqKm : " + indianStateCensusclass.getAreaInSqKm());
                System.out.println("densityPerSqKm : " + indianStateCensusclass.getDensityPerSqKm());
                System.out.println("======================================");
                count++;
            }
        }
        catch (IOException e)
        {
            throw new CensusAnalyserException(CensusAnalyserException.Exception_Type.FILE_NOT_FOUND,"Enter correct file name");
        }
        return count;
    }
}
