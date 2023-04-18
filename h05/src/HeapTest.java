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

    /**
     * Führt den Test der Klasse Heap mit Beispieldaten aus.
     * @param args Kommandozeilenparameter, welche ignoriert werden.
     */
    public static void main(String[] args) {
        Heap heap = new Heap();
        int[] test1 = {1, 6, 8, 18, 23, 5, 17, 20, 26, 21, 9};
        testHeap(heap, test1);

        System.out.println();

        int[] test2 = {1, 2, 3, 4};
        testHeap(heap, test2);
    }

    /**
     * Testet die Funktionen der Heap Klasse durch Hinzufügen und Entfernen der Elemente einer übergebenen Liste.
     * @param heap Heap Objekt der zu testenden Klasse.
     * @param test int[] Array mit Zahlen, die in ihrer Reihenfolge hinzugefügt werden sollen.
     */
    private static void testHeap(Heap heap, int[] test) {
        StringBuilder sb = new StringBuilder("Füge die Elemente\n    [");

        for (int i = 0; i < test.length; i++) {
            sb.append(test[i]);
            if (i < test.length -1) sb.append(", ");
        }
        sb.append("]\nin angegebener Reihenfolge zur Heap.\n");

        System.out.println(sb.toString());

        System.out.printf("Heap: %s\n", heap.toString());
        for (int t : test) {
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
