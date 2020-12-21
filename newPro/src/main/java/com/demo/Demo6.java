package com.demo;

import java.util.*;

/**
 * @Description
 * @Package com.demo
 * @ClassName
 * @Copyright: Copyright (c) 2020
 * @Date 2020-11-06 10:14
 * @Company www.peachfully.com.cn
 * @Author 桃我喜欢
 * @Version 1.0
 */


public class Demo6 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String str = sc.nextLine();
            Map<Character, Integer> map = new TreeMap();
            for (int j = 0; j < str.length(); j++) {
                char c = str.charAt(j);
                if (map.get(c) == null) {
                    map.put(c, 1);
                } else {
                    map.put(c, map.get(c) + 1);
                }
            }
            List<Character> list = new ArrayList<Character>();
            for (char c : map.keySet()) {
                list.add(c);
            }
            for (int i = 0; i < list.size() - 1; i++) {
                for (int j = 0; j < list.size() - i - 1; j++) {
                    if (map.get(list.get(j)) < map.get(list.get(j + 1))) {
                        char temp = list.get(j);
                        list.set(j, list.get(j + 1));
                        list.set(j + 1, temp);
                    }
                }
            }
            for (char c : list) {
                System.out.print(c + ":" + map.get(c) + ";");
            }
        }
    }
}
