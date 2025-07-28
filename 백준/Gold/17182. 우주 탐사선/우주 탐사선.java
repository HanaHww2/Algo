import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
  static int N, K;
  static int[][] dist;
  static HashMap<Key, Integer> MIN = new HashMap<>();

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());

    dist = new int[N][N];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        dist[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    System.out.println(dijkstra());
  }

  private static int dijkstra() {

    int mask = (1 << N) - 1;
    PriorityQueue<Path> pq = new PriorityQueue<>(Comparator.comparing(o -> o.dist));
    Path from = new Path(K, 0, 1 << K, 1);
    MIN.put(new Key(1 << K, K), 0);
    pq.add(from);

    while (!pq.isEmpty()) {
      Path cur = pq.poll();
      int curP =  cur.p;
      int curDist = cur.dist;
      int visited = cur.visited;
      int cnt = cur.cnt;

      if ((visited & mask) == mask) {
        return curDist;
      }

      for (int j = 0; j < N; j ++) {
        if (j == curP) continue;
        int nxtV = visited | 1 << j;
        int nxtD = curDist +  dist[curP][j];
        Key key = new Key(nxtV, j);
        if (MIN.getOrDefault(key, Integer.MAX_VALUE) <= nxtD) continue;

        Path nxt = new Path(j, nxtD, nxtV, cnt + 1);
        MIN.put(key, nxtD);
        pq.add(nxt);
      }
    }
    return -1;
  }

  static class Path {
    int p;
    int dist;
    int visited;
    int cnt;

    Path(int p, int dist, int visited, int cnt) {
      this.p = p;
      this.dist = dist;
      this.visited = visited;
      this.cnt = cnt;
    }
  }

  static class Key {
    int visited;
    int p;

    Key(int visited, int p) {
      this.visited = visited;
      this.p = p;
    }

    @Override
    public boolean equals(Object o) {
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      Key key = (Key) o;
      return visited == key.visited && p == key.p;
    }

    @Override
    public int hashCode() {
      return Objects.hash(visited, p);
    }
  }
}
