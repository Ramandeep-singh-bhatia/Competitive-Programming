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
/*class Solution {
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
}*/

class Solution {
    public int maxProfit(int[] prices) {
        int minPrice = prices[0]; // lowest buy price seen so far; initialize to first day since we haven't seen anything else yet
        int maxProfit = 0; // best profit seen so far; starts at 0 because we return 0 if no profitable trade exists
        
        // walk through every possible sell day
        for (int i = 1; i < prices.length; i++) {
            // if today's price is lower than anything we've seen, update our best buy opportunity
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            }
            
            int profitIfSellToday = prices[i] - minPrice; // best profit we could get selling today, given the cheapest buy so far
            if (profitIfSellToday > maxProfit) {
                maxProfit = profitIfSellToday; // update best if selling today beats our current record
            }
        }
        
        return maxProfit;
    }
}