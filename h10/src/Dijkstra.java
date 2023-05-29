import java.util.*;
import java.util.stream.IntStream;

/**
 * Dijkstra-Agorithmus
 * ==========================================
 * Hausaufgabe 10: Dijkstra
 * Algorithmen und Datenstrukturen, SoSe 2023
 * Aufgaben vom 22.05.2023
 * Abgabe der Loesungen am 29.05.2023
 *
 * @author Samuel Thesing, samuel.thesing@rwth-aachen.de
 * @author Christian Rene Thelen, christian.thelen@rwth-aachen.de
 * @author Michael Conrads, michael.conrads@rwth-aachen.de
 */
public class Dijkstra {

    private record Kante(int zielKnoten, int gewicht) {}

    /**
     * Wandelt eine Kantenliste in eine Adjazenzliste um
     * @param kantenListe die umzuwandelnde Kantenliste
     * @return die resultierende Adjazenzliste
     */
    private static List<List<Kante>> getAdjacencyListFromEdgeList(int[] kantenListe) {
        if (kantenListe.length < 1) {
            throw new IllegalArgumentException("Edge list is empty");
        }

        List<List<Kante>> kanten = new ArrayList<>(kantenListe[0]);
        for (int i = 0; i < kantenListe[0]+1; i++) {
            kanten.add(new ArrayList<>());
        }

        int c = 1;
        while (c+2 < kantenListe.length) {
            int anfang = kantenListe[c];
            int ziel = kantenListe[c+1];
            int gewicht = kantenListe[c+2];
            if (anfang > kanten.size() || anfang < 1 || ziel > kanten.size() || ziel < 1) {
                throw new IllegalArgumentException("Node-Index out of bounds.");
            }

            kanten.get(anfang).add(new Kante(ziel, gewicht));
            c += 3;
        }

        return kanten;
    }

    /**
     * Sucht den minimalen Wert, der noch nicht besucht wurde
     * @param arr Werte
     * @param visited besuchte Werte
     * @return index des minimalen noch nicht besuchten Wertes, -1 falls keiner existiert
     */
    private static int getIndexOfMinimumNotVisited(int[] arr, boolean[] visited) {
        int min = Integer.MAX_VALUE;
        int idx = -1;
        for (int i = 0; i < arr.length; i++) {
            if (!visited[i] && arr[i] < min) {
                min = arr[i];
                idx = i;
            }
        }
        return idx;
    }

    /**
     * Gibt die Daten formatiert aus
     * @param data auszugebende Daten
     * @param undefinedValue Zahl die durch "--" ersetzt werden soll
     */
    private static void printData(int[] data, int undefinedValue) {
        System.out.print("|");
        for (int i = 2; i < data.length; i++) {
            if (data[i] == undefinedValue) {
                System.out.printf(" %2s", "--");
            } else {
                System.out.printf(" %2d", data[i]);
            }
        }
    }

    /**
     * Wendet Dijkstra auf den uebergebenen Graphen an und gibt das dazugehoerige Dijkstra-Schema aus
     * @param kantenListe
     */
    public static void printDijkstra(int[] kantenListe) {
        List<List<Kante>> kanten = getAdjacencyListFromEdgeList(kantenListe);

        // 0 oder 1 Node ergeben kein sinnvolles Dijkstra-Diagramm
        if (kanten.size() < 2) return;

        // Header-Zeile des Dijkstra-Schema
        System.out.print("vi");
        var indices = IntStream.range(0, kanten.size()).toArray();
        printData(indices, -1);
        printData(indices, -1);
        System.out.println("|");
        System.out.println(("-").repeat(5 + 6 * (kanten.size()-2)));

        int[] parents = new int[kanten.size()];
        int[] distances = new int[kanten.size()];
        boolean[] visited = new boolean[kanten.size()];
        Arrays.fill(distances, Integer.MAX_VALUE);

        // Startknoten 1
        distances[1] = 0;
        while (true) {
            int newlyAdded = getIndexOfMinimumNotVisited(distances, visited);
            // Bricht ab, wenn es keinen unbesuchten, erreichbaren Knoten mehr gibt
            if (newlyAdded < 1) break;

            visited[newlyAdded] = true;

            for (Kante kante : kanten.get(newlyAdded)) {
                if (distances[newlyAdded] + kante.gewicht < distances[kante.zielKnoten]) {
                    distances[kante.zielKnoten] = distances[newlyAdded] + kante.gewicht;
                    parents[kante.zielKnoten] = newlyAdded;
                }
            }

            // Ausgabe Daten
            System.out.printf("%2s", newlyAdded);
            printData(distances, Integer.MAX_VALUE);
            printData(parents, 0);
            System.out.println("|");
        }
    }

}
