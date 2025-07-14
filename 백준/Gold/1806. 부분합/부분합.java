import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static int N;
  static int S;
  static int[] arr;
  
  public static void main(String[] args) throws IOException {
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    S = Integer.parseInt(st.nextToken());

    arr = new int[N];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }
    System.out.println(find());
  }

  private static int find() {
    int left = 0, right = 0;
    int sum = 0;
    int min = 100_000_000;

    while (right < N) {
      if (sum < S) {
        sum += arr[right];
        right++;
      }
      while (sum >= S && left < N) {
        min = Math.min(min, right - left);
        sum -= arr[left];
        left++;
      }
    }
    return min == 100_000_000 ? 0 : min;
  }
}