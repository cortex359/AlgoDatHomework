import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args){
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            int articleWeight = Integer.parseInt(reader.readLine());
            if(articleWeight == 0)
                System.out.println("Die Waage bleibt leer.");
            Solver.backtracking(articleWeight);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
