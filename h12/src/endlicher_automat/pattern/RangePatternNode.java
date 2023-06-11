package endlicher_automat.pattern;

import java.util.Set;
import java.util.stream.Collectors;

public class RangePatternNode extends PatternNode{

    private final Set<Character> range;

    public RangePatternNode(String range) {
        this.range = range.chars().mapToObj(c -> (char)c).collect(Collectors.toSet());
    }

    @Override
    public boolean match(char c) {
        return range.contains(c);
    }

}
