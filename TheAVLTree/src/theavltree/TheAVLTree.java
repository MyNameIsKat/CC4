package theavltree;

import java.util.Scanner;

/**
 *
 * @author MyNameIsKat
 */
public class TheAVLTree {

    private String[] array;
    private Node root;

    private static class Node {

        private int key;
        private int balance;
        private int height;
        private Node left;
        private Node right;
        private Node parent;

        Node(int key, Node parent) {
            this.key = key;
            this.parent = parent;
        }
    }

    public boolean insert(int key) {
        if (root == null) {
            root = new Node(key, null);
            return true;
        }

        Node n = root;
        while (true) {
            if (n.key == key) {
                return false;
            }

            Node parent = n;

            boolean goLeft = n.key > key;
            n = goLeft ? n.left : n.right;

            if (n == null) {
                if (goLeft) {
                    parent.left = new Node(key, parent);
                } else {
                    parent.right = new Node(key, parent);
                }
                rebalance(parent);
                break;
            }
        }
        return true;
    }

    private void delete(Node node) {
        if (node.left == null && node.right == null) {
            if (node.parent == null) {
                root = null;
            } else {
                Node parent = node.parent;
                if (parent.left == node) {
                    parent.left = null;
                } else {
                    parent.right = null;
                }
                rebalance(parent);
            }
            return;
        }

        if (node.left != null) {
            Node child = node.left;
            while (child.right != null) {
                child = child.right;
            }
            node.key = child.key;
            delete(child);
        } else {
            Node child = node.right;
            while (child.left != null) {
                child = child.left;
            }
            node.key = child.key;
            delete(child);
        }
    }

    public void delete(int delKey) {
        if (root == null) {
            return;
        }

        Node child = root;
        while (child != null) {
            Node node = child;
            child = delKey >= node.key ? node.right : node.left;
            if (delKey == node.key) {
                delete(node);
                return;
            }
        }
    }

    private void rebalance(Node n) {
        setBalance(n);

        if (n.balance == -2) {
            if (height(n.left.left) >= height(n.left.right)) {
                n = rotateRight(n);
            } else {
                n = rotateLeftThenRight(n);
            }

        } else if (n.balance == 2) {
            if (height(n.right.right) >= height(n.right.left)) {
                n = rotateLeft(n);
            } else {
                n = rotateRightThenLeft(n);
            }
        }

        if (n.parent != null) {
            rebalance(n.parent);
        } else {
            root = n;
        }
    }

    private Node rotateLeft(Node a) {

        Node b = a.right;
        b.parent = a.parent;

        a.right = b.left;

        if (a.right != null) {
            a.right.parent = a;
        }

        b.left = a;
        a.parent = b;

        if (b.parent != null) {
            if (b.parent.right == a) {
                b.parent.right = b;
            } else {
                b.parent.left = b;
            }
        }

        setBalance(a, b);

        return b;
    }

    private Node rotateRight(Node a) {

        Node b = a.left;
        b.parent = a.parent;

        a.left = b.right;

        if (a.left != null) {
            a.left.parent = a;
        }

        b.right = a;
        a.parent = b;

        if (b.parent != null) {
            if (b.parent.right == a) {
                b.parent.right = b;
            } else {
                b.parent.left = b;
            }
        }

        setBalance(a, b);

        return b;
    }

    private Node rotateLeftThenRight(Node n) {
        n.left = rotateLeft(n.left);
        return rotateRight(n);
    }

    private Node rotateRightThenLeft(Node n) {
        n.right = rotateRight(n.right);
        return rotateLeft(n);
    }

    private int height(Node n) {
        if (n == null) {
            return -1;
        }
        return n.height;
    }

    private void setBalance(Node... nodes) {
        for (Node n : nodes) {
            reheight(n);
            n.balance = height(n.right) - height(n.left);
        }
    }

    public void printBalance() {
        printBalance(root);
    }

    private void printBalance(Node n) {
        if (n != null) {
            printBalance(n.left);
            System.out.printf("%s ", n.balance);
            printBalance(n.right);
        }
    }

    private void reheight(Node node) {
        if (node != null) {
            node.height = 1 + Math.max(height(node.left), height(node.right));
        }
    }

    void toArray(Node node, int index) {
        if (node != null) {
            //System.out.print(node.key + " "); 
            if (index < array.length) {
                array[index] = Integer.toString(node.key);
                toArray(node.left, index * 2 + 1);
                toArray(node.right, index * 2 + 2);
            }
        }
    }

    void preOrder(Node node) {
        if (node != null) {
            System.out.print(node.key + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    void inOrder(Node node) {
        if (node != null) {
            inOrder(node.left);
            System.out.print(node.key + " ");
            inOrder(node.right);
        }
    }

    void postOrder(Node node) {
        if (node != null) {
            postOrder(node.left);
            postOrder(node.right);
            System.out.print(node.key + " ");
        }
    }

    public void print(TheAVLTree tree) {
        tree.array = new String[(int) Math.pow(2, tree.root.height + 1) - 1];
        for (int i = 0; i < tree.array.length; i++) {
            tree.array[i] = "_";
        }
        tree.toArray(tree.root, 0);

        System.out.print("INDEX\t");
        for (int i = 0; i < tree.array.length; i++) {
            System.out.print(i + "\t");
        }
        System.out.println("");
        System.out.print("VALUE\t");
        for (int i = 0; i < tree.array.length; i++) {
            System.out.print(tree.array[i] + "\t");
        }
    }

    public static void main(String[] args) {
        TheAVLTree tree = new TheAVLTree();

        Scanner sc = new Scanner(System.in);
        int choice;
        
        GetOutOfTheLoop:
        for (;;) {
            System.out.println("\n[1] Insert "
                    + "\n[2] Delete"
                    + "\n[3] Exit ");
            System.out.print(": ");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("\nInsert: ");
                    tree.insert(Integer.parseInt(sc.next()));
                    tree.print(tree);
                    break;
                case 2:
                    System.out.print("Delete: ");
                    tree.delete(Integer.parseInt(sc.next()));
                    tree.print(tree);
                    break;
                case 3:
                    System.out.println("END");
                    System.out.println("");
                    break GetOutOfTheLoop;
                default:
                    break;
            }
        }

        tree.array = new String[(int) Math.pow(2, tree.root.height + 1) - 1];
        for (int i = 0; i < tree.array.length; i++) {
            tree.array[i] = "_";
        }
        tree.toArray(tree.root, 0);
        System.out.print("INDEX\t");
        for (int i = 0; i < tree.array.length; i++) {
            System.out.print(i + "\t");
        }
        System.out.println("");
        System.out.print("VALUE\t");
        for (int i = 0; i < tree.array.length; i++) {
            System.out.print(tree.array[i] + "\t");
        }
        System.out.println("\n");
        System.out.println("[Pre-Order Traversal]");
        tree.preOrder(tree.root);
        System.out.println("");
        System.out.println("[In-Order Traversal]");
        tree.inOrder(tree.root);
        System.out.println("");
        System.out.println("[Post-Order Traversal]");
        tree.postOrder(tree.root);
        System.out.println("");
    }
}
