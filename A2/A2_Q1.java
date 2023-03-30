import java.util.*;

public class A2_Q1{
	
	public static int game_recursion(int[][] board) {

		return player1(board, 0, 0);
	}

	public static int player1(int[][] board, int player1Score, int player2Score) {
		int totalScore = player1Score - player1Score; //init total score for return
		//System.out.println(totalScore);

		//effectively try all values
		for (int row = 0; row < 5; row++) {
			for (int col = 0; col < 5; col++) {
				if (board[row][col] == -1) break; //break column loop if end of row reached (row loop will never give -1 so only saves on column loop)
				if (board[row][col] == 0) continue; //skip loop index for invalid token
				//check directions for possible move and then proceed into recursion
				if (tryUp(board, row, col)) {
					int[][] newBoard = copy(board);
					int workingScore = player2(newBoard, player1Score + moveBoard(newBoard, row, col, row - 2, col, 0), player2Score); //proceed to next layer
					if (workingScore > totalScore) totalScore = workingScore; //store max
				}
				if (tryDown(board, row, col)) {
					int[][] newBoard = copy(board);
					int workingScore = player2(newBoard, player1Score + moveBoard(newBoard, row, col, row + 2, col, 1), player2Score); //proceed to next layer
					if (workingScore > totalScore) totalScore = workingScore; //store max
				}
				if (tryLeft(board, row, col)) {
					int[][] newBoard = copy(board);
					int workingScore = player2(newBoard, player1Score + moveBoard(newBoard, row, col, row, col - 2, 2), player2Score); //proceed to next layer
					if (workingScore > totalScore) totalScore = workingScore; //store max
				}
				if (tryRight(board, row, col)) {
					int[][] newBoard = copy(board);
					int workingScore = player2(newBoard, player1Score + moveBoard(newBoard, row, col, row, col + 2, 3), player2Score); //proceed to next layer
					if (workingScore > totalScore) totalScore = workingScore; //store max
				}
				if (tryUpLeft(board, row, col)) {
					int[][] newBoard = copy(board);
					int workingScore = player2(newBoard, player1Score + moveBoard(newBoard, row, col, row - 2, col - 2, 4), player2Score); //proceed to next layer
					if (workingScore > totalScore) totalScore = workingScore; //store max
				}
				if (tryDownRight(board, row, col)) {
					int[][] newBoard = copy(board);
					int workingScore = player2(newBoard, player1Score + moveBoard(newBoard, row, col, row + 2, col + 2, 5), player2Score); //proceed to next layer
					if (workingScore > totalScore) totalScore = workingScore; //store max
				}
			}
		}
		//at this point the max score difference of all games in further layers is stored in totalScore
		//at this point also the base case has been reached by implication (no valid moves)
		//System.out.println(totalScore);
		return totalScore;
	}

	public static int player2(int[][] board, int player1Score, int player2Score) {
		int totalScore = player2Score - player1Score; //init total score for return

		//effectively try all values
		for (int row = 0; row < 5; row++) {
			for (int col = 0; col < 5; col++) {
				if (board[row][col] == -1) break; //break column loop if end of row reached (row loop will never give -1 so only saves on column loop)
				if (board[row][col] == 0) continue; //skip loop index for invalid token
				//check directions for possible move and then proceed into recursion
				if (tryUp(board, row, col)) {
					int[][] newBoard = copy(board);
					int workingScore = player1(newBoard, player1Score, player2Score + moveBoard(newBoard, row, col, row - 2, col, 0)); //proceed to next layer
					if (workingScore > totalScore) totalScore = workingScore; //store max
				}
				if (tryDown(board, row, col)) {
					int[][] newBoard = copy(board);
					int workingScore = player1(newBoard, player1Score, player2Score + moveBoard(newBoard, row, col, row + 2, col, 1)); //proceed to next layer
					if (workingScore > totalScore) totalScore = workingScore; //store max
				}
				if (tryLeft(board, row, col)) {
					int[][] newBoard = copy(board);
					int workingScore = player1(newBoard, player1Score, player2Score + moveBoard(newBoard, row, col, row, col - 2, 2)); //proceed to next layer
					if (workingScore > totalScore) totalScore = workingScore; //store max
				}
				if (tryRight(board, row, col)) {
					int[][] newBoard = copy(board);
					int workingScore = player1(newBoard, player1Score, player2Score + moveBoard(newBoard, row, col, row, col + 2, 3)); //proceed to next layer
					if (workingScore > totalScore) totalScore = workingScore; //store max
				}
				if (tryUpLeft(board, row, col)) {
					int[][] newBoard = copy(board);
					int workingScore = player1(newBoard, player1Score, player2Score + moveBoard(newBoard, row, col, row - 2, col - 2, 4)); //proceed to next layer
					if (workingScore > totalScore) totalScore = workingScore; //store max
				}
				if (tryDownRight(board, row, col)) {
					int[][] newBoard = copy(board);
					int workingScore = player1(newBoard, player1Score, player2Score + moveBoard(newBoard, row, col, row + 2, col + 2, 5)); //proceed to next layer
					if (workingScore > totalScore) totalScore = workingScore; //store max
				}
			}
		}

		//at this point the max score difference of all games in further layers is stored in totalScore
		//at this point also the base case has been reached by implication (no valid moves)
		//System.out.println(totalScore);
		return totalScore;
	}

