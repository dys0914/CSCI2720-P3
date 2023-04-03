//all written by Dongyun Seo except insert and leafNode
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
        if(key.compareTo((T)(n.info)) > 0 && n.right  == null){ //if right is empty but key is bigger
            n.right = new NodeType(key);
        }
        else if (key.compareTo((T)(n.info)) > 0) { //if right is not empty and key is bigger
            insertHelper(n.right, key);
        }
        else if(key.compareTo((T)(n.info)) < 0 && n.left  == null){ //if left is empty but key is smaller
            n.left = new NodeType(key);
        }
        else if (key.compareTo((T)(n.info)) < 0) { //if left is not empty and key is smaller
            insertHelper(n.left, key);
        }
        else  { //its equal                                                                                                                                            

        }

    } //insertHelper                                                                                                                                                   

    public void delete( T key) {
        if(root == null) {
            return;
        }
        if(key.compareTo((T)root.info) == 0) { //if the root is being deleted
            if(root.left == null && root.right == null) {  //no children on root
		        root = null;
            }
            else if(root.left == null) { //right child only
                root = root.right;
            }
            else if(root.right == null) { //left child only
                root = root.left;
            }
            else {                      //both children
                NodeType<T> temp = root.right; //to get the smallest number on the right side
                if(temp.left != null) { //if the right child has a left child
                    while(temp.left.left != null) {
                        temp = temp.left;
                    }
                    root.info = temp.left.info;
                    temp.left = null;
                    
                }
                else {
                    temp.left = root.left;
                    root = temp;
                }
            }
        }
        else if(key.compareTo((T)root.info) > 0 && root.right != null) { //if key is bigger than root
            deleteHelper(root.right, root, key);
        }
        else if(key.compareTo((T)root.info) < 0	&& root.left != null) { //if key is smaller than root
            deleteHelper(root.left,  root, key);
        }
        else {
            System.out.println("That number does not exist."); //else it does not exist
        }
    //delete                                                                                                                                                          
    }
    public void deleteHelper(NodeType<T> n, NodeType<T> parent, T key) { //basically same as above
        if(key.compareTo((T)n.info) == 0) {
            if(n.left == null && n.right == null) { //leaf node 
                if(parent.info.compareTo(n.info) < 0) { //removing the pointer from the parent to delete
                    parent.right = null;
                }
                else {
                    parent.left = null;
                }
            }
            else if(n.left == null) { //left child only
                if(parent.info.compareTo(n.info) < 0) {
                    parent.right = n.right;
                }
                else {
                    parent.left = n.right;
                }
            }
            else if(n.right == null) { //right child only
                if(parent.info.compareTo(n.info) < 0) {
                    parent.right = n.left;
                }
                else {
                    parent.left = n.left;
                }
            }
            else { //both children present
                NodeType<T> temp = n.right; //get the smallest on the right side
                if(temp.left != null) {
                    while(temp.left.left != null) {
                        temp = temp.left;
                    }
                    n.info = temp.left.info;
                    temp.left = null;
                    
                }
                else { //if the right child does not have a left child
                    if(parent.info.compareTo(n.info) < 0) {
                        parent.right = temp;
                    }
                    else {
                        parent.left = temp;
                    }
                    temp.left = n.left;
                }
            }
        }
        else if(key.compareTo((T)n.info) > 0) { //recur on right with new parent
            deleteHelper(n.right, n, key);
        }
        else if(key.compareTo((T)n.info) < 0) { //recur on left with new parent
            deleteHelper(n.left, n, key);
        }
    }//deleteHelper                                                                                                                                                    

    public boolean search ( T item ) {
        if(root == null) {
            return false;
        }
        return searchHelper(root, item);
    }//search                                                                                                                                                                         

    public boolean searchHelper(NodeType<T> n, T key) {
        if(n == null) {
            return false;
        }
        if(key.compareTo((T)n.info) == 0) {
            return true;
        }
        if(key.compareTo((T)n.info) > 0 && n.right  == null){ //if key is bigger but no right child
            return false;
        }
        else if (key.compareTo((T)n.info) > 0) { //if key is bigger recur on right child
            return searchHelper(n.right, key);
        }
        else if(key.compareTo((T)n.info) < 0 && n.left  == null){ //if key is smaller but no left child
            return false;
        }
        else { //key.compareTo((T)n.info) < 0... if key is smaller recur on left child                                                                                         
            return searchHelper(n.left, key);
        }

    } //searchHelper                                                                                                                                                                  

    public void inOrder() {
        inOrderHelper(root);
    }
    void inOrderHelper(NodeType<T> n) {
        if(n == null) {
            return;
        }
        else {
            inOrderHelper(n.left); //recur left
            if(n.info != null) {
                System.out.print(n.info + " "); //print
            }
            inOrderHelper(n.right); //recur right
        }
    }

    public void getSingleParent(){
        singleParentHelper(root);
    }

    public void singleParentHelper(NodeType<T> n) {
        if(n == null) {
            return;
        }
        else if(n.left == null ^ n.right == null) { //XOR for only single parent not both nor none
            System.out.print(n.info + " ");
        }

        singleParentHelper(n.left);
        singleParentHelper(n.right);
    }


    public int getNumLeafNodes(){

        return leafNodeHelper(root);
    }

    public int leafNodeHelper(NodeType<T> n) {
        if(n == null) {
            return 0;
        }
        else if(n.left == null && n.right == null) { //return 1 every time a leaf node is found
            return 1;
        }
        return leafNodeHelper(n.left) + leafNodeHelper(n.right); //add left and right subtrees count
    }

    public void getCousins(T key){
        int nodeLevel = level(root, key);                                                                                                               
        printCousins(root, key, nodeLevel); //traverse tree and print nodes on same level
    }

    public int level(NodeType<T> n, T key) { //Search first in getCousins in driver                                                                                                   
        if(key.compareTo((T)n.info) == 0) {
            return 0;
        }
        else if (key.compareTo((T)n.info) > 0) {
            return 1 + level(n.right, key);
        }
        else { //key.compareTo((T)n.info) < 0                                                                                                                                         
            return 1 + level(n.left, key);
        }
    }

    public void printCousins(NodeType<T> n, T key, int level) {
        if (n == null) {
            return;
        }

        // node is one level above key to check for siblings                                                                                             
        if (level == 1) {
            if(n.left != null && n.right != null) { //if there exists both children and the key is not in either, print
                if(!n.left.info.equals(key) && !n.right.info.equals(key)) { //if even one of them is key, don't print
                    System.out.print(n.left.info + " ");
                    System.out.print(n.right.info + " ");
                    
                }     
                return; 
            }
            if(n.left != null) { //if left child only and is not equal to key, print
                if(!n.left.info.equals(key)) {
                    System.out.print(n.left.info + " ");
                    
                }
                return;
            }
            if(n.right != null) { //if right child only and is not equal to key, print
                if(!n.right.info.equals(key)) {
                    System.out.print(n.right.info + " ");
                    
                }
                return;
            }
        else if (level == 0) {
            return;
        }
        } else {
            // traverse left and right subtrees                                                                                                                                   
            printCousins(n.left, key, level - 1);
            printCousins(n.right, key, level - 1);
        }
    }
}



