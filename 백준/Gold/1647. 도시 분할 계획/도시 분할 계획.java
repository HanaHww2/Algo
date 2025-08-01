import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

  static int N, M;
  static ArrayList<ArrayList<Edge>> edges;
  static boolean[] visited;


  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st =  new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    visited = new boolean[N];
    edges = new ArrayList<>(N);
    for (int i = 0; i < N; i++) {
      edges.add(i, new ArrayList<>());
    }

    for (int i = 0; i < M; i++) {
      st =  new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken()) - 1;
      int b = Integer.parseInt(st.nextToken()) - 1;
      int c = Integer.parseInt(st.nextToken());
      edges.get(a).add(new Edge(b, c));
      edges.get(b).add(new Edge(a, c));
    }

    System.out.println(prim());
  }

  private static int prim() {

    PriorityQueue<Edge> pq = new PriorityQueue<>();
    pq.offer(new Edge(0, 0));

    int maxCost = 0;
    int sum = 0;

    while (!pq.isEmpty()) {
      Edge e = pq.poll();
      if (visited[e.b]) continue;

      visited[e.b] = true;
      if (e.cost > maxCost) maxCost = e.cost;
      sum += e.cost;

      for (Edge nxt : edges.get(e.b)) {
        if (visited[nxt.b]) continue;
        pq.offer(nxt);
      }
    }
    return sum - maxCost;
  }

  static class Edge implements Comparable<Edge> {
    int b, cost;

    Edge(int b, int cost) {
      this.b = b;
      this.cost = cost;
    }

    @Override
    public int compareTo(Edge o) {
      return this.cost - o.cost;
    }
  }
}
