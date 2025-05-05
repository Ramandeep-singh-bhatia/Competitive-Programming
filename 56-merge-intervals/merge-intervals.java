/*
    Intution.

    Sort the array as per the first element in the interval.
    if the first element in second interval is less than the second element in the first 
    interval it can be merged. else we add the interval to result.

    1. We maintain a newInterval array to keep track of the updated array before passing it on to the result. 
    2. we will add the first interval into newIntervals and result
    3. we loop through each interval and compare the first element in second interval to the second element in the first interval which is 
    in the newInterval array.
    4. if it is true, we update the newInterval second element with the interval second element, We will keep doing it until the condition 
    is false. example the interval is [[1,3], [2,5], [4,8], [9,12]]. the new interval will have the value [1,8] before the 
    condition is false.

    In next iteration when the condition is false we will update the newInterval and also add that to the result.

    In the end we will return the result   
*/

class Solution {
    public int[][] merge(int[][] intervals) {
        List<int[]> result = new ArrayList<>();

        //Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        Arrays.sort(intervals, (a,b) -> a[0] - b[0]);

        //int[] newInterval = intervals[0];
        result.add(intervals[0]);
        for(int[] interval : intervals){
            if(interval[0] <= result.get(result.size() - 1)[1]){
                result.get(result.size() - 1)[1] = Math.max(interval[1], result.get(result.size() - 1)[1]);
            } else {
                //newInterval = interval;
                result.add(interval);
            }
        }

        /*
            Alternate approach

        int[][] array = new int[result.size()][];
        for (int i = 0; i < result.size(); i++) {
            array[i] = result.get(i);
        }

        return array; */

        return result.toArray(new int[result.size()][]);

        
    }
}