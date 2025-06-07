class Solution {
    public int[] countBits(int n) {
        int[] result = new int[n+1];
        result[0] = 0;
        for(int i = 1; i<= n; i++){
            result[i] = getCount(i);
        }

        return result;
    }

    private int getCount(int i){
        int count = 0;
        while(i >=1){
            i = i&(i-1);
            count++;
        }

        return count;
    }
}