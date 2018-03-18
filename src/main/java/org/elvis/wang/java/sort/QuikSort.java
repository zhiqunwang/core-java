package org.elvis.wang.java.sort;

/**
 * Created by wangzhiqun on 2018/1/21.
 *
 * 二分法排序步骤：
 * 1.定义一个基准值base = arr[end]
 * 2.预设一个基准值位置n
 * 3.遍历新的数组start--->end-1
 *   假如当前值大于等于base什么都不做，假如小于则交换基准值位置和当前位置数据，n++
 *
 *  end-start<1
 *
 */
public class QuikSort {
    /**
     *
     * @param arr 需要拆分的数组
     * @param start 数组拆分的开始索引 0
     * @param end  数组拆分的结束索引
     * @return
     */
    public static int partArr(int[] arr,int start,int end){

        int base = arr[end];//设置基准值
        int n = start;//假设基准值所在位置

        for(int i = 0;i<end-1;i++){
            if(arr[i]<base){
                if(n!=i){
                    //交换数据
                    exchangeE(arr,i,n);
                }
                n++;
            }
        }
        //遍历完成后交换数据
        exchangeE(arr,n,end);
        return n;
    }

    public static void exchangeE(int[] array, int index1, int index2) {
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    public static void recurPartiton(int[] arr,int start,int end){

        //递归调用的结束条件,开始要拆分的数组就剩下一个元素的时候
        if(end-start<1)
            return;
        int part = partArr(arr, start, end);
        //三种情况下的继续拆分
        if(part==start)
            recurPartiton(arr,part+1,end);
        else if(part ==end)
            recurPartiton(arr,start,end-1);
        else{
            recurPartiton(arr,start,part-1);
            recurPartiton(arr,part+1,end);
        }
    }

}
