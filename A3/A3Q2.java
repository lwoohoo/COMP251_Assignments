import java.util.*;

public class A3Q2 {

	//pieces -> 
	public static long[] num_pieces(long[] pieces, int[][] instructions) {

		int modules = pieces.length;
		ArrayList<Integer> sort = new ArrayList<>();

		//construct graph
		ArrayList<Integer>[] graph = new ArrayList[modules];
		for (int i = 0; i < modules; i++) {
			//create arraylist for each spot in the array
			graph[i] = new ArrayList<Integer>();
		}
		//add edges in graph
		for (int[] instruction: instructions) {
			graph[instruction[0]].add(instruction[1]);
		}
		//at this point the pieces dependencies have been 
		//respected and the graph is created
		//Topo sort
		Stack<Integer> s = new Stack<>();
		boolean[] map = new boolean[modules];
		for (int i = 0; i < modules; i++) {
			if (map[i]) continue;
			//sort from provided source
			dFS(graph, i, s, map);
		}
		//collapse stack
		while(!s.isEmpty()) {
			Integer item = s.pop();
			sort.add(item);
		}

		//go through sorted list
		for (int i = modules - 1; i >= 0; i--) {
			//for each instruction sum the number of times a piece is needed
			for (int j = 0; j < instructions.length; j++) {
				//check if current instruction requires piece i
				if (sort.get(i) != instructions[j][0]) continue;
				pieces[sort.get(i)] += instructions[j][2]*pieces[instructions[j][1]];
			}
		}
		return pieces;
	}

	public static void dFS(ArrayList<Integer>[] graph, int vertex, Stack<Integer> s, boolean[] map ) {
		//visit vertex
		//check adj
		ArrayList<Integer> adjacents = graph[vertex];
		map[vertex] = true;
		for (int adjacent: adjacents) {
			if (map[adjacent]) continue;
			//search deeper
			dFS(graph, adjacent, s, map);
		}
		//once this is reached then at end of the branch
		s.push(vertex);
	}

}
