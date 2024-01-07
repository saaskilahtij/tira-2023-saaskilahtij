package oy.interact.tira.student.graph;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import oy.interact.tira.student.graph.Edge.EdgeType;


/**
 * Implementation of the graph data structure and associated algorithms.
 * <p>
 * This abstract class implements the graph data structure and various
 * algorithms like breadth-first search, depth-first search and the Dijkstra
 * path finding algorithm.
 * <p>
 * The class needs your attention, dear student. Implement the methods
 * marked TODO in the comments, based on what you have learned about
 * graphs.
 * <p>
 * The Graph as a generic (template) class can use any data types conforming to 
 * the Comparable interface.
 * <p>
 * This implementation uses edge lists to store the graph vertices
 * and edges.
 * 
 * @author Antti Juustila
 * @version 1.0
 */
public class Graph<T> {

   /** The edge list of the grap. Select and instantiate
    * a suitable type of Map, depending on application needs.
    */
  private Hashtable<Vertex<T>, List<Edge<T>>> edgeList = null;
  private int edgeCount = 0;
  private int vertexCount = 0;

   /**
    * Constructor instantiates a suitable Map data structure
    * depending on the application requirements.
    */
  public Graph() {
    this.edgeList = new Hashtable<Vertex<T>, List<Edge<T>>>();
  }

   /**
    * Creates a vertex holding the dataItem (node in a vertex) in the graph.
    * Use this method always to add vertices to the graph.
    *
    * Create the vertex object with the data item, then create an empty
    * list of edges, and put the vertex and the empty list to the Map.
    *
    * The newly created vertex must have an empty list of edges.
    * 
    * @param element The data item to put in the vertex of the graph.
    * @return Returns the created vertex, placed in the graph's edge list.
    */
  public Vertex<T> createVertexFor(T element) {
    Vertex<T> vertex = new Vertex<T>(element);
    edgeList.put(vertex, new ArrayList<Edge<T>>());
    vertexCount++;
    return vertex;
  }

   /**
    * Get all the vertices of the graph in a Set.

    * @return A Set with all the vertices of the graph.
    */
  public Set<Vertex<T>> getVertices() {
    Set<Vertex<T>> vertices = new HashSet<>();
    for (int i = 0; i < vertexCount; i++) {
      vertices = edgeList.keySet();
    }
    return vertices;
  }

   /**
    * Adds an edge to the graph. Note that the vertices MUST have been created 
    * earlier by calling {@code createVertexFor(T)} and are already in the graph.
    * @param type The type of the edge, either directed or undirected.
    * @param source The source vertex of the edge.
    * @param destination The destination vertex of the edge.
    * @param weight The weight of the edge.
    */
  public void addEdge(Edge.EdgeType type, Vertex<T> source, Vertex<T> destination, double weight) {
    if (source == null || destination == null) {
      return;
    }
    List<Edge <T>> edges = edgeList.get(source);
    edges.add(new Edge<T>(source, destination, weight));
    edgeList.put(source, edges);
    edgeCount++;
    List <Edge<T>> edges2 = edgeList.get(destination);
    edges2.add(new Edge<T>(destination, source, weight));
    edgeList.put(destination, edges2);
    edgeCount++;
  }

   /**
    * Adds a directed edge to the graph. Note that the vertices must have been created 
    * earlier by calling  {@code createVertexFor(T)}.
    * @param source The source vertex of the edge.
    * @param destination The destination vertex of the edge.
    * @param weight The weight of the edge.
    */
   public void addDirectedEdge(Vertex<T> source, Vertex<T> destination, double weight) {
    List<Edge <T>> edges = edgeList.get(source);
    edges.add(new Edge<T>(source, destination, weight));
    edgeList.put(source, edges);
    edgeCount++;
   }

  /**
   * Gets the edges of the specified vertex. The vertex must be
   * already in the graph.
   * @param source The vertex edges of which we wish to get.
   * @return Returns the edges of the vertex or null if no edges from the source.
   */
  public List<Edge<T>> getEdges(Vertex<T> source) {
    if (source == null) {
      return null;
    }
    return edgeList.get(source);
  }

   /**
    * Gets a vertex for the specified node (contents) in a vertex, if found.
    * If the vertex with the node value is not found, returns null.
    * Use `equals` to search for the element from the vertices.
    *
    * @param element The value of T that is in some Vertex in the graph.
    * @return The vertex containing the node, or null if no vertex contains the element.
    */
   public Vertex<T> getVertexFor(T element) {
    for (Vertex<T> vertex: edgeList.keySet()) {
      if (vertex.getElement().equals(element)) {
        return vertex;
      }
    }
    return null;
   }

