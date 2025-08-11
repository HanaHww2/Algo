import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
  static int N;
  static int[] array;

  static int a, b, c;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine());
    array = new int[N];

    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      array[i] = Integer.parseInt(st.nextToken());
    }
    Arrays.sort(array);

    search();
    System.out.println(array[a] + " " + array[b] + " " + array[c]);
  }

  private static void search() {

    long min = Long.MAX_VALUE;

    for (int i = 0; i < N - 2; i++) {
      for (int j = i + 1; j < N - 1; j++) {

        long mix = array[i] + array[j];
        int mid = bs(j + 1, N - 1, mix);
        long thirdMix = Math.abs(mix + array[mid]);
        if (min > thirdMix) {
          min = thirdMix;
          a  = i;
          b = j;
          c = mid;
          if (thirdMix == 0) return;
        }
      }
    }
  }

  private static int bs(int left, int right, long mix) {

    int ans = right;
    long min = Long.MAX_VALUE;

    while (left <= right) {
      int mid = left + (right - left) / 2;
      long thirdMix = mix + array[mid];

      if (thirdMix == 0) return mid;

      if (min > Math.abs(thirdMix)) {
        ans = mid;
        min = Math.abs(thirdMix);
      }

      if  (thirdMix > 0) {
        right = mid - 1;
      } else {
        left = mid + 1;
      }
    }
    return ans;
  }
}
