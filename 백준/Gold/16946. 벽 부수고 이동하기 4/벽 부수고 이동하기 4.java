import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
  static int N, M;
  static int[][] arr;
  static int[][] indexedArr;

  static int[][] dir = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
  static List<Integer> counting = new ArrayList<>(N/2);

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    arr = new int[N][M];
    indexedArr = new int[N][M];

    for (int i = 0; i < N; i++) {
      char[] temp = br.readLine().toCharArray();
      for (int j = 0; j < M; j++) {
        arr[i][j] = temp[j] - '0';
        indexedArr[i][j] = arr[i][j];
      }
    }

    int idx = 2;
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        if (arr[i][j] != 1) continue;

        Set<Integer> visited = new HashSet<>();
        int result = 1;
        for (int[] d : dir) {
          int nr = i + d[0];
          int nc = j + d[1];

          if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
          if (indexedArr[nr][nc] == 1) continue;

          if (indexedArr[nr][nc] > 1) {
            if (visited.contains(indexedArr[nr][nc]-2)) continue;

            result += counting.get(indexedArr[nr][nc]-2);
            visited.add(indexedArr[nr][nc]-2);
            continue;
          }

          int cnt = countArr(nr, nc, idx++);
          counting.add(cnt % 10);
          visited.add(indexedArr[nr][nc]-2);
          result += cnt;
        }
        arr[i][j] = result % 10;
      }
    }

    print();
  }

  private static int countArr(int row, int col, int idx) {

    indexedArr[row][col] = idx;
    int cnt = 1;
    for (int[] d: dir) {
      int nr = row + d[0];
      int nc = col + d[1];

      if (nr >= 0 && nr < N && nc >= 0 && nc < M) {

        if (indexedArr[nr][nc] != 0) continue;

        indexedArr[nr][nc] = idx;
        cnt += countArr(nr, nc, idx);
      }
    }
    return cnt;
  }

  private static void print() {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        sb.append(arr[i][j]);
      }
      sb.append("\n");
    }
    System.out.println(sb);
  }
}
