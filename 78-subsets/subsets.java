/*class Solution {
    List<Integer> subset = new ArrayList<>();
    List<List<Integer>> result = new ArrayList<>();
    
    public List<List<Integer>> subsets(int[] nums) {
        backtrack (nums, 0);
        return result;
    }

    public void backtrack(int[] nums, int start){
        result.add(new ArrayList<>(subset));

        for(int i = start; i < nums.length; i++){
            subset.add(nums[i]);
            
            //System.out.println("nums[" + i + "]- " + nums[i]);
            backtrack(nums, i+1);
            //System.out.println("Subset Size- " + subset.size());
            subset.remove(subset.size() - 1);
        }
    }
}*/

/*class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        // start recursion at index 0 with an empty current subset
        backtrack(nums, 0, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int[] nums, int index, List<Integer> current, List<List<Integer>> result) {
        // base case: we've made an include/exclude decision for every element,
        // so whatever is in 'current' is a complete subset - add a copy of it
        if (index == nums.length) {
            result.add(new ArrayList<>(current)); // copy because 'current' is mutated as we backtrack
            return;
        }

        // CHOICE 1: include nums[index] in the current subset
        current.add(nums[index]);
        backtrack(nums, index + 1, current, result);

        // CHOICE 2: exclude nums[index] - undo the add and recurse without it
        current.remove(current.size() - 1); // backtrack by removing the element we just added
        backtrack(nums, index + 1, current, result);
    }
}*/

class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        int n = nums.length;
        List<List<Integer>> result = new ArrayList<>();

        // there are 2^n possible subsets, each represented by a unique bitmask
        // from 0 (empty set) to 2^n - 1 (full set)
        int totalSubsets = 1 << n; // bit shift is cleaner than Math.pow(2, n)

        for (int mask = 0; mask < totalSubsets; mask++) {
            List<Integer> subset = new ArrayList<>();

            // check each bit position j to see if element j is included in this subset
            for (int j = 0; j < n; j++) {
                // if bit j is set in mask, include nums[j] in this subset
                if (((mask >> j) & 1) == 1) {
                    subset.add(nums[j]);
                }
            }

            result.add(subset);
        }

        return result;
    }
}
