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
        Ein Heap ist ein Binärbaum mit folgenden Eigenschaften:
          - Er ist links-vollständig
          - Die Kinder eines Knotens sind höchstens so groß wie der Knoten selbst.
     */

    private ArrayList<Integer> data = new ArrayList<>();

    // gibt true zurück, falls der Heap leer ist ansonsten false.
    public boolean isEmpty() {
        return ! (data.size() > 1);
    }

    public Heap() {
        // Dummy-Element
        data.add(null);
    }

    // fügt den Wert i zum Heap hinzu.
    public void add(int i) {
        // Level-Order-Reihenfolge
        data.add(i);
        upHeap();
    }

    // entfernt das größte Element vom Heap und gibt es zurück
    public int getMax() {
        // das größte Element ist definitionsgemäß an der Wurzel
        int max = data.get(1);

        // Wenn der Heap dadurch noch nicht leer wird
        if (data.size() - 1 > 1) {
            // setze den weitesten rechtsstehenden Blattknoten aus der untersten Ebene als neue Wurzel
            data.set(1, data.remove(data.size() - 1));
        } else {
            // ansonsten entferne ihn einfach.
            data.remove(data.size() -1);
        }

        downHeap();
        return max;
    }

    // gibt das Feld (den Heap) in Stringdarstellung zurück
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

    private void upHeap() {
        int pos = data.size() - 1;
        while (pos > 1 && data.get(pos) > data.get(pos/2)) {
            Collections.swap(data, pos, pos / 2);
            pos = pos / 2;
        }
    }
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
