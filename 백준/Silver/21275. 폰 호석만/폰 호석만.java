import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/21275
public class Main {

  static int[] arr1, arr2;
  static int[] maxNum;
  static List<long[]> result;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    maxNum = new int[2];

    arr1 = toNumberArr(st.nextToken(), 0);
    arr2 = toNumberArr(st.nextToken(), 1);
    result = new ArrayList<>(1);
    System.out.println(check());
  }

  private static int[] toNumberArr(String s, int n) {

    int[] arr = new int[s.length()];
    int max = 2;
    for (int i = 0; i < s.length(); i++) {
      if (Character.isDigit(s.charAt(i))) arr[i] = s.charAt(i) - '0';
      else arr[i] = s.charAt(i) - 87;
      max = Math.max(max, arr[i] + 1);
    }
    maxNum[n] = max;

    return arr;
  }

  private static String check() {

    for (int i = maxNum[0]; i < 37; i++) {
      long num1 = convertNum(arr1, i);
      for (int j = maxNum[1]; j < 37; j++) {
        if (i == j) continue;

        long num2 = convertNum(arr2, j);
        if (num1 == num2) {
          if (!result.isEmpty()) return "Multiple";
          result.add(new long[]{num1, i, j});
        }
      }
    }
    if (result.isEmpty()) return "Impossible";
    return result.get(0)[0] + " " + result.get(0)[1] + " " + result.get(0)[2];
  }

  private static long convertNum(int[] arr, int i) {
    long num = 0L;
    for(int j = 0; j < arr.length; j++) num += arr[j] * Math.pow(i, arr.length - j - 1);
    return num;
  }
}
