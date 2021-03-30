package com.ning.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 二分查找
 */
public class BinarySearchLeetCode {
    public static void main(String[] args) {
//        System.out.println(searchInsert(new int[]{1,3,5,6},2));
//        System.out.println(sqrt(9));
//        System.out.println(newtonIterativeMethod(55));

//        System.out.println(Arrays.toString(twoNumberSum(new int[]{-1,0},-1)));

//        System.out.println(Arrays.toString(intersectin(new int[]{1,2,2,1},new int[]{2,2})));
//        System.out.println(Arrays.toString(intersectin(new int[]{4,9,5},new int[]{9,4,9,8,4})));

//        System.out.println(Arrays.toString(intersectionChangeWay1(new int[]{4,9,5},new int[]{9,4,9,8,4})));
//        System.out.println(Arrays.toString(intersectionChangeWay2(new int[]{1,2,2,1},new int[]{2,2})));
    }
    /**
     time:2021/3/19 35. 搜索插入位置
     给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
     你可以假设数组中无重复元素。
     时间复杂度为每次一半o(logn) 空间复杂度o(1)
     */
    public static int searchInsert(int[] arry,int target) {
        int left = 0;
        int right = arry.length-1;
        while(left <=  right) {//循环条件
            int mid = (left+right)>>1;//折半筛选
            if(target < arry[mid]) {//左半部分 right = mid - 1;
                right = mid-1;
            }else if (target > arry[mid]){//右半部分 left = mid + 1;
                left = mid+1;
            }else {//相等直接返回索引位置
                return mid;
            }
        }
        //如果一直是左半部分 最终放在left=0的位置 一直是右半部分 最终放在left更改(末尾)的位置
        return left;
    }
    /**
     time:2021/3/19 69. x 的平方根
     实现 int sqrt(int x) 函数。
     计算并返回 x 的平方根，其中 x 是非负整数。
     由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
     时间复杂度为每次一半o(logn) 空间复杂度o(1)
     */
    //方法一(折半)
    public static int sqrt(int n) {
        int left = 0;
        int right = n;
        int result = 0;
        while(left <= right) {
            int mid = (left+right)>>1;
            if((long)mid*mid <= n) {//转为长整型(int可能太小) 注意：一定是小于等于 并且先保存mid的值
                result = mid;
                left = mid+1;
            }else {
                right = mid-1;
            }
        }
        //返回最终的result 如果中间的数的平方一直比这个数大 result等于left等于0 否则就返回保存前的result
        return result;
    }
    //方法(二)泰勒级数求解 时间复杂度o(logn) 空间复杂度o(1) 因为是二次收敛的 时间复杂度虽然一样 但是比二分查找要快
    public static int newtonIterativeMethod(int n) {
        if(n == 0) {
            return 0;
        }
        double c = n;
        double x0 = n;
        while(true) {
            //泰勒级数求斜率方程
            double xi = 0.5*(x0+c/x0);
            if(Math.abs(x0-xi) < 1e-7) {//无限接近零点
                break;
            }
            x0 = xi;
        }
        return (int)x0;
    }
    /**
     time:2021/3/22 167. 两数之和 II - 输入有序数组
     给定一个已按照 升序排列  的整数数组 numbers ，请你从数组中找出两个数满足相加之和等于目标数 target 。
     函数应该以长度为 2 的整数数组的形式返回这两个数的下标值。numbers 的下标 从 1 开始计数 ，所以答案数组应当满足 1 <= answer[0] < answer[1] <= numbers.length 。
     你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。
     时间复杂度为每次一半o(logn) 空间复杂度o(1)
     */
    public static int[] twoNumberSum(int[] arry,int target){
        for(int i = 0;i < arry.length;i++) {
            //每次区间范围都是从第一个固定数以后的所有长度
            int low = i+1;
            int heigh = arry.length-1;
            //第二个数的范围
            while(low <= heigh) {
                //折半
                int mid = (low+heigh)>>1;
                if(arry[mid] == target-arry[i]) {
                    return new int[]{i+1,mid+1};
                }else if(arry[mid] < target-arry[i]) {
                    low = mid+1;
                }else {
                    heigh = mid-1;
                }
            }
        }
        //没有返回-1
        return new int[]{-1,-1};
    }
    /**
     time:2021/3/23  278. 第一个错误的版本
     你是产品经理，目前正在带领一个团队开发新的产品。不幸的是，你的产品的最新版本没有通过质量检测。由于每个版本都是基于之前的版本开发的，所以错误的版本之后的所有版本都是错的。
     假设你有 n 个版本 [1, 2, ..., n]，你想找出导致之后所有版本出错的第一个错误的版本。
     你可以通过调用 bool isBadVersion(version) 接口来判断版本号 version 是否在单元测试中出错。实现一个函数来查找第一个错误的版本。你应该尽量减少对调用 API 的次数。
     时间复杂度为每次一半o(logn) 空间复杂度o(1)
     */
    public static int firstVersion(int n) {
        int left = 1;
        int right = n;
        while(left < right) {
            int mid = left+(right-left)/2;
            if(isBadVersion(mid)) {
                right = mid;
            }else {
                left = mid+1;
            }
        }
        return left;
    }
    public static boolean isBadVersion(int i) {
        if(i == 6) {
            return false;
        }
        return true;
    }
    /**
     time:2021/3/23  349. 两个数组的交集
     给定两个数组，编写一个函数来计算它们的交集。
     说明：
     输出结果中的每个元素一定是唯一的。
     我们可以不考虑输出结果的顺序。
     双指针+排序
     时间复杂度：O(mlogm+nlogn)，其中 m和n分别是两个数组的长度。对两个数组排序的时间复杂度分别是O(mlogm)和O(nlogn)，双指针寻找交集元素的时间复杂度是O(m+n)，因此总时间复杂度是O(mlogm+nlogn)。
     空间复杂度：O(m+n)，其中m和n分别是两个数组的长度。
     */
    public static int[] intersectin(int[] num1,int[] num2) {
        //给两个数组排序
        Arrays.sort(num1);
        Arrays.sort(num2);
        //两个数组的长度
        int length1 = num1.length;
        int length2 = num2.length;
        //定义一个新的数组存放相同的数据
        int[] newData = new int[length1+length2];
        //新数组的下标
        int index = 0;
        //数组1的下标
        int index1 = 0;
        //数组2的下标
        int index2 = 0;
        //数组1 数组2内部一起遍历
        while(index1 < length1 && index2 < length2) {
            //拿出两个数组的各个位置的数
            int numFirst = num1[index1];
            int numSecond = num2[index2];
            //相等存放新数组
            if(numFirst == numSecond) {
                if(index == 0 || newData[index-1] != numFirst) {//新数组里面去重
                    newData[index++] = numFirst;
                }
                //移动下标
                index1++;
                index2++;
            }else if(numFirst < numSecond) {
                index1++;
            }else {
                index2++;
            }
        }
        return Arrays.copyOfRange(newData,0,index);
    }
    /**
     time:2021/3/24  350. 两个数组的交集 II
     给定两个数组，编写一个函数来计算它们的交集。
     说明：
     输出结果中每个元素出现的次数，应与元素在两个数组中出现次数的最小值一致。
     我们可以不考虑输出结果的顺序。
     双指针+排序
     时间复杂度：O(mlogm+nlogn)，其中 m和n分别是两个数组的长度。对两个数组排序的时间复杂度分别是O(mlogm)和O(nlogn)，双指针寻找交集元素的时间复杂度是O(m+n)，因此总时间复杂度是O(mlogm+nlogn)。
     空间复杂度：O(Min(m,n))，其中m和n分别是两个数组的长度
     */
    //方法(1) 双指针+排序
    public static int[] intersectionChangeWay1(int[] num1,int[] num2) {
        Arrays.sort(num1);
        Arrays.sort(num2);
        int length1 = num1.length;
        int length2 = num2.length;
        int[] intersection = new int[Math.min(length1,length2)];
        int index = 0;
        int index1 = 0;
        int index2 = 0;
        while(index1 < length1 && index2 < length2) {
            int firstArr = num1[index1];
            int secondArr = num2[index2];
            if(firstArr == secondArr) {
                intersection[index++] = firstArr;
                index1++;
                index2++;
            }else if(firstArr < secondArr) {
                index1++;
            }else {
                index2++;
            }
        }
        return Arrays.copyOfRange(intersection,0,index);
    }
    //方法(2)哈希表 时间复杂度o(m+n) m和n是两个数组的长度 哈希表操作的时间复杂度是o(1) 空间复杂度o(min(m,n)) m.n为两个数组的长度
    public static int[] intersectionChangeWay2(int[] num1,int[] num2) {
        if(num1.length > num2.length) {//将num1定为数组长度较小的那一个数组
            return intersectionChangeWay2(num2, num1);
        }

        Map<Integer,Integer> map = new HashMap<>();
        for(int num : num1) {//遍历数组长度较小的数组num1
            if(map.containsKey(num)) {//如果重复 记录重复的次数
                map.put(num,map.get(num)+1);
            }else {
                map.put(num,1);
            }
        }

        //创建新数组
        int[] intersection = new int[num1.length];
        int j = 0;
        for(int num : num2) {//遍历数组长度较大的数组
            if(map.containsKey(num) && map.get(num) > 0) {//map.get(num)确保能拿到出现次数较小的那一个数
                map.put(num,map.get(num)-1);//在较长数组中比较 重复出现的话 让它的次数减一，并且放在新数组中去
                intersection[j++] = num;
            }
        }
        return Arrays.copyOfRange(intersection,0,j);
    }
}
