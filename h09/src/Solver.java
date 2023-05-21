import java.util.*;

public class Solver {
    
    private static final int[] weights = {1, 3, 8, 20};
    private static final Stack<Integer> usedWeights = new Stack<>();
    private static final ArrayList<Integer> possibleSolution = new ArrayList<>();
    private static final ArrayList<ArrayList<Integer>> solutions = new ArrayList<>();
    
    public static boolean backtracking(int input){
        if(input > Arrays.stream(weights).sum() || input < 0){
            System.out.println("Keine Lösung möglich");
            return false;
        }
        
        backtracking(0, input, 0);
        printSolutions();
        return true;
    }
    private static void backtracking(int weightIndex, int targetWeight, int currentScaleWeight) {
        if (Math.abs(currentScaleWeight) == targetWeight) {
            Solver.rememberSolution();
        }
        
        if (weightIndex >= weights.length) {
            return;
        }
        
        //Gewicht wird auf die linke Seite gelegt
        usedWeights.push(weights[weightIndex]);
        backtracking(weightIndex+1, targetWeight, currentScaleWeight + weights[weightIndex]);
        usedWeights.pop();
        
        //Gewicht wird auf die rechte Seite gelegt
        usedWeights.push(-weights[weightIndex]);
        backtracking(weightIndex+1, targetWeight, currentScaleWeight - weights[weightIndex]);
        usedWeights.pop();
        
        //Gewicht wird ignoriert
        backtracking(weightIndex+1, targetWeight, currentScaleWeight);
    }
    private static boolean isValidSolution(){
        for (ArrayList<Integer> solution : solutions) {
            if(solution.equals(possibleSolution))
                return false;
        }
        return true;
    }
    
    private static void rememberSolution(){
        possibleSolution.addAll(usedWeights.stream().toList());
        possibleSolution.sort(Comparator.comparingInt(Math::abs));
        if(isValidSolution()) {
            solutions.add(new ArrayList<>(possibleSolution));
        }
        possibleSolution.clear();
    }
    
    private static void printSolutions(){
        System.out.println("Mögliche Kombinationen sind: ");
        for (ArrayList<Integer> solution: solutions) {
            System.out.println(solution);
        }
        solutions.clear();
    }
}
