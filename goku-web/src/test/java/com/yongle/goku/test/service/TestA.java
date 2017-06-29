package com.yongle.goku.test.service;

/**
 * Created by weinh on 2017/4/5.
 */
public class TestA {
    public static Integer a = 4;

    static {
        a = 2;
        System.out.println(a);
        a = 3;
        System.out.println("a static");
    }

    {
        System.out.println("a code");
        System.out.println(a);
        a = 4;
    }

    public TestA() {
        System.out.println("a c");
    }
}
