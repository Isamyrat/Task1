package com.company;

import java.util.*;

public class Main {

    static Graph g = new Graph(26);
    static int sizeArray = 0;
    public static void main(String[] args) {
        // write your code here
        List<List<Integer>> arrayLists = new ArrayList<List<Integer>>() {{
            add(Arrays.asList(4, 4, 5, 4, 2));
            add(Arrays.asList(3, 2, 4, 6, 0));
            add(Arrays.asList(4, 1, 5, 5, 2));
            add(Arrays.asList(3, 0, 9, 6, 0));
            add(Arrays.asList(1, 2, 6, 6, 3));

        }};
   /*     List<List<Integer>> arrayLists = new ArrayList<List<Integer>>() {{
            add(Arrays.asList(1, 3, 3));
            add(Arrays.asList(2, 1, 4));
            add(Arrays.asList(0, 6, 4));

        }};*/

        sizeArray = arrayLists.size();
        List<Integer> numbers = new ArrayList<>();

        for (List<Integer> arrayL : arrayLists) {
            numbers.addAll(arrayL);
        }

        // find first values [length - 1][0]
        for (int i = 0; i < numbers.size() - 1; i = i + sizeArray) {
            g.addEdge(0, i + 1, numbers.get(i));
        }

        // find the values from array that from left to right
        int flag = 1;

        for (int i = 0; i < arrayLists.size(); i++) {
            for (int j = 1; j < arrayLists.get(0).size(); j++) {
                flag++;
                g.addEdge(flag - 1, flag, arrayLists.get(i).get(j));
            }
            flag++;
        }
        // find values from left to diagonal up
        diagonalOrderList(arrayLists);
        // find values from left to diagonal down
        diagonalOrderDownList(arrayLists);
        int s = 0;
        g.longestPath(s);
    }

    static void diagonalOrderList(List<List<Integer>> arrayList) {
        int size = arrayList.size();
        for (int line = 1; line <= (size + size - 1); line++) {
            int start_col = max(0, line - size);
            int count = min(line, (size - start_col), size);
            for (int j = 0; j < count; j++) {
                if (count != 1 && j + 1 < count) {
                    g.addEdge(((size * (min(size, line) - j - 1)) + (start_col + j)) + 1, ((size * (min(size, line) - j - 2)) + ((start_col + j) + 1)) + 1, arrayList.get(min(size, line) - j - 2).get((start_col + j) + 1));
                }
            }
        }
    }

    static void diagonalOrderDownList(List<List<Integer>> arrayList) {
        int size = arrayList.size();
        int flag = 3;
        int flag1 = 2;
        for (int line = 1; line <= (size + size - 1); line++) {
            int start_col = max(0, line - size);
            int count = min(line, (size - start_col), size);
            for (int j = 0; j < count; j++) {
                if (count != 1 && j + 1 < count && line <= size) {
                    g.addEdge(((size * (min(size, line) - 2 - j)) + (size - 2 - j)) + 1, ((size * (min(size, line) - 1 - j)) + ((size - 1 - j))) + 1, arrayList.get(min(size, line) - 1 - j).get(size - 1 - j));
                } else if (count != 1 && j + 1 < count && line > size) {
                    g.addEdge(((size * (min(size, line) - 2 - j)) + (size - flag - j)) + 1, ((size * (min(size, line) - 1 - j)) + (size - flag1 - j)) + 1, arrayList.get(min(size, line) - 1 - j).get(size - flag1 - j));
                }
            }
            if (line > size) {
                flag++;
                flag1++;
            }
        }
    }

    static int min(int a, int b) {
        return (a < b) ? a : b;
    }
    static int min(int a, int b, int c) {
        return min(min(a, b), c);
    }
    static int max(int a, int b) {
        return (a > b) ? a : b;
    }
}
