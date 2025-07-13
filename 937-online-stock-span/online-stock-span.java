class StockSpanner {
    List<Integer> stocks;
    Stack<Integer> s;
    int index;
    public StockSpanner() {
        stocks = new ArrayList<>();
        s = new Stack<>();
        s.push(-1);
        index = 0;
    }
    
    public int next(int price) {
        stocks.add(price);
        int result = 0;
        
        while(s.peek() != -1 && stocks.get(s.peek()) <= price){
            s.pop();
        }
        result = index - s.peek();
        s.push(index++);
        return result;
    }
}

/**
 * Your StockSpanner object will be instantiated and called as such:
 * StockSpanner obj = new StockSpanner();
 * int param_1 = obj.next(price);
 */