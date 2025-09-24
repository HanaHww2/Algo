import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

  static int N;
  static int[] arr;
  static int[] dp;
  static int[] idx;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine());

    arr = new int[N];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }
    br.close();

    int len = lis();
    int lenCheck = len;
    int[] result = new int[len];
    for (int i = N - 1; i >= 0; i--) {
      if (lenCheck == idx[i]) {
        result[--lenCheck] = arr[i];
      }
    }

    StringBuilder sb = new StringBuilder();
    sb.append(len).append("\n");
    for (int i = 0; i < len; i++) {
      sb.append(result[i]).append(" ");
    }
    System.out.println(sb);
  }

  private static int lis() {
    dp = new int[N];
    idx = new int[N];

    int len = 1;
    dp[0] = arr[0];
    idx[0] = len;

    for (int i = 1; i < N; i++) {
      if (dp[len - 1] < arr[i]) {
        dp[len++] = arr[i];
        idx[i] = len;
      } else {
        int lower = lower_bound(arr[i], len);
        dp[lower] = arr[i];
        idx[i] = lower + 1;
      }
    }
    return len;
  }

  private static int lower_bound(int val, int len) {
    int min = 0, max = len - 1;

    while(min <= max) {
      int mid = min + (max - min) / 2;

      if (dp[mid] < val) {
        min =  mid + 1;
      } else {
        max = mid - 1;
      }
    }
    return min;
  }
}
