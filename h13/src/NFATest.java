public class NFATest {

    public static void main(String[] args) {
        System.out.println("1. test:");
        NFA nfa1 = new NFA("3,3, 1,2,a, 1,3,a, 2,2,a, 2,2,b, 2,3,a"); // a|a[ab]*a
        System.out.println(nfa1.testString("abba")); //true
        System.out.println(nfa1.testString("a")); //true
        System.out.println(nfa1.testString("ab")); //false

        System.out.println("\n2. test:");
        NFA nfa2 = new NFA("3,3, 1,2,a, 2,2,b, 2,3,a"); // ab*a
        System.out.println(nfa2.testString("aa")); // true
        System.out.println(nfa2.testString("abbbbbbba")); // true
        System.out.println(nfa2.testString("a")); // false
        System.out.println(nfa2.testString("abaa")); // false

        System.out.println("\n3. test:");
        NFA nfa3 = new NFA("1,1");
        System.out.println(nfa3.testString("")); // true
        System.out.println(nfa3.testString("a")); // false
    }

}
