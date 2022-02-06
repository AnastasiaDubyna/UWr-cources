package model;

public class Edge {
    Node startNode;
    Node endNode;

    public Edge(Node nodeOne, Node nodeTwo) {
        startNode = nodeOne;
        endNode = nodeTwo;
    }

    public int getStartX() {
        return startNode.getX();
    }

    public int getStartY() {
        return startNode.getY();
    }

    public int getEndX() {
        return endNode.getX();
    }

    public int getEndY() {
        return endNode.getY();
    }

    public Node getEndNode() {
        return endNode;
    }

    public Node getStartNode() {
        return startNode;
    }

    public Node getOtherNode(Node node) {
        if (node.getNumber() == startNode.getNumber()) {
            return endNode;
        } else {
            return startNode;
        }
    }
}
