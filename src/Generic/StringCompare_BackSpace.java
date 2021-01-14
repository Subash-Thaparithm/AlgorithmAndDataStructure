package Generic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StringCompare_BackSpace {
    public boolean backspaceCompare(String S, String T) {
        if (removeBackSpace(S).equals(removeBackSpace(T))) return true;
        else return false;
    }
    private List<Character> removeBackSpace(String str) {
        char[] charArr = str.toCharArray();
        List<Character> truncatedArr = new ArrayList<Character>();
        int backspaceCount = 0;
        for (int i = charArr.length - 1; i >= 0; i--) {
            if (charArr[i] == '#') backspaceCount++;
            else {
                if (backspaceCount != 0) {
                    backspaceCount--;
                    continue;
                } else truncatedArr.add(charArr[i]);
            }
        }
        Collections.reverse(truncatedArr);
        return truncatedArr;
    }
    public static void main(String args[]) {
        String S = "ab#c", T = "ad#c";
        boolean areEqual =  StringCompare_BackSpace.compare_ConstantSpace(S, T);
        System.out.println(areEqual);
    }

    /**
     * Compare the characters of 2 strings in one loop. Compare the characters one by one. Do not need extra list or array to put truncated characters.
     * Trick lies in finding the characters to be compared with each other in 2 strings.
     * Start at the end. Pick the first character from both strings which will not be truncated and compare. Then pick second characters not truncated and compare and go on.
     */
    public static boolean compare_ConstantSpace(String s, String t) {
        int i = s.length() - 1;
        int j = t.length() - 1;
        while (i >= 0 || j >= 0) {
            i = getCharacterToCompare(s, i);  // position of character to be compared
            j = getCharacterToCompare(t, j);  // position of character to be compared
            if (i >= 0 && j >= 0 && s.charAt(i) == t.charAt(j)) {
                i--;
                j--;
            } else {
                return i == -1 && j == -1;  // return false;
            }
        }
        return true;
    }
    /**
     *  return the (next character to the left of/OR) current character which will not be deleted.
     *  i.e the position of character for which comparision should be made, i.e is not deleted by #, #ed characters are irrelevant for comparision
     * @param pos position to read from
     * @return
     */

    private static int getCharacterToCompare(String s, int pos) {
        int cnt = 0;
        while (pos >= 0 && (s.charAt(pos) == '#' || cnt > 0)) { // True if # encountered or previous # count buffer != 0
            cnt += (s.charAt(pos) == '#') ? +1 : -1; // If encounter #, increase counter, else decrease counter
            pos--;
        }
        return pos;
    }
}