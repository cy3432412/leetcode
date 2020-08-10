public class Dynamic {
    //198 动态规划
    public int rob(int[] nums) {
        // dp[i] = max(dp[i-1],dp[i-2] + val)
        int []dp = new int[nums.length];
        dp[0] = nums[0];
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
        //dp [i] = dp[i] + max(0,dp[i-1]) + nums[i-1];
        int max = Integer.MIN_VALUE;
        int []dp = new int [nums.length + 1];
        for(int i = 1; i <= nums.length; i++){
            dp[i] = dp[i] + Math.max(0,dp[i-1]) + nums[i-1];
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
}