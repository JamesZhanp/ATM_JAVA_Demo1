package com.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CardEntity {
    @Id
    private int cardId;
    private String password;
    private double balance;
    private String cardNum;
    private int isUsed;
    private int userId;
    private int registeredCity;

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public int getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(int isUsed) {
        this.isUsed = isUsed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CardEntity that = (CardEntity) o;

        if (cardId != that.cardId) return false;
        if (Double.compare(that.balance, balance) != 0) return false;
        if (isUsed != that.isUsed) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (cardNum != null ? !cardNum.equals(that.cardNum) : that.cardNum != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = cardId;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        temp = Double.doubleToLongBits(balance);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (cardNum != null ? cardNum.hashCode() : 0);
        result = 31 * result + isUsed;
        return result;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRegisteredCity() {
        return registeredCity;
    }

    public void setRegisteredCity(int registeredCity) {
        this.registeredCity = registeredCity;
    }
}
