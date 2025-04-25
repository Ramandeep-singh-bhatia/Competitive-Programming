
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
    public int[][] kClosest(int[][] points, int k) {
        return quickSelect(points, k);
    }
    
    private int[][] quickSelect(int[][] points, int k) {
        int left = 0, right = points.length - 1;
        Random random = new Random();
        while (left < right) {
            // Repeatedly partition the array
            // while narrowing in on the kth element
            int pivotIndex = left + random.nextInt(right - left + 1);
            pivotIndex = partition(points, left, right, pivotIndex);
            if (pivotIndex < k) {
                left = pivotIndex + 1;
            } else {
                right = pivotIndex - 1;
            }
        }
        
        // Return the first k elements of the partially sorted array
        return Arrays.copyOf(points, k);
    }

    private int partition(int[][] points, int start, int end, int pivotIndex) {
        int pivotDistance = getDistance(points[pivotIndex]);
        // Move pivot to end for partitioning
        swap(points, pivotIndex, end);
        
        // Use two pointers for partitioning
        int left = start;      // Points to elements >= pivot
        int right = end - 1;   // Points to elements < pivot
        
        // Loop until pointers cross
        while (left <= right) {
            // Find an element on left side that is >= pivot
            while (left <= right && getDistance(points[left]) < pivotDistance) {
                left++;
            }
            
            // Find an element on right side that is < pivot
            while (left <= right && getDistance(points[right]) >= pivotDistance) {
                right--;
            }
            
            // Swap elements if pointers haven't crossed
            if (left <= right) {
                swap(points, left, right);
                left++;
                right--;
            }
        }
        
        // Put pivot back to its final position
        swap(points, left, end);
        return left;
    }
    
    private int getDistance(int[] point) {
        // Calculate and return the squared Euclidean distance
        return point[0] * point[0] + point[1] * point[1];
    }

    private void swap(int[][] points, int i, int j) {
        int[] temp = points[i];
        points[i] = points[j];
        points[j] = temp;
    }
}