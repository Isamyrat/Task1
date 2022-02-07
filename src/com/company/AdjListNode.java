package com.company;

public class AdjListNode {
    int v;
    int weight;

    AdjListNode(int _v, int _w)
    {
        v = _v;
        weight = _w;
    }
    int getV() { return v; }
    int getWeight() { return weight; }
}