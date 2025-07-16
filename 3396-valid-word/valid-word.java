class Solution {
    public boolean isValid(String word) {
        if (word.length() < 3) return false;

        int countV = 0;
        int countC = 0;

        for (char c : word.toCharArray()) {
            if (!Character.isLetterOrDigit(c)) return false;

            if (Character.isLetter(c)) {
                char lower = Character.toLowerCase(c);
                if (isVowel(lower)) {
                    countV++;
                } else {
                    countC++;
                }
            }
        }

        return countV > 0 && countC > 0;
    }

    private boolean isVowel(char c) {
        return (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u');
    }
}