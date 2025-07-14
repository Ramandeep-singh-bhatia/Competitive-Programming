/*class Solution {
    public boolean isHappy(int n) {
        
        Set<Integer> s = new HashSet<>();
        
        while (!s.contains(n)){
            s.add(n);   
            n = square(n); 
        }
        
        return n == 1;
    }

    private int square(int num) {
        int ans = 0;
        while(num > 0) {
            int remainder = num % 10;
            ans += remainder * remainder;
            num /= 10;
        }
        return ans;
    }
}*/

class Solution {
    public boolean isHappy(int n) {
        
        int slow = n;
        int fast = square(n);
        while (slow != fast) {
            slow = square(slow);
            fast = square(square(fast));
        } 

        return slow == 1;
    }

    private int square(int num) {
        int ans = 0;
        while(num > 0) {
            int remainder = num % 10;
            ans += remainder * remainder;
            num /= 10;
        }
        return ans;
    }
}