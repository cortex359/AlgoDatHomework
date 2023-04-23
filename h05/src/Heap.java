import java.util.ArrayList;
import java.util.Collections;

/**
 * Heap Datenstrukturen
 * ====================
 * Hausaufgabe 05: Heap Datenstrukturen
 * Algorithmen und Datenstrukturen, SoSe 2023
 * Aufgaben vom 17.04.2023
 * Abgabe der Loesungen am 24.04.2023
 *
 * @author Samuel Thesing, samuel.thesing@rwth-aachen.de
 * @author Christian Rene Thelen, christian.thelen@rwth-aachen.de
 * @author Michael Conrads, michael.conrads@rwth-aachen.de
 */
public class Heap {
    /* Definition
        Eine Heap ist ein Binärbaum mit folgenden Eigenschaften:
          - Er ist links-vollständig
          - Die Kinder eines Knotens sind höchstens so groß wie der Knoten selbst.
     */

    // Einbettung der Heap als Liste wie in den Folien beschrieben
    private ArrayList<Integer> data = new ArrayList<>();

    /**
     * Überprüft, ob der Heap leer ist.
     * @return gibt true zurück, falls der Heap leer ist ansonsten false.
     */
    public boolean isEmpty() {
        return data.size() <= 1;
    }

    /**
     * Initialisiert den Heap.
     */
    public Heap() {
        // Dummy-Element
        data.add(null);
    }

    /**
     * Fügt einen Wert zum Heap hinzu.
     * @param i der zum Heap hinzugefügt wird.
     */
    public void add(int i) {
        // Level-Order-Reihenfolge
        data.add(i);
        upHeap();
    }

    /**
     * Entfernt das größte Element (Wurzelelement) vom Heap, gibt es zurück und stellt die Ordnung wieder her.
     * @return Größtes Element, welches vom Heap entfernt wurde.
     */
    public int getMax() {
        if (this.isEmpty())
            throw new IndexOutOfBoundsException("Heap ist leer.");

        // das größte Element ist definitionsgemäß an der Wurzel
        int max = data.get(1);

        // Wenn der Heap aus mehr als nur einem Knoten besteht...
        if (data.size() - 1 > 1) {
            // ... setze den, am weitesten rechtsstehenden Blattknoten aus der untersten Ebene als neue Wurzel ...
            data.set(1, data.remove(data.size() - 1));
            // ... und ordne den Heap neu.
            downHeap();
        } else {
            // ... ansonsten entferne den letzten Knoten.
            data.remove(data.size() -1);
        }

        return max;
    }

    /**
     * Gibt die Feldeinbettung von dem Heap in Stringdarstellung zurück.
     * @return Stringdarstellung der Einbettung des Heaps.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 1; i < data.size(); i++) {
            sb.append(data.get(i));
            if (i < data.size() - 1)
                sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * upHeap stellt die Ordnung des Heaps durch Vertauschung von unten nach oben wieder her.
     */
    private void upHeap() {
        int pos = data.size() - 1;
        while (pos > 1 && data.get(pos) > data.get(pos/2)) {
            Collections.swap(data, pos, pos / 2);
            pos = pos / 2;
        }
    }

    /**
     * downHeap stellt die Ordnung des Heaps durch Vertauschung von oben nach unten wieder her.
     */
    private void downHeap() {
        int pos = 1;
        while (pos*2 < data.size()) {
            int j = pos*2;
            if (j < data.size() -1 && data.get(j) < data.get(j+1)) j++;
            if (data.get(pos) > data.get(j)) break;
            Collections.swap(data, pos, j);
            pos = j;
        }
    }

}
