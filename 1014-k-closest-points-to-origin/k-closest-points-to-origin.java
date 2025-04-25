
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
    public int[][] kClosest(int[][] points, int K) {
        return quickSelect(points, K);
    }
    private int[][] quickSelect(int[][] points, int K) {
        int i = 0, j = points.length - 1;
        while(i < j) {
            int mid = partition(points, i, j);
            if(mid == K) break;
            if(mid < K) {
                i = mid + 1;
            } else {
                j = mid - 1;
            }
        }
        return Arrays.copyOf(points, K);
    }
    
    private int partition(int[][] points, int start, int end) {
        int[] pivot = points[end];
        int swapIndex = start - 1;
        for(int i = start; i < end; i++) {
            if(value(points[i]) < value(pivot)) {
                swapIndex++;
                swap(points, swapIndex, i);
            }
        }
        swap(points, ++swapIndex, end);
        return swapIndex;
    }
    
    private int value(int[] a) {
        return a[0] * a[0] + a[1] * a[1];
    }
    
    private void swap(int[][] points, int i, int j) {
        int[] temp = points[i];
        points[i] = points[j];
        points[j] = temp;
    }
}