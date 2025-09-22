import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

  static int N;
  static int[][] COST;
  static int MIN;
  static int[][][] DP;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine());
    COST = new int[N][3];

    StringTokenizer st;
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < 3; j++) {
        COST[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    DP = new int[N][3][3];
    MIN = 1_000_000;
    System.out.println(dfs(0, -1, -1));
  }

  private static int dfs(int idx, int bColor, int startColor) {

    if (idx == N) {
      return 0;
    }

    if (idx > 0 && DP[idx][bColor][startColor] != 0) {
      return DP[idx][bColor][startColor];
    }

    int minCost = 1_000_000;
    for (int i = 0; i < 3; i++) {
      if (i == bColor) continue;
      if (idx == N - 1 && startColor == i) continue;

      minCost = Math.min(minCost, COST[idx][i] + dfs(idx + 1, i, idx == 0 ? i :startColor));
    }

    if (idx > 0) DP[idx][bColor][startColor] = minCost;
    return minCost;
  }
}
