/**
 * SchachbrettTest testet die Klasse Schachbrett
 * =============================================
 * Hausaufgabe 08: Backtracking / Tiefensuche
 * Algorithmen und Datenstrukturen, SoSe 2023
 * Aufgaben vom 08.05.2023
 * Abgabe der Loesungen am 14.05.2023
 *
 * @author Samuel Thesing, samuel.thesing@rwth-aachen.de
 * @author Christian Rene Thelen, christian.thelen@rwth-aachen.de
 * @author Michael Conrads, michael.conrads@rwth-aachen.de
 */
public class SchachbrettTest {

    /**
     * Testet das Damenproblem für Schachbretter bestimmter Größen.
     * @param args
     */
    public static void main(String[] args) {
        damenProblem(6);
        damenProblem(8);
        damenProblem(14);
    }

    /**
     * Lösung der Aufgabe 1: Bestimmt die Lösungen des Damen-Problems zu einem Schachbrett mit übergebener Größe mit
     * Backtracking und gib diese aus.
     * @param brettgroesse
     */
    public static void damenProblem(int brettgroesse) {
        Schachbrett sb = new Schachbrett(brettgroesse);
        sb.placeQueens();
        if (brettgroesse < 10) {
            sb.printSolutions(true);
        } else {
            sb.printSolutions(false);
        }
    }
}
