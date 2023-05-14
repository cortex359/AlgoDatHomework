import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Schachbrett sucht Lösungen für das Damen Problem
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
    // solutions enthält eine Liste aller Lösungen in Form von ArrayListen, welche spaltenweise die Zeilennummer angeben,
    //           in der sich die Dame befindet.
    private ArrayList<ArrayList<Integer>> solutions;

    private Deque<ArrayList<Integer>> partialSolutions;
    private int size;

    // SHOW_COORDINATES legt fest, ob bei der Visualisierung die Koordinaten des Schachbetts angezeigt werfen.
    //                  (funktioniert nur für n <= 26)
    private final boolean SHOW_COORDINATES = true;
    public Schachbrett(int n) {
        this.size = n;
        this.solutions = new ArrayList<ArrayList<Integer>>();
        this.partialSolutions = new ArrayDeque<>();
    }

    public void platziereDamen() {
        for (int col=0; col < this.size; col++) {
            ArrayList<Integer> ps = new ArrayList<>();
            ps.add(col);
            this.partialSolutions.add(ps);
        }
        while (!this.partialSolutions.isEmpty()) {
            ArrayList<Integer> ps = this.partialSolutions.pop();
            for (int row=1; row <= this.size; row++) {
                if (isPosValid(row, ps)) {
                    ps.add(row);
                    this.partialSolutions.add(ps);
                }
            }
            addQueen(ps);
        }
    }

    public void addQueen(int[] ps1, int col) {
        int[] potentialSolution = ps1.clone();
        if (col >= this.size) {
            for (var c : potentialSolution) {
                System.out.printf("%s", c);
            }
            System.out.println();
        } else {
            for (int row = 1; row <= this.size; row++) {
                if (isPosValid(row, potentialSolution)) {
                    potentialSolution[col] = row;
                    addQueen(potentialSolution, col + 1);
                }
            }
        }
    }

    /**
     * isPosValid prüft, ob in einer übergebenen Zeile der nächsten Spalte einer übergebenen Lösung eine Dame plaziert
     * werden kann.
     * @param row Nummer der Zeile, die Geprüft werden soll. Beginnend ab 1.
     * @param partialSolution Spaltenweise Liste der Zeilen, in denen bereits eine Dame gesetzt wurde.
     * @return True, wenn eine Dame plaziert werden kann. False, wenn die Dame geschlagen werden würde.
     */
    private boolean isPosValid(int row, ArrayList<Integer> partialSolution) {
        // Spalten müssen nicht geprüft werden, da die Lösung spaltenweise aufgebaut wird.
        int col = partialSolution.size() + 1;

        // Prüfe Zeilen:
        if (partialSolution.contains(row))
            return false;

        // Prüfe Diagonale:
        for (int i = 1; i <= partialSolution.size(); i++) {
            int j = partialSolution.get(i - 1);
            if (Math.abs(row - i) == Math.abs(col - j))
                return false;
        }

        return true;
    }
    private boolean isPosValid(int row, int[] partialSolution) {
        List<Integer> pS = Arrays.stream(partialSolution).boxed().collect(Collectors.toList());
        return isPosValid(row, (ArrayList<Integer>) pS);
    }

    public void visulizeSolution(int solutionIndex) {
        // Zeilenausgabe prüft auch IndexInBounds
        printSolution(solutionIndex);

        // Lade Lösung
        ArrayList<Integer> solution = this.solutions.get(solutionIndex);

        // (i,j) -> (Zeile, Spalte)
        for (int i = this.size - 1; i >= 0 ; i--) {
            if (SHOW_COORDINATES && this.size <= 26)
                System.out.printf("%2d ", i+1);

            // Zeilennummerierung beginnt bei 1
            for (int j = 0; j < this.size; j++) {
                int rowWithQueen = solution.get(j) - 1;
                // u2655 weiße Dame, u265B schwarze Dame
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
    }

    public void printSolution(int solutionIndex) {
        if (solutionIndex >= this.solutions.size())
            throw new IndexOutOfBoundsException("Es sind bislang nur " + this.solutions.size() + " Lösungen gefunden worden.");
        if (solutionIndex < 0)
            throw new IndexOutOfBoundsException("Der Index der Lösung muss positiv sein.");

        ArrayList<Integer> solution = this.solutions.get(solutionIndex);

        System.out.printf("\u265B in den Zeilen %s\n", solution);
    }

    public void printSolutions() {
        for (int i=0; i < this.solutions.size(); i++) {
            printSolution(i);
        }
    }

}
