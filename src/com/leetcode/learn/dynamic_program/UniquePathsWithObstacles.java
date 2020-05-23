package com.leetcode.learn.dynamic_program;

/**
 * 考虑有障碍物的路线总数
 * 数据类型为
 * [
 *   [0,0,0],
 *   [0,1,0],
 *   [0,0,0]
 * ]
 */
public class UniquePathsWithObstacles {

    public static void main(String[] args) {
        int[][] obstacleGrid = {{0,0,0}, {0,1,0}, {0,0,0}};
        int paths = uniquePathsWithObstacles(obstacleGrid);
        System.out.println(paths);
    }


    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        // 构造dp数组
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        int j = 0;
        // 边界条件
        for(int i = 0; i < m; i++){
            while (j < i){
                if(dp[j][0] == 1){
                    break;
                }
                j ++;
            }
            if(j < i){
                dp[i][0] = 0;
            }else {
                dp[i][0] = 1;
            }

        }
        return 0;
    }
}
