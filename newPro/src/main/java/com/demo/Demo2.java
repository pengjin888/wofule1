package com.demo;

/**
 * @Description
 * @Package com.demo
 * @ClassName
 * @Copyright: Copyright (c) 2020
 * @Date 2020-11-03 9:41
 * @Company www.peachfully.com.cn
 * @Author 桃我喜欢
 * @Version 1.0
 */
public class Demo2 {
    public static void main(String[] args) {
        method1();
    }
    public static void method1(){
        final Thread t1 = new Thread(new Runnable() {
            @Override public void run() {
                System.out.println("t1 is finished");
            }
        });
        final Thread t2 = new Thread(new Runnable() {
            @Override public void run() {
                try {
                    t1.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("t2 is finished");
            }
        });

        Thread t3 = new Thread(new Runnable() {
            @Override public void run() {
                try {
                    t2.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("t3 is finished");
            }
        });
        t3.start();
        t2.start();
        t1.start();
    }
}
