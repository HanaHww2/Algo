import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

  static char[] arr1;
  static char[] arr2;
  static int[][] DP;

  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    arr1 = br.readLine().toCharArray();
    arr2 = br.readLine().toCharArray();

    LCS();
    String result = trackingLCS();
    System.out.println(result.length());
    System.out.println(result);
  }

  private static int LCS() {
    DP = new int[arr1.length + 1][arr2.length + 1];

    for (int i = 1; i < arr1.length + 1; i++) {
      for (int j = 1; j < arr2.length + 1; j++) {
        if (arr1[i - 1] == arr2[j - 1]) {
          DP[i][j] = DP[i - 1][j - 1] + 1;
        }
        DP[i][j] = Math.max(Math.max(DP[i - 1][j], DP[i][j - 1]), DP[i][j]);
      }
    }
    return DP[arr1.length][arr2.length];
  }

  private static String trackingLCS() {
    int i = arr1.length;
    int j = arr2.length;
    StringBuilder sb = new StringBuilder();

    while(DP[i][j] > 0) {

      if (arr1[i - 1] == arr2[j - 1]) {
        sb.append(arr1[i - 1]);
        i--;
        j--;
        continue;
      }
      if (DP[i - 1][j] >= DP[i][j - 1]) {
        i--;
      } else {
        j--;
      }
    }
    return sb.reverse().toString();
  }
}
