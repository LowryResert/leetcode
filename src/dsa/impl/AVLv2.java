package dsa.impl;

import dsa.iface.INode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class AVLv2<T extends Comparable<T>> extends BinarySearchTree<T> {

    private static final int MAX_HEIGHT_DIFFERENCE = 1;

    private AVLNode<T> root;

    static class AVLNode<T extends Comparable<T>> implements INode<T> {
        T element;
        int height;
        AVLNode<T> left;
        AVLNode<T> right;

        public AVLNode(T element, AVLNode<T> left, AVLNode<T> right) {
            this.element = element;
            this.left = left;
            this.right = right;
        }

        @Override
        public T element() {
            return element;
        }
    }

    public void insert(T key) {
        if (key == null) {
            throw new NullPointerException();
        }
        root = insert(root, key);
    }

    private AVLNode<T> insert(AVLNode<T> node, T key) {
        if (node == null) {
            return new AVLNode<>(key, null, null);
        }

        int cmp = key.compareTo(node.element);
        if (cmp == 0) {
            return node;
        }
        if (cmp < 0) {
            node.left = insert(node.left, key);
        } else {
            node.right = insert(node.right, key);
        }

        if (Math.abs(height(node.left) - height(node.right)) > MAX_HEIGHT_DIFFERENCE) {
            node = balance(node);
        }
        refreshHeight(node);
        return node;
    }

    private int height(AVLNode<T> node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    private void refreshHeight(AVLNode<T> node) {
        node.height = Math.max(height(node.left), height(node.right)) + 1;
    }

    /**
     * 此方法中的node, node1, node2分别代表上文范型中的1、2、3号节点
     */
    private AVLNode<T> balance(AVLNode<T> node) {
        AVLNode<T> node1, node2;
        // ll & l
        if (height(node.left) > height(node.right) &&
                height(node.left.left) >= height(node.left.right)) {
            node1 = node.left;
            node.left = node1.right;
            node1.right = node;

            refreshHeight(node);
            return node1;
        }
        // lr
        if (height(node.left) > height(node.right) &&
                height(node.left.right) > height(node.left.left)) {
            node1 = node.left;
            node2 = node.left.right;
            node.left = node2.right;
            node1.right = node2.left;
            node2.left = node1;
            node2.right = node;

            refreshHeight(node);
            refreshHeight(node1);
            return node2;
        }
        // rr & r
        if (height(node.right) > height(node.left) &&
                height(node.right.right) >= height(node.right.left)) {
            node1 = node.right;
            node.right = node1.left;
            node1.left = node;

            refreshHeight(node);
            return node1;
        }
        // rl
        if (height(node.right) > height(node.left) &&
                height(node.right.left) > height(node.right.right)) {
            node1 = node.right;
            node2 = node.right.left;
            node.right = node2.left;
            node1.left = node2.right;
            node2.left = node;
            node2.right = node1;

            refreshHeight(node);
            refreshHeight(node1);
            return node2;
        }
        return node;
    }

    public void remove(T key) {
        if (key == null) {
            throw new NullPointerException();
        }
        root = remove(root, key);
    }

    private AVLNode<T> remove(AVLNode<T> node, T key) {
        if (node == null) {
            return null;
        }

        int cmp = key.compareTo(node.element);
        if (cmp < 0) {
            node.left = remove(node.left, key);
        }
        if (cmp > 0){
            node.right = remove(node.right, key);
        }
        if (cmp == 0) {
            if (node.left == null || node.right == null) {
                return node.left == null ? node.right : node.left;
            }
            T successorKey = successorOf(node).element;
            node = remove(node, successorKey);
            node.element = successorKey;
        }

        if (Math.abs(height(node.left) - height(node.right)) > MAX_HEIGHT_DIFFERENCE) {
            node = balance(node);
        }
        refreshHeight(node);
        return node;
    }

    /**
     * 寻找被删除节点的继承者
     */
    private AVLNode<T> successorOf(AVLNode<T> node) {
        if (node == null) {
            throw new NullPointerException();
        }
        if (node.left == null || node.right == null) {
            return node.left == null ? node.right : node.left;
        }

        return height(node.left) > height(node.right) ?
                findMax(node.left, node.left.right, node.left.right == null) :
                findMin(node.right, node.right.left, node.right.left == null);
    }

    private AVLNode<T> findMax(AVLNode<T> node, AVLNode<T> right, boolean rightIsNull) {
        if (rightIsNull) {
            return node;
        }
        return findMax((node = right), node.right, node.right == null);
    }

    private AVLNode<T> findMin(AVLNode<T> node, AVLNode<T> left, boolean leftIsNull) {
        if (leftIsNull) {
            return node;
        }
        return findMin((node = left), node.left, node.left == null);
    }











    public void print(AVLNode<Integer> root) {

        if(root == null) {
            System.out.println("(XXXXXX)");
            return;
        }

        int height = root.height,
                width = (int)Math.pow(2, height-1);

        // Preparing variables for loop.
        List<AVLNode<Integer>> current = new ArrayList<AVLNode<Integer>>(1),
                next = new ArrayList<AVLNode<Integer>>(2);
        current.add(root);

        final int maxHalfLength = 4;
        int elements = 1;

        StringBuilder sb = new StringBuilder(maxHalfLength*width);
        for(int i = 0; i < maxHalfLength*width; i++)
            sb.append(' ');
        String textBuffer;

        // Iterating through height levels.
        for(int i = 0; i < height; i++) {

            sb.setLength(maxHalfLength * ((int)Math.pow(2, height-1-i) - 1));

            // Creating spacer space indicator.
            textBuffer = sb.toString();

            // Print tree node elements
            for(AVLNode<Integer> n : current) {

                System.out.print(textBuffer);

                if(n == null) {

                    System.out.print("        ");
                    next.add(null);
                    next.add(null);

                } else {

                    System.out.printf("(%6d)", n.element);
                    next.add(n.left);
                    next.add(n.right);

                }

                System.out.print(textBuffer);

            }

            System.out.println();
            // Print tree node extensions for next level.
            if(i < height - 1) {

                for(AVLNode<Integer> n : current) {

                    System.out.print(textBuffer);

                    if(n == null)
                        System.out.print("        ");
                    else
                        System.out.printf("%s      %s",
                                n.left == null ? " " : "/", n.right == null ? " " : "\\");

                    System.out.print(textBuffer);

                }

                System.out.println();

            }

            // Renewing indicators for next run.
            elements *= 2;
            current = next;
            next = new ArrayList<AVLNode<Integer>>(elements);

        }

    }

    public static void main(String args[]) {
        AVLv2<Integer> t = new AVLv2<>();
        AVLNode<Integer> root = null;
        while (true) {
            System.out.println("(1) Insert");
            System.out.println("(2) Delete");

            try {
                BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
                String s = bufferRead.readLine();

                if (Integer.parseInt(s) == 1) {
                    System.out.print("Value to be inserted: ");
                    root = t.insert(root, Integer.parseInt(bufferRead.readLine()));
                }
                else if (Integer.parseInt(s) == 2) {
                    System.out.print("Value to be deleted: ");
                    root = t.remove(root, Integer.parseInt(bufferRead.readLine()));
                }
                else {
                    System.out.println("Invalid choice, try again!");
                    continue;
                }

                t.print(root);
            }
            catch(IOException e) {
                e.printStackTrace();
            }
        }
    }
}
