import java.util.Iterator;
import java.util.LinkedList;

class graphdfs {
    private int V;

    private LinkedList<Integer> adj[];

    graphdfs(int v){
        V = v;
        adj = new LinkedList[v];
        for(int i = 0; i < v; ++i){
            adj[i] = new LinkedList();
        }
    }

    void addEdge(int a, int b){
        adj[a].add(b);
    }

    void DFSUtility(int v, boolean visited[]){
        visited[v] = true;
        System.out.println(v+" ");

        Iterator<Integer> i = adj[v].listIterator();

        while(i.hasNext()){
            int n = i.next();

            if(!visited[n])
                DFSUtility(n,visited);
        }
    }

    void DFS(int v){
        boolean visited[] = new boolean[V];

        DFSUtility(v,visited);
    }

    public static void main(String[] args){
        graphdfs g = new graphdfs(4);

        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);

        g.DFS(2);
    }
}