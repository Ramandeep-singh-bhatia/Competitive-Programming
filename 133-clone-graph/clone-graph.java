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

/*class Solution {
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
}*/

// DFS
/*class Solution {
    private Map<Node, Node> visited = new HashMap<>();

    public Node cloneGraph(Node node) {
        if (node == null) {
            return node;
        }

        // If the node was already visited before.
        // Return the clone from the visited dictionary.
        if (visited.containsKey(node)) {
            return visited.get(node);
        }

        // Create a clone for the given node.
        // Note that we don't have cloned neighbors as of now, hence [].
        Node cloneNode = new Node(node.val, new ArrayList());
        // The key is original node and value being the clone node.
        visited.put(node, cloneNode);

        // Iterate through the neighbors to generate their clones
        // and prepare a list of cloned neighbors to be added to the cloned node.
        for (Node neighbor : node.neighbors) {
            cloneNode.neighbors.add(cloneGraph(neighbor));
        }
        return cloneNode;
    }
}*/

/*
Time - O(V + E) for number of nodes and edges
Space - O(V) For map that stores each node.
*/

class Solution {
    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }

        Node[] arr = new Node[101]; // track visited and point to new nodes
        Node sol = helper(node, arr);
        return sol;
    }

    public Node helper(Node node, Node[] arr) {
        if (arr[node.val] != null) {
            return arr[node.val];
        }
        arr[node.val] = new Node(node.val); // create new node
        for (Node n : node.neighbors) {
            arr[node.val].neighbors.add(helper(n, arr)); // for each neighbor in the og, add cloned version
        }
        return arr[node.val];
    }
}