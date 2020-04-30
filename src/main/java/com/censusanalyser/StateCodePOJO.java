package com.censusanalyser;
import com.opencsv.bean.CsvBindByName;
public class StateCodePOJO
{

    @CsvBindByName(column = "SrNo",required = true)
    public int srNo;

    @CsvBindByName(column = "State Name",required = true)
    public String state;

    @CsvBindByName(column = "TIN",required = true)
    public int tin;

    @CsvBindByName(column = "StateCode",required = true)
    public String stateCode;

    public int getSrNo()
    {
        return srNo;
    }

    public void setSrNo(int srNo)
    {
        this.srNo = srNo;
    }

    public String getState()
    {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getTin()
    {
        return tin;
    }

    public void setTin(int tin)
    {
        this.tin = tin;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode)
    {
        this.stateCode = stateCode;
    }

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
