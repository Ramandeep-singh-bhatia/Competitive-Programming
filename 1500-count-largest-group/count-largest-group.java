class Solution {

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
}