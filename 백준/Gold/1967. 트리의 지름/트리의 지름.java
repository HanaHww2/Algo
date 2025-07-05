import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

  static int N;
  static List<int[]>[] TREE;
  static int MAX = 0;
  static Set<Integer> NODES = new HashSet<>();

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine());

    TREE = new List[N];
    StringTokenizer st;
    for (int i = 0; i < N-1; i++) {
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken())-1;
      int b = Integer.parseInt(st.nextToken())-1;
      int c = Integer.parseInt(st.nextToken());

      if (TREE[a] == null) {
        TREE[a] = new ArrayList<>();
      }
      if (TREE[b] == null) {
        TREE[b] = new ArrayList<>();
      }
      TREE[a].add(new int[] {c, b});
      TREE[b].add(new int[] {c, a});
    }

    getNodes(0, 0, new boolean[N]);

    int max = 0;
    for (int i : NODES) {
      max = Math.max(max, dfs(i, 0,  new boolean[N]));
    }
    System.out.println(max);
  }

  private static void getNodes(int i, int cost, boolean[] visited) {

    boolean isLeaf = true;
    visited[i] = true;
    if (TREE[i] == null) return;
    
    for (int[] node :TREE[i]) {
      if (visited[node[1]]) continue;
      if (isLeaf) isLeaf = false;
      getNodes(node[1], node[0] + cost, visited);
    }
    if (isLeaf && MAX <= cost) {
      MAX = cost;
      NODES.add(i);
    }
  }

  private static int dfs(int i, int cost, boolean[] visited) {

    int max = cost;
    visited[i] = true;
    if (TREE[i] == null) return max;
    
    for (int[] node : TREE[i]) {
      if (visited[node[1]]) continue;
      max = Math.max(max, dfs(node[1], node[0] + cost, visited));
    }
    return max;
  }
}
