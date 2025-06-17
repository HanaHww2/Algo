import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int N, K;
  static int[] DP;
  static int[] W;
  static int[] V;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());
    DP = new int[K+1];
    W = new int[N+1];
    V = new int[N+1];

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      int w = Integer.parseInt(st.nextToken()), v = Integer.parseInt(st.nextToken());
      W[i+1] = w;
      V[i+1] = v;
    }

    for (int i = 1; i <= N; i++) {
      for (int j = K; j >= W[i]; j--) {
        DP[j] = Math.max(Math.max(DP[j-W[i]] + V[i], DP[j-1]), DP[j]);
      }
    }
    System.out.println(DP[K]);
  }
}
