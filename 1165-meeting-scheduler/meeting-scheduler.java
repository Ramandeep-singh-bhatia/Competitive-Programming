/*class Solution {
    public List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {
        Arrays.sort(slots1, (a, b) -> a[0] - b[0]);
        Arrays.sort(slots2, (a, b) -> a[0] - b[0]);

        int pointer1 = 0, pointer2 = 0;

        while (pointer1 < slots1.length && pointer2 < slots2.length) {
            // find the boundaries of the intersection, or the common slot
            int intersectLeft = Math.max(slots1[pointer1][0], slots2[pointer2][0]);
            int intersectRight = Math.min(slots1[pointer1][1], slots2[pointer2][1]);
            if (intersectRight - intersectLeft >= duration) {
                return new ArrayList<Integer>(Arrays.asList(intersectLeft, intersectLeft + duration));
            }
            // always move the one that ends earlier
            if (slots1[pointer1][1] < slots2[pointer2][1]) {
                pointer1++;
            } else {
                pointer2++;
            }
        }
        return new ArrayList<Integer>();
    }
}*/

/*
    Heap

    Another approach of systematically selecting slots and comparing them is using a heap. We would initialize a heap timeslots and push all of the time slots into it.

The key idea here is that we only need one heap. That is, we can put the time slots for both people into the same heap, and then if we find a common time slot, we know that the two-time slots couldn't possibly be for the same person.

Time complexity: O((M+N)log(M+N)), when M is the length of slots1 and N is the length of slots2.

There are two parts to be analyzed: 1) building up the heap; 2) the iteration when we keep popping elements from the heap. For the second part, popping one element takes O(log(M+N)), therefore, in the worst case, popping M+N elements takes O((M+N)log(M+N)).

Space - O(M+N). This is because we store all M+N time slots in a heap.
*/

class Solution {
    public List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {
        PriorityQueue<int[]> timeslots = new PriorityQueue<>((slot1, slot2) -> slot1[0] - slot2[0]);

        for (int[] slot: slots1) {
            if (slot[1] - slot[0] >= duration) timeslots.offer(slot);
        }
        for (int[] slot: slots2) {
            if (slot[1] - slot[0] >= duration) timeslots.offer(slot);
        }

        while (timeslots.size() > 1) {
            int[] slot1 = timeslots.poll();
            int[] slot2 = timeslots.peek();
            if (slot1[1] >= slot2[0] + duration) {
                return new ArrayList<Integer>(Arrays.asList(slot2[0], slot2[0] + duration));
            }
        }
        return new ArrayList<Integer>();
    }
}