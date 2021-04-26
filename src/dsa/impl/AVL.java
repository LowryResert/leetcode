package dsa.impl;

import dsa.iface.INode;

public class AVL<T extends Comparable<T>> extends BinarySearchTree<T> {

    // avl树的根节点
    private AVLNode<T> root;

    //内部AVLNode类
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

    @Override
    public boolean contains(T value) {
        return search(root, value) != null;
    }

    //从以结点x为根的avl树中查找element
    private AVLNode<T> search(AVLNode<T> x, T value) {
        if (x == null) return null;

        int cmp = value.compareTo(x.element);
        if (cmp < 0)
            return search(x.left, value);
        else if (cmp > 0)
            return search(x.right, value);
        else
            return x;
    }

    //插入一个元素
    public void insert(T element) {
        if (element == null) {
            throw new NullPointerException();
        }
        root = insert(root, element);
    }

    private AVLNode<T> insert(AVLNode<T> node, T element) {
        if (node == null) {
            return new AVLNode<>(element, null, null);
        }

        int cmp = element.compareTo(node.element);
        if (cmp == 0) {
            return node;
        }
        if (cmp < 0) {
            node.left = insert(node.left, element);
        } else {
            node.right = insert(node.right, element);
        }

        if (Math.abs(height(node.left) - height(node.right)) > 1) {
            node = trinodeRotation(node);
        }
        refreshHeight(node);
        return node;
    }

    //树的平衡调整: LL/RR/LR/RL
    private AVLNode<T> trinodeRotation(AVLNode<T> node) {
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

    //删除一个元素
    public void remove(T element) {
        if (element == null) {
            throw new NullPointerException();
        }
        root = remove(root, element);
    }

    private AVLNode<T> remove(AVLNode<T> node, T element) {
        if (node == null) {
            return null;
        }

        int cmp = element.compareTo(node.element);
        if (cmp < 0) {
            node.left = remove(node.left, element);
        }
        if (cmp > 0) {
            node.right = remove(node.right, element);
        }
        if (cmp == 0) {
            if (node.left == null || node.right == null) {
                return node.left == null ? node.right : node.left;
            }
            T successorKey = successorOf(node).element;
            node = remove(node, successorKey);
            node.element = successorKey;
        }

        if (Math.abs(height(node.left) - height(node.right)) > 1) {
            node = trinodeRotation(node);
        }
        refreshHeight(node);
        return node;
    }

    //返回树的高度
    private int height(AVLNode<T> node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    //更新树的高度
    private void refreshHeight(AVLNode<T> node) {
        node.height = Math.max(height(node.left), height(node.right)) + 1;
    }

    //寻找被删除节点的继承者
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

}
