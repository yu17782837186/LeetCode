package com.ning.array;

import java.util.Arrays;

/**
 * 二分查找
 */
public class BinarySearchLeetCode {
    public static void main(String[] args) {
//        System.out.println(searchInsert(new int[]{1,3,5,6},2));
//        System.out.println(sqrt(9));
//        System.out.println(newtonIterativeMethod(55));

        System.out.println(Arrays.toString(twoNumberSum(new int[]{-1,0},-1)));
    }
    /**
     * time:2021/3/19 35. 搜索插入位置
     给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
     你可以假设数组中无重复元素。
     * 时间复杂度为每次一半o(logn) 空间复杂度o(1)
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
     * time:2021/3/19 69. x 的平方根
     实现 int sqrt(int x) 函数。
     计算并返回 x 的平方根，其中 x 是非负整数。
     由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
     来源：力扣（LeetCode）
     链接：https://leetcode-cn.com/problems/sqrtx
     著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * 时间复杂度为每次一半o(logn) 空间复杂度o(1)
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
     * time:2021/3/22 167. 两数之和 II - 输入有序数组
     给定一个已按照 升序排列  的整数数组 numbers ，请你从数组中找出两个数满足相加之和等于目标数 target 。
     函数应该以长度为 2 的整数数组的形式返回这两个数的下标值。numbers 的下标 从 1 开始计数 ，所以答案数组应当满足 1 <= answer[0] < answer[1] <= numbers.length 。
     你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。
     来源：力扣（LeetCode）
     链接：https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted
     著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * 时间复杂度为每次一半o(logn) 空间复杂度o(1)
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
}
