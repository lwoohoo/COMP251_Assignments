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

	//rap x,y coords in node class
	public static class Node {

		public int row;
		public int column;
		public boolean visited;

		public Node(int row, int column) {
			this.row = row;
			this.column = column;
		// 	this.visited = false;
		}

		// public void visit() {
		// 	this.visited = true;
		// }

	}

	public static int find_exit(int time, String[][] board) {
		//iterate through board to find start position

		int startRow = board.length + 1; //give non-possible coords when init
		int startCol = board[0].length + 1; 

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				//if "S" encountered, break out of loop
				if (board[i][j].equals("S")) {
					startRow = i;
					startCol = j;
					break;
				}
			}
			//check that impossible coord not retained => S found
			//otherwise proceed outer loop
			if (!(startRow == board.length + 1)) break;
		}

		//create leading map
		//-> constant time access of whether node has been accessed yet
		int[][] map = new int[board.length][board[0].length];

		//create starting node
		Node start = new Node(startRow, startCol);
		
		Queue<Node> q = new LinkedList<>();
		q.add(start);

		int minimumTime = 0;
		//start breadth first search
		while (!q.isEmpty()) {
			//iterate through all at height
			for (int i = 0; i < q.size(); i++) {
				Node node = q.remove();
				//check to see if exit has been reached
				if (isExit(node, board)) {
					//check if exit reached in time
					if (minimumTime > time) {
						return -1; //bad exit case
					}
					//otherwise return minimum time to leave
					return minimumTime; // good exit case
				}

				//check all adj buildings
				//if canmove -> add to q and visit

				//up
				int destRow = node.row - 1;
				int destCol = node.column;
				if (canMove(0, board, destRow, destCol, map)) {
					map[destRow][destCol] = 1;
					q.add(new Node(destRow, destCol));
				}
				//down
				destRow = node.row + 1;
				destCol = node.column;
				if (canMove(1, board, destRow, destCol, map)) {
					map[destRow][destCol] = 1;
					q.add(new Node(destRow, destCol));
				}
				//left
				destRow = node.row;
				destCol = node.column - 1;
				if (canMove(2, board, destRow, destCol, map)) {
					map[destRow][destCol] = 1;
					q.add(new Node(destRow, destCol));
				}
				//right
				destRow = node.row;
				destCol = node.column + 1;
				if (canMove(3, board, destRow, destCol, map)) {
					map[destRow][destCol] = 1;
					q.add(new Node(destRow, destCol));
				}
			}
			minimumTime++;
		}

		return -1; //bad exit case: impossible to exit
	}

	public static boolean isExit(Node node, String[][] board) {

		int rows = board.length;
		int columns = board[0].length;

		int currentRow = node.row;
		int currentColumn = node.column;

		//check if current position is on any boundary
		if (currentRow == 0 || currentColumn == 0 || currentRow == rows - 1 || currentColumn == columns - 1) {
			return true;
		}
		return false;
	}

	public static boolean canMove(int direction, String[][] board, int row, int col, int[][] map) {
		
		String destinationString = board[row][col];

		//check if destination is 1 or destination node has been visited => cant move
		if (destinationString.equals("1") || map[row][col] == 1) return false;

		//check if destination is 0 => can move
		if (destinationString.equals("0")) return true;

		//check special cases U, D, L, R
		//direction encoded as 0->U, 1->D, 2->L, 3->R
		//if destination character is equal to the opposite direction of its encoding => can move
		if (direction == 0 && destinationString.equals("D")) return true;
		if (direction == 1 && destinationString.equals("U")) return true;
		if (direction == 2 && destinationString.equals("R")) return true;
		if (direction == 3 && destinationString.equals("L")) return true;
		return false;
	}

	public static void main(String[] args) {

		String[][] grid0 = {
				{"1", "1", "1", "1"},
				{"1", "S", "0", "1"},
				{"1", "0", "1", "1"},
				{"0", "U", "1", "1"}
		};

		String[][] grid1 = {
				{"1", "1", "1", "1"},
				{"1", "S", "0", "1"},
				{"1", "0", "1", "1"},
				{"0", "L", "1", "1"}
		};

		String[][] grid2 = {
				{"1", "S", "0", "1"},
				{"1", "0", "0", "1"},
				{"1", "0", "1", "1"},
				{"0", "U", "1", "1"}
		};

		String[][] grid3 = {
				{"1", "1", "1", "1"},
				{"1", "S", "0", "1"},
				{"1", "0", "1", "1"},
				{"0", "U", "1", "1"}
		};





		int shortestPath = find_exit(5,grid0);
		System.out.println(shortestPath);
	}
}
