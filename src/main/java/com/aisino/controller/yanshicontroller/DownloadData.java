package com.aisino.controller.yanshicontroller;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;

import java.util.Date;

//import com.alibaba.excel.annotation.format.DateTimeFormat;
//import com.alibaba.excel.annotation.write.style.ColumnWidth;
//import com.alibaba.excel.annotation.write.style.ContentRowHeight;
//import com.alibaba.excel.annotation.write.style.HeadRowHeight;

//内容行高度
//@ContentRowHeight(20)
//头部状态栏高度
//@HeadRowHeight(20)
//列宽度
@ColumnWidth(20)
public class DownloadData {

        @ExcelProperty(value={"主标题","主标题","OHID"},index = 0)
        private String OHID;
        @ExcelProperty(value = {"主标题","主标题","复核人"},index = 1)
        private String FHR;
        @ColumnWidth(40)
        @ExcelProperty(value = {"主标题","主标题","UPDATETIME"},index = 2)
        private String UPDATETIME;

        @ExcelProperty(value={"主标题2","主标题21","购方名称"},index = 3)
        private String GFMC;
        @ExcelProperty(value = {"主标题2","主标题21","购方税号"},index = 4)
        private String GFSH;
        @ExcelProperty(value = {"主标题2","主标题22","收款人"},index = 5)
        private String SKR;
        @ExcelProperty(value = {"主标题2","主标题22","CREATETIME"},index = 6)
        private String CREATETIME;

    @Override
    public String toString() {
        return "DownloadData{" +
                "OHID='" + OHID + '\'' +
                ", FHR='" + FHR + '\'' +
                ", UPDATETIME='" + UPDATETIME + '\'' +
                ", GFMC='" + GFMC + '\'' +
                ", GFSH='" + GFSH + '\'' +
                ", SKR='" + SKR + '\'' +
                ", CREATETIME='" + CREATETIME + '\'' +
                '}';
    }

    public void setOHID(String OHID) {
        this.OHID = OHID;
    }

    public void setFHR(String FHR) {
        this.FHR = FHR;
    }

    public void setUPDATETIME(String UPDATETIME) {
        this.UPDATETIME = UPDATETIME;
    }

    public void setGFMC(String GFMC) {
        this.GFMC = GFMC;
    }

    public void setGFSH(String GFSH) {
        this.GFSH = GFSH;
    }

    public void setSKR(String SKR) {
        this.SKR = SKR;
    }

    public void setCREATETIME(String CREATETIME) {
        this.CREATETIME = CREATETIME;
    }

    public String getOHID() {
        return OHID;
    }

    public String getFHR() {
        return FHR;
    }

    public String getUPDATETIME() {
        return UPDATETIME;
    }

    public String getGFMC() {
        return GFMC;
    }

    public String getGFSH() {
        return GFSH;
    }

    public String getSKR() {
        return SKR;
    }

    public String getCREATETIME() {
        return CREATETIME;
    }
}
