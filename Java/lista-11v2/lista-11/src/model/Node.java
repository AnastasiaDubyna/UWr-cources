package model;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Node {
    private static final AtomicInteger counter = new AtomicInteger();
    int x;
    int y;
    int number;
    ArrayList<Edge> edges;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
        number = counter.incrementAndGet();
        edges = new ArrayList<>();
    }

    public void beforeRemove() {
        counter.decrementAndGet();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setNumber(int newNumber) {
        number = newNumber;
    }

    public int getNumber() {
        return number;
    }

    public void addEdge(Edge edge) {
        if (!edges.contains(edge)) {
            edges.add(edge);
        }
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
