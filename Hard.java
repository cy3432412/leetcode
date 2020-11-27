import java.util.*;
public class Hard {
    //4 寻找两个数组的中位数要求O(log(m+n))
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        //暴力 O((m+n)log(m+n))
        //        int len = nums1.length + nums2.length;
        //        int []num = new int [len];
        //        for(int i = 0 ;i < nums1.length;i++){
        //            num[i] = nums1[i];
        //        }
        //        for(int i = 0 ;i < nums2.length;i++){
        //            num[i + nums1.length] = nums2[i];
        //        }
        //        Arrays.sort(num);
        //        double middle = num[len/2];
        //        if(len % 2 == 1)
        //            return middle;
        //        else
        //            return (middle + num[len/2 - 1])/2;

        //二分查找
        int len1 = nums1.length, len2 = nums2.length;
        int len = len1 + len2;
        int k;
        double middle;

        if(len % 2 == 1){
            k = (len - 1)/2;
            middle = findTheMinkElment(nums1,nums2,k+1);
        }
        else{
            k = len / 2;
            middle = (findTheMinkElment(nums1,nums2,k+1) + findTheMinkElment(nums1,nums2,k))/2.0;
        }

        return middle;
    }
    private double findTheMinkElment(int []nums1,int []nums2,int k){
        int len1 = nums1.length, len2 = nums2.length;
        int index1 = 0, index2 = 0;


        while (true) {
            // 边界情况
            if (index1 == len1) {
                return nums2[index2 + k - 1];
            }
            if (index2 == len2) {
                return nums1[index1 + k - 1];
            }
            if (k == 1) {
                return Math.min(nums1[index1], nums2[index2]);
            }

            // 正常情况
            int half = k / 2;
            int newIndex1 = Math.min(index1 + half, len1) - 1;
            int newIndex2 = Math.min(index2 + half, len2) - 1;
            int pivot1 = nums1[newIndex1], pivot2 = nums2[newIndex2];
            if (pivot1 <= pivot2) {
                k -= (newIndex1 - index1 + 1);
                index1 = newIndex1 + 1;
            }
            else {
                k -= (newIndex2 - index2 + 1);
                index2 = newIndex2 + 1;
            }
        }
    }
    public boolean isMatch(String s, String p) {
        return s.matches("^"+p+"$");
    }
    //84 柱状图中的最大面积
    public int largestRectangleArea(int[] heights) {
        //单调栈 + 哨兵
        int len = heights.length;
        if (len == 0)   return 0;

        if (len == 1)   return heights[0];

        int res = 0;

        int[] newHeights = new int[len + 2];
        newHeights[0] = 0;
        //数组复制
        System.arraycopy(heights, 0, newHeights, 1, len);
        newHeights[len + 1] = 0;
        len += 2;
        Deque<Integer> stack = new ArrayDeque<>(len);
        // 先放入哨兵，在循环里就不用做非空判断
        stack.addLast(0);

        for (int i = 1; i < len; i++) {
            while (newHeights[i] < newHeights[stack.peekLast()]) {
                int curHeight = newHeights[stack.pollLast()];
                int curWidth = i - stack.peekLast() - 1;
                res = Math.max(res, curHeight * curWidth);
            }
            stack.addLast(i);
        }
        StringBuffer sb = new StringBuffer();
        sb.toString();
        return res;
    }
    //30. 串联所有单词的子串
    //https://leetcode-cn.com/problems/substring-with-concatenation-of-all-words/
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        if (s == null || s.length() == 0 || words == null || words.length == 0) return res;
        HashMap<String, Integer> map = new HashMap<>();
        int one_word = words[0].length();
        int word_num = words.length;
        int all_len = one_word * word_num;
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        //滑动窗口
        for (int i = 0; i < one_word; i++) {
            int left = i, right = i, count = 0;
            HashMap<String, Integer> tmp_map = new HashMap<>();
            //边界条件
            while (right + one_word <= s.length()) {
                String w = s.substring(right, right + one_word);
                tmp_map.put(w, tmp_map.getOrDefault(w, 0) + 1);
                right += one_word;
                count++;
                //如果有一个单词出现次数比map中还要多则回退
                while (tmp_map.getOrDefault(w, 0) > map.getOrDefault(w, 0)) {
                    String t_w = s.substring(left, left + one_word);
                    count--;
                    tmp_map.put(t_w, tmp_map.getOrDefault(t_w, 0) - 1);
                    left += one_word;
                }
                if (count == word_num) res.add(left);

            }
        }

        return res;
    }
    //37.解数独
    //https://leetcode-cn.com/problems/sudoku-solver/
    boolean[][] row = new boolean[9][9];
    boolean[][] col = new boolean[9][9];
    boolean[][] block = new boolean[9][9];
    public void solveSudoku(char[][] board) {
        //对已有区域进行标记
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    int num = board[i][j] - '1';
                    int blockIndex = i / 3 * 3 + j / 3;
                    row[i][num] = true;
                    col[j][num] = true;
                    block[blockIndex][num] = true;
                }
            }
        }
        dfsSudoku(board,0,0);
    }
    private boolean dfsSudoku(char[][] board,int i,int j){

        while(board[i][j] != '.'){
            if(++j >= 9){
                i++;
                j = 0;
            }
            //结束标志
            if(i >= 9){
                return true;
            }
        }
        //填表
        for (int num = 0; num < 9; num++) {
            int blockIndex = i / 3 * 3 + j / 3;
            //num没有被标记
            if (!row[i][num] && !col[j][num] && !block[blockIndex][num]) {
                //num从0开始
                board[i][j] = (char) ('1' + num);
                row[i][num] = true;
                col[j][num] = true;
                block[blockIndex][num] = true;
                //递归
                if (dfsSudoku(board,i, j)) {
                    return true;
                }
                //回溯
                else {
                    row[i][num] = false;
                    col[j][num] = false;
                    block[blockIndex][num] = false;
                    board[i][j] = '.';
                }
            }
        }
        return false;

    }
    public void printBoard(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }



}
