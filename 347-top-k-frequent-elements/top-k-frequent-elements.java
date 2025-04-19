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

/*class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        int minNum = nums[0], maxNum = nums[0];
        for (int num : nums) {
            if(num > maxNum){
                maxNum = num;
            } else if(num < minNum){
                minNum = num;
            }
        }

        int[] numAndCnt = new int[maxNum - minNum + 1];
        for (int num : nums) {
            numAndCnt[num - minNum]++;
        }

        List<Integer>[] cntAndNums = new List[nums.length + 1];
        for (int i = 0; i < numAndCnt.length; i++) {
            int numCnt = numAndCnt[i];
            if (cntAndNums[numCnt] == null) {
                cntAndNums[numCnt] = new ArrayList<>();
            }
            cntAndNums[numCnt].add(i + minNum);
        }

        int[] result = new int[k];
        for (int i = cntAndNums.length - 1; k > 0; i--) {
            if (cntAndNums[i] != null) {
                for (int num : cntAndNums[i]) {
                    result[--k] = num;
                    if (k == 0) {
                        break;
                    }
                }
            }
        }
        return result;
        
    }
}*/

class Solution {
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
}