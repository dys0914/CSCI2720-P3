//all written by Miguel Delao
package project3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import project3.BinarySearchTree;

public class Driver<T extends Comparable<T>> {


    public static void main(String Args[]) {

        Scanner sc = new Scanner(System.in);
        BinarySearchTree bst = null;



        String input = "";
        System.out.println("Enter list type (i - int, d - double, s - string):");
        String inp = sc.nextLine();
        String type = "";
        //establish main variables
        if (inp.equals("i")) {
            bst = new BinarySearchTree<Integer>();
            type = "number";
        } else if (inp.equals("d")) {
            bst = new BinarySearchTree<Double>();
            type = "number";
        } else if (inp.equals("s")) {
            bst = new BinarySearchTree<String>();
            type = "string";
        } else {
            System.out.println("Incorrect type");
            System.exit(0);
        }


        //populate if there is an input file 
        if (Args.length > 0){
            populate(bst,inp,Args[0]);
        }
        

        System.out.println("Commands:\n" + "(i) - Insert Item\n" + "(d) - Delete Item\n"
                + "(p) - Print Tree\n" + "(s) - Search Item\n" + "(l) - Count Leaf Nodes\n"
                + "(sp) - Find Single Parents\n" + "(c) - Find Cousins\n" + "(q) - Quit program\n");

        String cmd = "";
        while (cmd != "q") {
            System.out.print("\nEnter a command: ");
            cmd = sc.nextLine();
            // Switch case for each command
            switch (cmd) {
                case "i":
                    bst.inOrder();
                    System.out.println("");
                    System.out.print("Enter a " + type + " to insert: ");


                    input = sc.nextLine();

                    if (inp.equals("s") && !bst.search(input)) {
                        bst.insert(input);
                    } else if (inp.equals("d") && !bst.search(Double.parseDouble(input))) {
                        bst.insert(Double.parseDouble(input));
                    } else if (inp.equals("i") && !bst.search(Integer.parseInt(input))) {
                        
                        bst.insert(Integer.parseInt(input));
                    } else {
                        System.out.println("Item already in list");
                    }
                    bst.inOrder();

                    break;
                case "d":
                    bst.inOrder();
                    System.out.println("");
                    System.out.print("Enter a " + type + " to delete: ");
                    input = sc.nextLine();
                    //convert to appropriate variable type 
                    if (inp.equals("s") && bst.search(input)) {
                        bst.delete(input);
                    } else if (inp.equals("d") && bst.search(Double.parseDouble(input))) {
                        bst.delete(Double.parseDouble(input));
                    } else if (inp.equals("i")&& bst.search(Integer.parseInt(input))) {
                        bst.delete(Integer.parseInt(input));
                    } else {
                        System.out.println("The " + type + " is not present in the list");
                    }
                    
                    bst.inOrder();
                    
                    break;
                case "p":
                    bst.inOrder();
                    break;
                case "s":
                    boolean isIn = false;
                    bst.inOrder();
                    System.out.println("");
                    System.out.print("Enter a number to search: ");
                    input = sc.nextLine();
                    if (inp.equals("s")) {
                        isIn = bst.search(input);
                    } else if (inp.equals("d")) {
                        isIn = bst.search(Double.parseDouble(input));
                    } else if (inp.equals("i")) {
                        isIn = bst.search(Integer.parseInt(input));
                    }
                    System.out.println(isIn ? "Item is present in the tree"
                            : "Item is not present in the tree");

                    break;
                case "l":
                    System.out.print("The number of leaf nodes are "+ bst.getNumLeafNodes());
                    break;
                case "sp":
                    System.out.print("Single parents: ");
                    bst.getSingleParent();
                    break;
                case "c":
                    bst.inOrder();
                    System.out.println("");
                    System.out.print("Enter a "+ type + ": ");
                    input = sc.nextLine();

                    if (inp.equals("s")) {
                        if(!bst.search(input)) {
                            System.out.println("Number does not exist");
                        }
                        else {
                            System.out.print(input + " cousins: ");
                            bst.getCousins(input);
                        }
                    } else if (inp.equals("d")) {
                        if(!bst.search(Double.parseDouble(input))) {
                            System.out.println("Number does not exist");
                        }
                        else {
                            System.out.print(input + " cousins: ");
                            bst.getCousins(Double.parseDouble(input));
                        }
                    } else if (inp.equals("i")) {
                        if(!bst.search(Integer.parseInt(input))) {
                            System.out.println("Number does not exist");
                        }
                        else {
                            System.out.print(input + " cousins: ");
                            bst.getCousins(Integer.parseInt(input));
                        }
                    }

                    
                    break;
                case "q":
                    sc.close();
                    System.exit(0);
                    break;

            }
        }

    }

    // takes the numbers from the file and adds them to the bst
    public static void populate(BinarySearchTree bst,String inp,String filename){

        try(Scanner sc = new Scanner(new File(filename))){
            while(sc.hasNextLine()){
                String line = sc.nextLine();
                String[] values = line.split(" ");
                switch(inp){
                    case "i":
                        for (String val : values) {
                            bst.insert(Integer.parseInt(val));
                        }
                        break;
                    case "d":
                        for (String val : values) {
                            bst.insert(Double.parseDouble(val));
                        }
                        break;
                    case "s":
                    for (String val : values) {
                        bst.insert(val);
                    }
                        break;
                }
            }
        } catch(FileNotFoundException e){
            e.printStackTrace();
        }

    }

}
