package org.elvis.wang.java.sort;

import java.util.Arrays;

/**
 * Created by zhiqun.wang on 2019/9/1 19:36
 *
 * 插入排序
 *
 * 1.确定一个需要插入的数据
 * 2.确定初始化插入的位置
 */
public class InsertSort {


    public static void main(String[] args) {
        int[] arr = {9,2,3,7,6,0,1};
        insertSort(arr);

        System.out.println(Arrays.toString(arr));
    }

    public static void insertSort(int[] arr){
        for(int i =1; i<arr.length;i++) {
            //插入的数
            int insertVal = arr[i];
            //被插入的位置(准备和前一个数比较)
            int index = i-1;
            //如果插入的数比被插入的数小
            while(index>=0&&insertVal<arr[index]) {
            //将把 arr[index] 向后移动
                arr[index+1]=arr[index];
            //让 index 向前移动
                index--;
            }
            //把插入的数放入合适位置
            arr[index+1]=insertVal; }

    }

}
