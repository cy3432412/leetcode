import java.util.HashMap;

public class Easy {
    //1512
    public int numIdenticalPairs(int[] nums) {
        HashMap<Integer,Integer> map = new HashMap<>();
        int ans = 0;
        for(int i = 0;i < nums.length;i++){
            if(!map.containsKey(nums[i])){
                map.put(nums[i],1);
            }
            else{
                int value = map.get(nums[i]);
                map.put(nums[i],value + 1);
            }
        }
        for (Integer key : map.keySet()) {
            int value = map.get(key);
            ans += value * (value - 1) / 2;
        }
        return ans;
    }
    //696
    public int countBinarySubstrings(String s) {
        char ch = '-';
        int pre = 0;
        int cur = 0;
        int ans = 0;
        for(int i = 0;i < s.length();i++){
            if(ch != s.charAt(i)){
                ans += Math.min(pre,cur);
                pre = cur;
                cur = 1;
                ch = s.charAt(i);
            }
            else{
                cur++;
            }
        }
        ans += Math.min(pre,cur);
        return ans;
    }

}
