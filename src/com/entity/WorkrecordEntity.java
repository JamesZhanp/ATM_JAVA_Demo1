package com.entity;

import java.sql.Timestamp;
import java.util.Date;

public class WorkrecordEntity {
    private int recordId;
    private int cardNum;
    private String type;
    private Date time;
    private double moneyNum;
    private double balance;
    private int workCity;

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public int getCardNum() {
        return cardNum;
    }

    public void setCardNum(int cardNum) {
        this.cardNum = cardNum;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public double getMoneyNum() {
        return moneyNum;
    }

    public void setMoneyNum(double moneyNum) {
        this.moneyNum = moneyNum;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getWorkCity() {
        return workCity;
    }

    public void setWorkCity(int workCity) {
        this.workCity = workCity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WorkrecordEntity that = (WorkrecordEntity) o;

        if (recordId != that.recordId) return false;
        if (cardNum != that.cardNum) return false;
        if (Double.compare(that.moneyNum, moneyNum) != 0) return false;
        if (Double.compare(that.balance, balance) != 0) return false;
        if (workCity != that.workCity) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (time != null ? !time.equals(that.time) : that.time != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = recordId;
        result = 31 * result + cardNum;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        temp = Double.doubleToLongBits(moneyNum);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(balance);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + workCity;
        return result;
    }
}
