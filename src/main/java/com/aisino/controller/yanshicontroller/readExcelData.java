package com.aisino.controller.yanshicontroller;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 *
 基础数据类.这里的排序和excel里面的排序一致
 */
public class readExcelData {

    //设定value代表指定读取哪一列,,index从0开始
    //value相同时,只有最后一个字段会读取到数据
    @ExcelProperty(index=0)
    private String column1;

    @ExcelProperty("部门")
    private String column2;

    @ExcelProperty("姓名")
    private String column3;

    @ExcelProperty(index = 3)
    private String column4;

    @Override
    public String toString() {
        return "readExcelData{" +
                "column1='" + column1 + '\'' +
                ", column2='" + column2 + '\'' +
                ", column3='" + column3 + '\'' +
                ", column4='" + column4 + '\'' +
                '}';
    }

    public void setColumn1(String column1) {
        this.column1 = column1;
    }

    public void setColumn2(String column2) {
        this.column2 = column2;
    }

    public void setColumn3(String column3) {
        this.column3 = column3;
    }

    public void setColumn4(String column4) {
        this.column4 = column4;
    }

    public String getColumn1() {
        return column1;
    }

    public String getColumn2() {
        return column2;
    }

    public String getColumn3() {
        return column3;
    }

    public String getColumn4() {
        return column4;
    }
}