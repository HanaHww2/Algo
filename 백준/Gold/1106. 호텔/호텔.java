import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static int C, N;
  static ArrayList<int[]> city;
  static int[] dp;

  public static void main(String[] args) throws IOException {
    StringTokenizer st = new StringTokenizer(br.readLine());
    C = Integer.parseInt(st.nextToken());
    N = Integer.parseInt(st.nextToken());

    city = new ArrayList<>(N);
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      city.add(new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
    }

    System.out.println(find());
  }

  private static int find() {
    dp = new int[C + 101];
    Arrays.fill(dp, 100_000_000);
    dp[0] = 0;

    for (int[] curr : city) {
      for (int i = curr[1]; i < dp.length; i++) {
        dp[i] = Math.min(dp[i], curr[0] + dp[i - curr[1]]);
      }
    }
    int min = Integer.MAX_VALUE;
    for(int i = C; i < C + 101; i++){
      min = Math.min(min, dp[i]);
    }
    return min;
  }
}
