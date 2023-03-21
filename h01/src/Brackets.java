import java.util.ArrayDeque;
import java.util.Set;

/**
 * Brackets stellt die Funktion isValid bereit.
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
public class Brackets {

    private static char invert(char c) {
        return switch (c) {
            case '[' -> ']';
            case '(' -> ')';
            case '{' -> '}';
            default -> '-';
        };
    }

    /**
     * isValid überprüft einen String, bestehend aus Klammern auf Gültigkeit.
     * @param s Zu überprüfender Ausdruck.
     * @return True, wenn valide, ansonsten false.
     */
    public static boolean isValid(String s) {
        Set<Character> open = Set.of('(', '[', '{');
        Set<Character> close = Set.of(')', ']', '}');

        ArrayDeque<Character> stack = new ArrayDeque<>();
        for (var c :  s.toCharArray()) {
            if (open.contains(c)) {
                stack.push(c);
            } else if (close.contains(c)) {
                if (stack.isEmpty() || invert(stack.pop()) != c)
                    return false;
            }
        }
        return (stack.isEmpty());
    }
}
