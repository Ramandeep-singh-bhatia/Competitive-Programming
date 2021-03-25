import java.util.Hashtable;
import java.util.Map;

class isAnagram {
    public boolean isAnagram(String s, String t) {
        Map<Character,Integer> sm = new Hashtable<>();
        Map<Character,Integer> tm = new Hashtable<>();
        int count = 0;
        int a = 0;
        
        for(int i = 0; i< s.length(); i++){
            if(!sm.containsKey(s.charAt(i)))
                sm.put(s.charAt(i),1);
            else{
               count = sm.get(s.charAt(i));
                sm.put(s.charAt(i), count+1);
            }
        }
        
        for(int i = 0; i< t.length(); i++){
            if(!tm.containsKey(t.charAt(i)))
                tm.put(t.charAt(i),1);
            else{
               count = tm.get(t.charAt(i));
                tm.put(t.charAt(i), count+1);
            }
        }
        
        for(Map.Entry<Character, Integer> entry : sm.entrySet()){
            a = entry.getValue();
            
            if(tm.get(entry.getKey()) != entry.getValue())
                return false;
        }

	for(Map.Entry<Character, Integer> entry : tm.entrySet()){
          
            
            if(sm.get(entry.getKey()) != entry.getValue())
                return false;
        }
        
        return true;
    }

     	public static void main(String[] args){
		isAnagram ia = new isAnagram();
		String a = ;

String b = ;
		System.out.println(ia.isAnagram(a,b));
	}
}