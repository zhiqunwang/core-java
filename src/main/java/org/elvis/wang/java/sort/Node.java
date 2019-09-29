package org.elvis.wang.java.sort;

/**
 * Created by zhiqun.wang on 2019/9/11 15:50
 */
public class Node {
    public int value;
    public Node left;
    public Node right;

    public void add(int val){
        if(val < this.value){
            if(left ==null){
                left = new Node(val);
            }else{
                this.left.add(val);
            }
        }else{

        }

    }

    Node(int val){
        this.value = val;
    }




}
