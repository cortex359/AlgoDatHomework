/**
 * LinkedList implementiert IList
 * ==============================
 * Hausaufgabe 03: Verkettete Listen
 * Algorithmen und Datenstrukturen, SoSe 2023
 * Aufgaben vom 03.04.2023
 * Abgabe der Loesungen am 10.04.2023
 *
 * @author Samuel Thesing, samuel.thesing@rwth-aachen.de
 * @author Christian Rene Thelen, christian.thelen@rwth-aachen.de
 * @author Michael Conrads, michael.conrads@rwth-aachen.de
 */
public class LinkedList implements IList {

    /**
     * Node Datenstruktur fuer Elemente der verketteten Liste.
     */
    static class Node {
        int value;
        Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    // Groesse der Liste
    private int size = 0;

    // Beginn der Liste.
    private Node root = null;

    /**
     * Hilfsfunktion um die Node an einer bestimmten Position der Liste zurueckzugeben.
     * @param pos Position.
     * @return Node.
     */
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

    /**
     * Fuegt einen Wert an einer bestimmten Position ein.
     * @param pos Position.
     * @param value Einzufuegender Wert.
     */
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

    /**
     * Loescht einen Wert an einer bestimmten Position.
     * @param pos Position.
     */
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

    /**
     * Liest einen Wert an einer bestimmten Position aus.
     * @param pos Position.
     * @return Wert an der Position.
     */
    @Override
    public int getAt(int pos) {
        Node n = this.getNodeAt(pos);
        return n.value;
    }

    /**
     * Sucht nach einem Wert und gibt die Position zurueck.
     * @param value Suchwort.
     * @return Position.
     */
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

    /**
     * Loescht alle Daten.
     */
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

    /**
     * Gibt die Anzahl der enthaltenen Elemente zurueck.
     * @return Anzahl der Elemente.
     */
    @Override
    public int count() {
        return size;
    }
}
