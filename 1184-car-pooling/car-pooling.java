/*
    The trips length will be between 1 and 1000 and also the range for form and to is also between 0 and 1000 so max value for N (Trips length) will be 1001 and the second loop will also me maximum of 1001.
    Overall the time complexity will be O(N + 1001). If N is maximum then time complexity will be N else it will be O(1001)

    Space. - O(1001) whis is O(1)

*/
class Solution {
    public boolean carPooling(int[][] trips, int capacity) {
    int maxLocation = 0;
    for (int[] trip : trips) {
        maxLocation = Math.max(maxLocation, trip[2]);
    }
    
    int[] passengerChanges = new int[maxLocation + 1];
    
    for (int[] trip : trips) {
        int passengers = trip[0];
        int pickup = trip[1];
        int dropoff = trip[2];
        
        passengerChanges[pickup] += passengers;
        passengerChanges[dropoff] -= passengers;
    }
    
    // Calculate cumulative sum and check capacity
    int currentPassengers = 0;
    for (int i = 0; i <= maxLocation; i++) {
        currentPassengers += passengerChanges[i];
        if (currentPassengers > capacity) {
            return false;
        }
    }
    
    return true;
    }
}