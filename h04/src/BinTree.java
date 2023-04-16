
public class BinTree {

    private static class Node {

        public int data;
        public Node left = null;
        public Node right = null;

        public Node(int data) {
            this.data = data;
        }

    }

    private Node root = null;

    private Node getNode(int x) {
        Node cur = this.root;
        while (cur != null && cur.data != x) {
            if (cur.data > x) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        return cur;
    }

    private Node getParentNode(int x) {
        if (this.root == null) {
            return null;
        }
        if (this.root.data == x) {
            return null;
        }

        Node cur = this.root;
        while (cur != null) {
            if (cur.data > x) {
                if (cur.left != null && cur.left.data == x) return cur;
                cur = cur.left;
            } else {
                if (cur.right != null && cur.right.data == x) return cur;
                cur = cur.right;
            }
        }
        return null;
    }

    public void insert(int x) {
        if (this.root == null) {
            this.root = new Node(x);
            return;
        }

        Node cur = this.root;
        while (cur.data != x) {
            if (cur.data > x) {
                if (cur.left == null) {
                    cur.left = new Node(x);
                    return;
                }
                cur = cur.left;
            } else {
                if (cur.right == null) {
                    cur.right = new Node(x);
                    return;
                }
                cur = cur.right;
            }
        }
        throw new ArithmeticException("Value already exists");
    }

    public void clear() {
        this.root = null;
    }

    public void remove(int x) {
        if (this.root == null) {
            throw new ArithmeticException("Value does not exist");
        }
        Node p = null;
        Node n = this.root;
        while (n != null && n.data != x) {
            p = n;
            if (n.data > x) {
                n = n.left;
            } else {
                n = n.right;
            }
        }
        if (n == null) {
            throw new ArithmeticException("Value does not exist");
        }

        //0 or 1 child
        if (n.left == null || n.right == null) {
            Node toMove = n.left == null ? n.right : n.left;
            if (p == null) {
                this.root = toMove;
            } else if (p.left == n) {
                p.left = toMove;
            } else {
                p.right = toMove;
            }
            return;
        }

        //2 children
        //get next bigger Node
        Node nextBiggerParent = n;
        Node nextBigger = n.right;
        while (nextBigger.left != null) {
            nextBiggerParent = nextBigger;
            nextBigger = nextBigger.left;
        }

        //replace with next bigger
        if (p == null) {
            this.root = nextBigger;
        } else if (p.left == n) {
            p.left = nextBigger;
        } else {
            p.right = nextBigger;
        }
        nextBigger.left = n.left;

        if (n.right != nextBigger) {
            nextBiggerParent.left = nextBigger.right;
            nextBigger.right = n.right;
        }
    }

    public static void printTree(Node n) {
        if (n == null) {
            System.out.println("Tree is empty");
            return;
        }
        System.out.println(n.data);
        if (n.left != null) printTree(n.left, 1, true);
        if (n.right != null) printTree(n.right, 1, false);
    }

    private static void printTree(Node n, int depth, boolean left) {
        for (int i = 0; i < depth; i++) {
            System.out.print('\t');
        }
        System.out.print(left ? "l: ": "r: ");
        System.out.println(n.data);
        if (n.left != null) printTree(n.left, depth+1, true);
        if (n.right != null) printTree(n.right, depth+1, false);
    }

    public static void main(String[] args) {
        BinTree tree = new BinTree();
        tree.insert(20);
        tree.insert(10);
        tree.insert(30);
        tree.insert(50);
        int[] testcases = { 30, 35, 50 };
        for (int testcase : testcases) {
            Node node = tree.getNode(testcase);
            if (node == null) {
                System.out.println("Knoten " + testcase + " nicht gefunden.");
            } else {
                System.out.println("Knoten " + testcase + " gefunden: " + node.data);
            }
        }
        tree.remove(30);
        System.out.println("Knoten geloescht: 30");
        System.out.println("Elternknoten von 50: " + tree.getParentNode(50).data);// 20
    }

}
