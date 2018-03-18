package org.elvis.wang.java.sort;

import java.util.List;

/**
 * Created by wangzhiqun on 2018/3/18.
 */
public class BinaryTree {
    private Node root;
    private List<Integer> list;

    class Node{
        private int value;
        private Node leftChild;
        private Node rightChild;

        public Node(int value,Node leftChild,Node rightChild){
            this.value = value;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
        }
    }

    public void insert(int value){
        if(root == null){
            Node node = new Node(value,null,null);
            root = node;
        }else {
            Node currentNode = root;
            while (true){
                if(currentNode.value>value){
                      if(currentNode.leftChild==null){
                          Node node = new Node(value,null,null);
                          currentNode.leftChild = node;
                          break;
                      }else{
                          currentNode = currentNode.leftChild;
                      }
                }else{
                    if(currentNode.rightChild==null){
                        Node node = new Node(value,null,null);
                        currentNode.rightChild = node;
                        break;
                    }else{
                        currentNode = currentNode.rightChild;
                    }
                }
            }
        }

    }

    public List<Integer> visit(Node node){

        if(node.leftChild!=null){
               visit(node.leftChild);
        }
        if(node.rightChild!=null){
            visit(node.rightChild);
        }
        list.add(node.value);

        return list;
    }
}
