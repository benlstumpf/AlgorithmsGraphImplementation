import java.util.LinkedList;


/**
 * The representation of a graph.
 * The graph is stored as an array of adjacency lists, one for
 * each vertex in the graph. The array is zero-based.
 * FIXME: indices for vertex numbers are not checked in addEdge nor getAdjacenciesOfVertex
 * FIXME: edge probability is not checked to be between 0.0 and 1.0
 * @author Erik Steinmetz
 */
public class Graph {

    //////////////////////////////////////
    //        Instance Variables        //
    //////////////////////////////////////

    /** The array of adjacency lists. */
    private LinkedList[] adjacencies;

    //////////////////////////////////////
    //            Constructors          //
    //////////////////////////////////////

    /**
     * Creates a Graph with the given number of vertices.
     * @param The number of vertices to include in the graph.
     */
    public Graph( int numberOfVertices) {
        adjacencies = new LinkedList[numberOfVertices];
        for( int index = 0; index < adjacencies.length; index++) {
            adjacencies[index] = new LinkedList();
        }
    }

    /**
     * Creates a Graph with the given number of vertices.
     * Edges are added with any given possible edge added at the
     * given probability.
     * @param numberOfVertices The number of vertices to include in the graph.
     * @param edgeProbability The probability of creating a possible edge.
     */
    public Graph( int numberOfVertices, double edgeProbability) {
        adjacencies = new LinkedList[numberOfVertices];
        for( int index = 0; index < adjacencies.length; index++) {
            adjacencies[index] = new LinkedList();
            for( int destination = 0; destination < numberOfVertices; destination++) {
                if( Math.random() < edgeProbability) {
                    addEdge( index, destination);
                }
            }
        }
    }

    //////////////////////////////////////
    //               Methods            //
    //////////////////////////////////////

    /**
     * Returns the number of vertices in this graph.
     * @return The number of vertices in this graph.
     */
    public int numberOfVertices() {
        return adjacencies.length;
    }

    /**
     * Returns the number of edges in this graph.
     * @return The number of edges in this graph.
     */
    public int numberOfEdges() {
        int answer = 0;
        for( LinkedList edgeList : adjacencies) {
            answer += edgeList.size();
        }
        return answer;
    }

    /**
     * Adds an edge to the graph.
     * @param from The source vertex of the edge to be added.
     * @param to The destination vertex of the edge to be added.
     */
    public void addEdge( int from, int to) {
        LinkedList aList = (LinkedList)adjacencies[from];
        aList.add( new Integer( to));
    }

    /**
     * Returns the adjacency list for the given vertex.
     * @param source The source vertex.
     * @return A LinkedList of Integers, one for each destination vertex of an edge.
     */
    public LinkedList getAdjacenciesOfVertex( int source) {
        return ((LinkedList)adjacencies[source]);
    }

    /**
     * Returns a string representation of this graph.
     * Each vertex is on its own line, followed by a list of vertices
     * which are the targets of edges from this vertex.
     * @return The String representation of the graph, in adjacency list format.
     */
    @Override
    public String toString() {
        String answer = "";
        for( int index = 0; index < adjacencies.length; index++) {
            answer += index + ": " + adjacencies[index].toString() + "\n";
        }
        return answer;
    }
}
