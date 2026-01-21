/*class BoundedBlockingQueue {
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
}*/

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class BoundedBlockingQueue {
    private int[] queue;
    private int capacity;
    private int size;
    private int head;  // Index where we dequeue from
    private int tail;  // Index where we enqueue to
    
    private Lock lock;              // Mutual exclusion lock
    private Condition notFull;      // Condition for producers to wait when queue is full
    private Condition notEmpty;     // Condition for consumers to wait when queue is empty
    
    public BoundedBlockingQueue(int capacity) {
        this.capacity = capacity;
        this.queue = new int[capacity];
        this.size = 0;
        this.head = 0;
        this.tail = 0;
        
        this.lock = new ReentrantLock();
        this.notFull = lock.newCondition();
        this.notEmpty = lock.newCondition();
    }
    
    public void enqueue(int element) throws InterruptedException {
        lock.lock();  // Acquire the lock
        try {
            // Wait while the queue is full - only producers wait on this condition
            while (size == capacity) {
                notFull.await();  // Release lock and wait on notFull condition
            }
            
            // Add element to the tail position
            queue[tail] = element;
            tail = (tail + 1) % capacity;
            size++;
            
            // Signal ONE waiting consumer that an element is available
            notEmpty.signal();
        } finally {
            lock.unlock();  // Always release the lock
        }
    }
    
    public int dequeue() throws InterruptedException {
        lock.lock();  // Acquire the lock
        try {
            // Wait while the queue is empty - only consumers wait on this condition
            while (size == 0) {
                notEmpty.await();  // Release lock and wait on notEmpty condition
            }
            
            // Remove element from the head position
            int element = queue[head];
            head = (head + 1) % capacity;
            size--;
            
            // Signal ONE waiting producer that space is available
            notFull.signal();
            
            return element;
        } finally {
            lock.unlock();  // Always release the lock
        }
    }
    
    public int size() {
        lock.lock();  // Need to acquire lock for thread-safe read
        try {
            return size;
        } finally {
            lock.unlock();
        }
    }
}