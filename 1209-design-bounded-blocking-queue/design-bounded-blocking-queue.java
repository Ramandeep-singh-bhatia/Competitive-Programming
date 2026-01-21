class BoundedBlockingQueue {
    private int[] queue;
    private int capacity;
    private int size;
    private int head;
    private int tail;

    public BoundedBlockingQueue(int capacity) {
        this.capacity = capacity;
        this.queue = new int[capacity];
        this.size = 0;
        this.head = 0;
        this.tail = 0;
    }
    
    public synchronized void enqueue(int element) throws InterruptedException {
        // Wait while the queue is full
        while (size == capacity) {
            wait();  // Release the lock and wait for notification
        }
        
        // Add element to the tail position
        queue[tail] = element;
        tail = (tail + 1) % capacity;  // Circular array - wrap around
        size++;
        
        // Notify waiting threads (consumers) that an element is available
        notifyAll();
    }
    
    public synchronized int dequeue() throws InterruptedException {
        // Wait while the queue is empty
        while (size == 0) {
            wait();  // Release the lock and wait for notification
        }
        
        // Remove element from the head position
        int element = queue[head];
        head = (head + 1) % capacity;  // Circular array - wrap around
        size--;
        
        // Notify waiting threads (producers) that space is available
        notifyAll();
        
        return element;
    }
    
    public synchronized int size() {
        return size;
    }
}