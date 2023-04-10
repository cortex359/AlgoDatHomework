public class LinkedListTest {

    public static void main(String[] args) {
        LinkedList list = new LinkedList();

        System.out.println("Teste insertAt und removeAt von LinkedList bei leerer Liste mit invaliden Argumenten:");
        try {
            list.insertAt(1, 1);
            System.out.printf("  %-18s -> should have thrown an exception\n",  "insertAt(1,1)");
        } catch (ArrayIndexOutOfBoundsException ignored) {
            System.out.printf("  %-18s -> threw exception as expected\n",  "insertAt(1,1)");
        }

        try {
            list.removeAt(0);
            System.out.printf("  %-18s -> should have thrown an exception\n",  "removeAt(0)");
        } catch (ArrayIndexOutOfBoundsException ignored) {
            System.out.printf("  %-18s -> threw exception as expected\n",  "removeAt(0)");
        }

        System.out.println("\nFuege Elemente am Anfang und Ende der Liste hinzu.");
        for (int i = 0; i < 5; i++) {
            list.insertAt(0, 4-i);
        }
        for (int i = 5; i < 10; i++) {
            list.insertAt(i, i);
        }
        System.out.println("\nTeste getAt, removeAt & search:");
        System.out.printf("  %-18s -> %d\n", "list.count()", list.count());          // 10
        System.out.printf("  %-18s -> %d\n", "list.getAt(5)", list.getAt(5));    // 5
        list.removeAt(5);
        System.out.println("  list.removeAt(5)");
        System.out.printf("  %-18s -> %d\n", "list.count()", list.count());          // 9
        System.out.printf("  %-18s -> %d\n", "list.getAt(5)", list.getAt(5));    // 6
        System.out.printf("  %-18s -> %d\n", "list.search(6)", list.search(6)); // 5

        System.out.println("\nTeste insertAt und removeAt mit invaliden Argumenten mit gefuellter Liste:");
        try {
            list.removeAt(10);
            System.out.printf("  %-18s -> should have thrown an exception\n",  "removeAt(10)");
        } catch (ArrayIndexOutOfBoundsException ignored) {
            System.out.printf("  %-18s -> threw exception as expected\n",  "removeAt(10)");
        }
        try {
            list.insertAt(-1, 1);
            System.out.printf("  %-18s -> should have thrown an exception\n",  "insertAt(-1, 1)");
        } catch (ArrayIndexOutOfBoundsException ignored) {
            System.out.printf("  %-18s -> threw exception as expected\n",  "insertAt(-1, 1)");
        }

        System.out.println("\nTeste clear:");
        System.out.println("  list.clear()");
        list.clear();
        System.out.printf("  %-18s -> %d\n", "list.count()", list.count());     // 0
    }

}
