import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/11403
public class Main {
  static int N;
  static int[][] arr;

  public static void main(String[] args) throws IOException {

    init();
    for (int i = 0; i < N; i++) {
      dfs(i, i, new boolean[N]);
    }
    print();
  }

  private static void dfs(int from, int path, boolean[] visited) {

    for (int i = 0; i < N; i++) {
      if (arr[path][i] == 1 && !visited[i]) {
        visited[i] = true;
        arr[from][i] = 1;
        dfs(from, i, visited);
      }
    }
  }

  private static void init() throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine());
    arr = new int[N][N];

    for (int i = 0; i < N; i ++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j ++) {
        arr[i][j] = Integer.parseInt(st.nextToken());
      }
    }
  }

  private static void print() {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < N; i ++) {
      for (int j = 0; j < N; j ++) {
        sb.append(arr[i][j] + " ");
      }
      sb.append("\n");
    }
    System.out.println(sb);
  }
}
