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

//        System.out.println(Arrays.toString(orderArraySort(new int[]{-7,-6,-4,-1,0,2,5,8})));
//        System.out.println(Arrays.toString(orderArraySortOptimization(new int[]{-7,-6,-4,-1,0,2,5,8})));

//        System.out.println(sumOddSubArrays(new int[]{1,2})); // 10 11 12  // 1 2

//        System.out.println(maxMultiplyOptimization(new int[]{-8,-10,3,4}));

        System.out.println(isThreeOddArrays(new int[]{2,5,7,9,10,11,12}));
        System.out.println(isThreeOddArraysBit(new int[]{2,5,0,9,10,11,12}));
        System.out.println(isThreeOddArraysPointer(new int[]{2,5,0,9,10,11,12}));
    }
    //转置矩阵1
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
    /**
     * time:2021/3/7 (1588)所有奇数长度子数组的和
     * 给你一个正整数数组 arr ，请你计算所有可能的奇数长度子数组的和。
     * 子数组 定义为原数组中的一个连续子序列。
     * 请你返回 arr 中 所有奇数长度子数组的和 。
     * 时间复杂度o(n) 空间复杂度o(1)
     */
    public static int sumOddSubArrays(int[] arry) {
        int sum = 0;
        for(int i = 0;i < arry.length;i++) {
            //arry = [10,11,12] arry = [1,2]
            //2 5 8 9 12 7 6 i=2时 左边有三种情况 取0个数 1个数 2个数即{8}，{8，5}，{8，5，2} 左边的情况就是i+1种情况
            int left = i+1;
            //右边同理
            int right = arry.length-(i+1)+ 1;
            //左边的情况 只有一个数的时候才是奇数的情况即{5} 这里的8是它的本身目前可以忽略 所以左边奇数种情况是left/2
            int left_odd = left/2;
            //左边的情况 只有0个或者两个数的时候才是偶数的情况即{}，{5，2} 所以左边偶数种情况是(left+1)/2
            int left_even = (left+1)/2;
            //右边奇数种情况同理
            int right_odd = right/2;
            //右边偶数种情况同理
            int right_even = (right+1)/2;
            //排列组合 左边奇 右边奇 加上它本身还是奇数种情况 左边偶 右边偶 加上它本身还是奇数种情况即左奇*右奇+左偶*右偶
            sum += arry[i] * (left_odd * right_odd + left_even * right_even);
        }
        return sum;
    }
    /**
     * time:2021/3/8 (628) 三个数的最大乘积
     * 给你一个整型数组 nums ，在数组中找出由三个数组成的最大乘积，并输出这个乘积。
     * 时间复杂度o(nlogn) 空间复杂度o(logn)
     */
    //(1)排序解法
    public static int maxMultiply(int[] arry) {
        Arrays.sort(arry);
        int n = arry.length;
        return Math.max(arry[0] * arry[1] * arry[n-1],arry[n-1] * arry[n-2] * arry[n-3]);
    }
    //(2)线性扫描解法 时间复杂度o(n) 空间复杂度o(1)
    public static int maxMultiplyOptimization(int[] arry) {
        //为负数的话 定义最小的两个负数min1 min2
        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;
        //为正数的话 定义最大的三个正数max1 max2 max3
        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        int max3 = Integer.MIN_VALUE;
        for(int i : arry) {
            if(i < min1) {//判断是否小于最小的数
                min2 = min1;
                min1 = i;
            }else if(i < min2) {//判断是否小于第二小的数
                min2 = i;
            }
            if(i > max1) {//判断是否大于最大的数
                max3 = max2;
                max2 = max1;
                max1 = i;
            }else if(i > max2) {//判断是否大于第二大的数
                max3 = max2;
                max2 = i;
            }else if(i > max3) {//判断是否大于第三大的数
                max3 = i;
            }
        }
        return Math.max(min1 * min2 * max1,max1 * max2 *max3);
    }
    /**
     * time:2021/3/3 (1550)存在连续三个奇数的数组
     * 给你一个整数数组 arr，请你判断数组中是否存在连续三个元素都是奇数的情况：如果存在，请返回 true ；否则，返回 false 。
     * 时间复杂度o(n) 空间复杂度o(1)
     */
    //(1)遍历数组
    public static boolean isThreeOddArrays(int[] arry) {
        int count = 0;
        for(int i = 0;i < arry.length;i++) {
            if(arry[i]%2 != 0) {//奇数
                count++;
            }else {
                count = 0;
            }
            //是否存在连续的三个奇数
            if(count == 3) {
                return true;
            }
        }
        return false;
    }
    //(2)位运算时间复杂度o(n)空间复杂度o(1)
    public static boolean isThreeOddArraysBit(int[] arry) {
        //小于数组长度减3的位置
        for(int i = 0;i < arry.length - 3;i++) {
            //不为0说明是连续的奇数
            if((arry[i]&1) != 0 && (arry[i+1]&1) != 0 && (arry[i+2]&1) != 0) {
                return true;
            }
        }
        return false;
    }
    //(3)双指针解法时间复杂度o(n) 空间复杂度为o(1)
    public static boolean isThreeOddArraysPointer(int[] arry) {
        int start = 0;
        int end = 0;
        int len = 0;
        for(int i = 0;i < arry.length;i++) {
            if(arry[i]%2 != 0) {//是奇数的情况
                end++;
                //区间的长度
                len = end - start;
                //等于3为连续的奇数
                if(len == 3) {
                    return true;
                }
            }else {//偶数的情况
                if(len == 3) {
                    return true;
                }
                start = i;
                end = i;
            }
        }
        return false;
    }
}
