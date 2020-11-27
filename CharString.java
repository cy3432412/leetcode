import java.util.*;
public class CharString {
    //1541 一个左括号对于两个右括号
    public int minInsertions(String s) {
        /*
        //右括号富裕量
        int right = 0;
        //操作结果
        int ans = 0;
        //字符串长度
        int len = s.length();
        //从尾部开始遍历
        for(int i = len - 1;i >= 0;i--){
            //右括号
            if(s.charAt(i) == ')'){
                right++;
            }
            //左括号
            else{
                //一个左对应两个右
                right -= 2;
                //如果右括号不满足
                if(right < 0){
                    ans -= right;
                    right = 0;
                }
                //如果右括号为单数
                if(right % 2 == 1){
                    ans++;
                    right++;
                }
            }
        }
        if(right % 2 == 0){
            ans += right/2;
        }
        else{
            ans += (right + 1)/2 + 1;
        }
        return ans;
        */
        Stack<Character> stack = new Stack<>();
        int ans = 0;
        for (int i = s.length() - 1; i >= 0 ; i--) {
            char c = s.charAt(i);
            if (c == ')') {
                stack.push(c);
            }
            else{
                if (stack.size() >= 2) {
                    stack.pop();
                    stack.pop();
                }
                else {
                    ans += 2 - stack.size();
                    while (!stack.empty()) {
                        stack.pop();
                    }
                }
                //如果右括号为单数
                if(stack.size() % 2 == 1){
                    ans++;
                    stack.push(')');
                }
            }
        }
        if (stack.size() % 2 == 0) {
            ans += stack.size() / 2;
        }
        else {
            ans += 1 + (stack.size() + 1) / 2;
        }
        return ans;
    }
    //921 一个左括号对应一个右括号
    public int minAddToMakeValid(String S) {
        int ans = 0, right = 0;
        int len = S.length();
        for(int i = len - 1;i >= 0;i--){
            if(S.charAt(i) == ')'){
                right++;
            }
            else{
                if(right == 0)  ans ++;
                else right--;
            }

        }
        ans += right;
        return ans;
    }
    //6 N型字符串变换
    public String convert(String s, int numRows) {
        if(numRows == 1)    return s;
        StringBuilder sb  = new StringBuilder();
        int cycle = 2 * numRows - 2;
        for(int j = 0;j < numRows;j++) {
            for (int i = 0; i < s.length(); i++) {
                if(i % cycle == j || i % cycle == cycle - j){
                    sb.append(s.charAt(i));
                }
            }
        }
        return sb.toString();
    }
    //14 最长公共前缀
    public String longestCommonPrefix(String[] strs) {

        if(strs.length == 0)   return "";
        String prefix = strs[0];
        //横向扫描
        /*
        for(int i = 1;i < strs.length;i++){
            prefix = helpPrefix(prefix,strs[i]);
            if(prefix.length() == 0){
                return prefix;
            }
        }
        */
        //纵向扫描
        int len = strs[0].length();
        for (int i = 0; i < strs.length; i++) {
            len = Math.min(len,strs[i].length());
        }
        for (int i = 0; i < len; i++) {
            for (int j = 1; j < strs.length; j++) {
                if(prefix.charAt(i) != strs[j].charAt(i)){
                    return prefix.substring(0,i);
                }
            }
        }

        return prefix.substring(0,len);
    }
    private String helpPrefix(String s1,String s2){
        int len = Math.min(s1.length(),s2.length());
        for (int i = 0; i < len; i++) {
            if(s1.charAt(i) != s2.charAt(i)){
                return s1.substring(0,i);
            }
        }
        return s1.substring(0,len);
    }
    //8 字符转化为整数
    public int myAtoi(String str) {
        char []chars = str.toCharArray();
        int len = str.length(),i = 0;
        int ans = 0;
        while(i < len && chars[i] == ' '){
            i++;
        }
        if(i == len)    return 0;
        boolean flag = true;
        if(chars[i] == '-'){
            flag = false;
            i++;
        }
        else if(chars[i] == '+'){
            i++;
        }
        else if(chars[i] > '9' || chars[i] < '0'){
            return  ans;
        }
        while(i < len && Character.isDigit(chars[i])){
            int digit = chars[i] - '0';
            if(ans > (Integer.MAX_VALUE-digit)/10){
                if(flag){
                    return Integer.MAX_VALUE;
                }
                else{
                    return Integer.MIN_VALUE;
                }
            }
            ans = ans*10 + digit;
            i++;
        }
        return flag?ans:-ans;
    }
    //剑指 Offer 20. 表示数值的字符串
    //https://leetcode-cn.com/problems/biao-shi-shu-zhi-de-zi-fu-chuan-lcof/
    public boolean isNumber(String s) {
        s = s.trim();
        if(s.length() == 0)     return false;
        boolean numFlag = false;
        boolean dotFlag = false;
        boolean eFlag = false;
        for(int i = 0;i < s.length();i++){
            if(Character.isDigit(s.charAt(i))){
                numFlag = true;
            }
            else if(s.charAt(i) == '.' && !dotFlag){
                dotFlag = true;
            }
            else if((s.charAt(i) == 'e'|| s.charAt(i) == 'E') && !eFlag && numFlag){
                eFlag = true;
                numFlag = false;
            }
            //判定为+-符号，只能出现在第一位或者紧接e后面
            else if((s.charAt(i) == '+' || s.charAt(i) == '-')
                    && (i == 0 || s.charAt(i - 1) == 'e' || s.charAt(i-1) == 'E')){
                continue;
            }
            else{
                return false;
            }
        }
        return numFlag;

    }
}



