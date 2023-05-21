import java.util.*;
/**
 * Scale sucht alle Gewichtskombinationen, um einen Artikel zu wiegen
 * ==================================================================
 * Hausaufgabe 09: Backtracking / Tiefensuche
 * Algorithmen und Datenstrukturen, SoSe 2023
 * Aufgaben vom 15.05.2023
 * Abgabe der Loesungen am 21.05.2023
 *
 * @author Samuel Thesing, samuel.thesing@rwth-aachen.de
 * @author Christian Rene Thelen, christian.thelen@rwth-aachen.de
 * @author Michael Conrads, michael.conrads@rwth-aachen.de
 */
public class Scale {
    //weights enthält alle verfügbaren Gewichte.
    private static final int[] weights = {1, 3, 8, 20};
    
    //usedWeights enthält alle bereits genutzten Gewichte innerhalb eines Rekursionsaufrufs
    private static final Stack<Integer> usedWeights = new Stack<>();
    
    // solutions enthaelt Listen von aller Loesungen, welche die möglichen Gewichtskombinationen speichern.
    private static final Set<List<Integer>> solutions = new HashSet<>();
    
    /**
     * ruft die Rekursionsvariante mit input von balance auf und ermittelt alle möglichen Lösungen.
     * Dann werden die Lösungen ausgegeben
     * @param input Gewicht des zu wiegenden Artikels
     * @return true bei korrekten input, false bei falschem input
     */
    public static boolean balance(int input){
        solutions.clear();
        usedWeights.clear();

        if(input > Arrays.stream(weights).sum() || input < 0){
            System.out.println("Keine Lösung möglich");
            return false;
        }

        balance(0, input, 0);
        printSolutions();
        return true;
    }
    
    /**
     * sucht mithilfe von Backtracking Rekursiv alle möglichen Lösungen für targetWeight.
     * @param weightIndex nächstes Gewicht
     * @param targetWeight gesuchtes Gewicht
     * @param currentScaleWeight aktuelles Gewicht auf der Waage
     */
    private static void balance(int weightIndex, int targetWeight, int currentScaleWeight) {
        if (Math.abs(currentScaleWeight) == targetWeight) {
            solutions.add(usedWeights.stream().sorted(Comparator.comparing(Math::abs)).toList());
        }
        
        if (weightIndex >= weights.length) {
            return;
        }
        
        //Gewicht wird auf die linke Seite gelegt
        usedWeights.push(weights[weightIndex]);
        balance(weightIndex+1, targetWeight, currentScaleWeight + weights[weightIndex]);
        usedWeights.pop();
        
        //Gewicht wird auf die rechte Seite gelegt
        usedWeights.push(-weights[weightIndex]);
        balance(weightIndex+1, targetWeight, currentScaleWeight - weights[weightIndex]);
        usedWeights.pop();
        
        //Gewicht wird ignoriert
        balance(weightIndex+1, targetWeight, currentScaleWeight);
    }
    
    /**
     * Gibt alle Lösungen auf der Konsole aus
     */
    private static void printSolutions(){
        System.out.println("Mögliche Kombinationen sind: ");
        for (List<Integer> solution: solutions) {
            System.out.println(solution);
        }
    }
}
