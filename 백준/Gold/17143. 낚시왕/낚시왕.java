import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int[][] dir = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
  static int R, C, M;
  static Shark[] sharks;
  static int[][] graph;
  static boolean[] removed;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    R = Integer.parseInt(st.nextToken());
    C = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    graph = new int[R][C];
    sharks = new Shark[M];
    removed = new boolean[M];

    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int r = Integer.parseInt(st.nextToken()) - 1;
      int c = Integer.parseInt(st.nextToken()) - 1;
      graph[r][c] = i + 1;

      sharks[i] = new Shark(r, c,
          Integer.parseInt(st.nextToken()),
          Integer.parseInt(st.nextToken()),
          Integer.parseInt(st.nextToken()));
    }

    System.out.println(goThrough());
  }

  private static int goThrough() {

    int result = 0;

    for (int i = 0; i < C; i++) {
      result += fishing(i);
      move();
    }

    return result;
  }

  private static int fishing(int col) {

    for (int i = 0; i < R; i++) {
      if (graph[i][col] > 0) {
        int idx = graph[i][col]-1;
        int z = sharks[idx].z;

        removed[idx] = true;
        graph[i][col] = 0;
        return z;
      }
    }
    return 0;
  }

  private static void move() {

    int[][] temp = new int[R][C];

    for (int i = 0; i < M; i++) {
      if (removed[i]) continue;

      Shark s = sharks[i];
      int dist;

      if (s.d < 2) {
        dist = R == 1 ? 0 : s.s % ((R - 1) * 2);
      } else {
        dist = C == 1 ? 0 : s.s % ((C - 1) * 2);
      }

      while (dist > 0) {
        int nr = s.r + dir[s.d][0] * dist;
        int nc = s.c + dir[s.d][1] * dist;

        if (nr < 0 || nr >= R || nc < 0 || nc >= C) {
          s.d = changeDir(s.d);

          if (nr < 0) {
            dist -= s.r;
            s.r = 0;
          }
          if (nr >= R) {
            dist -= R - 1 - s.r;
            s.r = R - 1;
          }
          if (nc < 0) {
            dist -= s.c;
            s.c = 0;
          }
          if (nc >= C) {
            dist -= C - 1 - s.c;
            s.c = C - 1;
          }
          continue;
        }

        s.r = nr;
        s.c = nc;
        dist = 0;
      }

      if (temp[s.r][s.c] > 0) {
        int idx = temp[s.r][s.c] - 1;
        if (sharks[idx].z < s.z) {
          temp[s.r][s.c] = i + 1;
          removed[idx] = true;
        } else {
          removed[i] = true;
        }
        continue;
      }
      temp[s.r][s.c] = i + 1;
    }
    graph = temp;
  }

  private static int changeDir(int d) {
    if (d == 0) {
      return 1;
    }
    if (d == 1) {
      return 0;
    }
    if (d == 2) {
      return 3;
    }
    if (d == 3) {
      return 2;
    }
    return 0;
  }

  static class Shark {
    int r, c, s, d, z;

    public Shark(int r, int c, int s, int d, int z) {
      this.r = r;
      this.c = c;
      this.s = s;
      this.d = d - 1;
      this.z = z;
    }
  }
}
