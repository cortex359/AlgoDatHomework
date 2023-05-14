import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Schachbrett sucht Loesungen fuer das Damen Problem
 * ================================================
 * Hausaufgabe 08: Backtracking / Tiefensuche
 * Algorithmen und Datenstrukturen, SoSe 2023
 * Aufgaben vom 08.05.2023
 * Abgabe der Loesungen am 14.05.2023
 *
 * @author Samuel Thesing, samuel.thesing@rwth-aachen.de
 * @author Christian Rene Thelen, christian.thelen@rwth-aachen.de
 * @author Michael Conrads, michael.conrads@rwth-aachen.de
 */
public class Schachbrett {
    // solutions enthaelt eine Liste aller Loesungen in Form von ArrayListen, welche spaltenweise die Zeilennummer angeben,
    //           in der sich die Dame befindet.
    private ArrayList<ArrayList<Integer>> solutions;

    // size speichert die Groesse des n x n Schachbretts an
    private final int size;

    // SHOW_COORDINATES legt fest, ob bei der Visualisierung die Koordinaten des Schachbetts angezeigt werfen.
    //                  (funktioniert nur fuer n <= 26)
    private final boolean SHOW_COORDINATES = true;

    /**
     * Erzeugt ein neues Schachbrett und legt die Liste fuer moegliche Loesungen des Damenproblems an.
     * @param n Groesse des quadratischen Schachbretts.
     */
    public Schachbrett(int n) {
        this.size = n;
        this.solutions = new ArrayList<ArrayList<Integer>>();
    }

    /**
     * Erzeugt ein Schachbrett mit der Standardgroesse 8 x 8.
     */
    public Schachbrett() {
        this(8);
    }

    /**
     * Errechnet alle Loesungen des Damen-Problems fuer das Schachbrett
     */
    public void placeQueens() {
        ArrayList<Integer> partialSolution = new ArrayList<>();
        this.addQueen(partialSolution);
    }

    /**
     * Rekursive Funktion, welche einer uebergebenen Teilloesung weitere Spalten hinzufuegt.
     * @param partialSolution Liste der bereits platzierten Damen in den vorangegangenen Spalten.
     */
    public void addQueen(ArrayList<Integer> partialSolution) {
        if (partialSolution.size() == this.size) {
            // Neue Loesung der Liste der Loesungen hinzufuegen.
            this.solutions.add(partialSolution);
        } else {
            // Loesungen fuer alle moeglichen Damen in Pre-Order erkunden:
            for (int row = 1; row <= this.size; row++) {
                if (isPosValid(row, partialSolution)) {
                    ArrayList<Integer> newPartialSolution = (ArrayList<Integer>) partialSolution.clone();
                    newPartialSolution.add(row);
                    addQueen(newPartialSolution);
                }
            }
        }
    }

    /**
     * isPosValid prueft, ob in einer uebergebenen Zeile (row) der naechsten Spalte einer uebergebenen Loesung
     * (partialSolution) eine Dame platziert werden kann.
     * @param row Nummer der Zeile, die Geprueft werden soll. Beginnend ab 1.
     * @param partialSolution Spaltenweise Liste der Zeilen, in denen bereits eine Dame gesetzt wurde.
     * @return True, wenn eine Dame plaziert werden kann. False, wenn die Dame geschlagen werden wuerde.
     */
    private boolean isPosValid(int row, ArrayList<Integer> partialSolution) {
        // Spalten muessen nicht geprueft werden, da die Loesung spaltenweise aufgebaut wird.
        int col = partialSolution.size() + 1;

        // Pruefe Zeilen:
        if (partialSolution.contains(row))
            return false;

        // Pruefe Diagonale:
        for (int i = 1; i <= partialSolution.size(); i++) {
            int j = partialSolution.get(i - 1);
            if (Math.abs(col - i) == Math.abs(row - j))
                return false;
        }

        return true;
    }

    /**
     * Visualisiert eine der gefundenen Loesungen des Damenproblems auf einem Schachbrett.
     * @param solutionIndex Index der gefundenen Loesung beginnend ab 0.
     */
    public void visulizeSolution(int solutionIndex) {
        System.out.printf("\nVisualisierung der Loesung %d/%d\n", solutionIndex + 1, this.solutions.size());

        // Zeilenausgabe prueft auch IndexInBounds
        printSolution(solutionIndex);

        // Lade Loesung
        ArrayList<Integer> solution = this.solutions.get(solutionIndex);

        // (i,j) -> (Zeile, Spalte)
        for (int i = this.size - 1; i >= 0 ; i--) {
            if (SHOW_COORDINATES && this.size <= 26)
                System.out.printf("%2d ", i+1);

            // Zeilennummerierung beginnt bei 1
            for (int j = 0; j < this.size; j++) {
                int rowWithQueen = solution.get(j) - 1;
                // u2655 weisse Dame, u265B schwarze Dame
                System.out.printf("[%c]", (i == rowWithQueen) ? '\u265B' : ' ');
            }

            System.out.printf("\n");
        }

        if (SHOW_COORDINATES && this.size <= 26) {
            System.out.printf("   ");
            for (int i = 0; i < this.size; i++) {
                System.out.printf(" %c ", (char) i + 65);
            }
        }

        System.out.printf("\n");
    }

    /**
     * Gibt eine Loesung als Zeilennummer der Dame in Reihenfolge der Spalten aus.
     * @param solutionIndex Index der gefundenen Loesung beginnend ab 0.
     */
    public void printSolution(int solutionIndex) {
        if (solutionIndex >= this.solutions.size())
            throw new IndexOutOfBoundsException("Es sind bislang nur " + this.solutions.size() + " Loesungen gefunden worden.");
        if (solutionIndex < 0)
            throw new IndexOutOfBoundsException("Der Index der Loesung muss positiv sein.");

        ArrayList<Integer> solution = this.solutions.get(solutionIndex);

        System.out.printf("\u265B in den Zeilen %s\n", solution);
    }

    /**
     * Gibt die ersten 8 Loesungen aus.
     */
    public void printSolutions(boolean first) {
        int showMax = 4;

        if (this.solutions.size() == 0) {
            System.out.println("Es wurden keine Loesungen gefunden.");
        } else {
            System.out.printf("\nFuer ein Schachbrett der Groesse %dx%d wurden %d Loesungen des Damenproblems gefunden.\n",
                    this.size, this.size, this.solutions.size());

            if (this.solutions.size() > showMax)
                System.out.printf("Es werden die %s %d Loesungen angezeigt:\n", first? "ersten" : "letzten", showMax);

            if (this.solutions.size() > showMax && !first)
                System.out.println("...");

            for (int i = first? 0 : this.solutions.size()-showMax; i < this.solutions.size() && (!first || i < showMax); i++) {
                printSolution(i);
            }

            if (this.solutions.size() > showMax && first)
                System.out.println("...");

            // Gebe Visualisierung der ersten/letzten Loesung aus
            if (first)
                visulizeSolution(0);
            else
                visulizeSolution(this.solutions.size() - 1);
        }
    }

}
