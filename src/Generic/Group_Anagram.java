package Generic;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
public class Group_Anagram {
    public List<List<String>> groupAnagrams(String[] strs) {
        // Compare contents of string  by sorting or not
        Map<String, List<String>> mapStringToAscii = new HashMap<>();

        for (String str : strs) {
            int ascii = 0;
            for (char c : str.toCharArray()) {
                ascii = ascii + c;
            }
            if (mapStringToAscii.containsKey(ascii)) mapStringToAscii.get(ascii).add(str);
            else mapStringToAscii.put(str, new ArrayList<String>() {
                {
                    add(str);
                }
            });
          }
            List<List<String>> output = new ArrayList<>();
            for (List<String> list : mapStringToAscii.values()
            ) {
                output.add(list);
            }
        return output;
    }
    void printDistinctPermutn(String str, String ans, List<String> list)  {
        // If string is empty
        if (str.length() == 0) {
            // print ans
            System.out.print(ans + " ");
            list.add(ans);
            return;
        }
        // Make a boolean array of size '26' which
        // stores false by default and make true
        // at the position which alphabet is being
        // used
        boolean alpha[] = new boolean[26];
        for (int i = 0; i < str.length(); i++) {
            // ith character of str
            char ch = str.charAt(i);
            // Rest of the string after excluding
            // the ith character
            String ros = str.substring(0, i) +  str.substring(i + 1);
            // If the character has not been used
            // then recursive call will take place.
            // Otherwise, there will be no recursive
            // call
            if (alpha[ch - 'a'] == false)
                printDistinctPermutn(ros, ans + ch,list);
            alpha[ch - 'a'] = true;
        }
    }
    public List<Integer> findAnagrams(String s, String p) {
        int[] map = new int[26];
        List<Integer> result = new ArrayList<>();

        for(int i=0;i<p.length();i++){
            map[p.charAt(i) - 'a']++;
        }

        int windowStart = 0;
        int windowEnd = 0;
        while(windowEnd<s.length()){
            // valid anagram
            if(map[s.charAt(windowEnd) - 'a'] > 0){
                map[s.charAt(windowEnd++) - 'a']--;
                // window size equal to size of P
                if(windowEnd-windowStart ==  p.length()){
                    result.add(windowStart);
                }
            } else if(windowStart == windowEnd){ // window is of size 0
                windowStart ++;
                windowEnd ++;
            } else {   // decrease window size
                map[s.charAt(windowStart++) - 'a']++;
            }
        }
        return result;
    }
    public int singleNonDuplicate() {
        return binarySearch(new int[] {1,1,2,3,3,4,4,8,8});
    }

    private int binarySearch(int[] arr){
        int left = 0;
        int right = arr.length-1;
        while(left < right){
            int mid = left + (right - left) / 2;
            if (mid ==0 && left == 0) return mid;
            else if (mid == right && right == arr.length-1) return right;
            if((arr[mid+1] != arr[mid]) && arr[mid-1] != arr[mid]) return mid ;

            if(((right-mid)%2 == 0 && arr[mid+1] == arr[mid]) ){
                left = mid + 2;  // Go to right half, also when even and found on right side, odd and found on left side
            }
            else if ((right-mid)%2 != 0 && arr[mid-1] == arr[mid]) left = mid + 1;
            else if ((right-mid)%2 == 0 && arr[mid-1] == arr[mid]){
                right = mid-2;     // Go to left half, also when even and found on left side, odd and found on right side
            }
            else if ((right-mid)%2 != 0 && arr[mid+1] == arr[mid])  right = mid-1;
        }
        return left;
    }
    public  static void  main(String args[])
    {
        String [] list = {"abc"};
        new Group_Anagram().groupAnagrams(list);
        new Group_Anagram().findAnagrams("cbaebabacd", "abc");
        new Group_Anagram().singleNonDuplicate();
    }
}
