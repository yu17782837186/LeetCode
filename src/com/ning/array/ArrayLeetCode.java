package com.ning.array;

import java.lang.reflect.Array;
import java.net.Inet4Address;
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

//        System.out.println(isThreeOddArrays(new int[]{2,5,7,9,10,11,12}));
//        System.out.println(isThreeOddArraysBit(new int[]{2,5,0,9,10,11,12}));
//        System.out.println(isThreeOddArraysPointer(new int[]{2,5,0,9,10,11,12}));

//        System.out.println(containsNearbyDuplicate(new int[]{2,3,5,-1,6,7,6},2));

//        System.out.println(maxArea(new int[]{1,8,6,2,5,4,8,3,7}));
//
//        System.out.println(threeSumClosest(new int[]{-1,2,1,-4},1));
//
//        System.out.println(fourNumberSum(new int[]{1,0,-1,0,-2,2},0));
//
//        System.out.println(Arrays.toString(nextMaxSequence(new int[]{1,2,3})));

//        System.out.println(search(new int[]{4,5,6,7,0,1,2},3));

//        System.out.println(Arrays.toString(searchRangeByHash(new int[]{1,3},1)));
//        System.out.println(Arrays.toString(searchRangeByBinary(new int[]{1,3},1)));

//        List<List<Integer>> lists = combineSum(new int[]{2, 3, 6, 7}, 7);
//        System.out.println(Arrays.asList(lists));

//        System.out.println(summaryRanges(new int[]{0,1,2,4,5,7}));

//        System.out.println(combineSum2(new int[]{2,5,2,1,2},5));

//        System.out.println(jumpGame(new int[]{2,3,1,1,4}));