  /**
   * If target is null, search is done for the whole graph. Otherwise,
   * search MUST be stopped when the target vertex is found.
   *
   * @param from The vertex where the search is started from.
   * @param target An optional ending vertex, null if not given.
   * @return Returns all the visited vertices traversed while doing BFS, in order they were found, or an empty list.
   */
  public List<Vertex<T>> breadthFirstSearch(Vertex<T> from, Vertex<T> target) {
    Set<Vertex<T>> visited = new HashSet<>();
    Queue<Vertex<T>> queue = new LinkedList<>();
    queue.add(from);
    visited.add(from);
    while (!queue.isEmpty()) {
      Vertex<T> current = queue.poll();
      List<Edge<T>> edges = getEdges(current);
      for (int i = 0; i < edges.size(); i++) {
        Vertex<T> destination = edges.get(i).getDestination();
        if (!visited.contains(destination)) {
          visited.add(destination);
          queue.add(destination);
        }
      }
    }
    if (visited.isEmpty()) {
      return null;
    }
    return new ArrayList<>(visited);
}

   /**
    * Does depth first search (DFS) of the graph starting from a vertex.
    * <p>
    * If target is null, search is done for the whole graph. Otherwise,
    * search MUST be stopped when the target vertex is found.
    * <p>
    *
    * @param from The vertex where the search is started from.
    * @param target An optional ending vertex, null if not given.
    * @return Returns all the visited vertices traversed while doing DFS.
    */
    public List<Vertex<T>> depthFirstSearch(Vertex<T> from, Vertex<T> target) {
      Set<Vertex<T>> visitedVertices = new HashSet<>();
      Stack<Vertex<T>> stack = new Stack<>();
      stack.push(from);
      visitedVertices.add(from);
      while (!stack.isEmpty()) {
        Vertex<T> current = stack.pop();
        List<Edge<T>> edges = getEdges(current);
        for (int i = 0; i < edges.size(); i++) {
          Vertex<T> destination = edges.get(i).getDestination();
          if (!visitedVertices.contains(destination)) {
            visitedVertices.add(destination);
            stack.push(destination);
          }
        }
      }
      return new ArrayList<>(visitedVertices);
  }
   
  /**
   * Returns a non-empty list if the graph is disconnected. A disconnected graph is a
   * graph that has separate areas without any connecting edges between them.
   * 
   * If the graph is disconnected, the list contains all the elements _not_ visited, 
   * doing a breadth first search from the vertex provided as the parameter.
   * If the parameter is null, starts from the first vertex of the graph.
   * 
   * @param toStartFrom Vertex to start investigating from. If null, start from the first vertex.
   * @return Returns non-empty list if the graph is disconnected, otherwise list is empty.
   */
  public List<T> disconnectedVertices(Vertex<T> toStartFrom) {
    if (toStartFrom == null) {
      toStartFrom = edgeList.keySet().iterator().next();
    }
    List<Vertex<T>> visitedVertices = breadthFirstSearch(toStartFrom, null);
    List<Vertex<T>> allVertices = new ArrayList<>(edgeList.keySet());
    List<T> notInVisited = new ArrayList<>();
    allVertices.removeAll(visitedVertices);
    for (int i = 0; i < allVertices.size(); i++) {  
      notInVisited.add(allVertices.get(i).getElement());
    } 
    return notInVisited;
  }

  /**
   * Returns true if the graph is disconnected. That means, the graph 
   * has areas that can not be reached from the starting vertex.
   *
   * @param toStartFrom The vertex to start the analysis from. Can be null, then starts from first vertex.
   * @return True if the graph is disconnected.
   */
  public boolean isDisconnected(Vertex<T> toStartFrom) {
    if (toStartFrom == null) {
      toStartFrom = edgeList.keySet().iterator().next();
    }
    if (disconnectedVertices(toStartFrom).isEmpty()) {
      return false;
    }
    return true;
  }

  /**
   * Checks if the graph has cycles.
   * 
   * If the graph is directed, provide true as the parameter, false for 
   * undirected graphs. 
   * 
   * <p>NB: For this course project it is enough that this method works for
   * connected graphs. It does not need to work on disconnected graphs when starting
   * from the given vertex.
   *
   * @param isDirected If true graph is directed.
   * @param fromVertex Start looking from this vertex. If null, starts from first vertex in adjacency list.
   * @return Returns true if the graph has cycles.
   */
  public boolean hasCycles(EdgeType edgeType, Vertex<T> fromVertex) {
    Set<Vertex<T>> visitedVertices = new HashSet<>();
    if (edgeType == EdgeType.UNDIRECTED) {
      if (fromVertex == null) {
        fromVertex = edgeList.keySet().iterator().next();
      }
      return hasCyclesUndirected(fromVertex, visitedVertices, null);
    }

    return false;
  }

  private boolean hasCyclesUndirected(Vertex<T> current, Set<Vertex<T>> visited, Vertex<T> parent) {
    visited.add(current);
    List<Edge<T>> edges = getEdges(current);
    for (int i = 0; i < edges.size(); i++) {
      Vertex<T> destination = edges.get(i).getDestination();
      if (!visited.contains(destination)) {
        if (hasCyclesUndirected(destination, visited, current)) {
          return true;
        }
      } else if (!destination.equals(parent)) {
        return true;
      }
    }
    return false;
  }


   // Dijkstra starts here.

