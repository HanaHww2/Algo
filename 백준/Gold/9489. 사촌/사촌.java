import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
  static int N;
  static int K;
  static int k;
  static int[] arr;
  static int[] parent;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    while (true) {
      initParam(br);
      if (N == 0 && K == 0) break;
      initArr(br);
      
      makeTree();
      bw.write(getCousinCnt() + "\n");
    }
    bw.flush();
    br.close();
    bw.close();
  }

  private static int getCousinCnt() {
    int cnt = 0;
    for (int i = 1; i < N; i++) {
      if (parent[i] != parent[k] && parent[parent[i]] == parent[parent[k]]) cnt++;
    }
    return cnt;
  }

  private static void makeTree() {
    parent = new int[N];
    int p = -1;
    
    for (int i = 0; i < N; i++) {
      if (arr[i] == K) k = i;

      if (i > 0 && arr[i]-1 != arr[i-1]) p++;
      parent[i] = p;
    }
  }

  private static void initParam(BufferedReader br) throws IOException {
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());
  }

  private static void initArr(BufferedReader br) throws IOException {
    StringTokenizer st = new StringTokenizer(br.readLine());
    arr = new int[N];
    for (int i = 0; i < N; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }
  }
}