package com.demo;

/**
 * @Description
 * @Package com.demo
 * @ClassName
 * @Copyright: Copyright (c) 2020
 * @Date 2020-11-03 11:04
 * @Company www.peachfully.com.cn
 * @Author 桃我喜欢
 * @Version 1.0
 */
public class Demo4 implements Runnable {
    private int flag ;
    public static final Object o1 = new Object();
    public static final Object o2 = new Object();
    @Override
    public void run(){
        if(flag==1){
            synchronized (o1){
                System.out.println(Thread.currentThread().getName() + " o1。");
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized(o2){
                    System.out.println(Thread.currentThread().getName()+"o2");
                }
            }
        }
        if(flag==2){
            synchronized (o2){
                System.out.println(Thread.currentThread().getName() + " o2。");
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized(o1){
                    System.out.println(Thread.currentThread().getName()+"o1");
                }
            }
        }
    }
    public void setFlag(int flag1){
        this.flag=flag1;
    }

    public static void main(String[] args) {
        Demo4 d1 = new Demo4();
        Demo4 d2 =new Demo4();
        d1.setFlag(1);
        Thread t1 = new Thread(d1,"name1");
        t1.start();

        d2.setFlag(2);
        Thread t2 = new Thread(d2,"name2");
        t2.start();

    }

}
