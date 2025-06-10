import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
  static int N;
  static int X;
  static int[] arr;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken()); X = Integer.parseInt(st.nextToken());

    arr = new int[N];
    st = new StringTokenizer(br.readLine());
    for(int i = 0; i < N; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }

    System.out.println(bs());
    br.close();
  }

  private static int bs() {
    int left = 1, right = N + 1;

    while (left < right) {
      int mid = left + (right - left) / 2;

      if (check(mid)) right = mid;
      else left = mid + 1;
    }
    return left;
  }

  private static boolean check(int p) {
    PriorityQueue<Integer> pq = new PriorityQueue<>();

    for (int i = 0; i < p; i++) {
      pq.add(arr[i]);
    }

    for (int i = p; i < N; i++) {
      int nCost = pq.poll() + arr[i];
      if (nCost > X) return false;
      pq.add(nCost);
    }
    return true;
  }
}
