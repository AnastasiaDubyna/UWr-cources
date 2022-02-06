package model;

import view.DrawingPanel;

import java.io.*;
import java.util.ArrayList;
import java.nio.charset.StandardCharsets;
import java.util.Random;

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
        if (!edges.contains(edge)) {
            edges.add(edge);
            drawingPanel.repaint();
        }
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

    public void removeNode(Node nodeToRemove) {
        nodeToRemove.beforeRemove();
        nodes.remove(nodeToRemove);

        ArrayList<Edge> edgesToRemove = nodeToRemove.getEdges();

        for (int i = 0; i < edgesToRemove.size(); i++) {
            Edge edge = edgesToRemove.get(i);
            Node otherNode = edge.getOtherNode(nodeToRemove);
            otherNode.removeEdge(edge);
            edges.remove(edge);
        }

        int number = 1;
        for (Node node : nodes) {
            node.setNumber(number);
            number++;
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

    public String toString() {
        String result = String.valueOf(nodes.size()) + "\n";
        for (Node node : nodes) {
            for (Edge edge: node.edges) {
                Node otherNode = edge.getOtherNode(node);
                result += String.valueOf(otherNode.getNumber()) + " ";
            }
            result += "\n";
        }
        return result;
    }

    public void serialize(String outputPath) {
        try {
           String str = this.toString();
           FileOutputStream fileOut = new FileOutputStream(outputPath);
           byte[] strToBytes = str.getBytes();
           fileOut.write(strToBytes);
           fileOut.close();
           System.out.print("Serialized data is saved in " + outputPath);
        } catch (IOException i) {
           i.printStackTrace();
        }
    }

    public void deserialize(String srcPath) {
        try {
            FileInputStream fiStream = new FileInputStream(srcPath);
            InputStreamReader isReader = new InputStreamReader(fiStream, StandardCharsets.UTF_8);
            BufferedReader br = new BufferedReader(isReader);
            String line;

            Random random = new Random();
            int index = 0;
            
            while ((line = br.readLine()) != null) {
                if (index == 0) {
                    for (int i = 0; i < Integer.valueOf(line); i++) {
                        Node node = new Node(
                            random.ints(100, (i + 2) * 100).findFirst().getAsInt(),
                            random.ints(100, (i + 2) * 100).findFirst().getAsInt()
                        );
                        nodes.add(node);
                    }
                } else if (line.length() > 0) {
                    Node node = nodes.get(index - 1);
                    String[] parts = line.split(" ");
                    for (String part: parts) {
                        System.out.println(part);
                        Edge edge = new Edge(node, nodes.get(Integer.valueOf(part) - 1));
                        node.addEdge(edge);
                        edges.add(edge);
                    }
                }


                index++;
            }
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

}
