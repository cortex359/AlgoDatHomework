/**
 * HeapTest testet die Klasse Heap
 * ===============================
 * Hausaufgabe 05: Heap Datenstrukturen
 * Algorithmen und Datenstrukturen, SoSe 2023
 * Aufgaben vom 17.04.2023
 * Abgabe der Loesungen am 24.04.2023
 *
 * @author Samuel Thesing, samuel.thesing@rwth-aachen.de
 * @author Christian Rene Thelen, christian.thelen@rwth-aachen.de
 * @author Michael Conrads, michael.conrads@rwth-aachen.de
 */
public class HeapTest {

    public static void main(String[] args) {
        Heap heap = new Heap();

        int[] test1 = {1, 6, 8, 18, 23, 5, 17, 20, 26, 21, 9};

        StringBuilder sb = new StringBuilder("Füge die Elemente\n    [");

        for (int i = 0; i < test1.length; i++) {
            sb.append(test1[i]);
            if (i < test1.length -1) sb.append(", ");
        }
        sb.append("]\nin angegebener Reihenfolge zur Heap.\n");

        System.out.println(sb.toString());

        System.out.printf("Heap: %s\n", heap.toString());
        for (int t : test1) {
            heap.add(t);
            System.out.printf("%3d hinzugefügt: %s\n", t, heap.toString());
        }

        System.out.println("\nEntnehme jeweils das größtes Element aus dem Heap...");
        while (!heap.isEmpty()) {
            System.out.printf("%3d entnommen: ", heap.getMax());
            System.out.printf("%s\n", heap.toString());
        }
    }
}
