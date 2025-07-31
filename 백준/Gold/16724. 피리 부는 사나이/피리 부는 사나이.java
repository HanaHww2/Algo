import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

  static int N, M;
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static char[][] board;
  static int[][] visited;

  static int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
  static Map<Character, Integer> dirMap = Map.of('R', 0, 'L', 1, 'D', 2, 'U', 3);
  static int group = 0;

  public static void main(String[] args) throws IOException {
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    board = new char[N][M];
    visited = new int[N][M];

    for (int i = 0; i < N; i++) {
      board[i] = br.readLine().toCharArray();
    }
    
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        if (visited[i][j] != 0) continue;
        search(i, j);
      }
    }
    System.out.println(group);
  }

  private static int search(int r, int c) {

    if (visited[r][c] != 0) {
      if (visited[r][c] == -1) visited[r][c] = ++group;
      return visited[r][c];
    }

    visited[r][c] = -1;
    Integer d = dirMap.get(board[r][c]);
    int nextr = r + dir[d][0];
    int nextc = c + dir[d][1];

    int group = search(nextr, nextc);
    visited[r][c] = group;

    return group;
  }
}
