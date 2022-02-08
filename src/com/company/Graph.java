package com.company;

import java.util.*;

public class Graph {
    int V; // No. of vertices'

    // Pointer to an array containing adjacency lists
    ArrayList<ArrayList<AdjListNode>> adj;
    static Map<String, Integer> map1 = new LinkedHashMap<>();
    static Map<String, Integer> map2 = new LinkedHashMap<>();
    static Map<String, Integer> map3 = new LinkedHashMap<>();

    Graph(int V) // Constructor
    {
        this.V = V;
        adj = new ArrayList<ArrayList<AdjListNode>>(V);

        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<AdjListNode>());
        }
    }

    void addEdge(int u, int v, int weight) {
        AdjListNode node = new AdjListNode(v, weight);
        adj.get(u).add(node); // Add v to u's list
    }

    void topologicalSortUtil(int v, boolean visited[],
                             Stack<Integer> stack) {
        // Mark the current node as visited
        visited[v] = true;

        // Recur for all the vertices adjacent to this vertex
        for (int i = 0; i < adj.get(v).size(); i++) {
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
    void longestPath(int s) {
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
        while (stack.isEmpty() == false) {

            // Get the next vertex from topological order
            int u = stack.peek();
            stack.pop();

            // Update distances of all adjacent vertices ;
            if (dist[u] != Integer.MIN_VALUE) {
                for (int i = 0; i < adj.get(u).size(); i++) {
                    AdjListNode node = adj.get(u).get(i);
                    if (dist[node.getV()] < dist[u] + node.getWeight()) {
                        dist[node.getV()] = dist[u] + node.getWeight();
                    }
                }
            }
        }

        // Print the calculated longest distances
        ArrayList<List<Integer>> numbers = new ArrayList<List<Integer>>();

        int size = Main.sizeArray;
        int count = 1;

        for (int i = 0; i < size; i++) {
            numbers.add(new ArrayList<Integer>());
            for (int j = 0; j < size; j++) {
                numbers.get(i).add(j, dist[count++]);
            }
        }
        System.out.println("all longest paths tree from initialVertex: ");

        for (int i = 0; i < numbers.size(); i++) {
            System.out.println("initialVertex -> [" + i + "][" + i + "] = " + numbers.get(i).get(i));
        }

        diagonalDownList(numbers);

        for (Map.Entry<String, Integer> pair : map1.entrySet()) {
            String key = pair.getKey();
            Integer value = pair.getValue();
            System.out.println("initialVertex -> " + key + " = " + value);
        }
        System.out.println("initialVertex -> " + map3.entrySet().stream().findFirst().get().getKey() + " = " + map3.entrySet().stream().findFirst().get().getValue());
        final long countMap = map3.entrySet().stream().count();
        System.out.println("initialVertex -> " + map3.entrySet().stream().skip(countMap - 1).findFirst().get().getKey() + " = " + map3.entrySet().stream().skip(countMap - 1).findFirst().get().getValue());

        for (Map.Entry<String, Integer> pair : map2.entrySet()) {
            String key = pair.getKey();
            Integer value = pair.getValue();
            System.out.println("initialVertex -> " + key + " = " + value);
        }

        int num = dist[0];
        for (int n : dist) {
            if (n > num) {
                num = n;
            }
        }
        System.out.println("longest path tree from vertex 'initialVertex' = " + num);
    }

    static void diagonalDownList(List<List<Integer>> arrayList) {
        int size = arrayList.size();
        int flag = 3;
        int flag1 = 2;
        for (int line = 1; line <= (size + size - 1); line++) {
            int start_col = Main.max(0, line - size);
            int count = Main.min(line, (size - start_col), size);
            for (int j = 0; j < count; j++) {
                if (count != 1 && j + 1 < count && line < size) {
                    String str = "[" + (Main.min(size, line) - 2 - j) + "][" + (size - 2 - j) + "]";
                    map1.put(str, arrayList.get(Main.min(size, line) - 2 - j).get(size - 2 - j));
                    String str1 = "[" + (Main.min(size, line) - 1 - j) + "][" + (size - 1 - j) + "]";
                    map1.put(str1, arrayList.get(Main.min(size, line) - 1 - j).get(size - 1 - j));
                } else if (count != 1 && j + 1 < count && line > size) {
                    String str = "[" + (Main.min(size, line) - 2 - j) + "][" + (size - flag - j) + "]";
                    map2.put(str, arrayList.get(Main.min(size, line) - 2 - j).get(size - flag - j));
                    String str1 = "[" + (Main.min(size, line) - 1 - j) + "][" + (size - flag1 - j) + "]";
                    map2.put(str1, arrayList.get(Main.min(size, line) - 1 - j).get(size - flag1 - j));
                } else if (count == 1 && line < size) {
                    String str = "[0][" + (size - 1) + "]";
                    map3.put(str, arrayList.get(0).get(size - 1));
                } else if (count == 1 && line > size) {
                    String str = "[" + (size - 1) + "][0]";
                    map3.put(str, arrayList.get(size - 1).get(0));
                }
            }
            if (line > size) {
                flag++;
                flag1++;
            }
        }
    }
}
