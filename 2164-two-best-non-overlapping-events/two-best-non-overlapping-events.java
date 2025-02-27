/*class Solution {
    public int maxTwoEvents(int[][] events) {
        int result = 0;
        int n = events.length;
        for(int i = 0; i< n; i++){
            result = Math.max(result, events[i][2]);
            for(int j = i+1; j < n; j++){
                if(events[i][1] < events[j][0] || events[j][1] < events[i][0]){
                    int sum = events[i][2] + events[j][2];
                    result = Math.max(result, sum);
                }
            }
        }
        return result;
    }
}*/

/*
    We need to find the maximum value of 2 non overlapping event value sum.  Also we know that the events are not sorted and it makes sense to sort the event based on start time so that we can loop through and find the non overlapping events to get the maximum sum. For any event to find the maximum sum of values we need to find the max value of all the events who has end time before the current event. Meaning if we know that max value of the event which have already finished before the current event, we can find the max valuue of the combination of that event value with the previous event value. 
    In a way when we are looping through the sorted events based on start time, we can keep track of the events and sort them in a data structure based on end time. For each event we can check if we have events which has already ended before this event. We can find the maximum value from those events and add it to the result to find the maximum sum of that value.

    Priority queue can be sorted using end time. So that we can pick all the events which have ended before the current event and find the max value of those events. Next we can add it to the value of the current event to find the maximum among the event. 
    
*/

class Solution {
    public int maxTwoEvents(int[][] events) {
        Arrays.sort(events , (a,b) -> a[0] - b[0]);
        int result = 0;
        int max = 0;
        Queue<int[]> pq = new PriorityQueue<>((a,b) -> a[1] - b[1]);
        for(int[] event : events){
            while(!pq.isEmpty() && pq.peek()[1] < event[0]){
                max = Math.max(max, pq.poll()[2]);
            }
            result = Math.max(result, max + event[2]);
            pq.add(event);
        }

        return result;
    }
}