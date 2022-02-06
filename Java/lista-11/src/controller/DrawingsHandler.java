package controller;

import model.Edge;
import model.Graph;
import model.Node;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class DrawingsHandler implements MouseListener {
    int nodeSize = 32;
    ArrayList<Node> nodes;
    Graph graph;
    Node draggedNode;

    public DrawingsHandler(Graph graph) {
        this.graph = graph;
        this.nodes = graph.getNodes();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        Node nodeAtPoint = getNodeAtPoint(x, y);

        if (nodeAtPoint != null) {
            graph.removeNode(nodeAtPoint);
        } else {
            graph.addNode(new Node(x, y));
        }
    }

    private Node getNodeAtPoint(int x, int y) {
        for (Node node : nodes) {
            if (x <= node.getX() + 16 && x >= node.getX() - 16 && y <= node.getY() + 16 && y >= node.getY() - 16) {
                return node;
            }
        }
        return null;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        Node nodeAtPoint = getNodeAtPoint(x, y);

        if (nodeAtPoint != null) {
            draggedNode = nodeAtPoint;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        System.out.println(x);
        System.out.println(y);

        Node nodeAtPoint = getNodeAtPoint(x, y);

        if (draggedNode != null) {
            if (nodeAtPoint != null) {
                Edge edgeBetween = graph.getEdgeBetweenNodes(nodeAtPoint, draggedNode);
                System.out.println("Before removing");
                System.out.println(draggedNode.getEdges());
                if (edgeBetween != null) {
                    nodeAtPoint.removeEdge(edgeBetween);
                    draggedNode.removeEdge(edgeBetween);
                    graph.removeEdge(edgeBetween);
                    System.out.println("After removing");
                    System.out.println(draggedNode.getEdges());
                } else {
                    Edge newEdge = new Edge(draggedNode, nodeAtPoint);
                    graph.addEdge(newEdge);
                    nodeAtPoint.addEdge(newEdge);
                    draggedNode.addEdge(newEdge);
                }
            } else {
                graph.changeNodeXY(x, y, draggedNode);
            }
        }
        draggedNode = null;

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
