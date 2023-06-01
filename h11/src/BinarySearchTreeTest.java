public class BinarySearchTreeTest {
    public static void main(String[] args) {

        System.out.println("Erzeugt einen Suchbaum mit ganzzahligen Schlüsseln...");
        BinarySearchTree tree = new BinarySearchTree();
        for (int i = 0; i < 20; i++) {
            int x = (int) (Math.random() * 50);
            System.out.printf("%d ", x);
            tree.insert(x);
        }
        System.out.println();

        System.out.println("Prüfe mittels contains() ob die ersten 50 Zahlen im Baum enthalten sind.");
        for (int i = 0; i < 50; i++) {
            System.out.println(i + ": " + tree.contains(i));
        }
    }
}
