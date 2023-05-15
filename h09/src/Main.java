import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            int articleWeight = Integer.parseInt(reader.readLine());
            ArrayList<ArrayList<Integer>> solutions = Solver.solveProblem(articleWeight);
            for (ArrayList<Integer> solution: solutions) {
                System.out.println(solution);
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
