package com.yongle.goku.test.service;

/**
 * Created by weinh on 2017/4/5.
 */
public class TestB extends TestA {
    public static Integer b = 4;

    static {
        b = 2;
        System.out.println(b);
        b = 3;
        System.out.println("b static");
    }

    {
        System.out.println("b code");
        System.out.println(b);
        b = 4;
    }
    public TestB() {
        System.out.println("b c");
    }
}
