//Graph (Adjacency list) representation using dynamic arrays
import java.util.ArrayList;

class graph{
	
	public void addedge(ArrayList<ArrayList<Integer>> adj, int node1, int node2){
		adj.get(node1).add(node2);
		adj.get(node2).add(node1);
	}

	public void printgraph(ArrayList<ArrayList<Integer>> adj){
		for(int i = 0; i < adj.size(); i++){
			System.out.print(i);
			for(int j = 0; j < adj.get(i).size(); j++){
				System.out.print(" -> "+adj.get(i).get(j));
			}
			System.out.println();
		}
	}

	public static void main(String[] args){
		int size = 5;
		ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>(size);
		
		for(int i = 0; i < size; i++){
			adj.add(new ArrayList<Integer>());
		}

		graph g = new graph();
		g.addedge(adj, 0, 1);
		g.addedge(adj, 0, 4);
		g.addedge(adj, 1, 2); 
        	g.addedge(adj, 1, 3); 
        	g.addedge(adj, 1, 4); 
        	g.addedge(adj, 2, 3); 
        	g.addedge(adj, 3, 4);

		g.printgraph(adj);
	}
}