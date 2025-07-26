import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PipedOutputStream;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

  static int N, M, K;
  static char[][] board;
  static String target;
  static int[] dy = {0, 1, 0, -1};
  static int[] dx = {1, 0, -1, 0};
  static int CNT;
  static Map<Pos, Integer> map = new HashMap<Pos, Integer>();
  static Set<Pos> set = new HashSet<Pos>();

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());

    board = new char[N][M];
    for (int i = 0; i < N; i++) {
      char[] arr = br.readLine().toCharArray();
      board[i] = Arrays.copyOf(arr, arr.length);
    }
    target = new StringBuilder(br.readLine()).reverse().toString();

    search();
    System.out.println(CNT);
  }

  private static void search() {

    Deque<Pos> q = new ArrayDeque<>();
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        if (board[i][j] != target.charAt(0))
          continue;
        Pos pos = new Pos(i, j, 0);
        q.add(pos);
        map.compute(pos, (key, prev) -> prev == null ? 1 : prev + 1);
      }
    }

    int total = 0;
    while(!q.isEmpty()) {
      Pos pos = q.poll();
      int cnt = map.get(pos);
      if (pos.idx == target.length() - 1) {
        total += cnt;
        continue;
      }

      for (int d = 0; d < dy.length; d++) {
        for (int k = 1; k <= K; k++) {
          int nr = pos.r + dy[d] * k;
          int nc = pos.c + dx[d] * k;
          if (nr >= 0 && nr < N && nc >= 0 && nc < M) {
            if (board[nr][nc] == target.charAt(pos.idx + 1)) {
              Pos npos = new Pos(nr, nc, pos.idx + 1);
              map.compute(npos, (key, prev) -> prev == null ? cnt : prev + cnt);
              if (set.contains(npos)) continue;
              set.add(npos);
              q.add(npos);
            }
          }
        }
      }
    }
    CNT = total;
  }

  static class Pos {
    int r, c, idx;

    Pos(int r, int c, int idx) {
      this.r = r;
      this.c = c;
      this.idx = idx;
    }

    @Override
    public boolean equals(Object o) {
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      Pos pos = (Pos) o;
      return r == pos.r && c == pos.c && idx == pos.idx;
    }

    @Override
    public int hashCode() {
      return Objects.hash(r, c, idx);
    }
  }
}
