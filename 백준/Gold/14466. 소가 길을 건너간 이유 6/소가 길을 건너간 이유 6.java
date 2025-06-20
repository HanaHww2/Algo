import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

  static int[] dy = {1, -1, 0, 0};
  static int[] dx = {0, 0, -1, 1};
  static Bridges bridges;
  static int N, K, R;
  static int[][] FARM;
  static Set<Position> COWS;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());
    R = Integer.parseInt(st.nextToken());

    bridges = new Bridges();

    for (int i = 0; i < R; i++) {
      st = new StringTokenizer(br.readLine());
      int r1, c1, r2, c2;
      r1 = Integer.parseInt(st.nextToken())-1;
      c1 = Integer.parseInt(st.nextToken())-1;
      r2 = Integer.parseInt(st.nextToken())-1;
      c2 = Integer.parseInt(st.nextToken())-1;
      bridges.setBridge(new Position(r1, c1), new Position(r2, c2));
      bridges.setBridge(new Position(r2, c2), new Position(r1, c1));
    }

    COWS = new HashSet<>();
    for (int i = 0; i < K; i++) {
      st = new StringTokenizer(br.readLine());
      COWS.add(new Position(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1));
    }

    FARM = new int[N][N];
    int num = 0;
    List<Integer> cowsCnt = new ArrayList<>();
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        if (FARM[i][j] != 0) continue;
        cowsCnt.add(bfs(i, j, ++num));
      }
    }
    int length = cowsCnt.size();
    int result = 0;
    for (int i = 0; i < length; i++) {
      for (int j = i + 1; j < length; j++) {
        result += cowsCnt.get(i) * cowsCnt.get(j);
      }
    }
    System.out.println(result);
  }

  private static int bfs(int r, int c, int mark) {

    Deque<Position> q = new ArrayDeque<>();
    q.add(new Position(r, c));
    int cowsCnt = 0;
    FARM[r][c] = mark;

    while (!q.isEmpty()) {
      Position curr = q.poll();
      int x = curr.x, y = curr.y;
      if (COWS.contains(curr)) cowsCnt++;

      for (int i = 0; i < dx.length; i++) {
        int nx = x + dx[i];
        int ny = y + dy[i];
        if (nx >= 0 && nx < N && ny >= 0 && ny < N && FARM[ny][nx] == 0) {
          Position nxt = new Position(ny, nx);
          if (bridges.ifExists(curr, nxt)) continue;
          if (FARM[ny][nx] != 0) continue;
          FARM[ny][nx] = mark;

          q.offer(nxt);
        }
      }
    }
    return cowsCnt;
  }

  static class Bridges {
    Map<Position, Set<Position>> bridgeMap;

    Bridges() {
      bridgeMap = new HashMap<>();
    }

    public void setBridge(Position from, Position to) {

      bridgeMap.computeIfAbsent(from, k -> new HashSet<>()).add(to);
    }

    public boolean ifExists(Position curr, Position nxt) {
      if (!bridgeMap.containsKey(curr)) return false;
      return bridgeMap.get(curr).contains(nxt);
    }
  }

  static class Position {
    int y, x;

    Position(int y, int x) {
      this.y = y;
      this.x = x;
    }

    @Override
    public boolean equals(Object pos) {
      return (this.y == ((Position) pos).y && this.x == ((Position) pos).x);
    }

    @Override
    public int hashCode() {
      return x * 100000 + y;
    }
  }
}
