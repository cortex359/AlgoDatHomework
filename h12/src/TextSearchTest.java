import endlicher_automat.TextSearchWithAutomaton;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.PatternSyntaxException;


public class TextSearchTest {
    public static void main(String[] args){
        long[] estimatedTime = new long[2];
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String text = "";
        String pattern = "";
        System.out.println("Bitte gib den Text ein.");
        try {
            text = reader.readLine();
            System.out.println("Bitte gib das Pattern ein.");
            pattern = reader.readLine();
        } catch (IOException e){
            e.printStackTrace();
        }
        try {
            estimatedTime[0] = System.nanoTime();
            System.out.println(TextSearch.textSearch(text, pattern));
            estimatedTime[0] = System.nanoTime() - estimatedTime[0];
            System.out.println("TextSearch hat " + estimatedTime[0]/Math.pow(10,6) + " ms gebraucht.");
            estimatedTime[1] = System.nanoTime();
            System.out.println(TextSearchWithAutomaton.textSearch(text, pattern));
            estimatedTime[1] = System.nanoTime() - estimatedTime[1];
            System.out.println("TextSearchWithAutomaton hat " + estimatedTime[1]/Math.pow(10,6) + " ms gebraucht.");
            long timeDifference = estimatedTime[0] - estimatedTime[1];
            if(timeDifference < 0) {
                System.out.println("TextSearch war " + Math.abs(timeDifference)/Math.pow(10,6) + " ms schneller");
            } else {
                System.out.println("TextSearchWithAutomaton war " + timeDifference/Math.pow(10,6) + " ms schneller");
            }
        } catch(PatternSyntaxException p){
            p.printStackTrace();
        }
    }
}
