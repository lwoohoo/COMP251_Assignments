import java.util.*;
import java.io.File;

public class FordFulkerson {


	//augmented dfs to find and track path from start to sink
	public static ArrayList<Integer> pathDFS(Integer source, Integer destination, WGraph graph){
		ArrayList<Integer> path = new ArrayList<Integer>();
		/* YOUR CODE GOES HERE*/
		Stack<Integer> s = new Stack<>();
		Stack<Integer> forkStack = new Stack<Integer>();
		s.push(source);
		int size = graph.getNbNodes();
		boolean[] visited = new boolean[size];
		ArrayList<Edge> edges = graph.getEdges();
		boolean isEnd = false;
		while(!s.isEmpty()) { //while stack not empty
			int vertex = s.pop();
			if (visited[vertex] == false) {
				visited[vertex] = true; //set visited
				path.add(vertex);

				//get adjacent
				isEnd = true;
				int adjCount = 0;
				for (Edge item: edges) {
					//if edge is wrt current vertex
					//if can proceed (not full capacity)
					if (item.nodes[0] == vertex && item.weight > 0){
						s.push(item.nodes[1]);
						adjCount++;
						//if this statment is reached => there is adj nodes
						isEnd = false;
					}
				}
				//if count > 1 => current vertex is a fork
				if (adjCount > 1) forkStack.push(vertex);
				//reset path if end reached that is not sink
				if (isEnd && vertex != destination) {

					Integer fork = forkStack.pop();
					for (int i = path.size() - 1; i > 0; i--) {
						Integer current = path.get(i);
						if (current == fork) {
							break;
						}
						path.remove(i);
					}
				}
			}
		}

		return path;
	}


	//find max flow of input graph
	public static String fordfulkerson( WGraph graph){
		String answer="";
		int maxFlow = 0;
		
		/* YOUR CODE GOES HERE		*/

		answer += maxFlow + "\n" + graph.toString();	
		return answer;
	}
	

	 public static void main(String[] args){
		WGraph g = new WGraph();
        g.setSource(0);
        g.setDestination(5);
        Edge[] edges = new Edge[] {
                new Edge(0, 1, 10),
                new Edge(0, 2, 5),
                new Edge(2, 4, 5),
                new Edge(1, 3, 10),
                new Edge(1, 6, 5),
                new Edge(3, 0, 10),
                new Edge(3, 5, 5)
        };

        Arrays.stream(edges).forEach(e->g.addEdge(e));
        for(Integer integer: FordFulkerson.pathDFS(0, 5, g))
            System.out.print(integer+", ");
        System.out.println();
	 }
}

