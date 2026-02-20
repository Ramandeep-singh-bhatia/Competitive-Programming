class Solution {
    // parent array for Union Find - sized at 100001 to accommodate both
    // actual numbers (up to 10^5) and prime factors as virtual nodes
    private int[] parent;
    
    // rank array to keep track of tree height for union by rank optimization
    // prevents trees from getting lopsided during union operations
    private int[] rank;
    
    public int largestComponentSize(int[] nums) {
        // size 100001 covers all possible values in nums plus their prime factors
        // in the same flat structure - no need for separate node mapping
        parent = new int[100001];
        rank = new int[100001];
        
        // initialize every element as its own parent - no connections yet
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }
        
        // for each number, find its prime factors and union the number
        // with each of its prime factors - this way two numbers sharing
        // a prime factor will end up in the same component
        for (int num : nums) {
            // trial division up to sqrt(num) to find all prime factors
            for (int factor = 2; factor * factor <= num; factor++) {
                if (num % factor == 0) {
                    // union the number itself with this prime factor
                    union(num, factor);
                    // also union with num/factor because that's the other factor
                    // e.g. for 12 and factor=2, we also want to union 12 with 6
                    union(num, num / factor);
                }
            }
        }
        
        // now count component sizes - but only count nodes that are
        // actual numbers from the input array, not the prime factor nodes
        // using a map from root -> count of array elements in that component
        Map<Integer, Integer> componentSize = new HashMap<>();
        int largest = 0;
        
        for (int num : nums) {
            // find the root of this number's component
            int root = find(num);
            // increment count for this component
            componentSize.put(root, componentSize.getOrDefault(root, 0) + 1);
            largest = Math.max(largest, componentSize.get(root));
        }
        
        return largest;
    }
    
    private int find(int x) {
        // path compression - point x directly to root on the way back up
        // so future find calls on x or its children are faster
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }
    
    private void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        
        // already in the same component, nothing to do
        if (rootX == rootY) return;
        
        // union by rank - attach smaller tree under larger tree
        // to prevent the tree from growing tall and slowing down find
        if (rank[rootX] < rank[rootY]) {
            parent[rootX] = rootY;
        } else if (rank[rootX] > rank[rootY]) {
            parent[rootY] = rootX;
        } else {
            // equal rank - pick one arbitrarily and increment its rank
            // because the tree just got one level taller
            parent[rootY] = rootX;
            rank[rootX]++;
        }
    }
}