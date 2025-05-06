/*
    Time - O(nlogn) base 10
    Space - O(logn)
*/

/*class Solution {

    public int countLargestGroup(int n) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int max = 0;
        for (int i = 1; i <= n; ++i) {
            int sum = 0;
            int j = i;
            while (j != 0) {
                sum += j % 10;
                j /= 10;
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
            max = Math.max(max, map.get(sum));
        }
        int count = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == max) {
                count++;
            }
        }
        return count;
    }
}*/

/*
    If we look at the constriant 1 <= n <= 10^4 and we have to calculate the sum of the digits, the largest sum will come from 9999 which is 36. SO instead of using map we can use an array of size 37
*/

class Solution {
    public int countLargestGroup(int n) {
        if(n <= 9)
            return n;
        int max = 0;
        int arr[] = new int[37];
        for(int i=1; i<=n; i++) {
            int sum = 0;
            int j = i;
            while (j != 0) {
                sum += j % 10;
                j /= 10;
            }
            arr[sum] = arr[sum] + 1;
            max = Math.max(max,arr[sum]);
        }
        int count = 0;
        for (int freq : arr) {
            if (freq == max) 
                count++;
        }
        return count;

    }
}