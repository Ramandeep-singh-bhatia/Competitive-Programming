class AllOne {
    private class BucketNode {
        int count;
        Set<String> keys;
        BucketNode prev;
        BucketNode next;
        BucketNode(int count){
            this.count = count;
            this.keys = new HashSet<>();
        }
    }

    private Map<String, Integer> keyCountMap;
    private Map<Integer, BucketNode> countBucketMap;
    private BucketNode head;
    private BucketNode tail;

    public AllOne() {
        head = new BucketNode(Integer.MIN_VALUE);
        tail = new BucketNode(Integer.MAX_VALUE);

        head.next = tail;
        tail.prev = head;

        keyCountMap = new HashMap<>();
        countBucketMap = new HashMap<>();
    }
    
    public void inc(String key) {
        if(keyCountMap.containsKey(key)){
            changeKey(key, 1);
        } else {
            keyCountMap.put(key, 1);
            if(head.next.count != 1){
                addBucket(new BucketNode(1), head);
            }

            head.next.keys.add(key);
            countBucketMap.put(1, head.next);
        }
    }
    
    public void dec(String key) {
        int count = keyCountMap.get(key);
        if(count == 1){
            keyCountMap.remove(key);
            BucketNode bucket = countBucketMap.get(1);
            bucket.keys.remove(key);
            if(bucket.keys.isEmpty()){
                bucket.prev.next = bucket.next;
                bucket.next.prev = bucket.prev;
                countBucketMap.remove(bucket.count);
            }
        } else {
            changeKey(key, -1);
        }
    }
    
    public String getMaxKey() {
        if(tail.prev == head)
            return "";

        return tail.prev.keys.iterator().next();
    }
    
    public String getMinKey() {
        if(head.next == tail)
            return "";

        return head.next.keys.iterator().next();
    }

    private void changeKey(String key, int num){
        int currentCount = keyCountMap.get(key);
        int newCount = currentCount + num;
        BucketNode currentBucket = countBucketMap.get(currentCount);
        BucketNode newBucket;
        if(countBucketMap.containsKey(newCount)){
            newBucket = countBucketMap.get(newCount);
        } else {
            newBucket = new BucketNode(newCount);
            if(num == 1){
                addBucket(newBucket, currentBucket);
            } else {
                addBucket(newBucket, currentBucket.prev);
            }
            countBucketMap.put(newCount, newBucket);
        }

        newBucket.keys.add(key);
        currentBucket.keys.remove(key);
        if(currentBucket.keys.isEmpty()){
            currentBucket.prev.next = currentBucket.next;
            currentBucket.next.prev = currentBucket.prev;
            countBucketMap.remove(currentBucket.count);
        }

        keyCountMap.put(key, newCount);
    }

    private void addBucket(BucketNode newBucket, BucketNode prevBucket){
        newBucket.prev = prevBucket;
        newBucket.next = prevBucket.next;
        prevBucket.next.prev = newBucket;
        prevBucket.next = newBucket;
    }
}

/**
 * Your AllOne object will be instantiated and called as such:
 * AllOne obj = new AllOne();
 * obj.inc(key);
 * obj.dec(key);
 * String param_3 = obj.getMaxKey();
 * String param_4 = obj.getMinKey();
 */