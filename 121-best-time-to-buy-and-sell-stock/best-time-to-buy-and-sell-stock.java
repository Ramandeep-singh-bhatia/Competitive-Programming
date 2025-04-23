/*
    How will I find the maximum profit on a given day when we sell is wehn we have bought at the minimum amount before selling it. SO all we need to do is maintain the min value of the stock left to the day we are planning to sell. In a way we are calculating the max profit by subtracting the buying amount which should be minimum because we can only sell if we have bouth it before.
*/


/*class Solution {
    public int maxProfit(int[] prices) {
        int cost = prices[0];
        int maxProfit = 0;

        for(int i = 1; i < prices.length; i++) {
            
            maxProfit = Math.max(maxProfit, prices[i] - cost);
            cost = Math.min(cost, prices[i]);
        }

        return maxProfit;
    }
}*/

// Kadane's algorithm
class Solution {
    public int maxProfit(int[] prices) {
        int max = 0;
        int sum = 0;
    
        for (int i = 0; i < prices.length - 1; i++){
            sum += prices[i + 1] - prices[i];
            
            if (sum > max)
                max = sum;
            else if (sum < 0)
                sum = 0;
        }
        
        return max;
    }
}