// Brute force
/*class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] result = new int[n - k + 1];
        int max = Integer.MIN_VALUE;

        for(int i = 0; i <= n - k; i++){
            result[i] = getMax(nums, i, k);
        }

        return result;
    }

    private int getMax(int[] nums, int start, int k){
        int max = Integer.MIN_VALUE;
        for(int i = start; i < start + k; i++){
            max = Math.max(max, nums[i]);
        }
        return max;
    }
}*/

class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] result = new int[n - k + 1];
        int index= 0;

        Deque<Integer> dq = new ArrayDeque<>();
        for(int i = 0; i< k; i++){
            while(!dq.isEmpty() && nums[i] >= nums[dq.peekLast()]){
                dq.pollLast();
            }
            dq.offerLast(i);
        }
        result[index++] = nums[dq.peekFirst()];

        for(int i = k; i < n; i++){
            if(dq.peekFirst() == i - k){
                dq.pollFirst();
            }
            while(!dq.isEmpty() && nums[i] >= nums[dq.peekLast()]){
                dq.pollLast();
            }
            dq.offerLast(i);
            result[index++] = nums[dq.peekFirst()];
        }

        return result;
    }
}