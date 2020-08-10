import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    public int solution(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if(root == null )   return 0;
        if (root != null) queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode temp = queue.poll();
            if (temp != null) {
                list.add(temp.val);
                queue.offer(temp.left);
                queue.offer(temp.right);
            } else {
                list.add(-1);
            }
        }
        int maxLength = 1;
        int pre = 0;
        int cur = 1;
        int maxIndex = 1;
        int deep = 0;
        while(cur < list.size()){
            int i = pre,j = cur;
            while(i < j){
                if(list.get(i) == -1){
                    i++;
                }
                if(list.get(j) == -1){
                    j--;
                }
            }
            if(j-i+1 > maxLength)
                maxLength = j-i+1;
            pre = cur + 1;
            deep++;
            cur = 2 << (deep-1) + cur;
        }
        return maxLength;
    }
    void QuickSort(int []R, int lo, int hi){
        int i = lo, j = hi;
        int temp;
        if(i < j){
            temp = R[i];
            while (i != j)
            {
                while(j > i && R[j] > temp)-- j;
                R[i] = R[j];
                while(i < j && R[i] < temp)++ i;
                R[j] = R[i];
            }
            R[i] = temp;
            QuickSort(R, lo, i - 1);
            QuickSort(R, i + 1, hi);
        }
    }
    //415
    public String addStrings(String num1, String num2) {
        int i = num1.length()-1, j = num2.length()-1, add = 0;
        StringBuffer ans = new StringBuffer();
        while (i >= 0 || j >= 0 || add != 0) {
            int x = i >= 0 ? num1.charAt(i) - '0' : 0;
            int y = j >= 0 ? num2.charAt(j) - '0' : 0;
            int result = x + y + add;
            ans.append(result % 10);
            add = result / 10;
            i--;
            j--;
        }
        // 计算完以后的答案需要翻转过来
        ans.reverse();
        return ans.toString();
    }
}
