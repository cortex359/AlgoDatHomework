import java.util.ArrayList;
import java.util.Arrays;

public class Solver {
    
    private static final int[] weights = {0,1, 3, 8, 20};
    private static final ArrayList<ArrayList<Integer>> solutions = new ArrayList<>();
    private static final ArrayList<Integer> currentSolution = new ArrayList<>();
    
    public static ArrayList<ArrayList<Integer>> solveProblem(int input) {
        if(input > Arrays.stream(weights).sum() || input < 0)
            System.out.println("Keine Lösung möglich");
        backtracking(input);
        return solutions;
    }
    
    public static void backtracking(int searchedDiff) {
        solveRecursive(searchedDiff, weights[1], 2);
        if(hasValidSolution()){
            solutions.add(new ArrayList<>(currentSolution));
            currentSolution.clear();
        }
        
        solveRecursive(searchedDiff, weights[0],2);
        if(hasValidSolution()){
            solutions.add(new ArrayList<>(currentSolution));
            currentSolution.clear();
        }
        solveRecursive(searchedDiff, -weights[1], 2);
        if(hasValidSolution()){
            solutions.add(new ArrayList<>(currentSolution));
            currentSolution.clear();
        }
    }
    
    public static void solveRecursive(int currentDiff, int currentWeight, int indexOfNextWeight){
        if(currentWeight == 0){
            solveRecursive(currentDiff, weights[indexOfNextWeight], indexOfNextWeight+1);
        }
        currentDiff -= currentWeight ;
        currentSolution.add(currentWeight);
        if(Math.abs(currentWeight) != weights[3]){
            if(currentDiff > 0) {
                solveRecursive(currentDiff, weights[indexOfNextWeight], indexOfNextWeight+1);
            } else if (currentDiff < 0) {
                solveRecursive(currentDiff, -weights[indexOfNextWeight], indexOfNextWeight+1);
            }
        }
    }
    
    private static boolean hasValidSolution(){
        if(currentSolution.isEmpty())
            return false;
        for(int i = 0; i < currentSolution.size()-1; i++){
            if(currentSolution.get(i) > currentSolution.get(i+1))
                return false;
        }
        return true;
    }
}
