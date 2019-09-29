package org.elvis.wang.java.sort;

/**
 * Created by zhiqun.wang on 2019/9/1 21:17
 */
public class QuickSort {


     public static void quickSort(int[] arr,int start,int end){
         if(start < end){

             int temp = arr[start];
             int left = start;
             int right = end-1;

             while (left <right){

                 while (left < right && arr[right] > temp){
                     right--;
                 }

                 arr[left] =  arr[right];

                 left++;

                 while (left < right && arr[left] <= temp){
                     left++;
                 }
                 arr[right] = arr[left];
             }

             arr[left]  = temp;


             quickSort(arr,start,left);

             quickSort(arr,left+1,right);


         }
     }
}
