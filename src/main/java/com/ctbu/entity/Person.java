package com.ctbu.entity;

import java.sql.Date;

/**
 * @Description 员工和老人的抽象父类
 * @Author LIYING
 */
public class Person {
    private String id;
    private String name;
    private String sex;
    private Date birth;
    private String idCard;
    private String telephone;
    private String address;

    public Person() {
    }

    public Person(String id, String name, String sex, Date birth, String idCard, String telephone, String address) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.birth = birth;
        this.idCard = idCard;
        this.telephone = telephone;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
