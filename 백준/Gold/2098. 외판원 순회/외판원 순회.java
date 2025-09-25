import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
  static int N;
  static int[][] graph;
  static int[][] dp;
  static int MAX = 16_000_001;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine());
    graph = new int[N][N];

    StringTokenizer st;
    for (int i = 0; i < N; i++) {
      st =  new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        graph[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    dp = new int[1<<N][N];
    for (int[] ints : dp) {
      Arrays.fill(ints, -1);
    }

    System.out.println(tsp(0, 1));
  }

  private static int tsp(int from, int route) {

    if (route == (1 << N) - 1) {
      return graph[from][0] == 0 ? MAX : graph[from][0];
    }
    if (dp[route][from] != -1) return dp[route][from];

    int min = MAX;
    for (int i = 0; i < N; i++) {
      if (graph[from][i] == 0 || (route & 1 << i) != 0) continue;

      min = Math.min(min, graph[from][i] + tsp(i, route | 1 << i));
    }
    return dp[route][from] = min;
  }
}
