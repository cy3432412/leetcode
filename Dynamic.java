public class Dynamic {
    //198 动态规划
    public int rob(int[] nums) {
        // dp[i] = max(dp[i-1],dp[i-2] + val)
        if(nums.length <= 0)    return 0;
        int []dp = new int[nums.length];
        dp[0] = nums[0];
        if(nums.length == 1)    return dp[0];
        dp[1] = Math.max(nums[0],nums[1]);
        for(int i = 2;i < nums.length;i++){
            dp[i] = Math.max(dp[i-1],dp[i-2] + nums[i]);
        }
        return dp[nums.length-1];
    }
    //746 使用最小代价爬楼梯
    public int minCostClimbingStairs(int[] cost) {
        // dp[i] = min(dp[i-1]+cost[i],dp[i-2] + cost[i])
        if(cost.length == 0)    return 0;
        if(cost.length == 1)    return cost[0];
        int []dp = new int[cost.length + 1];
        dp[0] = cost[0];
        dp[1] = cost[1];
        for (int i = 2; i < cost.length; i++) {
            dp[i] = Math.min(dp[i-1],dp[i-2]) + cost[i];
        }


        return Math.min(dp[cost.length - 1],dp[cost.length-2]);
    }
    //70 爬楼梯
    public int climbStairs(int n) {
        //dp[i] = dp[i-1] + dp[i-2];
        if(n == 0)  return 0;
        if(n == 1)  return 1;
        int []dp = new int [n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for(int i = 2;i <= n;i++){
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }
    //53 最大子序和
    public int maxSubArray(int[] nums) {
        //每一个数据都有两个选择，与前面相连或者自己另立门户.
        //dp [i] = max(0,dp[i-1]) + nums[i-1];
        int max = Integer.MIN_VALUE;
        int []dp = new int [nums.length + 1];
        for(int i = 1; i <= nums.length; i++){
            dp[i] = Math.max(0,dp[i-1]) + nums[i-1];
            if(max < dp[i]) max = dp[i];
        }
        return max;
    }
    //121 买卖股票
    public int maxProfit(int[] prices) {
        if (prices.length == 0) return 0;
        int buy = prices[0], profit = 0;

        for (int i = 1; i < prices.length; i++) {

            if (prices[i] <= buy){
                buy = prices[i];
            }

            if (prices[i] - buy > profit){
                profit = prices[i] - buy;
            }

        }
        return profit;
    }
    //486 最大赢家
    //https://leetcode-cn.com/problems/predict-the-winner/
    public boolean PredictTheWinner(int[] nums) {
        int length = nums.length;
        //分数之差的最大值
        int[][] dp = new int[length][length];
        for (int i = 0; i < length; i++) {
            dp[i][i] = nums[i];
        }
        for (int i = length - 2; i >= 0; i--) {
            for (int j = i + 1; j < length; j++) {
                dp[i][j] = Math.max(nums[i] - dp[i + 1][j], nums[j] - dp[i][j - 1]);
            }
        }
        return dp[0][length - 1] >= 0;
    }
    
    //62 不同路径
    public int uniquePaths(int m, int n) {
        if(m == 0 || n == 0)
            return 0;
        int[][] dp = new int[m][n];
        dp[0][0]=1;
        for(int i=1;i<m;++i)
            dp[i][0] = dp[i-1][0];
        for(int i=1;i<n;++i)
            dp[0][i] = dp[0][i-1];
        for(int i=1;i<m;++i)
        {
            for(int j=1;j<n;++j)
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
        }
        return dp[m-1][n-1];
    }
    
    //63 不同路径Ⅱ
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if(obstacleGrid == null || obstacleGrid.length == 0)
            return 0;
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];  //dp[i][j]:走到(i,j)位置有多少种方法
        dp[0][0] = obstacleGrid[0][0] == 0?1:0;
        for(int i=1;i<m;++i)   //第一列的初始值
        {
            if(obstacleGrid[i][0] == 0 && dp[i-1][0]==1)   //无障碍物，且上面的一格能走通
                dp[i][0]=1;
            else
                dp[i][0]=0;
        }
        for(int i=1;i<n;++i)
        {
            if(obstacleGrid[0][i] == 0 && dp[0][i-1] == 1)  //无障碍物，且左面的一格能走通
                dp[0][i]=1;
            else
                dp[0][i]=0;
        }
        for(int i=1;i<m;++i)
        {
            for(int j=1;j<n;++j)
            {
                if(obstacleGrid[i][j] == 1)
                    dp[i][j]=0;
                else
                    dp[i][j] = dp[i-1][j]+dp[i][j-1];
            }
        }
        return dp[m-1][n-1];
    }
    
    //64 最小路径和
     public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        if(m == 0 || n == 0)
            return 0;
        int[][] dp = new int[m][n];
        dp[0][0]=grid[0][0];
        for(int i=1;i<m;++i)
            dp[i][0] = dp[i-1][0]+grid[i][0];
        for(int i=1;i<n;++i)
            dp[0][i] = dp[0][i-1]+grid[0][i];
        for(int i=1;i<m;++i)
        {
            for(int j=1;j<n;++j)
                dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1])+grid[i][j];
        }
        return dp[m-1][n-1];
    }
}
