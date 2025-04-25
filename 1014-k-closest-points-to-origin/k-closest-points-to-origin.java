
/*
Brute Force - Calculate distance for all points from origin and add it to the array. Sort the array and go through each points again and only add the points whose distance is less than the kth point distance.

Time - O(nlogn)
Space - O(n)
*/

class Solution {
    public int[][] kClosest(int[][] points, int k) {
        /*Queue<int[]> maxHeap = new PriorityQueue<>((int[] a, int[] b) -> ((b[0] * b[0] + b[1] * b[1]) - (a[0] * a[0] + a[1] * a[1])));

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

        return result;*/

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
}

/*class Solution {
    private int[][] quickSelect(int[][] points, int k){
        int L = 0, R = points.length - 1;
        int M = points.length;
        while (M != k){
            M = partition(points, L, R);
            if (M < k){
                L = M;
            } else {
                R = M - 1;
            }
        }
        return Arrays.copyOf(points, k);
    }
    
    private int partition(int[][] points, int L, int R){
        int[] pivot = choosePivot(points, L, R);
        int pivotDist = squareDistance(pivot);
        while (L < R){
            if (squareDistance(points[L]) >= pivotDist){
                int[] temp = points[L];
                points[L] = points[R];
                points[R] = temp;
                R--;
            } else {
                L++;
            }
        }
        
        if (squareDistance(points[L]) < pivotDist)
            L++;
        return L;
    }
    
    private int[] choosePivot(int[][] points, int L, int R){
        return points[L + (R-L) / 2];
    }
    
    private int squareDistance(int[] point){
        return point[0] * point[0] + point[1] * point[1];
    }

    public int[][] kClosest(int[][] points, int k) {
        return quickSelect(points, k);
    }
}*/