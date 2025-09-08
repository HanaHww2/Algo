import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

  static int N, M;
  static int[] NUMS;
  static Integer[][] DP;

  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine());
    NUMS = new int[N];
    DP = new Integer[N][N];

    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      NUMS[i] = Integer.parseInt(st.nextToken());
    }

    M = Integer.parseInt(br.readLine());
    int s, e;
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      s = Integer.parseInt(st.nextToken()) - 1;
      e = Integer.parseInt(st.nextToken()) - 1;

      sb.append(isPalindrome(s, e)).append("\n");
    }
    System.out.println(sb);
  }

  private static int isPalindrome(int s, int e) {

    if (DP[s][e] != null) return DP[s][e];

    if (s == e) {
      return DP[s][e] = 1;
    }
    if (NUMS[s] == NUMS[e]) {
      if (s + 1 < e - 1) return DP[s][e] = isPalindrome(s + 1, e - 1);
      else return DP[s][e] = 1;
    }
    return DP[s][e] = 0;
  }
}
