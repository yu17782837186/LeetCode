package com.ning.array;

import java.lang.reflect.Array;
import java.util.*;

public class ArrayLeetCode {
    public static void main(String[] args) {
        /**
         *  1 2 3    1 4 7
         *  4 5 6 -->2 5 8  行列交换即可
         *  7 8 9    3 6 9
         */
//        System.out.println(Arrays.deepToString(transPose(new int[][]{{1,2,3},{4,5,6},{7,8,9}})));
//
//        System.out.println(getManyNumber(new int[]{1,1,1,1,1,1,1,2,3,4,5,6,7,8,9,9,8,8,8,9,9}));
//        System.out.println(getManyNumber(new int[]{2,3,3,3,4,5,5,5,5}));
//        System.out.println(getManyNumberOptimization(new int[]{7,8,9,9,10,10,10}));
        System.out.println(Arrays.toString(orderArraySort(new int[]{-7,-6,-4,-1,0,2,5,8})));
        System.out.println(Arrays.toString(orderArraySortOptimization(new int[]{-7,-6,-4,-1,0,2,5,8})));
    }
    //转置矩阵
    public static int[][] transPose(int[][] arry) {
        //原数组的行
        int row = arry.length;
        //原数组的列
        int col = arry[0].length;
        //创建新数组 行列交换位置
        int[][] newArray = new int[col][row];
        for(int i = 0;i < row;i++) {
            for(int j = 0;j < col;j++) {
                newArray[j][i] = arry[i][j];
            }
        }
        return newArray;
    }
    //求数组中出现次数最多的数(出现了多少次呢？) 没有返回-1(暴力解法)
    public static int getManyNumber(int[] arry) {
        //用来存储最大的计数器
        int temp = 0;
        //用来存储出现次数最多的数
        int maxNumber = 0;
        for(int i = 0;i < arry.length;i++) { //2 3 3 3 4 5 5 5 5 3
            //内部临时计数器 用每一个数挨个从头到尾进行比较 每一个数都有一个计数器
            int count = 0;
            for(int j = 0;j < arry.length;j++) {
                if(arry[i] == arry[j]) {
                    count++;
                }
            }
            //每一个数进行一次比较完成以后 判断count临时计数器 如果没有重复的数 计数器为1 值为当前拿出来的这个数
            //如果有重复的数 外界的计数器被赋值 并且将这个数赋值
            if(count >= temp) {
                temp = count; //tmp 4 maxNumber 5
                maxNumber = arry[i];
            }
        }
        //所有的数都比较完成以后 判断开始最大的这个计数器 如果为1则说明 没有重复的数 如果不为1说明有重复的数
        //如果两个数出现的次数一样多 返回这两个数中谁的位置排在后面就返回谁
        if(temp == 1) {
            return -1;
        }
        return maxNumber;
    }
    //1000个数中出现次数最多的数(n个url中找到出现重复次数最多的url)
    public static int getManyNumberOptimization(int[] arry) {
        Map<Integer,Integer> map = new HashMap<>();
        for(int i = 0;i < arry.length;i++) {
            // 7 8 9 9 10 10
            if(map.containsKey(arry[i])) {
                //再次重复的话 value值+1代表出现的次数 key为原来的key
                map.put(arry[i],map.get(arry[i])+1);
            }else {
                //第一次放进去 value为1 //7 8 9 9 10
                map.put(arry[i],1);
            }
        }
        //定义一个最小值 与map的value进行比较 相当于计数器
        int maxValue = Integer.MIN_VALUE;
        //数组的去重复每一项
        int value = Integer.MIN_VALUE;
        //遍历map 拿到key和value
        for(Integer key : map.keySet()) {
            int integer = map.get(key);
            if(integer > maxValue) {
                maxValue = integer;
                value = key;
            }
        }
        return value;
    }
    /**
     * time:2021/3/6 (977)有序数组的平方
     * 给你一个按非递减顺序排序的整数数组 nums，返回每个数字的平方组成的新数组，要求也按非递减顺序排序。
     */
    //(1)暴力解法
    public static int[] orderArraySort(int[] arry) {
        int[] newArray = new int[arry.length];
        for(int i = 0;i < arry.length;i++) {
            newArray[i] = arry[i] * arry[i];
        }
        Arrays.sort(newArray);
        return newArray;
    }
    //(2)双指针解法 类似归并排序 找到分界线borderPosition的位置和borderPosition+1的位置 用来区分正负子数组
    public static int[] orderArraySortOptimization(int[] arry) {
        //定以分界线位置为-1 如果数组中没有负数 那么正子数组的起始位置为borderPosition+1为0
        int borderPosition = -1;
        for(int i = 0;i < arry.length;i++) {
            //遍历数组 找是否有负数 有就返回最后一个负数的位置 没有直接break(该数组没有负数)
            if(arry[i] < 0) {
                borderPosition = i;
            }else {
                break;
            }
        }
        //创建与原数组长度相同的新数组
        int[] newArray = new int[arry.length];
        //定以新数组的最开始位置
        int changStart = 0;
        //定以正子数组的最开始位置
        int positiveStart = borderPosition + 1;
        //当分界线位置大于等于0或者正子数组开始的位置小于整个数组的长度时有效
        while(borderPosition >= 0 || positiveStart < arry.length) {
            if(borderPosition < 0) {//边界位置小于0 说明整个数组没有负数
                newArray[changStart] = arry[positiveStart] * arry[positiveStart];
                ++positiveStart;
            }else if(positiveStart == arry.length) {//正子数组的开始位置等于数组的长度 说明整个数组没有正数
                newArray[changStart] = arry[borderPosition] * arry[borderPosition];
                --borderPosition;
            }else if(arry[borderPosition] * arry[borderPosition] < arry[positiveStart] * arry[positiveStart]) {//有正负子数组 比较正数组的最开始位置的数与负数组最后位置的数的大小 如果小 放入负数组最后位置的数 --borderPosition的位置
                newArray[changStart] = arry[borderPosition] * arry[borderPosition];
                --borderPosition;
            }else {//当所有的负数组的数都放进新数组以后 放入正数组中的数 ++positiveStart的位置
                newArray[changStart] = arry[positiveStart] * arry[positiveStart];
                ++positiveStart;
            }
            ++changStart;
        }
        return newArray;
    }
}
