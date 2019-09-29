package org.elvis.wang.java.sort;

/**
 * Created by zhiqun.wang on 2019/9/1 22:37
 */
public class MergerSort {
    public void mergerSort(int[] arr,int start,int end){
        if(start < end){
            int middle = (start + end )/2;

            mergerSort(arr,start,middle);

            mergerSort(arr,middle+1,end);

            merger(arr,start,middle,end);

        }

    }


    public void merger(int[] arr,int start,int middle,int end){

        int left_len = middle - start + 1;
        int right_len = end - middle;

        int[] left =new  int[left_len];

        int[] right = new int[right_len];

        int i = 0;
        int j = 0;
        while (i < left_len){
            left[i++] = arr[start+i];
        }

        while (j < right_len){
            right[j++] = arr[middle+j];
        }

        i = 0;
        j = 0;



    }

}
