import java.util.*;

public class Graph {

    private List<Node> nodes;

    //Constructor
    public Graph () {
        this.nodes = new ArrayList<Node>();
    }

    public void addNode(Node node) {
        this.nodes.add(node);
    }

    public void addEdge (Node node1, Node node2) {
        node1.addNeighbor(node2);
        node2.addNeighbor(node1);
    }

    public List<Node> getNodes() {
        return this.nodes;
    }
}