package com.ctbu.entity;

import java.sql.Date;

/**
 * @Description
 * @Author LIYING
 */
public class Employee extends Person{
    private String dep;
    private String job;
    private Date hiredate;
    private String email;
    private double salary;

    public Employee(String id, String name, String sex, Date birth, String idCard, String telephone, String address) {
        super(id, name, sex, birth, idCard, telephone, address);
        this.dep=dep;
        this.job=job;
        this.hiredate=hiredate;
        this.email=email;
        this.salary=salary;
    }

    public Employee() {
    }

    public String getDep() {
        return dep;
    }

    public void setDep(String dep) {
        this.dep = dep;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Date getHiredate() {
        return hiredate;
    }

    public void setHiredate(Date hiredate) {
        this.hiredate = hiredate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
