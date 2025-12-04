/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *     // Constructor initializes an empty nested list.
 *     public NestedInteger();
 *
 *     // Constructor initializes a single integer.
 *     public NestedInteger(int value);
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // Set this NestedInteger to hold a single integer.
 *     public void setInteger(int value);
 *
 *     // Set this NestedInteger to hold a nested list and adds a nested integer to it.
 *     public void add(NestedInteger ni);
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return empty list if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
class Solution {
    
    public int depthSumInverse(List<NestedInteger> nestedList) {
        int maxDepth = getMaxDepth(nestedList);
        System.out.println(maxDepth);
        int sumTotal = 0;
        Queue<List<NestedInteger>> q = new LinkedList<>();
        q.add(nestedList);
        int depth = 1;
        while(!q.isEmpty()){
            int size = q.size();
            maxDepth = Math.max(maxDepth, depth);
            for(int i = 0; i < size; i++){
                List<NestedInteger> nested = q.poll();
                for(NestedInteger ni : nested){
                    if(!ni.isInteger()){
                        q.add(ni.getList());
                    } else {
                        int weight = maxDepth - depth + 1;
                        sumTotal += ni.getInteger() * weight;
                    }
                }
            }
            depth++;
        }
        return sumTotal;
    }

    private int getMaxDepth(List<NestedInteger> nestedList){
        Queue<List<NestedInteger>> q = new LinkedList<>();
        q.add(nestedList);
        int maxDepth = 1;
        int depth = 1;
        while(!q.isEmpty()){
            int size = q.size();
            maxDepth = Math.max(maxDepth, depth);
            for(int i = 0; i < size; i++){
                List<NestedInteger> nested = q.poll();
                for(NestedInteger ni : nested){
                    if(!ni.isInteger() && ni.getList().size() > 0){
                        q.add(ni.getList());
                    }
                }
            }
            depth++;
        }
        return maxDepth;
    }
}