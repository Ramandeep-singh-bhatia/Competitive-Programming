class MovingAverage {
    Queue<Integer> q;
    int size, sum = 0, count = 0;
    public MovingAverage(int size) {
        q = new LinkedList<>();
        this.size = size;
    }
    
    public double next(int val) {
        count++;
        q.add(val);

        int end = count > this.size ? q.poll() : 0;

        sum += val - end;

        return (double) sum / Math.min(count, this.size);
    }
}

/**
 * Your MovingAverage object will be instantiated and called as such:
 * MovingAverage obj = new MovingAverage(size);
 * double param_1 = obj.next(val);
 */