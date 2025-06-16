import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

  static int[] dy = {0, 0, 1, -1};
  static int[] dx = {1, -1, 0, 0};

  static int N;
  static int[][] MAP;
  static int cnt;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine());

    MAP = new int[N][N];
    StringTokenizer st;
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        MAP[i][j] = Integer.parseInt(st.nextToken());
      }
    }
    br.close();

    initMap();
    int result = getShortestBridge();
    System.out.println(result);
  }

  private static int getShortestBridge() {
    int shortest = Integer.MAX_VALUE;
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        if (MAP[i][j] < 2) continue;
        shortest = Math.min(bfs(i, j), shortest);
      }
    }
    return shortest;
  }

  private static int bfs(int y, int x) {
    boolean[][] visited = new boolean[N][N];
    Queue<int[]> q = new ArrayDeque<>(100);
    q.add(new int[] {y, x, 0});
    int from = MAP[y][x];

    while (!q.isEmpty()) {
      int[] node = q.poll();

      // 다른 섬 도달
      if (MAP[node[0]][node[1]] != from && MAP[node[0]][node[1]] > 1) return node[2] - 1;

      // 바다 건너
      for (int i = 0; i < dy.length; i++) {
        int ny = node[0] + dy[i], nx = node[1] + dx[i];
        if (ny < 0 || ny >= N || nx < 0 || nx >= N || MAP[ny][nx] == -1 || MAP[ny][nx] == from) continue;

        if (visited[ny][nx]) continue;
        visited[ny][nx] = true;

        q.add(new int[] {ny, nx, node[2] + 1});
      }
    }
    return Integer.MAX_VALUE;
  }

  private static void initMap() {
    int land = 1;
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        if (MAP[i][j] == 1) markMap(i, j, ++land);
      }
    }
    cnt = land - 1;
  }

  private static void markMap(int y, int x, int land) {

    for (int d = 0 ; d < dy.length; d++) {
      int ny = y + dy[d], nx = x + dx[d];
      if (ny < 0 || ny >= N || nx < 0 || nx >= N || MAP[ny][nx] == -1 || MAP[ny][nx] > 1) continue;
      if (MAP[ny][nx] == 0) {
        MAP[y][x] = land;
        continue;
      }
      MAP[ny][nx] = -1;
      markMap(ny, nx, land);
    }
  }
}
