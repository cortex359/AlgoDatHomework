/**
 * BracketsTest zum Testen der Brackets Klasse.
 * ==========================================
 * Hausaufgabe 01: Brackets
 * Algorithmen und Datenstrukturen, SoSe 2023
 * Aufgaben vom 20.03.2023
 * Abgabe der Lösungen am 27.03.2023
 *
 * @author Samuel Thesing, samuel.thesing@rwth-aachen.de
 * @author Christian Rene Thelen, christian.thelen@rwth-aachen.de
 * @author Michael Conrads, michael.conrads@rwth-aachen.de
 */
public class BracketsTest {
    public static void main(String[] args) {
        String[] tests = new String[] {
            "(([[]]))", "([][](()()))", "([{{{}}()[]}])",
            "([)]", "([]])", "(()))", "(()", "({[])}",
            ""
        };

        System.out.printf("%16s    %-5s\n", "Ausdruck", "isValid()");
        System.out.println("=============================");
        for (String t : tests) {
            System.out.printf("%16s -> %-5s\n", t, Boolean.toString(Brackets.isValid(t)));
        }
    }
}