//        System.out.println(spiralMatrix(new int[][]{{1,2,3},{4,5,6},{7,8,9}}));

        System.out.println(jumpGame2(new int[]{2,3,1,1,4}));
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
     time:2021/3/6 (977)有序数组的平方
     给你一个按非递减顺序排序的整数数组 nums，返回每个数字的平方组成的新数组，要求也按非递减顺序排序。
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
     time:2021/3/7 (1588)所有奇数长度子数组的和
     给你一个正整数数组 arr ，请你计算所有可能的奇数长度子数组的和。
     子数组 定义为原数组中的一个连续子序列。
     请你返回 arr 中 所有奇数长度子数组的和 。
     时间复杂度o(n) 空间复杂度o(1)
     */
    public static int sumOddSubArrays(int[] arry) {
        int sum = 0;
        for(int i = 0;i < arry.length;i++) {
            //arry = [10,11,12] arry = [1,2]
            //2 5 8 9 12 7 6 i=2时 arrr[i] = 8 左边有三种情况 取0个数 1个数 2个数即{8}，{8，5}，{8，5，2} 左边的情况就是i+1种情况
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
     time:2021/3/8 (628) 三个数的最大乘积
     给你一个整型数组 nums ，在数组中找出由三个数组成的最大乘积，并输出这个乘积。
     时间复杂度o(nlogn) 空间复杂度o(logn)
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
     time:2021/3/9 (1550)存在连续三个奇数的数组
     给你一个整数数组 arr，请你判断数组中是否存在连续三个元素都是奇数的情况：如果存在，请返回 true ；否则，返回 false 。
     时间复杂度o(n) 空间复杂度o(1)
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
    /**
     time:2021/3/10 219. 存在重复元素 II
     给定一个整数数组和一个整数 k，判断数组中是否存在两个不同的索引 i 和 j，使得 nums [i] = nums [j]，并且 i 和 j 的差的 绝对值 至多为 k
     时间复杂度o(n2) 空间复杂度o(1)
     */
    public static boolean containsNearbyDuplicate(int[] arry,int k) {
        for(int i = 0;i < arry.length;i++) {
            for(int j = Math.max(i-k,0);j < i;j++) {
                if(arry[i] == arry[j]) {
                    return true;
                }
            }
        }
        return false;
    }
    /**
     time:2021/3/11  11. 盛最多水的容器
     给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0) 。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
     说明：你不能倾斜容器。
     时间复杂度o(n) 空间复杂度o(1)
     */
    public static int maxArea(int[] arry) {
        //定义左边的底
        int left = 0;
        int right = arry.length-1;
        //定义右边的底
        int area = 0;
        while(left < right) {
            //选择高度较小的边进行移动
            int height = Math.min(arry[left],arry[right]);
            //将得到的面积与area作比较每次放入最大的面积
            area =Math.max((right-left)*height,area);
            //移动较小的边
            if(arry[left] < arry[height]) {
                left++;
            }else {
                right--;
            }
        }
        return area;
    }
    /**
     time:2021/3/13  15. 三数之和
     给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
     注意：答案中不可以包含重复的三元组。
     时间复杂度o(n2) 空间复杂度o(1)
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        int len = nums.length;
        if(nums == null || len < 3) {
            return result;
        }
        Arrays.sort(nums);//排序
        for(int i = 0;i < len;i++) {
            if(nums[i] > 0) {
                break;
            }
            if(i > 0 && nums[i] == nums[i-1]) {//去重
                continue;
            }
            int left = i+1;//左边
            int right = len-1;//右边
            while(left < right) {
                int sum = nums[i]+nums[left]+nums[right];
                if(sum == 0) {
                    result.add(Arrays.asList(nums[i],nums[left],nums[right]));
                    while(left < right && nums[left] == nums[left+1]) {//去重
                        left++;
                    }
                    while(left < right && nums[right] == nums[right-1]) {//去重
                        right--;
                    }
                    left++;
                    right--;
                }else if(sum < 0) {
                    left++;
                }else if(sum > 0) {
                    right--;
                }
            }
        }
        return result;
    }
    /**
     time:2021/3/15  16. 最接近的三数之和
     给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
     时间复杂度o(n2) 空间复杂度o(1)
     */
    public static int threeSumClosest(int[] arry, int target) {
        //排序
        Arrays.sort(arry);
        //最小的三个值为排序后的前三个数
        int closesSum = arry[0]+arry[1]+arry[2];
        for(int i = 0;i < arry.length;i++) {
            //定义左边指针
            int left = i+1;
            //定义右边指针
            int right = arry.length-1;
            //条件为左右指针区域
            while(left < right) {
                //每次循环从当前循环的第一个数，第二个数和第三个数相加的和开始比较
                int threeSum = arry[i]+arry[left]+arry[right];
                if(Math.abs(threeSum-target) < Math.abs(closesSum-target) ) {//绝对值最接近就赋值
                    closesSum = threeSum;
                }
                if(threeSum > target) {//当前循环中求和数比目标值大 左移右指针
                    right--;
                }else if(threeSum < target) {//当前循环中求和数比目标值小 右移左指针
                    left++;
                }else {//相等 直接返回即可
                    return threeSum;
                }
            }
        }
        //最终返回最接近的值
        return closesSum;
    }
    /**
     time:2021/3/16  18. 四数之和
     给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c + d 的值与 target 相等？找出所有满足条件且不重复的四元组。
     注意：答案中不可以包含重复的四元组。
     时间复杂度o(n3) 空间复杂度0(1) 双指针+剪枝
     */
    public static List<List<Integer>> fourNumberSum(int[] arry,int target) {
        //创建新数组存放满足的数据
        List<List<Integer>> result = new ArrayList<>();
        //排序
        Arrays.sort(arry);
        int len = arry.length;
        if(len < 4) {
            return result;
        }
        //第一个数的位置 开始为0 结束为 len-3(后面还有三个数)
        for(int i = 0;i < len-3;i++) {
            if(i > 1 && arry[i] == arry[i-1]) {//第一个数去重
                continue;
            }
            //最小的值 如果最小的值都比目标值大 则直接结束
            int min1 = arry[i]+arry[i+1]+arry[i+2]+arry[i+3];
            if(min1 > target) {
                break;
            }
            //当前循环中最大的值 如果这个值比目标值小 则说明本次循环中所有值只会更小 所以执行下次循环
            int max1 = arry[i]+arry[len-1]+arry[len-2]+arry[len-3];
            if(max1 < target) {
                continue;
            }
            //定义第二个数的位置
            for(int j = i+1;j < len-2;j++) {
                if(j > i+1 && arry[j] == arry[j-1]) {//第二个数去重
                    continue;
                }
                //定义左指针
                int left = j+1;
                //定义右指针
                int right = len-1;
                //相当于求三数之和 每次循环第一个数固定 后面三个数求和 再次判断第一个数固定，第二个数变化的本次循环中的最小值
                //如果这个最小值(相当于三数求和)都比目标值大 直接结束
                int min = arry[i]+arry[j]+arry[left]+arry[left+1];
                if(min > target) {
                    break;
                }
                //如果这个最大值(相当于三数求和)都比目标值小 跳过本次循环 让第二个数的下标++再次走到这里判断
                int max = arry[i]+arry[j]+arry[right]+arry[right-1];
                if(max < target) {
                    continue;
                }
                //左右指针指针区域
                while(left < right) {
                    //求和
                    int sum = arry[i]+arry[j]+arry[left]+arry[right];
                    //判断 符合就放入
                    if(sum == target) {
                        result.add(Arrays.asList(arry[i],arry[j],arry[left],arry[right]));
                        left++;
                        while(left < right && arry[left] == arry[left-1]) {//左边再次去重
                            left++;
                        }
                        right--;
                        while(left < right && arry[right] == arry[right+1]) {//右边再次去重
                            right--;
                        }
                    }else if(sum < target) {//左移右指针
                        left++;
                    }else {//右移左指针
                        right--;
                    }
                }
            }
        }
        return result;
    }
    /**
     time:2021/3/18  31. 下一个排列
     实现获取 下一个排列 的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
     如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
     必须 原地 修改，只允许使用额外常数空间。
     时间复杂度o(n) 空间复杂度o(1)
     */
    public static int[] nextMaxSequence(int[] arry) {
        //数组的前一位置 确保条件成立
        int i = arry.length-2;
        while(i >= 0 && arry[i] > arry[i+1]) {//从后向前找到第一个数比它后面的数要小的值作为交换的第一个数
            i--;
        }
        if(i >= 0) {//如果大于等于0 说明找到了
            int j = arry.length-1;
            while(j >= 0 && arry[i] >= arry[j]) {//在找到了第一个数的前提下 去找到比第一个数要大的数作为交换的第二个数
                j--;
            }
            swapPosition(arry,i,j);//交换值
        }
        int[] arrs = reversePosition(arry, i + 1, arry.length - 1);//交换位置
        return arrs;
    }
    public static void swapPosition(int[] arr,int start,int end) {
        int temp = arr[start];
        arr[start] = arr[end];
        arr[end] = temp;
    }
    public static int[] reversePosition(int[] arr,int left,int right) {
        while(left < right) {
            swapPosition(arr,left,right);
            left++;
            right--;
        }
        return arr;
    }
    /**
     time:2021/3/24  33. 搜索旋转排序数组(数组题用二分查找)
     整数数组 nums 按升序排列，数组中的值 互不相同 。
     在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。
     给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的索引，否则返回 -1 。
     时间复杂度o(logn) 空间复杂度o(1)
     */
    public static int search(int[] arry,int target) {
        if(arry == null || arry.length == 0) {
           return -1;
        }
        int left = 0;
        int right = arry.length-1;
        while(left <= right) {
            int mid = left+(right-left)/2;//折半
            if(target == arry[mid]) {//相等直接返回
                return mid;
            }
            if(arry[left] <= arry[mid]) {//左边有序
                if(target >= arry[left] && target < arry[mid]) {//target目标值在左侧
                    right = mid-1;
                }else {//target目标值在右侧
                    left = mid+1;
                }
            }else {//右边有序
                if(target <= arry[right] && target > arry[mid]) {//target目标值在右侧
                    left = mid+1;
                }else {//target目标值在左侧
                    right = mid-1;
                }
            }
        }
        return -1;
    }
    /**
     time:2021/3/25  34. 在排序数组中查找元素的第一个和最后一个位置
     给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
     如果数组中不存在目标值 target，返回 [-1, -1]。
     哈希表：时间复杂度o(n+m) 空间复杂度o(1)
     */
    //方法(1)
    public static int[] searchRangeByHash(int[] arry,int target) {//5,7,7,8,8,10 //1,3
        // Arrays.sort(nums);
        // if(nums.length == 1 && nums[0] == target) {
        //     return new int[]{0,0};
        // }
        int count = 0;
        Map<Integer,Integer> map = new HashMap<>();
        for(int i = 0;i < arry.length;i++) {
            if(map.containsKey(target) && arry[i] == target) {//包含target并且arr[i]==target 放进map中
                map.put(target,i);
                count++;//记录重复的次数
            }else {
                map.put(arry[i],i);
            }
        }
        if(count == 0) {//没有重复的数
            for(Integer key :map.keySet()) {
                if(key == target) {//判断每个数是否有跟target相同的 有直接return结果
                    return new int[]{map.get(key),map.get(key)};
                }
            }
            //没有返回-1
            return new int[]{-1,-1};
        }
        //有重复的数 返回对应的下标
        return new int[]{map.get(target)-count,map.get(target)};
    }
    /**
     time:2021/3/26  34. 在排序数组中查找元素的第一个和最后一个位置
     给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
     如果数组中不存在目标值 target，返回 [-1, -1]。
     二分查找：时间复杂度o(logn) 空间复杂度o(1)
     */
    //方法(2) 二分查找
    public static int[] searchRangeByBinary(int[] arry,int target) {
        int leftFirst = binarySearch(arry,target,true); //左边找第一个
        int rightLast = binarySearch(arry,target,false)-1;//右边找最后一个 需要减一 减一是因为它永远取不到相等的时候 所以最后一次出现的位置一定是目标值的下一个位置
        if(leftFirst <= rightLast && arry[leftFirst] == target && arry[rightLast] == target) {
            return new int[]{leftFirst,rightLast};
        }
        return new int[]{-1,-1};
    }
    public static int binarySearch(int[] arr,int target,boolean flag) {
        int left = 0;
        int right = arr.length-1;
        int result = arr.length;
        while(left <= right) {
            int mid = left+(right-left)/2;
            if(arr[mid] > target || (flag && arr[mid] >= target)) {
                right = mid-1;
                result = mid;
            }else {
                left = mid+1;
            }
        }
        return result;
    }
    /**
     time:2021/3/29  39. 组合总和
     给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
     candidates 中的数字可以无限制重复被选取。
     说明：
     所有数字（包括 target）都是正整数。
     解集不能包含重复的组合。
     回溯+递归：时间复杂度o(n) 空间复杂度o(target) n为所有可行解的长度和 target为最差需要递归的层数
     */
    public static List<List<Integer>> combineSum(int[] arry,int target) {
        //返回最终的数组
        List<List<Integer>> result = new ArrayList<>();
        //存储符合条件的数
        List<Integer> newArr = new ArrayList<>();
        rescursion(arry,target,result,newArr,0);
        return result;
    }
    /**
     * @param originArr 原数组
     * @param changeTarget 改变的目标值
     * @param res 最终结果的数组
     * @param newArr 存放符合条件的数组
     * @param index 递归的当前位置
     */
    private static void rescursion(int[] originArr, int changeTarget, List<List<Integer>> res, List<Integer> newArr, int index) {
        if(index == originArr.length) {//等于数组长度递归结束
            return;
        }
        if(changeTarget == 0) {//变化的目标值等于0的时候 将newArr放进res中返回
            res.add(new ArrayList<>(newArr));
            return;
        }
        //从头到尾每个数递归
        rescursion(originArr,changeTarget,res,newArr,index+1);
        if(changeTarget-originArr[index] >= 0) {//类似深度优先遍历 一条道走到黑 当结果<0时 为结束条件
            //把每个数放到新数组中保存
            newArr.add(originArr[index]);
            //再次递归只改变目标数 此次递归只针对当前深度优先遍历 所以最开始的位置还是index 改变的是每次目标节点数值
            rescursion(originArr,changeTarget-originArr[index],res,newArr,index);
            //回溯
            newArr.remove(newArr.size()-1);
        }
    }
    /**
     time:2021/3/30  228. 汇总区间
     给定一个无重复元素的有序整数数组 nums 。
     返回 恰好覆盖数组中所有数字 的 最小有序 区间范围列表。也就是说，nums 的每个元素都恰好被某个区间范围所覆盖，并且不存在属于某个范围但不属于 nums 的数字 x 。
     列表中的每个区间范围 [a,b] 应该按如下格式输出：
     "a->b" ，如果 a != b
     "a" ，如果 a == b
     回溯+递归：时间复杂度o(n) 空间复杂度o(1)
     */
    public static  List<String> summaryRanges(int[] arry) {
        List<String> result = new ArrayList<>();
        int start = 0;
        int len = arry.length;
        while(start < len) {
            int low = start;
            start++;
            while (start < len && arry[start] == arry[start-1]+1) {
                start++;
            }
            int high = start-1;
            StringBuilder sb = new StringBuilder(Integer.toString(low));
            if(low < high) {
                sb.append("->");
                sb.append(Integer.toString(high));
            }
            result.add(sb.toString());
        }
        return result;
    }
    /**
     time:2021/3/31  40. 组合总和 II
     给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
     candidates 中的每个数字在每个组合中只能使用一次。
     说明：
     所有数字（包括目标数）都是正整数。
     解集不能包含重复的组合。 
     回溯+递归：时间复杂度o(n*2^n) 空间复杂度o(n) 因为每个数都可以选或者不选 最坏情况下每个数都不相同也就是2^n 一个满足要求的组合需要o(n)的时间复杂度 所以总的时间复杂度为n*2^n
    */
    public static List<List<Integer>> combineSum2(int[] arry,int target) {
        //排序 方便下面的set去重
        Arrays.sort(arry);
        //返回最终结果的数组 set去重
        Set<List<Integer>> result = new HashSet<>();
        //存放符合条件的数
        List<Integer> newArr = new ArrayList<>();
        rescursion2(arry,newArr,target,0,result);
        return new ArrayList<>(result);
    }
    /**
     * @param originalArr 原数组
     * @param newArr 符合条件的数组
     * @param changeTarget 改变的目标值
     * @param index 递归的位置
     * @param res 返回的最终结果
     */
    private static void rescursion2(int[] originalArr, List<Integer> newArr, int changeTarget, int index, Set<List<Integer>> res) {
        if(changeTarget == 0) {
            res.add(new ArrayList<>(newArr));
            return;
        }
        if(index == originalArr.length || changeTarget < 0) {
            return;
        }
        newArr.add(originalArr[index]);
        //包含当前这个数
        rescursion2(originalArr,newArr,changeTarget-originalArr[index],index+1,res);
        //回溯
        newArr.remove(newArr.size()-1);
        //不包含当前这个数
        rescursion2(originalArr,newArr,changeTarget,index+1,res);
    }
    /**
     time:2021/4/1  45. 跳跃游戏 II
     给定一个非负整数数组，你最初位于数组的第一个位置。
     数组中的每个元素代表你在该位置可以跳跃的最大长度。
     你的目标是使用最少的跳跃次数到达数组的最后一个位置。
     贪心：时间复杂度o(n^2) 空间复杂度o(1) 寻求可优解
     */
    public static int jumpGame(int[] arry) {
        int position = arry.length-1;
        int step = 0;
        while(position > 0) {//3.判断记录的当前的位置 再次进入循环寻求可优解
            for(int i = 0;i < position;i++) {// 1.从后向前寻找可优解的最大方案
                if(i+arry[i] >= position) {// 2.找到了当前的位置跳跃的最大数 记录当前位置 步数+1 结束循环
                    position = i;
                    step++;
                    break;
                }
            }
        }
        return step;
    }
    /**
     time:2021/4/9  48. 旋转图像
     给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
     你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
     */
    //方法(1)使用辅助数组 时间复杂度 o(n^2) 空间复杂度o(n^2) 需要额外创建一个原数组一样大的空间
    public static void rotate1(int[][] arry) {
        int n = arry.length;
        int[][] newArr = new int[n][n];
        for(int i = 0;i < n;i++) {
            for(int j = 0;j < n;j++) {
                newArr[j][n-i-1] = arry[i][j];//顺时针旋转90度
            }
        }
        //将新数组的值赋给原数组
        for(int i = 0;i < n;i++) {
            for(int j = 0;j < n;j++) {
                arry[i][j] = newArr[i][i];
            }
        }
    }
    //方法(2)翻转 时间复杂度 o(n^2) 空间复杂度o(1)
    public static void rotate2 (int[][] arry) {
        int n = arry.length;
        //上下翻转
        for(int i = 0;i < n/2;i++) {//行长度为一半即可
            for(int j = 0;j < n;j++) {
                int tmp = arry[i][j];
                arry[i][j] = arry[n-i-1][j];
                arry[n-i-1][j] = tmp;
            }
        }
        //斜对称翻转
        for(int i = 0;i < n;i++) {
            for(int j = 0;j < i;j++) {
                int tmp = arry[i][j];
                arry[i][j] = arry[j][i];
                arry[j][i] = tmp;
            }
        }
    }
    /**
     time:2021/4/12  54. 螺旋矩阵
     给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
     输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
     输出：[1,2,3,6,9,8,7,4,5]
     */
    //思路：从外到内，按层模拟，依次访问，时间复杂度o(mn)(m,n是输入矩阵的行数和列数) 空间复杂度o(1)
    public static List<Integer> spiralMatrix(int[][] matrix) {
        List<Integer> list = new ArrayList<>();
        if(matrix == null || matrix[0].length == 0 || matrix.length == 0) {
            return list;
        }
        int rows = matrix.length;
        int cols = matrix[0].length;
        int left = 0;//左边
        int right = cols-1;//右边
        int top = 0;//上边
        int bottom = rows-1;//下边
        while(left <= right && top <= bottom) {
            for(int col = left;col <= right;col++) {//从左到右
                list.add(matrix[left][col]);
            }
            for(int row = top+1;row <= bottom;row++) {//从右到下
                list.add(matrix[row][right]);
            }
            if(left < right && top < bottom) {
                for(int col = right-1;col > left;col--) {//从下到左
                    list.add(matrix[bottom][col]);
                }
                for(int row = bottom;row > top;row--) {//从左到上
                    list.add(matrix[row][left]);
                }
            }
            left++;
            right--;
            top++;
            bottom--;
        }
        return list;
    }
    /**
     time:2021/4/16  55. 跳跃游戏
     给定一个非负整数数组 nums ，你最初位于数组的 第一个下标 。
     数组中的每个元素代表你在该位置可以跳跃的最大长度。
     判断你是否能够到达最后一个下标。
     */
    //贪心  时间复杂度o(n)  空间复杂度o(1) 从后向前推
    public static boolean jumpGame2(int[] arry) {
        int finalPos = arry.length-1;
        for(int i = arry.length-1;i >= 0;i--) {//反向遍历
            if(i+arry[i] >= finalPos) {//如果从当地位置(i)出发, 跳array[i]步能达到终点(finalPos), 那么更新终点为当前位置
                finalPos = i;
            }
        }
        return finalPos == 0;// 逆推是否到达了起点, 如果到达了, 那么说明可达
    }
}
