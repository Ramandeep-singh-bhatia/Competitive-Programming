/*
The input string s is trimmed to remove any leading or trailing spaces using the trim() method.
The trimmed string is then split into an array of words using the split("\\s+") method. The regular expression "\s+" matches one or more whitespace characters, effectively separating the words.
*/

/*class Solution {
    public String reverseWords(String s) {
        String[] arr = s.trim().split("\\s+");
        String result = "";

        for(int i = arr.length - 1; i >= 0; i--){
            result += arr[i] + " ";
        }

        return result.trim();
    }
}*/

class Solution {
    public String reverseWords(String s) {
        int l = 0;
        int r = s.length() - 1;
        while(l <= r && s.charAt(l) == ' '){
                ++l;
        }

        while(l <= r && s.charAt(r) == ' '){
                --r;
        }

        StringBuilder word = new StringBuilder();
        Deque<String> result = new ArrayDeque<>();

        while(l <= r){
            char c = s.charAt(l);
            if(c != ' '){
                word.append(c);
            } else if((word.length() != 0) && (c == ' ')){
                result.offerFirst(word.toString());
                word = new StringBuilder();
            }
            ++l;
        }

        result.offerFirst(word.toString());

        //return String.join(" ", result);

        // Alternate

        StringBuilder sb = new StringBuilder(result.pollFirst());
        while (!result.isEmpty()) {
            sb.append(" ").append(result.pollFirst());
        }
        return sb.toString();

    }
}