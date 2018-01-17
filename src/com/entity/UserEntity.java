package com.entity;

public class UserEntity {
    private int userId;
    private String userName;
    private int gender;
    private int age;
    private String address;
    private String telNum;
    private String idNum;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelNum() {
        return telNum;
    }

    public void setTelNum(String telNum) {
        this.telNum = telNum;
    }

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (userId != that.userId) return false;
        if (gender != that.gender) return false;
        if (age != that.age) return false;
        if (userName != null ? !userName.equals(that.userName) : that.userName != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (telNum != null ? !telNum.equals(that.telNum) : that.telNum != null) return false;
        if (idNum != null ? !idNum.equals(that.idNum) : that.idNum != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + gender;
        result = 31 * result + age;
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (telNum != null ? telNum.hashCode() : 0);
        result = 31 * result + (idNum != null ? idNum.hashCode() : 0);
        return result;
    }
}
