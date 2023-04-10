public class LinkedList implements IList {

    static class Node {
        int value;
        Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    private int size = 0;
    private Node root = null;

    private Node getNodeAt(int pos) {
        if (pos < 0 || pos >= size) throw new ArrayIndexOutOfBoundsException("Out of bounds");
        var cur = root;
        int idx = 0;
        while (idx < pos) {
            cur = cur.next;
            idx++;
        }
        return cur;
    }

    @Override
    public void insertAt(int pos, int value) {
        if (pos == 0) {
            Node n = new Node(value);
            n.next = this.root;
            this.root = n;
            this.size++;
            return;
        }

        Node before = this.getNodeAt(pos-1);
        Node n = new Node(value);
        n.next = before.next;
        before.next = n;
        this.size++;
    }

    @Override
    public void removeAt(int pos) {
        if (pos >= size) throw new ArrayIndexOutOfBoundsException("Out of bounds");
        if (pos == 0) {
            this.root = this.root.next;
            this.size--;
            return;
        }
        Node before = getNodeAt(pos - 1);
        before.next = before.next.next;
        this.size--;
    }

    @Override
    public int getAt(int pos) {
        Node n = this.getNodeAt(pos);
        return n.value;
    }

    @Override
    public int search(int value) {
        Node cur = this.root;
        int idx = 0;
        while (cur != null) {
            if (cur.value == value) {
                return idx;
            }
            idx++;
            cur = cur.next;
        }
        throw new ArrayIndexOutOfBoundsException("Out of bounds");
    }

    @Override
    public void clear() {
        if (this.size == 0) return;
        Node prev = this.root;
        Node cur = this.root.next;
        while (cur != null) {
            prev.next = null;
            prev = cur;
            cur = cur.next;
        }
        this.size = 0;
    }

    @Override
    public int count() {
        return size;
    }
}
