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
    public static void main(String[] args) {
        damenProblem(3);
    }

    public static void damenProblem(int brettgroesse) {
        Schachbrett sb = new Schachbrett(brettgroesse);
        sb.platziereDamen();
        //sb.visulizeSolution(0);
    }
}
