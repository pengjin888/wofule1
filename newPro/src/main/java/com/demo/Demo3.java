package com.demo;

/**
 * @Description
 * @Package com.demo
 * @ClassName
 * @Copyright: Copyright (c) 2020
 * @Date 2020-11-03 10:59
 * @Company www.peachfully.com.cn
 * @Author 桃我喜欢
 * @Version 1.0
 */
public class Demo3 implements Runnable{
    private int flag = 1;
    private static final Object o1 = new Object();
    private static final Object o2 = new Object();
    public void setFlag(int flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        if (flag == 1) {
            synchronized (o1) {
                System.out.println(Thread.currentThread().getName() + " o1。");
                try {
                    Thread.sleep(800);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o2) {
                    System.out.println(Thread.currentThread().getName() + " o2~");
                }
            }
        }
        if (flag == 2) {
            synchronized (o2) {
                System.out.println(Thread.currentThread().getName() + " o2。");
                try {
                    Thread.sleep(800);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o1) {
                    System.out.println(Thread.currentThread().getName() + " o1~");
                }
            }
        }
    }

    public static void main(String[] args) {
        Demo3 deadLock1 = new Demo3();
        Demo3 deadLock2 = new Demo3();

        deadLock1.setFlag(1);
        Thread thread1= new Thread(deadLock1, "Thread1");
        thread1.start();

        deadLock2.setFlag(2);
        Thread thread2= new Thread(deadLock2, "Thread2");
        thread2.start();
    }
}
