package com.ctbu.entity;

import java.sql.Date;

/**
 * @Description
 * @Author LIYING
 */
public class Elder extends Person {
    private String bedFee;//床位套餐
    private String bedNum;//床号
    private String relaName;//亲属名称
    private String relaPhnoe;//亲属电话

    public Elder() {
    }

    public Elder(String id, String name, String sex, Date birth, String idCard, String telephone, String address, String bedFee, String bedNum, String relaName, String relaPhnoe) {
        super(id, name, sex, birth, idCard, telephone, address);
        this.bedFee = bedFee;
        this.bedNum = bedNum;
        this.relaName = relaName;
        this.relaPhnoe = relaPhnoe;
    }

    public String getBedFee() {
        return bedFee;
    }

    public void setBedFee(String bedFee) {
        this.bedFee = bedFee;
    }

    public String getBedNum() {
        return bedNum;
    }

    public void setBedNum(String bedNum) {
        this.bedNum = bedNum;
    }

    public String getRelaName() {
        return relaName;
    }

    public void setRelaName(String relaName) {
        this.relaName = relaName;
    }

    public String getRelaPhnoe() {
        return relaPhnoe;
    }

    public void setRelaPhnoe(String relaPhnoe) {
        this.relaPhnoe = relaPhnoe;
    }
}
