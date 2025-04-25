
/*
Brute Force - Calculate distance for all points from origin and add it to the array. Sort the array and go through each points again and only add the points whose distance is less than the kth point distance.

Time - O(nlogn)
Space - O(n)
*/

/*class Solution {
    public int[][] kClosest(int[][] points, int k) {

        int[] distance  = new int[points.length];
        int j = 0;
        for(int[] d: points){
            distance[j] = d[0]*d[0] + d[1]*d[1];
            j++;
        }

        Arrays.sort(distance);
        int[][] result = new int[k][2];
        int index = 0;
        for(int[] d: points){
            if(d[0]*d[0] + d[1]*d[1] <= distance[k-1]){
                result[index++] = d; 
            }
        }
        return result;
    }
}*/

/*
    Max heap implementation
    Time - O(nlogk)
    Space - O(k)
*/

/*class Solution {
    public int[][] kClosest(int[][] points, int k) {
        Queue<int[]> maxHeap = new PriorityQueue<>((int[] a, int[] b) -> ((b[0] * b[0] + b[1] * b[1]) - (a[0] * a[0] + a[1] * a[1])));

        for (int[] n : points){
            maxHeap.add(n);
            if(maxHeap.size() > k)
                maxHeap.poll();
        }

        int[][] result = new int[k][2];
        int i = 0;
        while(!maxHeap.isEmpty()){
            result[i++] = maxHeap.poll();
        }

        return result;

    }
}*/

class Solution {
    private Random random = new Random();
    
    public int[][] kClosest(int[][] points, int k) {
        if (points == null || points.length == 0) {
            return new int[0][0];
        }
        
        // Apply iterative quickselect algorithm
        return quickSelect(points, k);
    }
    
    private int[][] quickSelect(int[][] points, int k) {
        int left = 0;
        int right = points.length - 1;
        int pivotIndex = -1;
        
        // Iterative quickselect
        while (pivotIndex != k - 1) {
            pivotIndex = partition(points, left, right);
            
            if (pivotIndex < k - 1) {
                // If pivot is to the left of k, search in the right part
                left = pivotIndex + 1;
            } else if (pivotIndex > k - 1) {
                // If pivot is to the right of k, search in the left part
                right = pivotIndex - 1;
            }
        }
        
        // Return the first k points
        return Arrays.copyOf(points, k);
    }
    
    private int partition(int[][] points, int left, int right) {
        // Choose a random pivot
        int pivotIndex = left + random.nextInt(right - left + 1);
        int[] pivot = points[pivotIndex];
        int pivotDist = distanceSquared(pivot);
        
        // Move pivot to the end
        swap(points, pivotIndex, right);
        
        // Two-pointer approach for partitioning
        int storeIndex = left;
        for (int i = left; i < right; i++) {
            if (distanceSquared(points[i]) < pivotDist) {
                swap(points, i, storeIndex);
                storeIndex++;
            }
        }
        
        // Move pivot to its final place
        swap(points, storeIndex, right);
        
        return storeIndex;
    }
    
    private int distanceSquared(int[] point) {
        return point[0] * point[0] + point[1] * point[1];
    }
    
    private void swap(int[][] points, int i, int j) {
        int[] temp = points[i];
        points[i] = points[j];
        points[j] = temp;
    }
}