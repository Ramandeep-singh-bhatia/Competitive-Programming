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