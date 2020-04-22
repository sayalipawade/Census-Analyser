package com.censusanalyser;
import com.opencsv.bean.CsvBindByName;
public class StateCodePOJO
{
    @CsvBindByName(column = "SrNo",required = true)
    private String srNo;

    @CsvBindByName(column = "State Name",required = true)
    private String state;

    @CsvBindByName(column = "TIN",required = true)
    private String tin;

    @CsvBindByName(column = "StateCode",required = true)
    private String stateCode;

    @Override
    public String toString()
    {
        return "StateCodePOJO{" +
                "srNo='" + srNo + '\'' +
                ", state='" + state + '\'' +
                ", tin='" + tin + '\'' +
                ", stateCode='" + stateCode + '\'' +
                '}';
    }
}
