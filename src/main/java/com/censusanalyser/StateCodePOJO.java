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

    public String getSrNo() {
        return srNo;
    }

    public void setSrNo(String srNo) {
        this.srNo = srNo;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTin() {
        return tin;
    }

    public void setTin(String tin) {
        this.tin = tin;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    @Override
    public String toString() {
        return "StateCodePOJO{" +
                "srNo='" + srNo + '\'' +
                ", state='" + state + '\'' +
                ", tin='" + tin + '\'' +
                ", stateCode='" + stateCode + '\'' +
                '}';
    }
}
