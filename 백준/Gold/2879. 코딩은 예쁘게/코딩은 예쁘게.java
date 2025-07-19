import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int N;
  static int[] CURR;
  static int[] OBJ;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine());
    OBJ = new int[N];


    StringTokenizer st1 = new StringTokenizer(br.readLine());
    StringTokenizer st2 = new StringTokenizer(br.readLine());
    for (int j = 0; j < N; j++) {
      OBJ[j] = Integer.parseInt(st2.nextToken()) - Integer.parseInt(st1.nextToken());
    }

    int cnt = fix();
    System.out.println(cnt);
  }

  private static int fix() {
    int cnt = Math.abs(OBJ[0]);
    int before = OBJ[0];

    for (int i = 1; i < N; i++) {
      if (OBJ[i] * before > 0) {
        if (Math.abs(OBJ[i]) > Math.abs(before)) {
          cnt += Math.abs(OBJ[i]) - Math.abs(before);
        }
      } else {
        cnt += Math.abs(OBJ[i]);
      }
      before = OBJ[i];
    }
    return cnt;
  }
}
