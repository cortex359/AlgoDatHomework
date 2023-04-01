import java.util.ArrayList;
import java.util.Collections;
/**
 * MyHashSetTest testet die Funktionen der Klasse MyHashSetTest.
 * =============================================================
 * Hausaufgabe 02: Hashtabelle
 * Algorithmen und Datenstrukturen, SoSe 2023
 * Aufgaben vom 27.03.2023
 * Abgabe der Loesungen am 02.04.2023
 *
 * @author Samuel Thesing, samuel.thesing@rwth-aachen.de
 * @author Christian Rene Thelen, christian.thelen@rwth-aachen.de
 * @author Michael Conrads, michael.conrads@rwth-aachen.de
 */
public class MyHashSetTest {

    public static void main(String[] args) {
        MyHashSet<Integer> myHash = new MyHashSet<>();
        for (int i = 0; i < 30; i++) {
            myHash.add(i);
        }
        System.out.println(myHash.contains(5)); // true
        System.out.println(myHash.delete(5));
        System.out.println(myHash.contains(5)); // false
        ArrayList<Integer> el = myHash.getElements();
        System.out.println(el); // Zahlen 0..29 ausser der 5 unsortiert
        Collections.sort(el);
        System.out.println(el); // 0,1,2,3,4,6,7,....,29
    }

}
