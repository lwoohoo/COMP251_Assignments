import java.util.*;
import java.io.File;

public class FordFulkerson {


	//augmented dfs to find and track path from start to sink
	public static ArrayList<Integer> pathDFS(Integer source, Integer destination, WGraph graph){
		ArrayList<Integer> path = new ArrayList<Integer>();
		/* YOUR CODE GOES HERE*/
		Stack<Integer> s = new Stack<>();
		Stack<Integer[]> forkStack = new Stack<Integer[]>();
		s.push(source);
		int size = graph.getNbNodes();
		boolean[] visited = new boolean[size];
		ArrayList<Edge> edges = graph.getEdges();
		boolean isEnd;
		while(!s.isEmpty()) { //while stack not empty
			int vertex = s.pop();
			isEnd = true;
			if (visited[vertex] == false) {
				visited[vertex] = true; //set visited
				path.add(vertex);
				if (vertex == destination) break;


				//get adjacent
				int adj = 0;
				for (Edge item: edges) {
					//if edge is wrt current vertex
					//if can proceed (not full capacity)
					if (item.nodes[0] == vertex && item.weight != 0){
						s.push(item.nodes[1]);
						adj++;
						//if this statment is reached => there is adj nodes
						isEnd = false;
					}
				}
				//if adj > 1 => fork
				if (adj > 1) forkStack.push(new Integer[]{vertex, adj - 1});
			}

			if (isEnd && vertex != destination && forkStack.isEmpty()) {
				path.clear();
				break;
			}
			if (isEnd && vertex != destination) {
				Integer[] fork = forkStack.pop();
				for (int i = path.size() - 1; i >= 0; i--) {
					Integer current = path.get(i);
					if (current == fork[0]) {
						break;
					}
					path.remove(i);
				}
				//change number of still-to-search branches
				//add again if still more otherwise remove
				fork[1]--;
				if (fork[1] > 0) forkStack.push(fork);
			}

		}
		return path;
	}

	//find max flow of input graph
	public static String fordfulkerson(WGraph graph){
		String answer="";
		int maxFlow = 0;
		
		/* YOUR CODE GOES HERE		*/
		WGraph capacities = new WGraph(graph);
		WGraph residual = new WGraph(graph);
		//zero edges
		ArrayList<Edge> edges = graph.getEdges();
		for (int i = 0; i < edges.size(); i++) {
			edges.get(i).weight = 0;
		}
		//while there is a path
		Integer source = graph.getSource();
		Integer destination = graph.getDestination();
		ArrayList<Integer> path = pathDFS(source, destination, residual);
		//while there is a path - will terminate when a path from source to sink not found
		while(!path.isEmpty()) {
			//get vertices for first edge access
			Integer current = path.get(0);
			Integer next = path.get(1);
			//find bottleneck
			//path.size() - 1 = #edges
			int beta = residual.getEdge(current, next).weight;
			for (int i = 0; i < path.size() - 1; i++) {
				//get edge between current and next verteces
				Edge edge = residual.getEdge(path.get(i), path.get(i+1));
				if (edge == null) continue;
				if (edge.weight < beta) beta = edge.weight;
			}
			for (int i = 0; i < path.size() - 1; i++) {
				Edge edge = graph.getEdge(path.get(i), path.get(i + 1));
				//since path is in residual graph
				//not implied that there exists an edge in graph
				//between each set of vertices in path
				if (edge == null) {
					//try backwards edge
					graph.getEdge(path.get(i+1), path.get(i)).weight -= beta;
				} else {
					//add flow to normal edge
					edge.weight += beta;
				}
			}
			//once this loop terminates, graph is updated to flow addition beta
			Edge temp, flow, capacity;
			// update the residual graph with augmented path
			// for all in path compare 
			for (int i = 0; i < path.size() - 1; i++) {
				current = path.get(i);
				next = path.get(i + 1);
				temp = residual.getEdge(current, next);
				flow = graph.getEdge(current, next);
				capacity = capacities.getEdge(current, next);
				//if flow less than capacity
				if (flow.weight <= capacity.weight) temp.weight = capacity.weight - flow.weight;
				if (flow.weight > 0) {
					//if the back edge in the residual graph already exists then we don't want to duplicate
					//set the new ed
					if (temp != null) {
						residual.setEdge(next, current, flow.weight);
					} else {
						residual.addEdge(new Edge(current, next, flow.weight));
					}
					
				}
			}

			maxFlow += beta;

			path = pathDFS(source, destination, residual);
		}


		answer += maxFlow + "\n" + graph.toString();	
		return answer;
	}

	 public static void main(String[] args){
		WGraph g = new WGraph();
		g.setSource(0);
		g.setDestination(9);
		Edge[] edges = new Edge[] {
						new Edge(0, 1, 10),
						new Edge(0, 2, 5),
						new Edge(2, 3, 5),
						new Edge(1, 3, 10),
						new Edge(3, 4, 5),
						new Edge(4, 5, 10),
						new Edge(4, 6, 5),
						new Edge(6, 7, 5),
						new Edge(6, 8, 10),
						new Edge(8, 9, 10),
					};
		Arrays.stream(edges).forEach(e->g.addEdge(e));            
		String result = FordFulkerson.fordfulkerson(g);
		//weightsFromString(result);
		System.out.print(result);
	}	
}

