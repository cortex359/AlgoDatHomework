import java.util.ArrayList;

public class Solver {
    
    private static final int[] weights = {1, 3, 8, 20};
    
    private static final WeightsTree weightstree = new WeightsTree(weights);
    
    public static ArrayList<ArrayList<Integer>> solveProblem(int input) {
        ArrayList<ArrayList<Integer>> solutions = new ArrayList<>();
        backtracking(input, solutions);
        return solutions;
    }
    
    public static void backtracking(int searchedDiff, ArrayList<ArrayList<Integer>> solutions) {
    }
}
