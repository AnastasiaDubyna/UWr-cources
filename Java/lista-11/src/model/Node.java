package model;

import java.util.ArrayList;

public class Node {
    int x;
    int y;
    ArrayList<Edge> edges;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
        edges = new ArrayList<>();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void addEdge(Edge edge) {
        edges.add(edge);
    }

    public void removeEdge(Edge edge) {
        edges.remove(edge);
    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }

    public void changeXY(int newX, int newY) {
        x = newX;
        y = newY;
    }
}
