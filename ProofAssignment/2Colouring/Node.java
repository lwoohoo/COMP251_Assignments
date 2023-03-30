import java.util.*;

public class Node {

    private int colour;
    private int value;
    private ArrayList<Node> neighbourhood;
    private boolean coloured;

    public Node (int value){
        this.value = value;
        this.coloured = false;
        this.neighbourhood = new ArrayList<Node>();
    }
    public int getValue() {
        return this.value;
    }
    public int getColour() {
        return this.colour;
    }
    public ArrayList<Node> getNeighborhood() {
        return this.neighbourhood;
    }
    public boolean getColoured() {
        return this.coloured;
    }
    public void setValue(int value) {
        this.value = value;
    }
    public void setColour(int colour) {
        this.colour = colour;
    }
    public void addNeighbor(Node node) {
        this.neighbourhood.add(node);
    }
    public void setColoured (boolean a) {
        this.coloured = a;
    }
}