/*
    MinHeap
    Time - O(N logk)
    Space - O(N +k) - N for map and k for pq
*/

/*class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer,Integer> m = new HashMap<>();
        
        
        for(int x : nums) m.put(x, m.getOrDefault(x,0) +1);
        
        PriorityQueue<Integer> pq = new PriorityQueue<>((num1,num2) -> m.get(num1) - m.get(num2));
        
        for(int x : m.keySet()){ 
            pq.add(x);
            if(pq.size() > k)
                pq.poll();
        }
        
        int[] ans = new int[k];
        
        for(int i = 0; i < k; i++){
            ans[i] = pq.poll();
        }
        
        return ans;  
    }
}*/

/*
Quick Select
Time - O(n) average, O(n^2) worst case if we choose bad pivots
Space - O(n^2), O(n) for frequency list, Hashmap. Additional O(n) for creating list in every recursion call.
*/

/*class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> frequency = new HashMap<>();
        for(int num: nums){
            frequency.put(num, frequency.getOrDefault(num, 0) + 1);
        }

        List<Pair<Integer, Integer>> fl = new ArrayList<>();

        for(int key: frequency.keySet()){
            fl.add(new Pair<>(key, frequency.get(key)));
        }

        List<Pair<Integer, Integer>> kfrequent = quickselect(fl, k);

        int[] result = new int[kfrequent.size()];

        for(int i = 0; i < kfrequent.size(); i++){
            result[i] = kfrequent.get(i).getKey();
        }
        return result;
    }

    public List<Pair<Integer, Integer>> quickselect(List<Pair<Integer, Integer>> fl, int k){
        if(fl.size() <= 1)
            return fl;
        List<Pair<Integer, Integer>> left = new ArrayList<>();
        List<Pair<Integer, Integer>> right = new ArrayList<>();
        List<Pair<Integer, Integer>> mid = new ArrayList<>();
        int pivot = new Random().nextInt(fl.size());
        
        for(int i = 0; i < fl.size(); i++){
            if(fl.get(i).getValue() > fl.get(pivot).getValue())
                left.add(fl.get(i));
            else if (fl.get(i).getValue() < fl.get(pivot).getValue())
                right.add(fl.get(i));
            else
                mid.add(fl.get(i));
        }

        if(left.size() >= k)
            return quickselect(left, k);

        List<Pair<Integer, Integer>> result = new ArrayList<>(left);
        int midElementsNeeded = Math.min(mid.size(), k - left.size());
        result.addAll(mid.subList(0, midElementsNeeded));
        if(result.size() < k)
            result.addAll(quickselect(right, k - left.size() - mid.size()));
            
        return result;

    }
}*/

/*
    Array Based quick select
    Time Complexity - O(n), worst O(n^2) if we choose bad pivots and the problem is not divided by half at each step, it becomes just one element less
    Space complexity - O(n) - to store map and array of unique elemnets
*/

/*class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> frequency = new HashMap<>();
        for(int num: nums){
            frequency.put(num, frequency.getOrDefault(num, 0) + 1);
        }

        List<int[]> freqList = new ArrayList<>();
        for(int key: frequency.keySet()){
            freqList.add(new int[]{key, frequency.get(key)});
        }

        // Use quickselect to find k most frequent elements
        List<int[]> topK = quickselect(freqList, 0, freqList.size() - 1, k);
        
        int[] result = new int[k];
        for(int i = 0; i < k; i++){
            result[i] = topK.get(i)[0];
        }
        return result;
    }

    // Returns k most frequent elements
    private List<int[]> quickselect(List<int[]> freqList, int start, int end, int k) {
        if (start > end) return new ArrayList<>();
        
        int pivotIndex = partition(freqList, start, end);
        
        // If pivot is at the kth position
        if (pivotIndex == k - 1) {
            return freqList.subList(0, k);
        } 
        // If pivot is after k, search in the left part
        else if (pivotIndex > k - 1) {
            return quickselect(freqList, start, pivotIndex - 1, k);
        } 
        // If pivot is before k, search in the right part
        else {
            return quickselect(freqList, pivotIndex + 1, end, k);
        }
    }
    
    // Partition the array around a pivot and return the pivot index
    private int partition(List<int[]> freqList, int start, int end) {
        // Choose random pivot
        int pivotIndex = start + new Random().nextInt(end - start + 1);
        int pivotFreq = freqList.get(pivotIndex)[1];
        
        // Move pivot to the end
        swap(freqList, pivotIndex, end);
        
        // Move all elements with higher frequency than pivot to the left
        int i = start;
        for (int j = start; j < end; j++) {
            if (freqList.get(j)[1] > pivotFreq) {
                swap(freqList, i, j);
                i++;
            }
        }
        
        // Move pivot back to its final position
        swap(freqList, i, end);
        return i;
    }
    
    private void swap(List<int[]> list, int i, int j) {
        int[] temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }
}*/

/*
    Bucket Sort
    Time complexity - O(n)
    Space - O(range + n) Range = MaxNum - minNum + 1 for frequency array and n for counts array
*/

class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        int minNum = nums[0], maxNum = nums[0];
        for (int num : nums) {
            if(num > maxNum){
                maxNum = num;
            } else if(num < minNum){
                minNum = num;
            }
        }

        int[] frequency = new int[maxNum - minNum + 1];
        for (int num : nums) {
            frequency[num - minNum]++;
        }

        List<Integer>[] counts = new List[nums.length + 1];
        for (int i = 0; i < frequency.length; i++) {
            int numCnt = frequency[i];
            if (counts[numCnt] == null) {
                counts[numCnt] = new ArrayList<>();
            }
            counts[numCnt].add(i + minNum);
        }

        int[] result = new int[k];
        for (int i = counts.length - 1; k > 0; i--) {
            if (counts[i] != null) {
                for (int num : counts[i]) {
                    result[--k] = num;
                    if (k == 0) {
                        break;
                    }
                }
            }
        }
        return result;
        
    }
}