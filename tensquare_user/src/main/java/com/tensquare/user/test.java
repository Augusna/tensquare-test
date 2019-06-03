package com.tensquare.user;

import java.math.BigDecimal;

public class test {
    public static void main(String[] args) {
        BigDecimal decimal=new BigDecimal("4.00000");
        //decimal=decimal.add(new BigDecimal("3").multiply(new BigDecimal(4))).subtract(new BigDecimal("1")).setScale(3,BigDecimal.ROUND_UP);
       decimal=decimal.add(new BigDecimal("0.002"));
        System.out.println(new BigDecimal("12").compareTo(new BigDecimal("11")));


    }
}
