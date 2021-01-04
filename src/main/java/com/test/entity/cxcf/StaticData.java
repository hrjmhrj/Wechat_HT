package com.test.entity.cxcf;

import java.math.BigDecimal;

/**
 * @program: gyxxdz
 * @description:
 * @author: Bruse Queen
 * @create: 2019-03-01 11:19
 **/
public class StaticData {

    // 各方条件都查找不到开票人时用
    public static final String defaultKPR = "超管";
    // 各种条件都找不到超限金额时用
    public static final BigDecimal CXJE = new BigDecimal("100000");
    // 临时超行限制
    public static final int minHangShuXianZhi=100;
    // 出现自定义异常打印控制台
    public static final String ziDingTiShi = "出现自定义异常";
    // 出现最大异常
    public static final String exceptionTiShi = "程序错误,出现最大异常!!";
    // 服务器异常提示
    public static final String fuWuQiYiChangTiShi = "服务器异常，请稍后再试！";
    // 签章出现问题提示
    public static final String qianZahngCuoWuTiShi = "签章出现错误，请确认签章证书已提交服务器!";
    // 出现最大异常返回前端信息
    public static final ReturnJsonNoData exceptionData = new ReturnJsonNoData(1,fuWuQiYiChangTiShi);


    // 用于数字取反，和设置折扣行的数量
    public static final BigDecimal fuShuBigdecimal = new BigDecimal("-1");
    // 取默认数量
    public static final BigDecimal zhengOneBigdecimal = new BigDecimal("1");
    // 用户比较是否大于0
    public static final BigDecimal zeroBigDecimal = new BigDecimal("0");
    // 最小折扣金额
    public static final BigDecimal minZheKouBigDecimal = new BigDecimal("0.01");




}
