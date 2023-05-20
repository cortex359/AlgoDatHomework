import java.util.*;

public class Solver {
    
    private static final int[] _weights = {1, 3, 8, 20};
    private static final Stack<Integer> usedWeights = new Stack<>();
    
    private static final ArrayList<Integer> possibleSolution = new ArrayList<>();
    private static final ArrayList<ArrayList<Integer>> solutions = new ArrayList<>();
    
    public static void backtracking(int input){
        if(input > Arrays.stream(_weights).sum() || input < 0)
            System.out.println("Keine Lösung möglich");
        backtracking(_weights, input, 0);
        solutions.clear();
    }
    private static void backtracking(int[] weights, int targetWeight, int currentScaleWeight) {
        if (Math.abs(currentScaleWeight) == targetWeight) {
            Solver.print();
        }
        
        if (weights.length == 0) {
            return;
        }
        int[] remainingWeights = new int[weights.length - 1];
        System.arraycopy(weights, 1, remainingWeights, 0, weights.length - 1);
        
        //Gewicht wird auf die linke Seite gelegt
        usedWeights.push(weights[0]);
        backtracking(remainingWeights, targetWeight, currentScaleWeight + weights[0]);
        usedWeights.pop();
        
        //Gewicht wird auf die rechte Seite gelegt
        usedWeights.push(-weights[0]);
        backtracking(remainingWeights, targetWeight, currentScaleWeight - weights[0]);
        usedWeights.pop();
        
        //Gewicht wird ignoriert
        backtracking(remainingWeights, targetWeight, currentScaleWeight);
    }
    
    private static void print(){
        Stack<Integer> tmp = new Stack<>();
        while (!usedWeights.empty()){
            possibleSolution.add(usedWeights.peek());
            tmp.push(usedWeights.pop());
        }
        while (!tmp.empty())
            usedWeights.push(tmp.pop());
        
        possibleSolution.sort(Comparator.comparingInt(Math::abs));
        if(isValidSolution()) {
            System.out.println(possibleSolution);
            solutions.add(new ArrayList<>(possibleSolution));
        }
        possibleSolution.clear();
    }
    
    private static boolean isValidSolution(){
        for (ArrayList<Integer> solution : solutions) {
            if(solution.equals(possibleSolution))
                return false;
            for (int i = 0; i < possibleSolution.size()-1; i++)
                if(Math.abs(possibleSolution.get(i)) == Math.abs(possibleSolution.get(i+1)))
                    return false;
        }
        return true;
    }
}
