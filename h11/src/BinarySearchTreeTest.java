public class BinarySearchTreeTest {

    private static BinarySearchTree2 randomTree() {
        BinarySearchTree2 tree = new BinarySearchTree2();
        for (int i = 0; i < 20; i++) {
            int x = (int) (Math.random() * 50);
            System.out.printf("%d ", x);
            tree.insert(x);
        }
        System.out.println();
        return tree;
    }

    private static BinarySearchTree2 fixedTree(int[] array) {
        BinarySearchTree2 tree = new BinarySearchTree2();
        for (int i : array) {
            tree.insert(i);
        }
        return tree;
    }

    public static void main(String[] args) {

        System.out.println("Erzeugt einen Suchbaum mit ganzzahligen Schlüsseln...");
        //BinarySearchTree2 tree = fixedTree(new int[]{34, 8, 41, 36, 12, 9, 17, 37, 2, 30, 20, 32, 19, 42, 1});
        BinarySearchTree2 tree = randomTree();
        tree.show();
        System.out.println("Gehe die Schlüssel in aufsteigender Reihenfolge durch und gebe jeweils den Mittelwert, " +
                "sowie die Summe und die Anzahl der SubTree Nodes (einschließlich der aktuellen Node) aus:");
        for (int i = 0; i < 50; i++) {
            if (tree.contains(i)) {
                System.out.printf("%2d: %5.2f (%d/%d)\n",
                        i, tree.getAverageOfSubtree(i),
                        tree.getNode(i).sumOfSubNodes, tree.getNode(i).numberOfSubNodes);
            }
        }
    }
}
