public class DijkstraTest {

    public static void main(String[] args) {
        Dijkstra.printDijkstra(new int[]{4, 1,2,2, 1,4,5, 2,4,1, 2,3,4, 3,1,1, 4,3,1});
        System.out.println();

        Dijkstra.printDijkstra(new int[]{
            10,1,2,30,1,3,10,2,5,15,2,8,55,3,4,5,3,9,35,4,2,10,4,5,45,4,6,10,5,3,20,5,7,15,5,9,25,6,7,5,7,10,20,8,10,15,
            9,8,10,9,10,30
        });
        System.out.println();

        Dijkstra.printDijkstra(new int[]{
            6, 1,2,5, 1,3,20, 2,3,10, 2,4,50, 3,2,20, 3,5,20, 4,5,20, 4,6,20, 5,4,5, 5,6,30
        });
        System.out.println();

        Dijkstra.printDijkstra(new int[]{
            6, 1,2,20, 1,3,10, 2,4,30, 2,5,20, 3,4,10, 3,5,20, 4,6,10, 5,6,30
        });
        System.out.println();

        Dijkstra.printDijkstra(new int[]{1});

        try {
            Dijkstra.printDijkstra(new int[]{});
            throw new RuntimeException("Should have thrown an exception but nothing happened");
        } catch (IllegalArgumentException ignored) {
        }
    }

}
