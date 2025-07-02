/*
    We have to find the maximum manhattan distance at any given point when we are navigating through the string s which means at every step we keep track of the maximum distance. Lets take an example - S = "NS" and k = 1
    When we are at N, we move North and the distance is 1
    When we are at S, we move South and come back to origin the distance is 0.
    Maximum will be 1. But we have an option of changing one direction as k is 1.
    Lets think what would have happended if we change S to N. At that point the distance would have become 2 and the max would have increased. 
    If we look closely, Ideal would have been NN and the distance 2 but becuase it was NS, the distance when we reached S became 0. Meaning we wasted 2 distance because of S. To Maximize we can add this wasted distance which implies that we have changed the direction. But we cannot blindly add the wasted distance. We can only add at most k time and every time we flip, we can add 2 to the distance and keep track of maximum.
    Example - NSWWEW, k = 3
     N - i = 0, dis = 1, wasted = dis - (i + 1) = 0. No wasted distance so we move on
     S - i = 1, dis = 0, wasted = 0 - (i+1) = 2. We have wasted distance so we add it to the actual distance and maintain a maximum distance. At this point k = 3 We can either add the wasted or 2 * k which ever is minimum. Why? becuase we can atmost do k flips so we cnnot go beyond that. Everytime we flip we add 2 to the distance. We know we have calculated the wasted distance at this point, if that wasted distance is more than k*2 we can only add k*2 as we have to do k flips at most. if it is less then we only add the wasted part.
*/

class Solution {
    public int maxDistance(String s, int k) {
        int north = 0, south = 0, west = 0, east = 0;
        int result = Integer.MIN_VALUE;
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == 'N')
                north++;
            else if (s.charAt(i)  == 'S')
                south++;
            else if (s.charAt(i) == 'E')
                east++;
            else 
                west++;

            int currentMD = Math.abs(north - south) + Math.abs(west - east);
            int wasted = i + 1 - currentMD;
            int dis = Math.min(2*k, wasted);

            result = Math.max(result, dis + currentMD);
        }

        return result;
    }
}