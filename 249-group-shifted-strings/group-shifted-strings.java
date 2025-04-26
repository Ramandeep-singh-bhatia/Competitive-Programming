/*
    Intuition - Assign a key that represents a difference in the consicutive 
    charactres in the string. ex - abc - key should be 1,1 as there is 1 character 
    differene between a and b / b and c.

    Have a map that stores the key alnong with the string.

    Match the key in the map. For same key, assign the string into a single array list.
    
    After 1 iteration, pass the ArrayList into the result and return

    for key the calculation is difference between two consecutive character. This can
    be a negative number hence add 26 before taking the modulo. ba has the difference 
    of -1. adding 26 will get us to 25. Modulo will make sure the value is always 
    less than 26.

    az when rotated once will be equal to ba but if there is no modulo, az will be 
    25 + 26 = 51 and ba will be -1 + 26 = 25. Having modulo by 26 makes sure that 
    the value is less tahn 26 and comparable.
    
    Time - O(n*m) where n is number of string and m is average number of characters for each string
    Space - O(n) Each string could have different key so overall n string can be stored. If you consider the space for each key, we can say average space for each key will be m so overall space coult be O(n*m)
*/

class Solution {
    public List<List<String>> groupStrings(String[] strings) {
        List<List<String>> result = new ArrayList<>();

        if(strings.length == 1){
            result.add(new ArrayList<>(List.of(strings[0])));
            return result;
        }

        Map<String, List<String>> map = new HashMap<>();

        for(int i =0; i < strings.length; i++){
            StringBuilder key = new StringBuilder();
            
            for(int j = 1; j < strings[i].length(); j++){
                int diff = (strings[i].charAt(j) - strings[i].charAt(j-1) + 26) % 26;
                key.append(diff);
                key.append(",");
            }

            List<String> currentList = map.getOrDefault(key.toString(), new ArrayList<>());
            currentList.add(strings[i]);
            map.put(key.toString(), currentList);
        }
        
        for(List<String> l : map.values()){
            result.add(l);
        }

        return result;
    }
}