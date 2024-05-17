package com.mountain.model.dto;

import java.sql.Timestamp;

public class User {
    private int serial;
    private String id;
    private String password;
    private String name;
    private Timestamp birthDate;
    private String gender;
    private String email;
    private Timestamp regDate;
    private int point;

    public User() {
    }

    public User(int serial, String id, String password, String name, Timestamp birthDate, String gender, String email,
            Timestamp regDate, int point) {
        this.serial = serial;
        this.id = id;
        this.password = password;
        this.name = name;
        this.birthDate = birthDate;
        this.gender = gender;
        this.email = email;
        this.regDate = regDate;
        this.point = point;
    }

    public int getSerial() {
        return serial;
    }

    public void setSerial(int serial) {
        this.serial = serial;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Timestamp birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Timestamp getRegDate() {
        return regDate;
    }

    public void setRegDate(Timestamp regDate) {
        this.regDate = regDate;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    @Override
    public String toString() {
        return "User [serial=" + serial + ", id=" + id + ", password=" + password + ", name=" + name + ", birthDate="
                + birthDate + ", gender=" + gender + ", email=" + email + ", regDate=" + regDate + ", point=" + point
                + "]";
    }

}
