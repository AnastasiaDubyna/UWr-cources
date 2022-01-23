package model;

import view.DrawingPanel;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Graph implements Serializable {
    ArrayList<Node> nodes;
    ArrayList<Edge> edges;
    DrawingPanel drawingPanel;


    public Graph(DrawingPanel drawingPanel) {
        nodes = new ArrayList<>();
        edges = new ArrayList<>();
        this.drawingPanel = drawingPanel;
    }

    public void addNode(Node node) {
        nodes.add(node);
        drawingPanel.repaint();
    }

    public void addEdge(Edge edge) {
        edges.add(edge);
        drawingPanel.repaint();
    }

    public void removeEdge(Edge edge) {
        edges.remove(edge);
        drawingPanel.repaint();
    }

    public ArrayList<Node> getNodes() {
        return nodes;
    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }

    public void removeNode(Node node) {
        nodes.remove(node);

        for (Edge edge : node.getEdges()) {
            edges.remove(edge);
        }
        drawingPanel.repaint();
    }

    public Edge getEdgeBetweenNodes(Node nodeOne, Node nodeTwo) {
        for (Edge edge : edges) {
            if ((edge.getStartNode() == nodeOne && edge.getEndNode() == nodeTwo) || (edge.getStartNode() == nodeTwo && edge.getEndNode() == nodeOne)) {
                return edge;
            }
        }
        return null;
    }

    public void changeNodeXY(int newX, int newY, Node node) {
        node.changeXY(newX, newY);
        drawingPanel.repaint();
    }

    private void writeObject(ObjectOutputStream out) throws IOException {

    }

    private void readObject(ObjectInputStream in)
            throws IOException, ClassNotFoundException;

}
