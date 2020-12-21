package com.demo;


/**
 * @Description
 * @Package com.demo
 * @ClassName
 * @Copyright: Copyright (c) 2020
 * @Date 2020-10-24 19:00
 * @Company www.peachfully.com.cn
 * @Author 桃我喜欢
 * @Version 1.0
 */

import java.util.*;

public class Demo1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] ints = new int[100];
        for (int i = 0; i < 100; i++) {
            ints[i] = 1;//1代表就绪
        }
        if(n<=100||n>0){
            Map<Integer,Integer> map = new HashMap();
            while (sc.hasNext()) {
                String[] strs = new String[n];
                for (int i = 0; i < n; i++) {
                    String op = sc.next();
                    int num = Integer.parseInt(op.split("=")[1]);
                    if (num > 100 || num <= 0) {
                        System.out.println("error");
                    } else {
                        if ("REQUEST".equals(op.substring(0, 7))) {
                            //请求
                            int cacul = cacul(num,ints);
                            if (cacul==-1){
                                System.out.println("error");
                            }else{
                                for(int k = cacul-1;k<num;k++){
                                    ints[k]=0;
                                }
                                map.put(cacul, num);
                            }
                        } else {

                            if(map.get(num)==null){
                                System.out.println("error");
                            }else{
                                int number = map.get(num);
                                for(int k = num-1;k<number;k++){
                                    ints[k]=1;
                                }
                                map.remove(num);
                            }
                        }
                    }
                }
            }
        }
    }
    public static int cacul(int temp,int[] ints) {
        for (int i = 0; i < 100; i++) {
            if (ints[i] == 1) {
                for (int j = 0; j < 100; j++) {
                    if (ints[j] == 0 && j - i > temp) {
                        return i + 1;
                    }
                }
            }
        }
        return -1;
    }
}


