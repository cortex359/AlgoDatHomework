import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BarcketsTest {

    @Test
    public void test() {
        assertTrue(Brackets.isValid("(([[]]))"));
        assertFalse(Brackets.isValid("([)]"));
        assertFalse(Brackets.isValid("([]])"));
        assertFalse(Brackets.isValid("(()))"));
        assertFalse(Brackets.isValid("(()"));
        assertFalse(Brackets.isValid("({[])}"));
    }

}
