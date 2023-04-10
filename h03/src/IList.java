/**
 * IList spezifiziert das Interface eines Datencontainers
 * ======================================================
 * Hausaufgabe 03: Verkettete Listen
 * Algorithmen und Datenstrukturen, SoSe 2023
 * Aufgaben vom 03.04.2023
 * Abgabe der Loesungen am 10.04.2023
 *
 * @author Samuel Thesing, samuel.thesing@rwth-aachen.de
 * @author Christian Rene Thelen, christian.thelen@rwth-aachen.de
 * @author Michael Conrads, michael.conrads@rwth-aachen.de
 */
public interface IList {

    /**
     * Fuegt einen Wert an einer bestimmten Position ein.
     * @param pos Position.
     * @param value Einzufuegender Wert.
     */
    void insertAt(int pos, int value);

    /**
     * Loescht einen Wert an einer bestimmten Position.
     * @param pos Position.
     */
    void removeAt(int pos);

    /**
     * Liest einen Wert an einer bestimmten Position aus.
     * @param pos Position.
     * @return Wert an der Position.
     */
    int getAt(int pos);

    /**
     * Sucht nach einem Wert und gibt die Position zurueck.
     * @param value Suchwort.
     * @return Position.
     */
    int search(int value);

    /**
     * Loescht alle Daten.
     */
    void clear();

    /**
     * Gibt die Anzahl der enthaltenen Elemente zurueck.
     * @return Anzahl der Elemente.
     */
    int count();
}
