import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

  static int N, C;
  static int[] arr1, arr2;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    C = Integer.parseInt(st.nextToken());

    int mid = N / 2;
    int remainder = N - mid;
    arr1 = new int[mid];
    arr2 = new int[remainder];

    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      if (i < mid) arr1[i] = Integer.parseInt(st.nextToken());
      else arr2[i - mid] = Integer.parseInt(st.nextToken());
    }

    ArrayList<Integer> result1 = new ArrayList<>();
    result1.add(0);
    dfs(0, arr1, result1);

    ArrayList<Integer> result2 = new ArrayList<>();
    result2.add(0);
    dfs(0, arr2, result2);

    Collections.sort(result2);

    long result = 0;
    int l = result1.size();
    for (int i = 0; i < l; i++) {
      result += bs(result1.get(i), result2);
    }
    System.out.println(result);
  }

  private static int bs(int x, ArrayList<Integer> result) {
    int threshold = C - x;
    if (threshold < 0) return 0;

    int left = 0;
    int right = result.size() - 1;

    while (left <= right) {

      int mid = (left + right) / 2;
      if (result.get(mid) > threshold) right = mid - 1;
      else left = mid + 1;
    }
    return left;
  }

  private static void dfs(int start, int[] arr, ArrayList<Integer> possible) {

    if (start > arr.length - 1) return;

    int l = possible.size() - 1;
    int x = possible.get(l);
    if (x > C) return;

    for (int i = start; i < arr.length; i++) {

      if (x + arr[i] <= C) {
        possible.add(x + arr[i]);
        dfs(i + 1, arr, possible);
      }
    }
  }
}
