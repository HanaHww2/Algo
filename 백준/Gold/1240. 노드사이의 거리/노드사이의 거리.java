import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

  static BufferedReader br;
  static BufferedWriter bw;

  static int N;
  static int M;
  static List<Edge>[] graph;
  static int[][] tree;
  static int[] dist;

  public static void main(String[] args) throws IOException {

    br = new BufferedReader(new InputStreamReader(System.in));
    init();

    tree = new int[N][2]; // (parent, depth)
    dist = new int[N];
    makeTree(0);

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < M; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken()) - 1; int b = Integer.parseInt(st.nextToken()) - 1;
      sb.append(getDist(a, b)).append("\n");
    }

    bw = new BufferedWriter(new OutputStreamWriter(System.out));
    bw.write(sb.toString());
    bw.flush();

    br.close();
    bw.close();
  }

  private static int getDist(int a, int b) {
    int answer = 0;

    if (tree[a][1] > tree[b][1]) {
      int temp = a;
      a = b; b = temp;
    }

    while (a != b) {
      if (tree[b][1] > tree[a][1]) {
        answer = answer + dist[b];
        b = tree[b][0];
        continue;
      }
      answer = answer + dist[a] + dist[b];
      a = tree[a][0]; b = tree[b][0];
    }
    return answer;
  }

  private static void makeTree(int fr) {

    for (Edge nxt : graph[fr]) {
      if (tree[fr][0] == nxt.n) continue;
      tree[nxt.n][0] = fr;
      tree[nxt.n][1] = tree[fr][1] + 1;
      dist[nxt.n] = nxt.w;
      makeTree(nxt.n);
    }
  }

  private static void init() throws IOException {
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken()); M = Integer.parseInt(st.nextToken());

    graph = new List[N];
    for (int i = 0; i < N; i++) {
      graph[i] = new ArrayList<>();
    }

    for (int i = 0; i < N - 1; i++) {
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken()) - 1; int b = Integer.parseInt(st.nextToken()) - 1;
      int c = Integer.parseInt(st.nextToken());
      graph[a].add(new Edge(b, c));
      graph[b].add(new Edge(a, c));
    }
  }

  static class Edge {
    int n, w;
    public Edge(int n, int w) {
      this.n = n;
      this.w = w;
    }
  }
}
