package com.company;

import java.util.*;

public class Graph {
    int V; // No. of vertices'

    // Pointer to an array containing adjacency lists
    ArrayList<ArrayList<AdjListNode>> adj;

    Graph(int V) // Constructor
    {
        this.V = V;
        adj = new ArrayList<ArrayList<AdjListNode>>(V);

        for(int i = 0; i < V; i++){
            adj.add(new ArrayList<AdjListNode>());
        }
    }
    void addEdge(int u, int v, int weight)
    {
        AdjListNode node = new AdjListNode(v, weight);
        adj.get(u).add(node); // Add v to u's list
    }

    void topologicalSortUtil(int v, boolean visited[],
                             Stack<Integer> stack)
    {
        // Mark the current node as visited
        visited[v] = true;

        // Recur for all the vertices adjacent to this vertex
        for (int i = 0; i<adj.get(v).size(); i++) {
            AdjListNode node = adj.get(v).get(i);
            if (!visited[node.getV()])
                topologicalSortUtil(node.getV(), visited, stack);
        }

        // Push current vertex to stack which stores topological
        // sort
        stack.push(v);
    }

    // The function to find longest distances from a given vertex.
    // It uses recursive topologicalSortUtil() to get topological
    // sorting.
    void longestPath(int s)
    {
        Stack<Integer> stack = new Stack<Integer>();
        int dist[] = new int[V];

        // Mark all the vertices as not visited
        boolean visited[] = new boolean[V];
        for (int i = 0; i < V; i++)
            visited[i] = false;

        // Call the recursive helper function to store Topological
        // Sort starting from all vertices one by one
        for (int i = 0; i < V; i++)
            if (visited[i] == false)
                topologicalSortUtil(i, visited, stack);

        // Initialize distances to all vertices as infinite and
        // distance to source as 0
        for (int i = 0; i < V; i++)
            dist[i] = Integer.MIN_VALUE;

        dist[s] = 0;

        // Process vertices in topological order
        while (stack.isEmpty() == false)
        {

            // Get the next vertex from topological order
            int u = stack.peek();
            stack.pop();

            // Update distances of all adjacent vertices ;
            if (dist[u] != Integer.MIN_VALUE)
            {
                for (int i = 0; i<adj.get(u).size(); i++)
                {
                    AdjListNode node = adj.get(u).get(i);
                    if (dist[node.getV()] < dist[u] + node.getWeight()) {
                        dist[node.getV()] = dist[u] + node.getWeight();
                    }
                }
            }
        }

        // Print the calculated longest distances
        for (int i = 1; i < V; i++) {
            if (dist[i] == Integer.MIN_VALUE)

                System.out.print("INF ");
            else
                System.out.print(dist[i] + " ");

        }
        int num = dist[0];
        for (int n : dist) {
            if( n > num) {
                num  = n;
            }
        }
        System.out.println("The longest path is: " + num);
    }
}
