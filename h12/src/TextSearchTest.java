
public class TextSearchTest {
    public static void main(String[] args){
        System.out.println(TextSearch.textSearch("AJHGAKGGADGASAG", "GA"));
        System.out.println(TextSearch.textSearch("agdhGHAJGVAGÖOLJR", "[aB].dh"));
        System.out.println(TextSearch.textSearch("Hi", "[Hi"));
    }
}
