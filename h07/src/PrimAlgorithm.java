import java.util.Arrays;


/**
 * Algorithmus von Prim
 * ====================
 * Hausaufgabe 07: Algorithmus von Prim
 * Algorithmen und Datenstrukturen, SoSe 2023
 * Aufgaben vom 02.05.2023
 * Abgabe der Loesungen am 07.05.2023
 *
 * @author Samuel Thesing, samuel.thesing@rwth-aachen.de
 * @author Christian Rene Thelen, christian.thelen@rwth-aachen.de
 * @author Michael Conrads, michael.conrads@rwth-aachen.de
 */
public class PrimAlgorithm {

    /**
     * Stellt den Knoten mit minimaler Distanz zu den enthaltenen Knoten dar.
     * @param idx Index des Knoten
     * @param dist Distanz des Knotens zu den enthaltenen Knoten
     */
    private record Minimum (int idx, int dist){}

    /**
     * Sucht den noch nicht enthaltenen Knoten mit der geringsten Distanz zu einem enthaltenen Knoten
     * @param distances minimale Distanzen zu den enthaltenen Knoten
     * @param contained enthaltene Knoten
     * @return Index des Knotens mit minimaler Distanz<br>
     * Index ist -1, falls kein Knoten uebrig ist, der mit dem Graphen verbunden ist
     */
    private static Minimum getMinIdx(int[] distances, boolean[] contained) {
        int minDist = Integer.MAX_VALUE;
        int minIdx = -1;
        for (int i = 0; i < distances.length; i++) {
            if (!contained[i] && distances[i] < minDist) {
                minDist = distances[i];
                minIdx = i;
            }
        }
        return new Minimum(minIdx, minDist);
    }

    /**
     * Prueft, ob der Graph gerichtet ist
     * @param edges Adjazenzmatrix des Graphen
     * @return true, falls der Graph gerichtet ist, sonst false
     */
    private static boolean isDirected(int[][] edges) {
        for (int i = 0; i < edges.length; i++) {
            for (int j = i+1; j < edges.length; j++) {
                if (edges[i][j] != edges[j][i]) {
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * Implementierung des Algorithmus von Prim
     * @param edges Adjazenzmatrix der Knoten
     * @return Kosten des minimalen Spannbaums
     */
    public static int getMST(int[][] edges) {
        if (edges.length == 0) return 0;
        if (Arrays.stream(edges).anyMatch(el -> el.length != edges.length)) {
            throw new IllegalArgumentException("Die Adjazenzmatrix muss quadratisch sein");
        }
        if (isDirected(edges)) {
            throw new IllegalArgumentException("Der Graph soll ungerichtet sein");
        }

        int totalDist = 0;
        boolean[] contained = new boolean[edges.length];
        int[] distances = new int[edges.length];
        for (int i = 0; i < edges.length; i++) {
            distances[i] = Integer.MAX_VALUE;
        }

        // Garantiert, das der erste Knoten gewaehlt wird
        distances[0] = 0;

        for (int i = 0; i < edges.length; i++) {
            Minimum min = getMinIdx(distances, contained);
            if (min.idx == -1) {
                throw new IllegalArgumentException("Der Graph muss zusammenhaengend sein");
            }
            totalDist += min.dist;
            contained[min.idx] = true;
            // Aktualisiert die minimalen Distanzen anhand des neuen Knotens
            for (int j = 0; j < edges.length; j++) {
                if (edges[min.idx][j] != 0 && !contained[j] && edges[min.idx][j] < distances[j]) {
                    distances[j] = edges[min.idx][j];
                }
            }
        }

        return totalDist;
    }

}
