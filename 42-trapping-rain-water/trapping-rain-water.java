/*
    Previous submission used monotonic stack with nearest greater element on th eleft to find the trapping water. This is O(n) time and space complexity. To improve on space complexity we can use two pointers instead of stack.

    We can have a left pointer and a right pointer and we can also maintain left_max and right_max which is the maximum height on the left and the right. 

    Algorithm. We check which one is smaller between left and right max. We take the smaller max Height. 
    If it is left we calculate water from left pointer to left max or in other words we calculate left. Why does it work. because if left max is smaller than right max, we know that the boundary on the right being bigger will be able to store the water upto the leftmax which is currently smaller. Similarly we calculate for right by checking right max.
    Calculation - when we check and find leftMax is smaller, we will calculate left. How do we do that. we move the left pointer and then compare the height[left] with leftMax. We update the Leftmax i.e. if left max is greater than the height[left], then left max remains the same, else we update the leftMax as we found a new height in the left which is max. We calculate the water by subtracting leftMax by height[left]. Why this work? Lets say the leftMax was 3 and height[left] is 2. When we comapre we know leftMax is greater to it remains the same and the difference will give the amount of water that can be stored which is 1 here. Lest say the leftMax was 3 and height[left] was 4. Now leftMax will be 4 as we do math.max(leftMax, height[left]) and the difference will be 4-4 so no water can be stored. 

    We do similar calculation for right if rightMax is less than leftmax at any given point. It is also possible that the leftmax and rightmax are equal. in this case we can either do calculation in left or right. It does not matter.
*/

class Solution {
    public int trap(int[] height) {
        int left  = 0;
        int right = height.length-1;
        int leftmax = height[left];
        int rightmax = height[right];
        int result = 0;

        while(left < right){
            if(leftmax <= rightmax){
                left++;
                leftmax = Math.max(leftmax, height[left]);
                result += leftmax - height[left];
            } else{
                right--;
                rightmax = Math.max(rightmax, height[right]);
                result += rightmax - height[right];
            }
        }

        return result;
    }
}

/*class Solution {
    public int trap(int[] height) {
        int n = height.length;
        int[] leftMax = new int[n];
        int[] rightMax = new int[n];
        int result = 0;
        int lMax = Integer.MIN_VALUE;
        for(int i = 0; i < n; i++){
            lMax = Math.max(lMax, height[i]);
            leftMax[i] = lMax;
        }
        int rMax = Integer.MIN_VALUE;
        for(int i = n-1; i >= 0; i--){
            rMax = Math.max(rMax, height[i]);
            rightMax[i] = rMax;  
        }

        for(int i = 0; i < n; i++){
            if(Math.min(leftMax[i], rightMax[i]) > height[i])
            result+= Math.min(leftMax[i], rightMax[i]) - height[i];
        }

        return result;
    }
}*/

/*
    Below implementation id using monotonic stack. For any given height if we can find the nearest larger element, we know that we got a pillar on the left which is larger than the current height and we can store some water in their. 

    How do we usually implement nearest greater element is we move through the array and keep storing the value in the stack. We only remove when we find that the stack.peek() is less than the current height. When we pop the element we know it is smaller than the height of the current index. Now all we have to do is find if there is a boundary on the left. How does that work.

    Lets take the example of height = [0,1,0,2,1,0,1,3,2,1,2,1].

    When at index 6, the stack will have 3,4,5. When compared with index 5, it will pop it out as the height is lower than at index 6 and do the calculation which I will explain later. Next it will go back and chaek the next stack.peek which at this point is 4 and it will pop that out too because we will be checking <= in while loop. Hence we will only be left with 3 and then at the end 6 will be inserted.

    When at index 7 where the height is 3, we know the stack will have index 3 and 6. Why not 4 index, because that would be popped when we were ar index 6 as they are equal in height. as mentioned above. Now since the stack.peek is 6 and the height is 1 which is less than the height at index 7 which is 3, it will pop it out. Once we have poped out the element, to find if the water can be stored we have to find the boundary to the left. If you look at the stack implementation. It will have the element which are larger than the index before the current index. i.e. height[7], the previous height[6] is 1 and the stack will have the next element greater than the height[6] which will be height[3]. So we can say that if the stack is not empty, we know that there will be a boundary to the left. 

    So we have the logic inside the if condition (!stack.isEmpty()). Now we pop and store the index of the previous element which is 6 as it is of less height than of index 7 where we currently are. As we move into the if condition we know that there is a left boundry at index 3. How do we calculate how much water we can store. we take the minimum of the current index which is 7 and the stack.peek to get the overall height and then subtract with the prev index which is 6. Why? Because we we are comparing and getting the water stored for index 6 while we are processing index 7. Index 6 is already 1 so we have to subtract the height from the left boundry to get the height. For width, we take the index i - 1 which is previous element and subtract it with the stack.peek index to get the width. We got the heoght and weight and we can calculate the ater stored between index 3 and 7. which will be height 1 * width 3 which will be 3. Technically we are storing 4 untis of water and not 3. Why there is a discripency. Actually there is not. It seems that we are not calculating the water storage at index 5 but tahts not correct.

   Water storage at index 5 will be calculated before we even reach index 7. It will be calculated at index 6 ebcause at that point stack will have 3,4,5 and we will pop 5.then calculate the height and width. at index 6 before we push it into the stack, we pop index 5 anc compare it with the stack.peek which is index 4. The min(height[stack.peek], height[i]) will return 1 as both are same and then we subtract it with the height[index popped] which is 0. So overall the height will be 1 and width will be i-1 which is 5 - stack.peek which is 4 hence it will be 1. SO we would have already added a 1 to the answer.
*/

/*class Solution {
    public int trap(int[] height) {
        Stack<Integer> stack = new Stack<>();
        int ans = 0;
        for(int i = 0; i < height.length; i++){
            while(!stack.isEmpty() && height[stack.peek()] <= height[i]){
                int index = stack.pop();
                if(!stack.isEmpty()){
                    int h = Math.min(height[stack.peek()], height[i]) - height[index];
                    int w = i - 1 - stack.peek();
                    ans += h * w;
                }
            }
            stack.push(i);
        }
        return ans;
    }
}*/