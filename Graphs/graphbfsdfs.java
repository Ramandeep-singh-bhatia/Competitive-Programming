import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

class graphbfsdfs{
	
	public void addedge(ArrayList<ArrayList<Integer>> adj, int x, int y){
		adj.get(x).add(y);
		adj.get(y).add(x);
	}

	public void bfs(ArrayList<ArrayList<Integer>> adj, int vertex){
		boolean[] visited = new boolean[adj.size()];
		Queue<Integer> q = new LinkedList<>();

		q.add(vertex);
		visited[vertex] = true;

		while(!q.isEmpty()){
			int v = q.remove();
			
			System.out.print(v + " ");

			for(int i = 0; i < adj.get(v).size(); i++){
				if(!visited[adj.get(v).get(i)]){
					visited[adj.get(v).get(i)] = true;
					q.add(adj.get(v).get(i));
				}
			}
		}
	}

	public void dfsUtility(ArrayList<ArrayList<Integer>> adj, int vertex, boolean[] visited){
		visited[vertex] = true;
		System.out.println(vertex +" ");
		
		for(int i = 0; i < adj.get(vertex).size(); i++){
			if(!visited[adj.get(vertex).get(i)]){
				visited[adj.get(vertex).get(i)] = true;
				dfsUtility(adj, adj.get(vertex).get(i), visited);
			}
		}
	}

	public void dfs(ArrayList<ArrayList<Integer>> adj, int vertex){
		boolean[] visited = new boolean[adj.size()];
		
		dfsUtility(adj,vertex,visited);
	}

	public static void main(String[] args){
		ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>(6);

		for(int i =0 ; i < 7; i++)
			adj.add(new ArrayList());

		graphbfsdfs gbd = new graphbfsdfs();

		/*gbd.addedge(adj, 1, 2);
        	gbd.addedge(adj, 1, 3);
        	gbd.addedge(adj, 2, 4);
        	gbd.addedge(adj, 2, 5);
        	gbd.addedge(adj, 3, 5);
        	gbd.addedge(adj, 4, 5);
        	gbd.addedge(adj, 4, 6);
        	gbd.addedge(adj, 5, 6);
		
		gbd.bfs(adj,1);*/

		gbd.addedge(adj,0, 1);
        	gbd.addedge(adj,0, 2);
        	gbd.addedge(adj,1, 2);
        	gbd.addedge(adj,2, 0);
        	gbd.addedge(adj,2, 3);
        	gbd.addedge(adj,3, 3);

		gbd.dfs(adj,2);
		
	}
}