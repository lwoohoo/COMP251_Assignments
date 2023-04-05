import java.util.*;

public class A3Q2 {

	//begin by establishing graph data structure
	public static class Graph {
		//has both verteces and edges
		public static class Vertex {
			private int value;
			private ArrayList<Edge> adjs;

			public Vertex(int value) {
				this.adjs = new ArrayList<>();
				this.value = value;
			}
		
			public int getValue() {
				return this.value;
			}

			public ArrayList<Edge> getAdjacent() {
				return this.adjs;
			}

			public void setValue(int value) {
				this.value = value;
			}

			public void addEdge(Edge edge) {
				adjs.add(edge);
			}
		}


		public static class Edge {
			private int weight;
			private Vertex next;

			public Edge(int weight, Vertex next) {
				this.weight = weight;
				this.next = next;
			}

			public int getWeight() {
				return this.weight;
			}

			public Vertex getNext() {
				return this.next;
			}

			public void setWeight(int weight) {
				this.weight = weight;
			}

			public void setNext(Vertex vertex) {
				this.next = vertex;
			}
		}

	}


	//pieces -> 
	public static long[] num_pieces(long[] pieces, int[][] instructions) {

		int modules = pieces.length;
		

		return pieces;
	}

}
