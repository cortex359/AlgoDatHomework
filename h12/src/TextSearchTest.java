import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.PatternSyntaxException;

public class TextSearchTest {
    public static void main(String[] args){
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
            System.out.println(TextSearch.textSearch(text, pattern));
        } catch(PatternSyntaxException p){
            p.printStackTrace();
        }
    }
}
