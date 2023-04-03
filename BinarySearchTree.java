package project3;

import project3.NodeType;

public class BinarySearchTree<T extends Comparable<T>> {

    NodeType<T> root;

    public void insert ( T key ) {
        if(root == null) {
            root = new NodeType(key);
                return;
        }
        insertHelper(root, key);
    } //insert                                                                                                                                                      

    public void insertHelper(NodeType<T> n, T key){
        if(key.compareTo(n.info()) > 0 && n.right  == null){
            n.right = new NodeType(key);
        }
        else if (key.compareTo(n.info) > 0) {
            insertHelper(n.right, key);
        }
        else if(key.compareTo(n.info) < 0 && n.left  == null){
            n.left = new NodeType(key);
        }
        else if (key.compareTo(n.info) < 0) {
            insertHelper(n.left, key);
        }
        else  { //its equal                                                                                                                                         

        }

    } //insertHelper                                                                                                                                                

    public void delete( T key) {
        deleteHelper(root, key);
    }//delete                                                                                                                                                       

    public void deleteHelper(NodeType n, T key) {
        if(key.compareTo(n.info) == 0) {
            if(n.left == null && n.right == null) {
                n = null;
            }
            else if(n.left == null) {
                n = n.right;
            }
            else if(n.right == null) {
                n = n.left;
            }
            else {
                NodeType temp = n.right;
                while(temp.left != null) {
                    temp = temp.left;
                }
                n.info = temp.info;
                temp = null;
            }
        }
        else if(key.compareTo(n.info) > 0) {
            deleteHelper(n.right, key);
        }
        else if(key.compareTo(n.info) < 0) {
            deleteHelper(n.left, key);
        }
    }//deleteHelper                                                                                                                                                 


    public boolean search ( T item ) {
        return searchHelper(root, item);
    }//search                                                                                                                                                       

    public boolean searchHelper(NodeType n, T Key) {
        if(key.compareTo(n.info) == 0) {
            return true;
        }
        if(key.compareTo(n.info) > 0 && n.right  == null){
            return false;
        }
        else if (key.compareTo(n.info) > 0) {
            searchHelper(n.right, key);
        }
        else if(key.compareTo(n.info) < 0 && n.left  == null){
            return false;
        }
        else if (key.compareTo(n.info) < 0) {
            searchHelper(n.left, key);
        }

    } //searchHelper                                                                                                                                                

    public void inOrder() {
        inOrderHelper(root);
    }
    void inOrderHelper(NodeType n) {
           if(n == null) {
            return;
        }
        else {
            inOrderHelper(n.left);
            System.out.println(n.info + " ");
            inOrderHelper(n.right);
        }
    }

    void getSingleParent(){
        System.out.println("blah");
    }


    int getNumLeafNodes(){

        return 0;
    }

    void getCousins(T key){

        System.out.println("got cousins lol");
    }



}

