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
     * Testet das Damenproblem fuer Schachbretter bestimmter Groessen.
     * @param args Komandozeilenparameter, welche ignoriert werden.
     */
    public static void main(String[] args) {
        damenProblem(6);
        damenProblem(8);
        // damenProblem(14); // Ausgabe ist 365.596 Zeilen lang

        solutionOverview(14);
    }

    /**
     * Loesung der Aufgabe 1: Bestimmt die Loesungen des Damen-Problems zu einem Schachbrett mit uebergebener Groesse mit
     * Backtracking und gib diese aus.
     * @param brettgroesse Groesse des Schachbretts.
     */
    public static void damenProblem(int brettgroesse) {
        Schachbrett sb = new Schachbrett(brettgroesse);
        sb.placeQueens();
        sb.printAll();
    }

    /**
     * solutionOverview(int n) kann an Stelle von damenProblem(int brettgroesse) verwendet werden, um eine schoenere
     * und verkuerzte Uebersicht der Testcases zu erhalten.
     * @param n Groesse des Schachbretts.
     */
    public static void solutionOverview(int n) {
        Schachbrett sb = new Schachbrett(n);
        sb.placeQueens();
        sb.printSolutionOverview(n < 10);
    }
}
