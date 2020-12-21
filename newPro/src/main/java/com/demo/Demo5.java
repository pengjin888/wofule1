package com.demo;

/**
 * @Description
 * @Package com.demo
 * @ClassName
 * @Copyright: Copyright (c) 2020
 * @Date 2020-11-03 14:49
 * @Company www.peachfully.com.cn
 * @Author 桃我喜欢
 * @Version 1.0
 */
public class Demo5 {
    public static int thirdMax(int[] nums) {
        if (nums.length==1){
            return nums[0];
        }
        if (nums.length==2){
            return nums[1]>nums[0]?nums[1]:nums[0];
        }else{
            select(nums, 1);
            select(nums, 2);
            select(nums, 3);
            return nums[nums.length-3];
        }
    }
    public static void select(int[] nums,int x){
        for(int i = 0;i<nums.length-x;i++){
            if (nums[i]>nums[i+1]){
                int temp=nums[i];
                nums[i]=nums[i+1];
                nums[i+1]=temp;
            }
        }
    }
    public static void main(String[] args) {
        int[] ints =  {2,1,3,4,8};
        int i = thirdMax(ints);
        System.out.println(i);
    }
}
