import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.file.Path;
import java.sql.SQLOutput;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import java.util.StringTokenizer;
import javax.sound.midi.Soundbank;

public class Main {

  static int[] dz = {1, -1, 0, 0, 0, 0};
  static int[] dx = {0, 0, 1, -1, 0, 0};
  static int[] dy = {0, 0, 0, 0, 1, -1};
  static int L;
  static int R;
  static int C;
  static char[][][] BUILDING;
  static boolean[][][] VISITED;
  static Path START;

  static String SUCCESS_MESSAGE = "Escaped in %d minute(s).";
  static String FAIL_MESSAGE = "Trapped!";
  static String NEW_LINE = "\n";

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    while (true) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      L = Integer.parseInt(st.nextToken());
      R = Integer.parseInt(st.nextToken());
      C = Integer.parseInt(st.nextToken());

      if (L == 0) break;

      BUILDING = new char[L][R][C];

      for (int i = 0; i < L; i++) {
        for (int j = 0; j < R; j++) {
          String cols = br.readLine();
          for (int k = 0; k < C; k++) {
            BUILDING[i][j][k] = cols.charAt(k);
            if (BUILDING[i][j][k] == 'S') {
              START = new Path(i, j, k, 0);
            }
          }
        }
        br.readLine();
      }

      int result = bfs();
      if (!sb.isEmpty()) sb.append(NEW_LINE);
      if (result != -1) sb.append(String.format(SUCCESS_MESSAGE, result));
      else sb.append(FAIL_MESSAGE);
    }
    br.close();
    System.out.println(sb);
  }

  private static int bfs() {
    boolean visited[][][] = new boolean[L][R][C];
    visited[START.z][START.y][START.x] = true;
    Deque<Path> q = new ArrayDeque<>();
    q.add(START);

    while (!q.isEmpty()) {
      Path cur = q.poll();

      for (int i = 0; i < dz.length; i++) {

        int nz = cur.z + dz[i], ny = cur.y + dy[i], nx = cur.x + dx[i];
        if (nz < 0 || nz >= L || ny < 0 || ny >= R || nx < 0 || nx >= C
            || BUILDING[nz][ny][nx] == '#' || visited[nz][ny][nx]) continue;
        if (BUILDING[nz][ny][nx] == 'E') return cur.time + 1;

        q.add(new Path(nz, ny, nx, cur.time + 1));
        visited[nz][ny][nx] = true;
      }
    }
    return -1;
  }

  static class Path {
    int z, y, x, time;
    public Path(int z, int y, int x, int time) {
      this.z = z; this.y = y; this.x = x; this.time = time;
    }
  }
}
