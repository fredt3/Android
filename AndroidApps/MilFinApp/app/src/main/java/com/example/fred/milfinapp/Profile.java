package com.example.fred.milfinapp;

import java.io.Serializable;

/**
 * Created by Fred on 8/20/2015.
 */
public class Profile implements Serializable
{
    private String name;
    private String rank;
    private int years;
    private int zipCode;
    private double BAH;
    private double BAS;
    private boolean b_dependents;
    private double dutyPay;
    private double otherIncome;

    Profile()
    {
        this("noName");
    }
    Profile(String name)
    {
        rank = "";
        years = -1;
        zipCode = -1;
        BAH = -1;
        BAS = -1;
        b_dependents = false;
        dutyPay = -1;
        otherIncome = -1;
        this.setName(name);
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public void setRank(String rank)
    {
        this.rank = rank;
    }
    public void setYears(int years)
    {
        this.years = years;
    }
    public void setZipCode(int zipCode)
    {
        if(String.valueOf(zipCode).length() == 5)
        {
            this.zipCode = zipCode;
        }
    }
    public void setBAH(double BAH)
    {
        this.BAH = BAH;
    }
    public void setBAS(double BAS)
    {
        this.BAS = BAS;
    }
    public void setB_dependents(boolean b_dependents)
    {
        this.b_dependents = b_dependents;
    }
    public void setDutyPay(double dutyPay)
    {
        this.dutyPay = dutyPay;
    }
    public void  setOtherIncome(double otherIncome)
    {
        this.otherIncome = otherIncome;
    }

    public String getName() {
        return name;
    }

    public String getRank()
    {
        return rank;
    }
    public int getYears()
    {
        return years;
    }
    public int getZipCode()
    {
        return zipCode;
    }
    public double getBAH()
    {
        return BAH;
    }
    public double getBAS()
    {
        return BAS;
    }
    public boolean getB_dependents()
    {
        return b_dependents;
    }
    public double getDutyPay()
    {
        return dutyPay;
    }
    public double  getOtherIncome()
    {
        return otherIncome;
    }



}
