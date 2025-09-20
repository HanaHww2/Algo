import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

  static int N, M, K;
  static int[] candy;
  static List<Integer>[] friendship;
  static int[][] dp;
  static List<int[]> possible;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());

    candy = new int[N];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      candy[i] = Integer.parseInt(st.nextToken());
    }

    friendship = new List[N];
    for (int i = 0; i < N; i++) {
      friendship[i] = new ArrayList<>();
    }

    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int x = Integer.parseInt(st.nextToken()) - 1;
      int y = Integer.parseInt(st.nextToken()) - 1;
      friendship[x].add(y);
      friendship[y].add(x);
    }

    search();
    System.out.println(dp[possible.size()][K-1]);
  }

  private static void search() {
    init();

    int psize = possible.size();
    dp = new int[psize + 1][K];

    for (int i = 1; i < psize + 1; i++) {
      int w = possible.get(i-1)[1];
      for (int j = 1; j < K; j++) {
        if (j < w) {
          dp[i][j] = dp[i - 1][j];
          continue;
        }
        dp[i][j] = Math.max(Math.max(dp[i][j - 1], dp[i-1][j - w] + possible.get(i-1)[0]), dp[i-1][j]);
      }
    }
  }

  private static void init() {
    possible = new ArrayList<>(N);
    boolean[] visited = new boolean[N];

    for (int i = 0; i < N; i++) {
      if (visited[i]) continue;
      int[] result = checkFriends(visited, i);
      possible.add(result);
    }
  }

  private static int[] checkFriends(boolean[] visited, int i) {
    if (visited[i]) return new int[] {0, 0};

    int val = candy[i];
    int cnt = 1;
    visited[i] = true;
    for (int f : friendship[i]) {
      int[] result = checkFriends(visited, f);
      val += result[0];
      cnt += result[1];
    }
    return new int[] {val, cnt};
  }
}
