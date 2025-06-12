import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
  static int V;
  static List<int[]>[] graph;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    V = Integer.parseInt(br.readLine());

    graph = new List[V];
    for (int i = 0; i < V; i++) {
      graph[i] = new ArrayList<>();
    }

    StringTokenizer st;
    for (int i = 0; i < V; i++) {
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken()) - 1;
      while (st.hasMoreTokens()) {
        int b = Integer.parseInt(st.nextToken()) - 1;
        if (b == -2) break;
        graph[a].add(new int[] {b, Integer.parseInt(st.nextToken())});
      }
    }
    br.close();

    int[] maxNode = bfs(0);
    maxNode = bfs(maxNode[0]);

    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    bw.write(maxNode[1] + "");
    bw.flush();
    bw.close();
  }

  private static int[] bfs(int i) {
    Deque<int[]> q = new ArrayDeque<>(V);
    q.add(new int[]{i, 0});

    boolean[] visited = new boolean[V];
    visited[i] = true;

    int maxNode[] = new int[]{-1, 0};

    while (!q.isEmpty()) {
      int[] cur = q.poll();
      if (cur[1] > maxNode[1]) {
        maxNode = cur;
      }

      for (int[] nxt : graph[cur[0]]) {
        if (visited[nxt[0]]) continue;
        visited[nxt[0]] = true;
        q.add(new int[]{nxt[0], cur[1] + nxt[1]});
      }
    }
    return maxNode;
  }
}