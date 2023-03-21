import java.util.ArrayDeque;
import java.util.Set;

/**
 *
 */
public class Brackets {

    private static char invert(char c) {
        switch (c) {
            case '[':
                return ']';
            case '(':
                return ')';
            case '{':
                return '}';
        }
        return '-';
    }

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

    public static void main(String[] args) {
        if (isValid("(([[]]))"))
            System.out.printf("(([[]]))     true\n");
        if (isValid("([][](()()))"))
            System.out.printf("([][](()())) true\n");
        if (isValid("([)]"))
            System.out.printf("([)]     false");
        if (isValid("([]])"))
            System.out.printf("([]])    false");
        if (isValid("(()))"))
            System.out.printf("(()))    false");
        if (isValid("(()"))
            System.out.printf("(()      false");
        if (isValid("({[])}"))
            System.out.printf("({[])}   false");
        if (isValid(""))
            System.out.printf("Gar keine Klammern   true\n");
    }
}
