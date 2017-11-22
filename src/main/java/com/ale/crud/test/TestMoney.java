package com.ale.crud.test;

import java.math.BigDecimal;

public class TestMoney {

    public static void main(String[] args) {
        //  四舍五入
        double f = 111231.5545;
        BigDecimal b = new BigDecimal(f);
        //保留2位小数
        double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        System.out.println(f1);
    }
}
