import java.util.*;

class Solution {
    // dp[n-1][m] = max(dp[n][m], dp[n][m+1]) + node[n-1][m]
    private int [][] dp;
    private int[][] triangle;
    
    public int solution(int[][] triangle) {
        
        this.triangle = triangle;
        int height = triangle.length;
        this.dp = new int[height+1][triangle[height-1].length+1];
   
        this.setArrays();
        return this.dp[0][0];
    }
    
    private void setArrays() {
        
        for (int n = this.dp.length-1; n > 0; n--) {     
            for (int m = 0; m < n; m++) {
                this.dp[n-1][m] = Math.max(dp[n][m], this.dp[n][m+1]) + triangle[n-1][m];
            }    
        }
        
    }
}
// 인덱싱 때문에 한참 헤맸다.
// 나는 dp 공부를 많이 안했다. 즉, dp 문제에 굉장히 약하다.
// 그럼에도 dp 공부하는 걸 실어한다. 이유를 생각해보니, 내가 인덱스 계산하는 걸 굉장히 싫어하기 때문이었다.
// 따져보면, 투포인터 같이 인덱스를 많이 고민해야 하는 문제들도 약하고, 사실ㅠ 인덱싱이 복잡한 문제면 다 약한 편인 거 같다.
// 이건 그냥 문제 가리지 않고 많이 풀어보는 수 밖에 없는 것 같다.


// 다른 사람 풀이
// 출발지에서부터 더해서 내려가도 된다.
// 깔끔
import java.util.*;
class Solution {
    public int solution(int[][] triangle) {
        for (int i = 1; i < triangle.length; i++) {
            triangle[i][0] += triangle[i-1][0];
            triangle[i][i] += triangle[i-1][i-1];
            for (int j = 1; j < i; j++) 
                triangle[i][j] += Math.max(triangle[i-1][j-1], triangle[i-1][j]);
        }

        return Arrays.stream(triangle[triangle.length-1]).max().getAsInt();
    }
}