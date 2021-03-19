package com.ning.array;

/**
 * 二分查找
 */
public class BinarySearchLeetCode {
    public static void main(String[] args) {
        System.out.println(searchInsert(new int[]{1,3,5,6},2));
        System.out.println(sqrt(9));
        System.out.println(newtonIterativeMethod(-1));
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
}
