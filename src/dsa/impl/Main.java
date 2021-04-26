package dsa.impl;

public class Main {
    public static void main(String[] args) {

        // Replace this with your code to test your implementations.
        // This just an example of one simple test for a AVL Tree.
        AVL<Integer> myAVL = new AVL<Integer>();
//        myAVL.insert(1);
//        myAVL.insert(2);
//        System.out.println(myAVL.contains(1));
//        myAVL.inOrder();
//        myAVL.insert(3);
//        myAVL.remove(2);
//        System.out.println(myAVL.contains(10));
//        myAVL.inOrder();
//        myAVL.insert(3);
//        myAVL.insert(8);
//        myAVL.insert(2);
        myAVL.insert(1);
        myAVL.insert(2);
        myAVL.insert(3);
        myAVL.insert(4);
        myAVL.insert(5);
        myAVL.insert(6);
        myAVL.insert(7);
        myAVL.insert(8);
//        myAVL.remove(1);
//        myAVL.remove(2);
        myAVL.remove(7);

//        Traversal<Integer> myTraversal = new Traversal<Integer>();
//        myTraversal.preOrderTraversal(myAVL);


    }


}
