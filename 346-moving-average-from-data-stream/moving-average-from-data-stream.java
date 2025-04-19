/*
    Using double ended queue (ArrayDeque)
    Keeps track of the elements in the queue. When the size of the queue exceeds the window size, we discard the earlier element. So we reduce the element from the sum and add the new element. 
    Average will be the sum / either the queue size or the window size, which ever is smaller
*/

class MovingAverage {
    Queue<Integer> q;
    int size, sum = 0, count = 0;
    public MovingAverage(int size) {
        q = new ArrayDeque<>();
        this.size = size;
    }
    
    public double next(int val) {
        q.add(val);

        int discard = q.size() > this.size ? q.poll() : 0;

        sum += val - discard;

        return (double) sum / Math.min(q.size(), this.size);
    }
}

/**
 * Your MovingAverage object will be instantiated and called as such:
 * MovingAverage obj = new MovingAverage(size);
 * double param_1 = obj.next(val);
 */