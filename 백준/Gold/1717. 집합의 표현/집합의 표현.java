import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
  static final String YES = "YES";
  static final String NO = "NO";
  static final String NEW_LINE = "\n";

  static int N, M;
  static int[] PARENT;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    PARENT = new int[N+1];

    for (int i = 1; i <= N; i++) {
      PARENT[i] = i;
    }

    StringBuilder sb = new StringBuilder();
    for (int j = 0; j < M; j++) {
      st = new StringTokenizer(br.readLine());
      int i = Integer.parseInt(st.nextToken());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());


      if (i == 0) {
        union(a, b);
      } else {
        sb.append(findParent(a)==findParent(b)? YES : NO).append(NEW_LINE);
      }
    }
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    bw.write(sb.toString());
    bw.flush();
    
    br.close();
    bw.close();
  }

  private static boolean union(int a, int b) {
    int pa = findParent(a);
    int pb = findParent(b);

    if (pa == pb) return false;
    if (pa < pb) {
      PARENT[pb] = pa;
    } else {
      PARENT[pa] = pb;
    }
    return true;
  }

  private static int findParent(int a) {

    if (PARENT[a] == a) return a;
    return PARENT[a]= findParent(PARENT[a]);
  }
}