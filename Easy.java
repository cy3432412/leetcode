import java.util.*;

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
    public String reverseLeftWords(String s, int n) {
        String s1 = s.substring(0,n);
        String s2 = s.substring(n);
        return s2 + s1;
    }
    //20
    public boolean isValid(String s) {
        Stack <Character>stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if(stack.isEmpty()){
                stack.push(ch);
                continue;
            }
            if(ch == ')'){
                if(stack.pop() == '(')
                    continue;
                else
                    return false;
            }
            if(ch == '}'){
                if(stack.pop() == '{')
                    continue;
                else
                    return false;
            }
            if(ch == ']'){
                if(stack.pop() == '[')
                    continue;
                else
                    return false;
            }
            stack.push(ch);

        }
        return stack.isEmpty();
    }
    //1051
    public int heightChecker(int[] heights) {
        int count = 0;
        int []temp = heights.clone();
        Arrays.sort(temp);
        for(int i = 0;i < temp.length;i++){
            if(temp[i] != heights[i]){
                count++;
            }
        }
        return count;
    }
    //16 最接近三数之和
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int i;
        int j;
        int ans = nums[0] + nums[1] + nums[2];
        for(int k = 0;k < nums.length-2;k++){
            i = k + 1;
            j = nums.length-1;
            while(i < j ){
                int temp = nums[i] + nums[j] + nums[k];
                ans = Math.abs(temp-target) < Math.abs(ans-target) ? temp : ans;
                if(temp > target)   j--;
                if(temp < target)   i++;
                if(temp == target)  return temp;
            }

        }
        return ans;

    }
    //18 四数之和
    //https://leetcode-cn.com/problems/4sum/
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> ansList = new ArrayList<>();
        Map<List<Integer>,Integer> mp= new HashMap<>();
        if(nums.length < 4) return ansList;
        Arrays.sort(nums);
        for(int i = 0;i < nums.length - 3;i++){
            for(int j = i + 1;j < nums.length - 2;j++){
                int l = j + 1;
                int r = nums.length-1;
                while(l < r){
                    int ans = nums[i] + nums[j] + nums[l] + nums[r];
                    if(ans == target){
                        List<Integer> list = new ArrayList<>();
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(nums[l]);
                        list.add(nums[r]);
                        if(!mp.containsKey(list)){
                            ansList.add(list);
                            mp.put(list,1);
                        }

                    }
                    if(ans < target){
                        l++;
                    }
                    else{
                        r--;
                    }
                }
            }
        }
        return ansList;

    }
    public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        if(nums[left] < target)
            return left + 1;
        else
            return left;
    }
    public String countAndSay(int n) {
        if (n == 1) return "1";
        String last = countAndSay(n - 1);
        StringBuffer sb = new StringBuffer();
        char ch = last.charAt(0);
        int i = 0, index = 0;
        while (index < last.length()) {
            if (last.charAt(index) != ch) {
                sb.append(i);
                sb.append(ch);
                i = 1;
                ch = last.charAt(index);
            } else {
                i++;
            }
            index++;
        }
        sb.append(i);
        sb.append(ch);
        return sb.toString();
    }
    //104 根据后序中序重建二叉树
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        TreeNode root = reConstruct(postorder,0,postorder.length-1,inorder,0,inorder.length-1);
        return root;
    }
    private TreeNode reConstruct(int[] postorder,int postStart,int postEnd,int[] inorder, int inStart,int inEnd){
        if(postStart > postEnd || inStart > inEnd){
            return null;
        }
        TreeNode root = new TreeNode(postorder[postEnd]);
        for (int i = inStart; i <= inEnd; i++) {
            if(inorder[i] == postorder[postEnd]){
                int leftLen = i - inStart;
                root.left = reConstruct(postorder,postStart,leftLen + postStart - 1,inorder,inStart,i - 1);
                root.right = reConstruct(postorder,leftLen + postStart,postEnd -1,inorder,i + 1,inEnd);
                break;
            }
        }
        return root;
    }
    public boolean checkPalindromeFormation(String a, String b) {
        if(check(a) || check(b))
            return true;
        int len = a.length();
        for(int i = 1;i < len - 1;i++){
            String a1 = a.substring(0,i);
            String a2 = a.substring(i);
            String b1 = b.substring(0,i);
            String b2 = b.substring(i);
            if(check(a1+b2) || check(b1+a2))
                return true;
        }
        return false;
    }
    public boolean check(String s){
        int len = s.length();
        if(len == 0 || len == 1){
            return true;
        }
        char ch[] = s.toCharArray();
        for(int i = 0;i < len / 2;i++){
            if(ch[i] != ch[len- i - 1]){
                return false;
            }
        }
        return true;
    }
    public int maximalNetworkRank(int n, int[][] roads) {
        Arrays.sort(roads,(a,b)->a[0]-b[0]);
        int []nums = new int [n];
        for(int i = 0;i < roads.length;i++){
                nums[roads[i][0]] ++;
                nums[roads[i][1]] ++;
        }
        int max1, max2;// 第一大，第二大数字
        max1 = max2 = nums[0];
        int v;
        for (int i = 1; i < n; i++) {
            v = nums[i];
            if (v > max2) {
                if (v > max1) {
                    max2 = max1;//原来最大值变第二大
                    max1 = v;//最大值更新为当前值
                } else {
                    max2 = v;//当前值为第二大
                }
            }
        }

        if(max1 == max2){//解决数组第一个数是最大值，上述for 循环 不执行问题
            max2 = nums[1];
            for(int i=2;i<nums.length;i++) {

                if(nums[i]>max2)   // 判断最大值
                    max2 =nums[i];

            }
        }
        return max1 + max2;

    }
    public void setZeroes(int[][] matrix) {
        ArrayList<int[]> list = new ArrayList<>();
        for(int i = 0; i < matrix.length;i++){
            for(int j = 1;j < matrix[0].length;j++){
                if(matrix[i][j] == 0){
                    list.add(new int[]{i,j});
                }
            }
        }
        for(int []a : list){
            int row = a[0];
            int col = a[1];
            for(int j = 0;j < matrix[0].length;j++){
                matrix[row][j] = 0;
            }
            for(int i = 0;i < matrix.length;i++){
                matrix[i][col] = 0;
            }
        }
    }
    public boolean searchMatrix(int[][] matrix, int target) {

        if(matrix.length == 0 || matrix[0].length == 0){
            return false;
        }
        if(matrix.length == 0)
            return false;
        int row = 0, col = matrix[0].length-1;
        while(row < matrix.length && col >= 0){
            if(matrix[row][col] < target)
                row++;
            else if(matrix[row][col] > target)
                col--;
            else
                return true;
        }
        return false;

    }
    public int[] sortedSquares(int[] A) {
        int i = 0;
        int j = A.length -1;
        int k = j;
        int []B = new int [k + 1];
        while(i < j){
            if(Math.abs(A[i]) < Math.abs(A[j])){
                B[k--] = A[j] * A[j--];
            }
            else{
                B[k--] = A[i] * A[i++];
            }
        }
        return B;
    }
    public boolean isLongPressedName(String name, String typed) {
        if(name.length() > typed.length()){
            return false;
        }
        int lenN = name.length();
        int lenT = typed.length();

        int i=0;
        int j=0;
        while(i < lenN && j<lenT){
            if(name.charAt(i)==typed.charAt(j)){
                i++;
                j++;
            }
            else if( j>0 && typed.charAt(j)==typed.charAt(j-1)){
                j++;
            }
            else{
                return false;
            }
        }
        return i == lenN;
    }
    public int minOperations(int[] nums, int x) {
        int i = 0;
        int j = nums.length - 1;
        int count = 0;
        if(x < nums[i] && x < nums[j]){
            return -1;
        }
        while(x > 0 && i <= j){
            if(x >= nums[i] && x >= nums[j]){
                if(nums[i] > nums[j]){
                    x -= nums[i++];
                }
                else{
                    x -= nums[j--];
                }
                count++;
                continue;
            }
            if(x >= nums[i]){
                x -= nums[i++];
                count++;
                continue;
            }
            if(x >= nums[j]){
                x-=nums[j--];
                count++;
            }
        }
        if(x == 0)  return count;
        else    return -1;
    }
    public boolean closeStrings(String word1, String word2) {
        if(word1.length() != word2.length())    return false;
        HashMap<Character,Integer> mp1 = new HashMap<>();
        HashMap<Character,Integer> mp2 = new HashMap<>();
        for(int i = 0;i < word1.length();i++){
            char ch1 = word1.charAt(i);
            char ch2 = word2.charAt(i);
            if(mp1.containsKey(ch1)){
                mp1.put(ch1,mp1.get(ch1) + 1);
            }
            else{
                mp1.put(ch1,1);
            }
            if(mp2.containsKey(ch2)){
                mp2.put(ch2,mp1.get(ch2) + 1);
            }
            else{
                mp2.put(ch2,1);
            }
        }
        if(mp1.size()!=mp2.size())
            return false;

        for (char ch : mp1.keySet()){
            Collection<Integer> col = mp2.values();
            if(!mp2.containsKey(ch)){
                return false;
            }
            if(!mp2.containsValue(mp1.get(ch))){
                return false;
            }
            else{
                col.remove(mp1.get(ch));
            }
        }
        return true;
    }
    //406 根据身高重排序列
    //https://leetcode-cn.com/problems/queue-reconstruction-by-height/
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0] != o2[0]){
                    return o1[0]-o2[0];
                }
                else {
                    return o2[1]-o1[1];
                }
            }
        });
        int[][] ans = new int[people.length][];
        for (int[] person : people) {
            int spaces = person[1] + 1;
            for (int i = 0; i < people.length; ++i) {
                if (ans[i] == null) {
                    --spaces;
                    if (spaces == 0) {
                        ans[i] = person;
                        break;
                    }
                }
            }
        }
        return ans;


    }
}
