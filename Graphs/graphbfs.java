import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

class graphbfs{
	public void addedge(ArrayList<ArrayList<Integer>> adj, int vertex1, int vertex2){
		adj.get(vertex1).add(vertex2);
		adj.get(vertex2).add(vertex1);
	}

	public void bfs(ArrayList<ArrayList<Integer>> adj, int vertex){
		int v = adj.size();
		boolean[] visited = new boolean[v];
	
		Queue<Integer> q = new LinkedList<>();
		
		for(int i = 0; i <v; i++)
			visited[i] = false;
		
		visited[vertex]=true;
		q.add(vertex);

		while(!q.isEmpty()){
			int s = q.poll();
			System.out.print(s + " ");
			
			for(int i = 0; i < adj.get(s).size(); i++){
				if(!visited[adj.get(s).get(i)]){
					visited[adj.get(s).get(i)] = true;
					q.add(adj.get(s).get(i));
				}	
			}	
		}
	}

	public static void main(String[] args){
		ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>(6);
		graphbfs gbfs = new graphbfs();

		for(int i = 0; i < 7; i++){
			adj.add(new ArrayList<>());
		}

		gbfs.addedge(adj, 1, 2);
        	gbfs.addedge(adj, 1, 3);
        	gbfs.addedge(adj, 2, 4);
        	gbfs.addedge(adj, 2, 5);
        	gbfs.addedge(adj, 3, 5);
        	gbfs.addedge(adj, 4, 5);
        	gbfs.addedge(adj, 4, 6);
        	gbfs.addedge(adj, 5, 6);
		
		gbfs.bfs(adj,1);
	}
}