package endlicher_automat;

import endlicher_automat.pattern.PatternNode;
import endlicher_automat.pattern.RangePatternNode;
import endlicher_automat.pattern.SimplePatternNode;
import endlicher_automat.pattern.WildcardPatternNode;

import java.util.regex.PatternSyntaxException;

public class PatternConstructor {

    private final String pattern;
    private int index = 0;

    private final PatternNode startNode;

    public PatternConstructor(String pattern) {
        if (pattern.isEmpty()) {
            throw new PatternSyntaxException("Empty pattern is not allowed", pattern, -1);
        }

        this.pattern = pattern;
        startNode = getNextPatternNode();
        index++;
        PatternNode curNode = startNode;
        while (index < pattern.length()) {
            PatternNode node = getNextPatternNode();
            curNode.setNext(node);
            curNode = node;
            index++;
        }
    }

    private PatternNode getNextPatternNode() {
        char c = pattern.charAt(index);

        if (c == '[') {
            int endingBracket = pattern.indexOf(']', index+1);
            if (endingBracket == -1) {
                throw new PatternSyntaxException("Missing closing bracket in pattern", pattern, index);
            }

            if (endingBracket == index+1) {
                throw new PatternSyntaxException("Empty range not allowed", pattern, index);
            }

            String rangeString = pattern.substring(index+1, endingBracket);
            index = endingBracket;
            return new RangePatternNode(rangeString);
        }

        if (c == '\\') {
            index++;
            return new SimplePatternNode(pattern.charAt(index));
        }

        if (c == '.') {
            return new WildcardPatternNode();
        }

        return new SimplePatternNode(c);
    }

    public PatternNode getStartNode() {
        return startNode;
    }

}
