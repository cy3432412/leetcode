import java.util.*;
public class Solution_3 {
    //最长不重复子串
    //滑动窗口
    public int lengthOfLongestSubstring(String s) {
        if(s.length() <= 0) return 0;
        int left = 0;
        int maxLen = 0;
        HashMap<Character,Integer> map = new HashMap<>();
        for (int i = 0;i < s.length();i++){
            char ch = s.charAt(i);
            if(map.containsKey(ch)){
               left = Math.max(left,map.get(ch) + 1);
            }
            map.put(ch,i);
            maxLen = Math.max(maxLen,i-left+1);
        }
        return maxLen;
    }

}
