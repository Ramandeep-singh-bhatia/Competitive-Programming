/*
    Using double ended queue (ArrayDeque)
    Keeps track of the elements in the queue. When the size of the queue exceeds the window size, we discard the earlier element. So we reduce the element from the sum and add the new element. 
    Average will be the sum / either the queue size or the window size, which ever is smaller

    Time - O(M) , M is th enumber of Next method call
    Space - O(size) - Queue size which is window size
*/

/*class MovingAverage {
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
}*/

/*
    Using circular queue with array
    We don't have to delete the first element in the queue everytime a new element is added after we reach window size limit.
    This automatically replaces the element with the new element
    tail = count%size gives me the position of the element that has to be replaced with the new one

    Time - O(M) , M is the number of Next method call
    Space - O(size) - Array size which is window size
*/

class MovingAverage {
    int[] queue;
    int size;
    int count = 0;
    int sum = 0;
    int head = 0;
    public MovingAverage(int size) {
        this.queue =  new int[size];
        this.size = size;
    }

    public double next(int val) {
        count++;

        int tail = count % size;
        sum += val - queue[tail];
        queue[tail] = val;

        return (double) sum / Math.min(count, size);
    }
}

/**
 * Your MovingAverage object will be instantiated and called as such:
 * MovingAverage obj = new MovingAverage(size);
 * double param_1 = obj.next(val);
 */