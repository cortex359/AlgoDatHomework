import java.util.ArrayDeque;

/**
 *
 */
public class Brackets {
    public static boolean isValid(String s) {
        ArrayDeque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '{' || c == '[' || c == '(') {
                stack.addLast(c);
                continue;
            }
            if (c == '}') {
                if (stack.size() == 0 || stack.peekLast() != '{') {
                    return false;
                }
                stack.removeLast();
            }
            if (c == ']') {
                if (stack.size() == 0 || stack.peekLast() != '[') {
                    return false;
                }
                stack.removeLast();
            }
            if (c == ')') {
                if (stack.size() == 0 || stack.peekLast() != '(') {
                    return false;
                }
                stack.removeLast();
            }
        }
        return stack.size() == 0;
    }
}
