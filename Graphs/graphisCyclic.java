import java.util.LinkedList;
import java.util.Iterator;

class graphisCyclic{
	int V;
	LinkedList<Integer> adj[];

	graphisCyclic(int V){
		this.V = V;
		adj = new LinkedList[V];
		for(int i = 0; i< V; i++)
			adj[i] = new LinkedList<>();
	}

	public void addedge(int x, int y){
		adj[x].addFirst(y);
	}
	
	public boolean isCyclicUtil(int i, boolean[] visited, boolean[] recursiveArr){

		visited[i] = true;
		recursiveArr[i] = true;

		//Iterator<Integer> j = adj[i].listIterator(); 
		

		for(int j = 0; j< adj[i].size(); j++){
			int n = adj[i].get(j);
			if(!visited[n] && isCyclicUtil( n, visited, recursiveArr)){
				return true;
			}else if(recursiveArr[n])
				return true;
		
		}
		recursiveArr[i] = false;
		return false;
	}

	public boolean isCyclic(){
		boolean[] visited = new boolean[V];
		boolean recursiveArr[] = new boolean[V];

		for(int i = 0; i< V; i++){
			if(isCyclicUtil(i,visited, recursiveArr));
				return true;
		}
		return false;
	}

	public static void main(String[] args){
		graphisCyclic graph = new graphisCyclic(4);

		graph.addedge(0, 1);
        	//graph.addedge(0, 2);
        	graph.addedge(1, 2);
        	//graph.addedge(2, 0);
        	graph.addedge(2, 3);
		//graph.addedge(3, 1);
        	//graph.addedge(3, 3);

		if(graph.isCyclic())
			System.out.println("Graph contains cycle");
		else
			System.out.println("Graph does not contain cycle");
	}
}