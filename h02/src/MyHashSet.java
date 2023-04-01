import java.util.ArrayList;

/**
 * MyHashSet implementiert eine generische Hashtabelle.
 * ====================================================
 * Hausaufgabe 02: Hashtabelle
 * Algorithmen und Datenstrukturen, SoSe 2023
 * Aufgaben vom 27.03.2023
 * Abgabe der Loesungen am 02.04.2023
 *
 * @author Samuel Thesing, samuel.thesing@rwth-aachen.de
 * @author Christian Rene Thelen, christian.thelen@rwth-aachen.de
 * @author Michael Conrads, michael.conrads@rwth-aachen.de
 */
public class MyHashSet<K> {

    private int count = 0;

    private ArrayList<K>[] arr;

    public MyHashSet() {
        this.arr = new ArrayList[10];
        for (int i = 0; i < this.arr.length; i++) {
            this.arr[i] = new ArrayList<K>();
        }
    }

    private void grow() {
        ArrayList<K>[] newArr = new ArrayList[this.arr.length * 2];
        for (int i = 0; i < newArr.length; i++) {
            newArr[i] = new ArrayList<K>();
        }
        for (ArrayList<K> arrPart : this.arr) {
            for (K element : arrPart) {
                newArr[element.hashCode() % newArr.length].add(element);
            }
        }
        this.arr = newArr;
    }

    public boolean add(K element) {
        if (this.contains(element)) {
            return true;
        }
        if (this.count >= this.arr.length * 2) {
            grow();
        }
        arr[element.hashCode() % this.arr.length].add(element);
        count++;
        return false;
    }

    public boolean delete(K element) {
        if (!this.arr[element.hashCode() % this.arr.length].remove(element)) {
            return false;
        }
        this.count--;
        return true;
    }

    public boolean contains(K element) {
        return this.arr[element.hashCode() % this.arr.length].contains(element);
    }

    public ArrayList<K> getElements() {
        ArrayList<K> elements = new ArrayList<>();
        for (ArrayList<K> ks : this.arr) {
            elements.addAll(ks);
        }
        return elements;
    }

}
