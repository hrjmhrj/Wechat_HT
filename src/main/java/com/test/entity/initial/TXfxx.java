package com.test.entity.initial;

import java.util.Date;

public class TXfxx {
    private String UUID;

    private Date CREATETIME;

    private Date UPDATETIME;

    private String XFMC;

    private String XFSH;

    private String XFYHZH;

    private String XFDZDH;

    private String terminalCode;

    private String appId;

    private String authorizationCode;

    private String requestCode;

    private String sigCode;

    private String password;

    private String token;

    private String fjh;

    private String jqbh;

    private String serverUrl;

    private String BY1;

    private String BY2;

    private String BY3;

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public Date getCREATETIME() {
        return CREATETIME;
    }

    public void setCREATETIME(Date CREATETIME) {
        this.CREATETIME = CREATETIME;
    }

    public Date getUPDATETIME() {
        return UPDATETIME;
    }

    public void setUPDATETIME(Date UPDATETIME) {
        this.UPDATETIME = UPDATETIME;
    }

    public String getXFMC() {
        return XFMC;
    }

    public void setXFMC(String XFMC) {
        this.XFMC = XFMC;
    }

    public String getXFSH() {
        return XFSH;
    }

    public void setXFSH(String XFSH) {
        this.XFSH = XFSH;
    }

    public String getXFYHZH() {
        return XFYHZH;
    }

    public void setXFYHZH(String XFYHZH) {
        this.XFYHZH = XFYHZH;
    }

    public String getXFDZDH() {
        return XFDZDH;
    }

    public void setXFDZDH(String XFDZDH) {
        this.XFDZDH = XFDZDH;
    }

    public String getTerminalCode() {
        return terminalCode;
    }

    public void setTerminalCode(String terminalCode) {
        this.terminalCode = terminalCode;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAuthorizationCode() {
        return authorizationCode;
    }

    public void setAuthorizationCode(String authorizationCode) {
        this.authorizationCode = authorizationCode;
    }

    public String getRequestCode() {
        return requestCode;
    }

    public void setRequestCode(String requestCode) {
        this.requestCode = requestCode;
    }

    public String getSigCode() {
        return sigCode;
    }

    public void setSigCode(String sigCode) {
        this.sigCode = sigCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getFjh() {
        return fjh;
    }

    public void setFjh(String fjh) {
        this.fjh = fjh;
    }

    public String getJqbh() {
        return jqbh;
    }

    public void setJqbh(String jqbh) {
        this.jqbh = jqbh;
    }

    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public String getBY1() {
        return BY1;
    }

    public void setBY1(String BY1) {
        this.BY1 = BY1;
    }

    public String getBY2() {
        return BY2;
    }

    public void setBY2(String BY2) {
        this.BY2 = BY2;
    }

    public String getBY3() {
        return BY3;
    }

    public void setBY3(String BY3) {
        this.BY3 = BY3;
    }

    @Override
    public String toString() {
        return "TXfxx{" +
                "UUID='" + UUID + '\'' +
                ", CREATETIME=" + CREATETIME +
                ", UPDATETIME=" + UPDATETIME +
                ", XFMC='" + XFMC + '\'' +
                ", XFSH='" + XFSH + '\'' +
                ", XFYHZH='" + XFYHZH + '\'' +
                ", XFDZDH='" + XFDZDH + '\'' +
                ", terminalCode='" + terminalCode + '\'' +
                ", appId='" + appId + '\'' +
                ", authorizationCode='" + authorizationCode + '\'' +
                ", requestCode='" + requestCode + '\'' +
                ", sigCode='" + sigCode + '\'' +
                ", password='" + password + '\'' +
                ", token='" + token + '\'' +
                ", fjh='" + fjh + '\'' +
                ", jqbh='" + jqbh + '\'' +
                ", serverUrl='" + serverUrl + '\'' +
                ", BY1='" + BY1 + '\'' +
                ", BY2='" + BY2 + '\'' +
                ", BY3='" + BY3 + '\'' +
                '}';
    }
}