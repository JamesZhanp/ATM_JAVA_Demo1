package com.entity;

/**
 * 这个类是在数据表访问之后保存结果的
 * */
public class InformationAccessDB {
    private String cardNum;
    private String name;
    private String gender;
    private int age;
    private String IDNum;
    private String registerCity;
    public InformationAccessDB(){}
    public InformationAccessDB(String cardNum, String name, String gender, int age, String IDNum, String registerCity){
        this.cardNum = cardNum;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.IDNum = IDNum;
        this.registerCity = registerCity;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setIDNum(String IDNum) {
        this.IDNum = IDNum;
    }

    public String getIDNum() {
        return IDNum;
    }

    public void setRegisterCity(String registerCity) {
        this.registerCity = registerCity;
    }

    public String getRegisterCity() {
        return registerCity;
    }

    @Override
    public String toString(){
        return "卡号" + cardNum + "姓名" + name + "性别" + gender + "年龄" + age
                + "身份证号" + IDNum + "注册城市" + registerCity;
    }
}
