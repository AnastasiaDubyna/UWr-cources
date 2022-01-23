package view;

import controller.DrawingsHandler;
import model.Edge;
import model.Graph;
import model.Node;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DrawingPanel extends JPanel {
    Graph graph;


    public DrawingPanel() {
        super();

        graph = new Graph(this);

        setBackground(new Color(158, 155, 246));
        addMouseListener(new DrawingsHandler(graph));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        ArrayList<Node> nodes = graph.getNodes();
        ArrayList<Edge> edges = graph.getEdges();
        int index = 1;
        int addToX = -2;
        int addToY = 5;

        for (Edge edge : edges) {
            g.setColor(Color.BLACK);
            g.drawLine(edge.getStartX(), edge.getStartY(), edge.getEndX(), edge.getEndY());
        }

        for (Node node : nodes) {
            g.setColor(Color.BLACK);
            g.fillOval(node.getX() - 16, node.getY() - 16, 32, 32);
            g.setColor(Color.WHITE);
            g.drawString(String.valueOf(index), node.getX() + addToX, node.getY() + addToY);
            index++;
            if (String.valueOf(index).length() == 2) {
                addToX = -6;
            }

        }
    }
}
