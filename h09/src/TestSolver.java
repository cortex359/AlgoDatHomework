public class TestSolver {
    public static void main(String[] args){
        for(int i = 0; i < 34; i++){
            System.out.println("Gewicht des Artikels: " + i);
            Solver.backtracking(i);
        }
    }
}
