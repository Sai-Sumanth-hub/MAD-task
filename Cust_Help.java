package com.example.bank;

public class Cust_Help {
    private int mCid;
    private String mCustomerName;
    private String mAccountNumber;
    private String mLocation;
    private double mBalance;

    public Cust_Help(int mCid, String mCustomerName, String mAccountNumber, double mBalance) {
        this.mCid = mCid;
        this.mCustomerName = mCustomerName;
        this.mAccountNumber = mAccountNumber;
//        this.mLocation = mLocation;
        this.mBalance = mBalance;
    }
    public Cust_Help(String mCustomerName, String mAccountNumber, double mBalance) {
        //this.mCid = mCid;
        this.mCustomerName = mCustomerName;
        this.mAccountNumber = mAccountNumber;
//        this.mLocation = mLocation;
        this.mBalance = mBalance;
    }

    public int getmCid() {
        return mCid;
    }

    public void setmCid(int mCid) {
        this.mCid = mCid;
    }

    public String getmCustomerName() {
        return mCustomerName;
    }

    public void setmCustomerName(String mCustomerName) {
        this.mCustomerName = mCustomerName;
    }

    public String getmAccountNumber() {
        return mAccountNumber;
    }

    public void setmAccountNumber(String mAccountNumber) {
        this.mAccountNumber = mAccountNumber;
    }

    public String getmLocation() {
        return mLocation;
    }

    public void setmLocation(String mLocation) {
        this.mLocation = mLocation;
    }

    public double getmBalance() {
        return mBalance;
    }

    public void setmBalance(double mBalance) {
        this.mBalance = mBalance;
    }
}
