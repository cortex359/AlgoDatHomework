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

    /**
     * Test zur Ueberpruefung der MyHashSet Klasse.
     * @param args Komandozeilenparameter, welche ignoriert werden.
     */
    public static void main(String[] args) {
        System.out.println("Initialisiere myHash als MyHashSet<Integer> Objekt mit den Zahlen von 0-29.");
        MyHashSet<Integer> myHash = new MyHashSet<>();
        for (int i = 0; i < 30; i++) {
            myHash.add(i);
        }

        System.out.println("Teste die Funktionen contains(K element) und delete(K element):");
        System.out.printf("  %-18s -> %b\n", "myHash.contains(5)", myHash.contains(5));       // true
        System.out.printf("  %-18s -> %b\n", "myHash.delete(5)",   myHash.delete(5)); // true
        System.out.printf("  %-18s -> %b\n", "myHash.contains(5)", myHash.contains(5));       // false

        System.out.println("Teste die Funktion getElements():");
        ArrayList<Integer> el = myHash.getElements();
        System.out.printf("  %s\n", el); // Zahlen 0..29 ausser der 5 unsortiert
        Collections.sort(el);
        System.out.printf("  %s\n", el); // 0,1,2,3,4,6,7,....,29

        System.out.println(myHash.size());
    }

}
