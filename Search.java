import java.util.*;
public class Search {
    //搜索
    //130 深度优先搜索
    int n,m;
    int[] dx = {1, -1, 0, 0,1,-1,1,-1};
    int[] dy = {0, 0, 1, -1,1,-1,-1,1};
    public void solve130(char[][] board) {
        // 列长
        n = board.length;
        if (n == 0)     return;
        // 宽长
        m = board[0].length;
        //按列搜索
        for (int i = 0; i < n; i++) {
            dfs130(board, i, 0);
            dfs130(board, i, m - 1);
        }
        //按行搜索
        for (int i = 1; i < m - 1; i++) {
            dfs130(board, 0, i);
            dfs130(board, n - 1, i);
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 'A') {
                    board[i][j] = 'O';
                } else if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }
    }

    private void dfs130(char[][] board, int x, int y) {
        if (x < 0 || x >= n || y < 0 || y >= m || board[x][y] != 'O') {
            return;
        }
        //被搜索过的O标记为A
        board[x][y] = 'A';
        dfs130(board, x + 1, y);
        dfs130(board, x - 1, y);
        dfs130(board, x, y + 1);
        dfs130(board, x, y - 1);
    }
    //733 图像渲染
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        return dfs733(image, sr, sc, newColor, image[sr][sc]);
    }

    public int[][] dfs733(int[][] image, int i, int j, int newColor, int num){
        if(i<0 || i>=image.length || j<0 || j>=image[0].length || image[i][j]==newColor || image[i][j]!=num){

        }else{
            int temp=image[i][j];
            image[i][j]=newColor;
            dfs733(image, i+1, j, newColor, temp);
            dfs733(image, i-1, j, newColor, temp);
            dfs733(image, i, j+1, newColor, temp);
            dfs733(image, i, j-1, newColor, temp);

        }
        return image;
    }
    //529 扫雷
    public char[][] updateBoard(char[][] board, int[] click) {
        int x = click[0];
        int y = click[1];
        if(board[x][y] == 'M')
        {
            board[x][y] = 'X';
            return board;
        }
        else{
            bfsBoard(board,x,y);
        }
        return board;
    }
    private void bfsBoard(char[][] board,int x,int y){
        Queue<int[]> queue = new LinkedList<>();
        boolean [][]vis = new boolean[board.length][board[0].length];
        queue.offer(new int[]{x,y});
        vis[x][y] = true;
        while(!queue.isEmpty()){
            int []temp = queue.poll();
            int count = 0;
            for(int i = 0;i < 8;i++){
                int tx = temp[0] + dx[i];
                int ty = temp[1] + dy[i];
                //边界条件
                if(tx >= board.length
                        || ty >= board[0].length
                        || tx < 0
                        || ty < 0){
                    continue;
                }
                //判断八个方向有多少地雷
                if (board[tx][ty] == 'M') {
                    count++;
                }
            }
            //周围有地雷
            if(count > 0){
                board[temp[0]][temp[1]] = (char)(count + '0');
            }
            //递归展示空白格
            else{
                board[temp[0]][temp[1]] = 'B';
                for(int i = 0;i < 8;i++) {
                    int tx = temp[0] + dx[i];
                    int ty = temp[1] + dy[i];
                    //边界条件
                    if (tx >= board.length
                            || ty >= board[0].length
                            || tx < 0
                            || ty < 0
                            || board[tx][ty] != 'E'
                            || vis[tx][ty]) {
                        continue;
                    }
                    queue.offer(new int[]{tx,ty});
                    vis[tx][ty] = true;
                }
            }
        }

    }
    //17 电话号码字母组合
    public List<String> letterCombinations(String digits) {
        List <String> list = new ArrayList<>();
        if(digits.length() == 0)  return list;
        HashMap<Character,String> map = new HashMap<>();
        map.put('2',"abc");
        map.put('3',"def");
        map.put('4',"ghi");
        map.put('5',"jkl");
        map.put('6',"mno");
        map.put('7',"pqrs");
        map.put('8',"tuv");
        map.put('9',"wxyz");
        String []s=new String[digits.length()];
        for(int i = 0;i < digits.length();i++){
            s[i] = map.get(digits.charAt(i));
        }
        letterString(s,list,0,"");
        return list;
    }
    private  void letterString(String []s,List <String> list,int index,String temp){
        if(index == s.length - 1){
            for(int i = 0;i < s[index].length();i++)
                list.add(temp + s[index].charAt(i));
        }
        else{
            for(int i = 0;i < s[index].length();i++)
                letterString(s,list,index+1,temp+s[index].charAt(i));

        }
    }

    //332 重新排序行程 图深搜
    private Map<String,PriorityQueue <String>> map = new HashMap<>();
    private List<String> list = new LinkedList<>();
    public List<String> findItinerary(List<List<String>> tickets) {
        for(List<String> ticket : tickets){
            String src = ticket.get(0);
            String dst = ticket.get(1);
            if(!map.containsKey(src)){
                PriorityQueue<String> pq = new PriorityQueue<>();
                pq.add(dst);
                map.put(src,pq);
            }

        }
        dfsItinerary("JFK");
        return list;
    }
    private void dfsItinerary(String src){
        PriorityQueue <String> pq = map.get(src);
        while(pq != null && !pq.isEmpty()){
            dfsItinerary(pq.poll());
        }
        list.add(0,src);
    }
    //22 括号生成
    //https://leetcode-cn.com/problems/generate-parentheses/
    public List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<>();
        if(n <= 0)  return list;
        dfsGenerate("",n,n,list);
        return list;

    }
    private void dfsGenerate(String curStr, int left, int right, List<String> res) {
        // 因为每一次尝试，都使用新的字符串变量，所以无需回溯
        // 在递归终止的时候，直接把它添加到结果集即可，注意与「力扣」第 46 题、第 39 题区分
        if (left == 0 && right == 0) {
            res.add(curStr);
            return;
        }

        // 剪枝（如图，左括号可以使用的个数严格大于右括号可以使用的个数，才剪枝，注意这个细节）
        if (left > right) {
            return;
        }

        if (left > 0) {
            dfsGenerate(curStr + "(", left - 1, right, res);
        }

        if (right > 0) {
            dfsGenerate(curStr + ")", left, right - 1, res);
        }
    }
    //78.子集
    //https://leetcode-cn.com/problems/subsets/
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>());
        for(int i = 0;i < nums.length;i++){
            int size = res.size();
            for(int j = 0;j < size;j++){
                List<Integer> tmp = new ArrayList<>(res.get(j));
                tmp.add(nums[i]);
                res.add(tmp);
            }
        }
        return res;
    }

}
