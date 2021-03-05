package com.ning.array;

import java.util.Arrays;

public class ArrayLeetCode {
    public static void main(String[] args) {
        /**
         *  1 2 3    1 4 7
         *  4 5 6 -->2 5 8  行列交换即可
         *  7 8 9    3 6 9
         */
        System.out.println(Arrays.deepToString(transPose(new int[][]{{1,2,3},{4,5,6},{7,8,9}})));
    }
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
}
