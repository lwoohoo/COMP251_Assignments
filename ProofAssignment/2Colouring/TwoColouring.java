import java.util.*;

public class TwoColouring {

    public static void main(String[] args) {
        Graph g = new Graph();
        
        //initialize nodes
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);

        //add nodes to graph
        g.addNode(n1);
        g.addNode(n2);
        g.addNode(n3);
        g.addNode(n4);
        g.addNode(n5);
        g.addNode(n6);

        //add edges
        g.addEdge(n1, n2);
        g.addEdge(n1, n3);
        g.addEdge(n2, n4);
        g.addEdge(n2, n5);
        g.addEdge(n3, n4);
        g.addEdge(n4, n6);

        twoColour(g);
        System.out.println("Colour of the vertices post-traversal");
         for (Node n : g.getNodes()) {
            System.out.println("Node " + n.getValue() + " has colour " + n.getColour());
        }
    }

    public static void twoColour(Graph g) {
        colourOne(g.getNodes().get(0)); //start at arbitrary first node
    }

    public static void colourOne(Node node) {
        if (node.getColoured()) return; //base case
        node.setColour(1);
        node.setColoured(true);
        for (Node n : node.getNeighborhood()) {
            colourTwo(n);
        }
    }

    public static void colourTwo(Node node) {
        if (node.getColoured()) return; //base case
        node.setColour(2);
        node.setColoured(true);
        for (Node n : node.getNeighborhood()) {
            colourOne(n);
        }
    }





}