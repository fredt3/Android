package com.example.fred.milfinapp;

import java.io.File;
import java.io.Serializable;
import java.util.Scanner;

/**
 * Created by Fred on 8/20/2015.
 */
public class Profile implements Serializable
{
    private String name;
    private String rank;
    private String years;
    private int zipCode;
    private double BAH;
    private double BAS;
    private String b_dependents;
    private double dutyPay;
    private double otherIncome;

    private double basePay;

    Profile()
    {
        this("noName");
    }
    Profile(String name)
    {
        rank = "";
        years = "";
        zipCode = -1;
        BAH = -1;
        BAS = -1;
        b_dependents = "false";
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
    public void setYears(String years)
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
    public void setB_dependents(String b_dependents)
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
    public String getYears()
    {
        return years;
    }
    public int getZipCode()
    {
        return zipCode;
    }
    public double getBAH()
    {
        try {
            Scanner in = new Scanner(new File("KeyWestTemp.txt"));
            List<String> list = in.
        } catch(Exception e){}
        return BAH;
    }
    public double getBAS()
    {
        return BAS;
    }
    public String getB_dependents()
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

    public double getTax()
    {

        double taxRate = 0;
        double taxedAllotments = basePay + getOtherIncome();

        if(b_dependents.equals("true"))
        {
            if(taxedAllotments > 0 && taxedAllotments < 18150)
            {
               taxRate = 0.1;
            }
            else if(taxedAllotments > 18150 && taxedAllotments < 73800)
            {
                taxRate = 0.15;
            }
            else if(taxedAllotments > 73800 && taxedAllotments < 148850)
            {
                taxRate = 0.25;
            }
            else if(taxedAllotments > 148850 && taxedAllotments < 226850)
            {
                taxRate = 0.28;
            }
            else if(taxedAllotments > 226850 && taxedAllotments < 405100)
            {
                taxRate = 0.33;
            }
            else if(taxedAllotments > 405100 && taxedAllotments < 457600)
            {
                taxRate = 0.35;
            }
            else
            {
                taxRate = 0.396;
            }
        }
        else
        {
            if(taxedAllotments > 0 && taxedAllotments < 9075)
            {
                taxRate = 0.1;
            }
            else if(taxedAllotments > 9075 && taxedAllotments < 36900)
            {
                taxRate = 0.15;
            }
            else if(taxedAllotments > 36900 && taxedAllotments < 89350)
            {
                taxRate = 0.25;
            }
            else if(taxedAllotments > 89350 && taxedAllotments < 186350)
            {
                taxRate = 0.28;
            }
            else if(taxedAllotments > 186350 && taxedAllotments < 405100)
            {
                taxRate = 0.33;
            }
            else if(taxedAllotments > 405100 && taxedAllotments < 406750)
            {
                taxRate = 0.35;
            }
            else
            {
                taxRate = 0.396;
            }
        }

        return taxRate * taxedAllotments;
    }

}
