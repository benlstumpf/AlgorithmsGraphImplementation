import java.lang.Math;
import java.util.LinkedList;
//@author Ben Stumpf

class DepthFirstSearchGraph {
    /**
     * Runs the expierement
     * @param args not applicable
     */
    public static void main(String[] args) {
        int numberOfRuns = 10000;
        int maxSizeOfGraph = 500;
        int sizeIncrementingFactor = 10;
        int startingSize = 10;
        double maxEdgeProbability = 1.0;
        double startingEdgeProbibility = 0.1;
        double edgeProbabilityIncrementation = 0.1;
        System.out.println("Number of Vertices,Edge Probability,Average Edges,Average Opperations,Differance of Expected and Counted as a Ratio");
        for(double edgeProbability= startingEdgeProbibility; edgeProbability <= maxEdgeProbability; edgeProbability += edgeProbabilityIncrementation){
            for (int sizeOfGraph = startingSize; sizeOfGraph <= maxSizeOfGraph; sizeOfGraph += sizeIncrementingFactor) {
                averageRunning(numberOfRuns, sizeOfGraph, edgeProbability);
            }
        }
    }

    /**
     * Finds the log base 2 of a number. used for calculating the expected
     * average number of operations.
     * @param  a The number that is needing the log found
     * @return   returns log base 2
     */
    public static double log2(double a){
        return (Math.log10(a) / Math.log10(2));
    }

    /**
     * Runs the expierement for a data set size and prints the results
     * @param numberOfRuns controls how robust the data set is
     * @param sizeOfArray  size of data set
     */
    public static void averageRunning(int numberOfRuns, int sizeOfGraph, double edgeProbability) {
        double totalOperations = 0;
        double averageOperations = 0;
        double totalEdges = 0;
        double averageEdges = 0;
        double expectedAverageOperations;
        for (int i = 1; numberOfRuns >= i; i++) {
            Graph graph = new Graph(sizeOfGraph, edgeProbability);
            int runOpperations = DFS(graph);
            totalOperations = totalOperations + runOpperations;
            totalEdges = totalEdges + graph.numberOfEdges();
        }
        averageEdges = totalEdges / numberOfRuns;
        expectedAverageOperations = sizeOfGraph + averageEdges;
        averageOperations = totalOperations / numberOfRuns;
        System.out.print(sizeOfGraph + ",");
        System.out.print(edgeProbability + ",");
        System.out.print(averageEdges + ",");
        System.out.print(averageOperations + ",");
        System.out.println(averageOperations/expectedAverageOperations);
    }

    private static int DFS(Graph graph){
        int operation = 0;
        int time = 0;
        boolean[] haveVisited = new boolean[graph.numberOfVertices()]; //0 means that it has not been visited
        Integer[] predecessor = new Integer[graph.numberOfVertices()];
        int[] arrivalTime = new int[graph.numberOfVertices()];
        int[] departureTime = new int[graph.numberOfVertices()];

        for (int index = 0; index < graph.numberOfVertices(); index++){
                haveVisited[index] = false;
                operation++;
                predecessor[index] = null;
                operation++;
                arrivalTime[index] = 0;
                operation++;
                departureTime[index] = 0;
                operation++;
        }
      for (int index = 0; index < graph.numberOfVertices(); index++){
          operation++;
          if (haveVisited[index] == false){
              operation += DFSVISIT(graph, index, haveVisited, predecessor, time, arrivalTime, departureTime);
          }
      }
     return operation;
    }

    private static int DFSVISIT(Graph graph, int index, boolean[] haveVisited, Integer[] predecessor,
    int time, int[] arrivalTime, int[] departureTime){
        int operation = 0;
        Object[] edgeList = graph.getAdjacenciesOfVertex(index).toArray();
        time = time + 1;
        operation++;
        arrivalTime[index] = time;
        operation++;
        haveVisited[index] = true;
        operation++;
        for(int adjacencyIndex = 0; adjacencyIndex < edgeList.length; adjacencyIndex++){
            operation++;
            if (haveVisited[Integer.parseInt(edgeList[adjacencyIndex].toString())] == false){
                predecessor[Integer.parseInt(edgeList[adjacencyIndex].toString())] = index;
                operation++;
                operation += DFSVISIT(graph, Integer.parseInt(edgeList[adjacencyIndex].toString()), haveVisited, predecessor, time, arrivalTime, departureTime);
            }
        }
        time=time+1;
        operation++;
        departureTime[index] = time;
        operation++;
        return operation;
    }

}
