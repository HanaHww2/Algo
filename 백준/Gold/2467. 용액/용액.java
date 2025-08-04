import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
  static int N;
  static int[] vals;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine());

    vals = new int[N];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      vals[i] = Integer.parseInt(st.nextToken());
    }

    int[] answer = search();
    System.out.println(answer[0] + " " + answer[1]);
  }

  private static int[] search() {
    int left = 0, right = N-1;
    int leftAnswer = 0, rightAnswer = 0;
    int min = Integer.MAX_VALUE;

    while (left < right) {
      int mix = vals[right] + vals[left];
      if (Math.abs(mix) < min) {
        min = Math.abs(mix);
        leftAnswer = vals[left];
        rightAnswer = vals[right];
      }

      if (mix == 0) break;
      if (mix < 0) {
        left++;
      } else {
        right--;
      }
    }
    return new int[] {leftAnswer, rightAnswer};
  }
}