   /**
    * The result of the Dijkstra's search.
    */
   public class DijkstraResult<E> {
      public boolean pathFound = false;
      public List<E> path;
      public int steps = 0;
      public double totalWeigth = 0.0;

      @Override
      public String toString() {
         StringBuilder builder = new StringBuilder();
         builder.append(String.format("Dikstra result:\n- Path found: %s%n", (pathFound ? "yes" : "no")));
         if (pathFound) {
            builder.append(String.format("- steps: %d%n", steps));
            builder.append(String.format("- total edge weights: %.2f%n", totalWeigth));
            if (null != path) {
               builder.append(String.format("- path: %s", path.toString()));
            } else {
               builder.append("Path not found\n");
            }
         }
         return builder.toString();
      }
   }

   /**
    * Finds the shortest path from start to end using Dijkstra's algorithm.
    * The return value contains information about the result.
    * @param start The vertex to start from.
    * @param end The vertex to search the shortest path to.
    * @return An object containing information about the result of the search.
    */
   public DijkstraResult<T> shortestPathDijkstra(Vertex<T> start, Vertex<T> end) {
      DijkstraResult<T> result = new DijkstraResult<>();
      result.pathFound = false;
      result.path = null;
      result.steps = 0;
      result.totalWeigth = 0.0;
      // TODO: Student, implement this.
      return result;
   }


  /**
   * Finds a route to a destination using paths already constructed.
   * Before calling this method, cal {@link shortestPathsFrom} to construct
   * the paths from the staring vertex of Dijkstra algorithm.
   * A helper method for implementing the Dijkstra algorithm.
   * 
   * @param toDestination The destination vertex to find the route to.
   * @param paths The paths to search the destination.
   * @return Returns the vertices forming the route to the destination.
   */
  private List<Edge<T>> route(Vertex<T> toDestination, Map<Vertex<T>, Visit<T>> paths) {
    return null;
  }

   private double distance(Vertex<T> toDestination, Map<Vertex<T>, Visit<T>> viaPath) {
      double distance = 0.0;
      // TODO: Student, implement this.
      return distance;
   }
   
   /**
    * Finds the shortest paths in the graph from the starting vertex.
    * In doing Dijkstra, first call this method, then call {@link route}
    * with the paths collected using this method, to get the shortest path to the destination.
    *
    * @param start The starting vertex for the path searching.
    * @return Returns the visits from the starting vertex.
    * @see oy.tol.tira.graph.Graph#route(Vertex, Map)
    */
   private Map<Vertex<T>, Visit<T>> shortestPathsFrom(Vertex<T> start) {
      Visit<T> visit = new Visit<>();
      visit.type = Visit.Type.START;
      Map<Vertex<T>, Visit<T>> paths = new HashMap<>();
      // TODO: Student, implement this.
      return paths;
   }

   // OPTIONAL task in the course!
   /**
    * Do breadth-first-search on the grap and export vertices and edges to a dot file
    * Note that if the graph is disconnected, you must check if some vertices
    * were not visited and continue the BFS until _all_ vertices have been visited.
    * Otherwise, part of the graph is missing from the output file.
    *
    * The simplest way to do this is to first start from the given vertex, do 
    * the BFS saving data to dot file. Then, after this loop, get the disconnected vertices starting from
    * the starting vertex by calling disconnectedVertices(from). If there are not visited vertices, 
    * then pick one of the non visited vertices to be the new starting vertex (from).
    * Repeat this until all vertices have been visited. Basically you need an outer loop repeating
    * the BFS in the inner loop, and the outer loop stops when all vertices have been visited.
    *
    * @param from Start the BFS from this vertex.
    * @param outputFileName Write the dot to this text file.
    * @throws IOException If something goes wrong with file operations.
    */
   public void toDotBFS(Vertex<T> from, String outputFileName) throws IOException {
      
   }


  /**
   * Provides a string representation of the graph, printing  out the vertices and edges.
   * <p>
   * Quite useful if you need to debug issues with algorithms. You can see is the graph
   * what it is supposed to be like.
   * <p>
   * Simple graph as a string would look like this:<br/>
   * <pre>
   * Created simple undirected graph where integers are vertice values:
   * [1] -> [ 2 ]
   * [2] -> [ 1, 3, 4, 5 ]
   * [3] -> [ 2, 4, 5 ]
   * [4] -> [ 2, 3, 5 ]
   * [5] -> [ 2, 3, 4 ]
   * </pre> 
   * @return The graph as a string.
   */
  @Override
   public String toString() {
    StringBuilder output = new StringBuilder();
    for (Map.Entry<Vertex<T>, List<Edge<T>>> entry : edgeList.entrySet()) {
      output.append("[");
      output.append(entry.getKey().toString());
      output.append("] -> [ ");
      int counter = 0;
      int count = entry.getValue().size();
      for (Edge<T> edge : entry.getValue()) {
        output.append(edge.getDestination().toString());
        if (counter < count - 1) {
          output.append(", ");
        }
        counter++;
      }
      output.append(" ]\n");
    }
      return output.toString();
   }
}
