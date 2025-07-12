// Brute Force

/*class Solution {
    public long kthSmallestProduct(int[] nums1, int[] nums2, long k) {
        Queue<Long> pq = new PriorityQueue<>((a,b) -> Long.compare(b,a));
        for(int num1: nums1){
            for(int num2 : nums2){
                pq.add((long)num1 * num2);
                if(pq.size() > k){
                    pq.poll();
                }
            }
        }
        return pq.peek();
    }
}*/

/*
    Brute force takes O(n*m) time to create all products and O(k) sapce for the priority queue
    To improve this we can actually make use of binary search as the arrays are sorted.
    Lets take and example and do a dry run
    nums1 = [2,5] and nums2 = [3,4,6] , k = 5
    Products will be 6,8,12,15,20,30
    If we look at the products lest say we have i = 0 for nums1 and j = 2 for nums2. The product is 12. All the products that wil be formed before 12 will be smaller than 12. So even if we don't calculate the products we can still calculate the number of products before the current product. The number of products uptil here is 3 and k is 5 we know that we have to check to find number higher than 12 meaning we can move i by 1 and try to find the product . At this point the product for i = 1 for nums1 and j = 2 for nums2 will be 30 and the number of products at this point is 6 and we have to find the product smaller than 30. 
    Now first thing we can d. is we can try to take the product os nums1 one by one with the number is nums2 using binary search. We can keep track of the number of products we will get for the given index combination of i in nums1 and j in nums2. When we reach the count == k we will return the product. Also for products we can do another binary search for the between the min product value and max product value. 
    If the product that we got at certain i and j combination is greater than the one we are trying to find, we will move towards left to find the one that is equal to the product . But since the nums in nums1 and nums2 is -ve, when we get a product it will be -ve and to find the larger product, we will have to move to the right instead of left. 
    So we will do a binary search for the product range and for each product will will try to find the combination of num1 and num2. If the product is larger than the required product we will move to the left in nums2 in case of +ve product or vice versa. Similarly we check if the product that we calculated is smaller than the product we are trying to find and move right or left using binary search. Once we find the product, we can return the number of products. We will be doing a binary search on products as well and when we get the number of products > k , we know we will have to check for smaller product to find the number of products = k
    Time - (n1 logn2 log(Total num of products) )
    Space - O(1)
*/

/*class Solution {
    public long kthSmallestProduct(int[] nums1, int[] nums2, long k) {
        long left = -10000000000L, right = 10000000000L;
        long result = 0;
        while(left <= right){
            long mid = left + (right - left) / 2;
            long count = getCount(nums1, nums2, mid);
            if(count >= k){
                result = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return result;
    }

    private long getCount(int[] nums1, int[] nums2, long midProduct){
        long productCount = 0;
        for(int i = 0; i< nums1.length; i++){
            if(nums1[i] >= 0){
                int left = 0, right = nums2.length -1;
                int pos = -1;
                while(left <= right){
                    int mid = left + (right - left) / 2;
                    long product = (long)nums1[i] * nums2[mid];
                    if(product <= midProduct){
                        pos = mid;
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                }
                productCount += (pos + 1);
            } else {
                int left = 0, right = nums2.length -1;
                int pos = nums2.length;
                while(left <= right){
                    int mid = left + (right - left) / 2;
                    long product = (long)nums1[i] * nums2[mid];
                    if(product <= midProduct){
                        pos = mid;
                        right = mid - 1;
                       
                    } else {
                         left = mid + 1;
                    }
                }
                productCount += (nums2.length - pos);
            }
        }
        return productCount;
    }
}*/

class Solution {

    int f(int[] nums2, long x1, long v) {
        int n2 = nums2.length;
        int left = 0, right = n2 - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            long prod = (long) nums2[mid] * x1;
            if ((x1 >= 0 && prod <= v) || (x1 < 0 && prod > v)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        if (x1 >= 0) {
            return left;
        } else {
            return n2 - left;
        }
    }

    public long kthSmallestProduct(int[] nums1, int[] nums2, long k) {
        int n1 = nums1.length;
        long left = -10000000000L, right = 10000000000L;
        while (left <= right) {
            long mid = (left + right) / 2;
            long count = 0;
            for (int i = 0; i < n1; i++) {
                count += f(nums2, nums1[i], mid);
            }
            if (count < k) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
}