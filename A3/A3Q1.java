import java.util.*;

		//if a letter is on its boarder (L at bottom, U at top, etc) then consider it as a 1
		//otherwise it can be accessed
		//destination is at a building on the edge of the city

		//say for some 
		//	1 0 1 1
		//  1 0 0 1
		//  1 1 S 1
		//  1 0 1 1
		//has solution Up Left Up

		//say for some
		//  1 D 1 1
		//  1 0 0 1
		//  1 1 S 1
		//  1 0 1 1
		//has the same ssolution as above

public class A3Q1 {

	// public class Graph {

	// 	public class Node {
	// 		//fields
	// 		public boolean canMove;
	// 		public ArrayList<Node> nodes;

	// 		public Node(boolean canMove) {
	// 			this.canMove = canMove;
	// 			this.nodes = new ArrayList<Node>();
	// 		}

	// 	}

	// 	public Node startNode;
	
	
	
	
	
	// }



	public static int find_exit(int time, String[][] board) {
		return 0;
	}

	public static void constructGraph(String[][] board) {
		for (String[] column: board) {
			for (String row: column) {

			}
		}
	}

	public static void findPath() {
	}
}
