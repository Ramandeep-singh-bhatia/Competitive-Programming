/*
    Brute force
*/

/*class Solution {
    public char kthCharacter(int k) {
        StringBuilder s = new StringBuilder();
        s.append("a");
        while (s.length() < k) {
            s.append(addString(s));
        }

        return s.charAt(k-1);
    }

    private String addString(StringBuilder temp){
        StringBuilder ans = new StringBuilder();
        for(int i = 0; i < temp.length(); i++){
            ans.append((char)(temp.charAt(i) + 1));
        }
        return ans.toString();
    }
}*/
/*
    Below is how the string is generated
    "a" -> length 1
    "ab" -> length 2
    "abbc" -> length 4
    "abbcbccd" -> length 8
    "abbcbccdbccdcdde" -> length 16
    and so on

    To get a certain character we have generate the string to a length which is just greater than k. Lets say k = 100. So the string will be 128 length then only we can get to the k th character.
    Lets see how we can find k = 15. For k = 15 the length of the string should be 16 so we will have to have a string "abbcbccdbccdcdde" to get the 15th index character. If we look at the the 15th index the binary representation is 1111 and the number of set bits are 4. and if we shift a by 4 , we get e which is the answer. 
    Lets try to get k = 11 which is 10th index. binary representation of 10 is 1010 and the number fo set bit is 2 and we shift a by 2 we get c and the character at 10th index is c. So we can get any character at k - 1 index by getting the number of set bit at that position. 

    "a|b|bc|bccd|bccdcdde"
    After 1 shift a becomes b
    After 2 shifts a becomes c
    After 3 shifts a becomes d
    After 4 shifts a becomes e and so on.
*/

class Solution {
    public char kthCharacter(int k) {
        int count = 0;
        int pos = k - 1;
        while(pos != 0){
            count += (pos & 1);
            pos = pos >> 1;
        }

        return (char)('a' + count);
    }
}