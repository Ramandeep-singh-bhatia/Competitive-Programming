/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

/*
    We have to return the deep copy of clone of the graph. We can mainatin that clone for each node in a map. We can do bfs on the original node and for each node, we add a clone node in the map
    When we do bfs, and we reach a node. We get the neighbours for each node from the original graph and add the cloned node for each neighbour in the map. Once we have the clones, we add the neighbours for the cloned graph from the original graph

    Time - O(V + E) for number of nodes and edges
    Space - O(V) For map that stores each node. Queue will also use some space which will be width of the graph but overall it will be O(V)
*/

class Solution {
    public Node cloneGraph(Node node) {
        if(node == null)
            return node;
        
        Queue<Node> q = new LinkedList<>();
        Map<Node, Node> visited = new HashMap<>();
        
        q.add(node);
        
        visited.put(node, new Node(node.val, new ArrayList()));
        
        while(!q.isEmpty()){
            Node n = q.poll();
            
            for(Node neighbor : n.neighbors){
                if(!visited.containsKey(neighbor)){
                    visited.put(neighbor, new Node(neighbor.val, new ArrayList()));
                    q.add(neighbor);
                }
                
                visited.get(n).neighbors.add(visited.get(neighbor));
            }
        }
        
        return visited.get(node);
    }
}