import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

  static int MOD = 1_000_000_000;
  static int N;
  static int[][][] DP;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine());

    DP = new int[N + 1][10][1 << 10];
    System.out.println(solve());

  }

  // 9876543210
  private static int solve() {

    for (int i = 1; i <= 9; i++) {
      DP[1][i][1 << i] += 1;
    }

    for (int i = 2; i <= N; i++) {
      for (int j = 0; j <= 9; j++) {
          for (int k = 0; k < 1 << 10; k++) {
            if (j > 0) {
                DP[i][j][k | 1 << j] = (DP[i][j][k | 1 << j] + DP[i - 1][j - 1][k]) % MOD;
          
            }
            if (j < 9) {
                DP[i][j][k | 1 << j] = (DP[i][j][k | 1 << j] + DP[i - 1][j + 1][k]) % MOD;
             }
        }
      }
    }

    int sum = 0;
    for (int[] result: DP[N]) {
      sum = (sum + result[(1 << 10) - 1]) % MOD;
    }
    return sum;
  }
}