	public static int[][] copy(int[][] board) {
		int[][] newBoard = new int[5][5];
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				newBoard[i][j] = board[i][j];
			}
		}
		return newBoard;
	}

	public static boolean tryUp(int[][] board, int row, int col) {
		//check bounds or if adj zero
		if (row - 2 < 0 || board[row - 1][col] <= 0 || board[row - 2][col] == -1) return false;
		return board[row - 2][col] == 0;
	}

	public static boolean tryDown(int[][] board, int row, int col) {
		//check bounds
		if (row + 2 > 4 || board[row + 1][col] <= 0 || board[row + 2][col] == -1) return false;
		return board[row + 2][col] == 0;
	}

	public static boolean tryLeft(int[][] board, int row, int col) {
		//check bounds
		if (col - 2 < 0 || board[row][col - 1] <= 0|| board[row][col - 2] == -1) return false;
		return board[row][col - 2] == 0;
	}

	public static boolean tryRight(int[][] board, int row, int col) {
		//check bounds
		if (col + 2 > 4 || board[row][col + 1] <= 0 || board[row][col + 2] == -1) return false;
		return board[row][col + 2] == 0;
	}

	public static boolean tryUpLeft(int[][] board, int row, int col) {
		//check bounds
		if (row - 2 < 0 || col - 2 < 0 || board[row - 1][col - 1] <= 0 || board[row - 2][col - 2] == -1) return false;
		return board[row -2][col -2] == 0;
	}

	public static boolean tryDownRight(int[][] board, int row, int col) {
		if (row + 2 > 4 || col + 2 > 4 || board[row + 1][col + 1] <= 0 || board[row + 2][col + 2] == -1) return false;
		return board[row + 2][col + 2] == 0;
	}

	//does the manipulation to affect change to array scheme
	//this method will assume move is valid
	public static int moveBoard(int[][] board, int startR, int startC, int destR, int destC, int dir) {
		int jumper = board[startR][startC]; //get value of start space for later multiplication
		int jumpee = 0;

		//use direction encoding to identify jumpee and clear its square
		if (dir == 0) { //up
			jumpee = board[destR + 1][destC];
			clearSquare(board, destR + 1, destC);
		}
		if (dir == 1) { //down
			jumpee = board[destR - 1][destC];
			clearSquare(board, destR - 1, destC);
		}
		if (dir == 2) { //left
			jumpee = board[destR][destC + 1];
			clearSquare(board, destR, destC + 1);
		}
		if (dir == 3) { //right
			jumpee = board[destR][destC - 1];
			clearSquare(board, destR, destC - 1);
		}
		if (dir == 4) { //up left
			jumpee = board[destR + 1][destC + 1];
			clearSquare(board, destR + 1, destC + 1);
		}
		if (dir == 5) { //down right
			jumpee = board[destR - 1][destC - 1];
			clearSquare(board, destR - 1, destC - 1);
		}

		//move the jumper
		board[destR][destC] = jumper; //move jumper to the destination
		clearSquare(board, startR, startC);

		return jumper * jumpee; //return value of the 
	}

	public static void clearSquare(int[][] board, int row, int column) {
		board[row][column] = 0;
	}

	public static void main(String[] args) {
		int[][] board = {{1, -1, -1, -1, -1}, {1,1,-1,-1,-1}, {1,1,1,-1,-1}, {1,1,1,1,-1}, {1, 1, 0, 100, 1}};
		//int[][] board = {{1, -1, -1}, {1, 4, -1}, {7, 5, 0}};
		System.out.println(game_recursion(board));


	}




}
