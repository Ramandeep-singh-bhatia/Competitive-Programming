/*class Solution {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> result = new ArrayList<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[0] - b[0]);
        Set<Pair<Integer, Integer>> visited = new HashSet<>();
        pq.offer(new int[]{nums1[0] + nums2[0], 0 , 0});
        visited.add(new Pair<>(0, 0));
        while(result.size() < k && !pq.isEmpty()){
            int[] curr = pq.poll();
            int i = curr[1];
            int j = curr[2];
            result.add(List.of(nums1[i], nums2[j]));
            if(i+1 < nums1.length && !visited.contains(new Pair<>(i+1, j))){
                pq.offer(new int[]{nums1[i+1] + nums2[j], i+1, j});
                visited.add(new Pair<>(i+1, j));
            }
                
            if(j+1 < nums2.length && !visited.contains(new Pair<>(i, j+1))){
                pq.offer(new int[]{nums1[i] + nums2[j+1], i, j+1});
                visited.add(new Pair<>(i, j+1));
            }
                
        }

        return result;
    }
}*/

class Solution {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> result = new ArrayList<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[0] - b[0]);
        for (int i = 0; i < Math.min(nums1.length, k); i++) 
            pq.offer(new int[]{nums1[i] + nums2[0], i, 0});

         while(result.size() < k && !pq.isEmpty()){
            int[] curr = pq.poll();
            int i = curr[1];
            int j = curr[2];
            result.add(List.of(nums1[i], nums2[j]));

            if (j + 1 < nums2.length)
                pq.offer(new int[]{nums1[i] + nums2[j + 1], i , j+1});
         }

         return result;
    }
}