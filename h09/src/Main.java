import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args){
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            int articleWeight;
            do {
                System.out.println("Geben Sie ein Gewicht zwischen 0 und 32 ein:");
                articleWeight = Integer.parseInt(reader.readLine());
                System.out.println("Gewicht des Artikels: " + articleWeight);
            } while(!Solver.backtracking(articleWeight));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
