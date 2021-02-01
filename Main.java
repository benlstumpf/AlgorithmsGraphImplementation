public class Main {
    public static void main(String[] args) {
        Graph g = new Graph(4, 0.6);
        System.out.println("The graph is");
        System.out.println( g.toString());
        System.out.println("It had " + g.numberOfVertices() + " vertices and "
                + g.numberOfEdges() + " edges.");
    }
}
