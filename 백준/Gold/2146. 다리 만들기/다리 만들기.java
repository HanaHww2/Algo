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
  static Queue<int[]> q;
  static int shortest;

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
    int result = bfs();
    System.out.println(result);
  }

  private static int bfs() {
    int[][] overSea = new int[N][N];
    int min = Integer.MAX_VALUE;

    while (!q.isEmpty()) {
      int[] node = q.poll();

      if (overSea[node[0]][node[1]] >= min) break;

      // 바다 건너
      for (int i = 0; i < dy.length; i++) {
        int ny = node[0] + dy[i], nx = node[1] + dx[i];
        if (ny < 0 || ny >= N || nx < 0 || nx >= N || MAP[ny][nx] == -1 || MAP[ny][nx] == node[2]) continue;

        // 다른 섬 도달
        if (MAP[ny][nx] != node[2] && MAP[ny][nx] > 1) {
          min = Math.min(min, overSea[node[0]][node[1]] + overSea[ny][nx]);
        }

        // 방문 거리 체크
        if (MAP[ny][nx] == 0) {
          overSea[ny][nx] = overSea[node[0]][node[1]] + 1;
          MAP[ny][nx] = node[2];
          q.add(new int[] {ny, nx, node[2]});
        }
      }
    }
    return min;
  }

  private static void initMap() {
    int land = 1;
    q = new ArrayDeque<>(N*N);

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        if (MAP[i][j] == 1) markMap(i, j, ++land);
      }
    }
  }

  private static void markMap(int y, int x, int land) {

    boolean edge = false;
    for (int d = 0 ; d < dy.length; d++) {
      int ny = y + dy[d], nx = x + dx[d];
      if (ny < 0 || ny >= N || nx < 0 || nx >= N || MAP[ny][nx] == -1 || MAP[ny][nx] > 1) continue;
      if (MAP[ny][nx] == 0) {
        MAP[y][x] = land;
        edge = true;
        continue;
      }
      MAP[ny][nx] = -1;
      markMap(ny, nx, land);
    }
    if (edge) q.add(new int[] {y, x, land});
  }
}
