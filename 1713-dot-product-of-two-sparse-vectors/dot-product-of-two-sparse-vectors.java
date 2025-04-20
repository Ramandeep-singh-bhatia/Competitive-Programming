/*
    sparseproduct is the sum of multiplication of each element in the respective index of the array. In case of two vectors, we will multiply both the numbers with same index in different vectors and sum it together to get the result.

Complexity Analysis

Let n be the length of the input array.

Time complexity: O(n) for both constructing the sparse vector and calculating the dot product.

Space complexity: O(1) for constructing the sparse vector as we simply save a reference to the input array and O(1) for calculating the dot product.

*/

/*class SparseVector {
    int[] arr;
    SparseVector(int[] nums) {
        this.arr = nums;
    }
    
	// Return the dotProduct of two sparse vectors
    public int dotProduct(SparseVector vec) {
        int result = 0;

        for(int i = 0; i < this.arr.length; i++){
            result += this.arr[i] * vec.arr[i];
        }

        return result;
    }
}*/

/*
    Above approach had unnecessary computation even if either of the value in the vector was 0. To avoid that we can have a hashset that stores only non zero values from both vectors and we can save on computation

    Complexity Analysis

Let n be the length of the input array and L be the number of non-zero elements.

Time complexity: O(n) for creating the Hash Map; O(L) for calculating the dot product.

Space complexity: O(L) for creating the Hash Map, as we only store elements that are non-zero. O(1) for calculating the dot product.
*/

/*class SparseVector {
    Map<Integer, Integer> hm;
    SparseVector(int[] nums) {
        this.hm = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            if(nums[i] != 0)
                hm.put(i, nums[i]);
        }
    }
    
	// Return the dotProduct of two sparse vectors
    public int dotProduct(SparseVector vec) {
        int result = 0;
        for (int i : this.hm.keySet()){
            if(vec.hm.containsKey(i)){
                result += this.hm.get(i) * vec.hm.get(i);
            }
        }

        return result;
    }
}*/

class SparseVector {

    List<int[]> nonZero;

    SparseVector(int[] nums) {
        nonZero = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nonZero.add(new int[]{i, nums[i]});
            }
        }
    }

    // Return the dotProduct of two sparse vectors
    public int dotProduct(SparseVector vec) {
        int result = 0, i = 0, j = 0;
        while (i < nonZero.size() && j < vec.nonZero.size()) {
            if (nonZero.get(i)[0] == vec.nonZero.get(j)[0]) {
                result += nonZero.get(i)[1] * vec.nonZero.get(j)[1];
                i++;
                j++;
            }
            else if (nonZero.get(i)[0] > vec.nonZero.get(j)[0]) {
                j++;
            }
            else {
                i++;
            }
        }
        return result;
    }
}

// Your SparseVector object will be instantiated and called as such:
// SparseVector v1 = new SparseVector(nums1);
// SparseVector v2 = new SparseVector(nums2);
// int ans = v1.dotProduct(v2);