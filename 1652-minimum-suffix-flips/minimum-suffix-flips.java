/*
    To move from 00000 to 10111 we can start from the 0th index and flip everything once. After 1 operation we know that we have flipped the left most index. Next we go to the second index and see if it is equal to the target or not and flip everything from second index to the end and so on. Everytime we compare with the target and if the character is not same as in the target, we flip everything
    Time - O(n^2)
    Space - O(n)
*/

/*class Solution {
    public int minFlips(String target) {
        int n= target.length();
        char[] s = new char[n];
        int result = 0;
        for(int i = 0; i < n; i++){
            s[i] = '0';
        }

        for(int i = 0; i < n; i++){
            if(target.charAt(i) != s[i]){
                result++;
                for(int j = i; j < n; j++){
                    s[j] = (s[j] == '0') ? '1' : '0';
                }
            }
        }

        return result;
    }
}*/

/*
    If we have done 2 flips for the index, the value at the index will be come 0 again because 1 flips make it 1 and 2 flips make it 0
    Time - O(n)
    Space - O(1)
*/

class Solution {
    public int minFlips(String target) {
        int flips = 0;
        int n = target.length();
        for(int i = 0; i < n; i++){
            int curr = flips & 1; // Alternate - flips % 2;
            if(curr != (target.charAt(i) - '0'))
                flips++;
        }

        return flips;
    }
}