package com.yinhai.bean;

import java.util.Date;

public class User {
    private Short ruUserId;

    private String ruUserName;

    private String ruName;

    private String ruIdentityCode;

    private String ruPassword;

    private String ruMobile;

    private String ruEmail;

    private String rduName;

    private Date ruCreateTime;

    private Date ruUpdateTime;

    private Short ruStatus;

    @Override
    public String toString() {
        return "User [ruUserId=" + ruUserId + ", ruUserName=" + ruUserName
                + ", ruName=" + ruName + ", ruIdentityCode=" + ruIdentityCode
                + ", ruPassword=" + ruPassword + ", ruMobile=" + ruMobile
                + ", ruEmail=" + ruEmail + ", rduName=" + rduName
                + ", ruCreateTime=" + ruCreateTime + ", ruUpdateTime="
                + ruUpdateTime + ", ruStatus=" + ruStatus + "]";
    }

    public Short getRuUserId() {
        return ruUserId;
    }

    public void setRuUserId(Short ruUserId) {
        this.ruUserId = ruUserId;
    }

    public String getRuUserName() {
        return ruUserName;
    }

    public void setRuUserName(String ruUserName) {
        this.ruUserName = ruUserName == null ? null : ruUserName.trim();
    }

    public String getRuName() {
        return ruName;
    }

    public void setRuName(String ruName) {
        this.ruName = ruName == null ? null : ruName.trim();
    }

    public String getRuIdentityCode() {
        return ruIdentityCode;
    }

    public void setRuIdentityCode(String ruIdentityCode) {
        this.ruIdentityCode = ruIdentityCode == null ? null : ruIdentityCode.trim();
    }

    public String getRuPassword() {
        return ruPassword;
    }

    public void setRuPassword(String ruPassword) {
        this.ruPassword = ruPassword == null ? null : ruPassword.trim();
    }

    public String getRuMobile() {
        return ruMobile;
    }

    public void setRuMobile(String ruMobile) {
        this.ruMobile = ruMobile == null ? null : ruMobile.trim();
    }

    public String getRuEmail() {
        return ruEmail;
    }

    public void setRuEmail(String ruEmail) {
        this.ruEmail = ruEmail == null ? null : ruEmail.trim();
    }

    public String getRduName() {
        return rduName;
    }

    public void setRduName(String rduName) {
        this.rduName = rduName == null ? null : rduName.trim();
    }

    public Date getRuCreateTime() {
        return ruCreateTime;
    }

    public void setRuCreateTime(Date ruCreateTime) {
        this.ruCreateTime = ruCreateTime;
    }

    public Date getRuUpdateTime() {
        return ruUpdateTime;
    }

    public void setRuUpdateTime(Date ruUpdateTime) {
        this.ruUpdateTime = ruUpdateTime;
    }

    public Short getRuStatus() {
        return ruStatus;
    }

    public void setRuStatus(Short ruStatus) {
        this.ruStatus = ruStatus;
    }
}