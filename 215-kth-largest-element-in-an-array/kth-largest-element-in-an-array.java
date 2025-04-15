class Solution {
    public int findKthLargest(int[] nums, int k) {
        List<Integer> list = new ArrayList<>();
        for(int num : nums){
            list.add(num);
        }
        return quickSelect(list, k);
    }

    private int quickSelect(List<Integer> list, int k){
        int i = new Random().nextInt(list.size());
        int pivot = list.get(i);

        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        List<Integer> mid = new ArrayList<>();

        for(int num : list){
            if(num > pivot)
                left.add(num);
            else if (num < pivot)
                right.add(num);
            else
                mid.add(num);
        }

        if(left.size() >= k)
            return quickSelect(left,k);

        if(left.size() + mid.size() < k)
            return quickSelect(right, k - left.size() - mid.size());

        return pivot;

    }
}