package com.entity;

public class ManagerEntity {
    private int managerId;
    private String managerNum;
    private String password;

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    public String getManagerNum() {
        return managerNum;
    }

    public void setManagerNum(String managerNum) {
        this.managerNum = managerNum;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ManagerEntity that = (ManagerEntity) o;

        if (managerId != that.managerId) return false;
        if (managerNum != null ? !managerNum.equals(that.managerNum) : that.managerNum != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = managerId;
        result = 31 * result + (managerNum != null ? managerNum.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }
}
