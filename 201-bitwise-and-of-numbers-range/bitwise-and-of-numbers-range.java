/*
    When we do AND operation for all the numbers we end up getting only the bits that are common between all the numbers because AND will only return 1 for all 1's
    Example - left = 5, right = 7
            5 - 101
            6 - 110
            7 - 111
    When we do AND between 5, 6 and 7 the common bit is the left most so we get 100 which is 4. 
    1st apporach is to do and between all numbers and get the result but for larger number it is not an optimal solution. 
    For optimized approach we can loop until we reach a point where bits are common which meand ma dn n becomes same. Until then we right shift both the numbers. Once we find the common bits we have to move that bit backto its original position, so we keep track of the number of position we shift
*/

/*class Solution {
    public int rangeBitwiseAnd(int left, int right) {
        int position = 0;
        while(left < right){
            left = left >> 1;
            right = right >> 1;
            position++;
        }
        return right << position;
    }
}*/

/*
    Another way to do this to keep removing the right most set bit from the right. Once we reach to the common bit , right will be smaller than left so we loop until left < right. In the end we return right
    left = 5, right = 7
    5 - 101
    7 - 111

    when wereach the common bit by removing the right most set bit we get 100 which is smaller than left which is 101.
*/

class Solution {
    public int rangeBitwiseAnd(int left, int right) {
        while(left < right){
            right = right & (right - 1);
        }
        return right;
    }
}