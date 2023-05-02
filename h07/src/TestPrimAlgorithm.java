public class TestPrimAlgorithm {

    public static void main(String[] args) {
        int[][] am1 = { { 0, 3, 0, 2, 0, 0 },
                        { 3, 0, 2, 0, 3, 0 },
                        { 0, 2, 0, 1, 6, 0 },
                        { 2, 0, 1, 0, 0, 0 },
                        { 0, 3, 6, 0, 0, 5 },
                        { 0, 0, 0, 0, 5, 0 } };

        int[][] am2 = { { 0, 5, 4, 0, 0, 0},
                        { 5, 0, 0, 1, 2, 0},
                        { 4, 0, 0, 3, 3, 0},
                        { 0, 1, 3, 0, 0, 3},
                        { 0, 2, 3, 0, 0, 2},
                        { 0, 0, 0, 3, 2, 0} };

        int[][] am3 = { { 0 } };

        int[][] am4 = { { 0, 0 },
                        { 0, 0 } };

        int[][] am5 = { { 0, 1 },
                        { 2 } };

        int[][] am6 = { { 0, 1 },
                        { 2, 0 } };

        System.out.printf("  %-18s -> %d\n", "Kosten des Spannbaums des Beispiels vom Hausaufgabenblatt",
                PrimAlgorithm.getMST(am1));
        System.out.printf("  %-18s -> %d\n", "Kosten des Spannbaums eines Beispiels aus der Vorlesung",
                PrimAlgorithm.getMST(am2));
        System.out.printf("  %-18s -> %d\n", "Kosten eines leeren Spannbaums",
                PrimAlgorithm.getMST(am3));

        try {
            PrimAlgorithm.getMST(am4);
            System.out.println("  Should have thrown an exception because the graph is not connected");
        } catch (IllegalArgumentException ignored) {
            System.out.println("  Threw exception as expected because the graph is not connected");
        }

        try {
            PrimAlgorithm.getMST(am5);
            System.out.println("  Should have thrown an exception because the adjacencymatrix is invalid");
        } catch (IllegalArgumentException ignored) {
            System.out.println("  Threw exception as expected because the adjacencymatrix is invalid");
        }

        try {
            PrimAlgorithm.getMST(am6);
            System.out.println("  Should have thrown an exception because the graph ist directed");
        } catch (IllegalArgumentException ignored) {
            System.out.println("  Threw exception as expected because the graph is directed");
        }
    }
}
