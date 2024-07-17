package com.example.demo.test;

public class RefTest {
    /**反射获取对象的三种方式
     *   1.Class.forName()
     *   2.类名.class
     *   3.对象.getClass();
     */
    Class<?> clazz=Class.forName("com.example.demo.test.RefTest");

    public RefTest() throws ClassNotFoundException {


    }
}
