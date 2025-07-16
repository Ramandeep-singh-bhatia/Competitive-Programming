class Solution {
    public int maxNumberOfAlloys(int n, int k, int budget, List<List<Integer>> composition, List<Integer> stock, List<Integer> cost) {
        int maxAlloys = Integer.MIN_VALUE;
        for(int machine = 0; machine < k; machine++){
            int left = 0; 
            int right = 2*100000000;
            while (left <= right){
                int mid = left + (right - left) / 2;
                if(canProduce(mid, machine, composition, stock, cost, budget)){
                    maxAlloys = Math.max(maxAlloys, mid);
                    left = mid + 1;
                } else
                    right = mid - 1;
            }
        }

        return maxAlloys;
    }

    private boolean canProduce(int alloys, int machine, List<List<Integer>> composition, List<Integer> stock, List<Integer> cost, int budget){
        long total = 0;
        for(int i = 0; i < composition.get(machine).size(); i++){
            long required = (long)alloys * composition.get(machine).get(i);
            long available = stock.get(i);
            if( required > available){
                total += (required - available) * cost.get(i);
                if(total > budget)
                    return false;
            }
            
        }

        return total <= budget;
    }

}