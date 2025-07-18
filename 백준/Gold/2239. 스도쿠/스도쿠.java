import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.StringTokenizer;

public class Main {
  static int[][] arr;
  static int[][] set33;
  static int[] setH;
  static int[] setV;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    arr = new int[9][9];
    set33 = new int[3][3];
    setH = new int[9];
    setV = new int[9];

    for (int i = 0; i < 9; i++) {
      byte[] str = br.readLine().getBytes(StandardCharsets.UTF_8);
      if (setH[i] == 0) setH[i] = 0b1111111110;

      for (int j = 0; j < 9; j++) {
        if (setV[j] == 0) setV[j] = 0b1111111110;
        if (set33[i/3][j/3] == 0) set33[i/3][j/3] = 0b1111111110;

        arr[i][j] = str[j] - '0';

        if (arr[i][j] != 0) {
          set33[i/3][j/3] &= ~(1 << arr[i][j]);
          setH[i] &= ~(1 << arr[i][j]);
          setV[j] &= ~(1 << arr[i][j]);
        }
      }
    }

    if (!dfs(0, 0)) return;

    StringBuilder sb = new StringBuilder();
    for  (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        sb.append(arr[i][j]);
      }
      sb.append("\n");
    }
    System.out.println(sb.toString());
  }

  private static boolean dfs(int r, int c) {
    if (r == 9) return true;

    int nc = c+1 == 9 ? 0 : c+1;
    int nr = nc == 0 ? r+1 :r;

    if (arr[r][c] == 0) {
      int mask = getIntersectionSet(r, c);
      if (mask == 0) return false;

      while (mask != 0) {
        int lsb = Integer.numberOfTrailingZeros(mask & -mask);
        mask &= (mask - 1);

        toggleSet(r, c, lsb);
        arr[r][c] = lsb;
        if (dfs(nr, nc)) return true;
        toggleSet(r, c, lsb);
        arr[r][c] = 0;
      }
    }
    else {
      return dfs(nr, nc);
    }
    return false;
  }

  private static void toggleSet(int r, int c, int lsb) {
    set33[r/3][c/3] ^= (1 << lsb);
    setH[r] ^= (1 << lsb);
    setV[c] ^= (1 << lsb);
  }

  private static int getIntersectionSet(int i, int j) {
    return set33[i/3][j/3] & setH[i] & setV[j];
  }
}
