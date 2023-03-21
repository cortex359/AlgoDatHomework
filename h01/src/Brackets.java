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
                stack.push(c);
                continue;
            }
            if (c == '}') {
                if (stack.size() == 0 || stack.peek() != '{') {
                    return false;
                }
                stack.pop();
            }
            if (c == ']') {
                if (stack.size() == 0 || stack.peek() != '[') {
                    return false;
                }
                stack.pop();
            }
            if (c == ')') {
                if (stack.size() == 0 || stack.peek() != '(') {
                    return false;
                }
                stack.pop();
            }
        }
        return stack.size() == 0;
    }
}
