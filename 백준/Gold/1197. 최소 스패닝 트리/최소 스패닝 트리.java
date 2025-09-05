import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.function.BiFunction;

public class Main {

  static int V, E;
  static Map<Integer, List<int[]>> graph = new HashMap<>();

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    V = Integer.parseInt(st.nextToken());
    E = Integer.parseInt(st.nextToken());

    for (int i = 0; i < E; i++) {
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken()) - 1;
      int b = Integer.parseInt(st.nextToken()) - 1;
      int c = Integer.parseInt(st.nextToken());

      graph.compute(a, (k, v) -> v == null ? new ArrayList<>() : v).add(new int[] { b, c });
      graph.compute(b, (k, v) -> v == null ? new ArrayList<>() : v).add(new int[] { a, c });
    }

    System.out.println(prim());
  }

  private static int prim() {
    int mst = 0;
    int cnt = 0;
    PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
    boolean[] visited = new boolean[V];

    for (int[] edge : graph.get(0)) {
      pq.offer(new int[]{0, edge[0], edge[1]});
    }
    visited[0] = true;

    while(!pq.isEmpty()) {

      int[] cur = pq.poll();
      int x = cur[0];
      int y = cur[1];

      if (visited[y]) continue;

      mst += cur[2];
      cnt++;
      visited[y] = true;

      if (cnt == V - 1) break;

      for (int[] edge : graph.get(y)) {
        if (visited[edge[0]]) continue;
        pq.offer(new int[]{y, edge[0], edge[1]});
      }
    }

    return mst;
  }
}
