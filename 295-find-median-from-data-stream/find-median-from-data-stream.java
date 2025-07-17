/*
Time - O(log N)
Space - O(N)

    We need to keep the ordered integer list (ascending order) and at any given point the findMedian is called, we have to return the median which is middle value for odd size and average of two middle values for even size.
    Initial thought was to use priority queue but when we have to find the median, we will have to get the middle element. We will have to loop through the priority queue and get the middle element making it inefficient -O(n)
    If we maintain two priority queue, one maxheap and one minheap, we can get the middle elements in O(1). middle elements will be the top element for both heaps. But for odd number of elements there would be only one middle value. 
    What we can do is, when we get the element and if it is less than the top most element of the maxHeap, we will have to put it in the maxHeap else we put it in minHeap. 
    We can check the size of the maxHeap and minHeap. If maxHeap size is smaller than minHeap we will have to put some element into maxHeap.
    Also if the number fo numbers in maxHeap is more than 1 + minHeap, we will have to move some element to minHeap.


*/

class MedianFinder {
    PriorityQueue<Integer> maxHeap;
    PriorityQueue<Integer> minHeap;
    public MedianFinder() {
        maxHeap = new PriorityQueue<>((a,b) -> Integer.compare(b,a));
        minHeap = new PriorityQueue<>();
    }
    
    public void addNum(int num) {
        if(maxHeap.isEmpty() || num < maxHeap.peek()){
            maxHeap.add(num);
        } else {
            minHeap.add(num);
        }

        if(maxHeap.size() < minHeap.size()){
            maxHeap.add(minHeap.poll());
        } else if(maxHeap.size() - minHeap.size() > 1){
            minHeap.add(maxHeap.poll());
        }
    }
    
    public double findMedian() {
        int size = maxHeap.size() + minHeap.size();
        if(size%2 == 0){
            return (double)(maxHeap.peek() + minHeap.peek()) / 2;
        }
        return (double) maxHeap.peek();
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */