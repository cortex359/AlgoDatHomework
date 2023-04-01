import java.util.ArrayList;
import java.util.Arrays;

/**
 * MyHashSet implementiert eine generische Hashtabelle mit Teillisten.
 * ===================================================================
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
    // count ist die Anzahl der Elemente
    private int count = 0;

    // arr ist ein Array of ArrayLists, also ein Feld von Teillisten
    @SuppressWarnings("unchecked") // Um die Zuweisung von ArrayList[] zu ArrayList<K>[] this.arr ohne Warnung durchzufuehren.
    private ArrayList<K>[] arr = new ArrayList[10];

    /**
     * Erzeugt ein neues MyHashSet Objekt mit 10 Teillisten.
     */
    public MyHashSet() {
        Arrays.setAll(this.arr, element -> new ArrayList<K>());
    }

    /**
     * Verdoppelt die Anzahl der Teillisten und sortiert die Elemente neu ein.
     */
    private void grow() {
        @SuppressWarnings("unchecked") // Um die Zuweisung einer neuen ArrayList[] zu ArrayList<K>[] newArr ohne Warnung durchzufuehren.
        ArrayList<K>[] newArr = new ArrayList[this.arr.length * 2];
        Arrays.setAll(newArr, element -> new ArrayList<K>());

        for (ArrayList<K> arrPart : this.arr) {
            for (K element : arrPart) {
                newArr[element.hashCode() % newArr.length].add(element);
            }
        }
        this.arr = newArr;
    }

    /**
     * Fuegt der Menge ein Element hinzu.
     *
     * @param element Element, welches der Menge hinzugefuegt werden soll.
     *
     * @return boolean True, wenn das Element schon existiert, ansonsten False. Achtung: Dies verhaelt sich anders als
     * Collection.add(E e), welches True zurueckgibt, wenn die Liste durch den Aufruf veraendert wurde.
     */
    public boolean add(K element) {
        if (this.contains(element)) {
            return true;
        }

        // Statt den Fuellgrad der Hashtabelle zu berechnen (Anzahl der Elemente / Anzahl der Teillisten) und zu pruefen,
        // ob dieser groesser 2 ist, laesst sich aequivalent pruefen, ob die Anzahl der Elemente groesser als die Anzahl der
        // Teillisten * 2 ist.
        if (this.count >= this.arr.length * 2) {
            grow();
        }

        this.arr[element.hashCode() % this.arr.length].add(element);
        this.count++;
        return false;
    }

    /**
     * Loescht das angegebene Element, falls dieses existiert; dabei wird die Anzahl der Teillisten nicht verkleinert.
     *
     * @param element Zu loeschendes Element.
     *
     * @return boolean True, wenn das Element existiert hat, ansonsten False.
     */
    public boolean delete(K element) {
        if (!this.arr[element.hashCode() % this.arr.length].remove(element)) {
            return false;
        }
        this.count--;
        return true;
    }

    /**
     * Gibt zurueck, ob das Element in der Hashtabelle existiert.
     *
     * @param element Zu suchendes Element.
     *
     * @return boolean True, wenn die Hashtabelle das Element enthaelt, anderenfalls False.
     */
    public boolean contains(K element) {
        return this.arr[element.hashCode() % this.arr.length].contains(element);
    }

    /**
     * Gibt eine ArrayList mit allen Elementen der Hashtabelle zurueck.
     *
     * @return ArrayList, welche alle Elemente der Hashtabelle enthaelt.
     */
    public ArrayList<K> getElements() {
        ArrayList<K> elements = new ArrayList<>();
        for (ArrayList<K> ks : this.arr) {
            elements.addAll(ks);
        }
        return elements;
    }
}
