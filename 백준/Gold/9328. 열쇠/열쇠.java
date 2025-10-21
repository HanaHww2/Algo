import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

  static int[][] D = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
  static int TC;
  static int H, W;
  static char[][] MAP;
  static Set<Character> KEY;
  static boolean[][] VISITED;
  static Set<Pos> ENTR;
  static Set<Pos> V_ENTR;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    TC = Integer.parseInt(br.readLine());

    for (int i = 0; i < TC; i++) {

      StringTokenizer st = new StringTokenizer(br.readLine());
      H = Integer.parseInt(st.nextToken());
      W = Integer.parseInt(st.nextToken());
      MAP = new char[H][W];

      for (int j = 0; j < H; j++) {
        MAP[j] = br.readLine().toCharArray();
      }

      KEY = new HashSet<>();
      char[] chars = br.readLine().toCharArray();
      for (int k = 0; k < chars.length; k++) {
        if (chars[k] == '0') break;
        KEY.add(chars[k]);
      }
      System.out.println(solve());
    }

  }

  private static int solve() {

    ENTR = new HashSet<>();
    V_ENTR = new HashSet<>();

    for (int i = 0; i < H; i++) {
      for (int j = 0; j < W; j++) {
        if (i == 0 || j == 0 || i == H - 1 || j == W - 1) {
          ENTR.add(new Pos(i, j));
        }
      }
    }

    int result = 0;
    VISITED = new boolean[H][W];

    while (!ENTR.equals(V_ENTR)) {
      for (Pos e : ENTR) {
        int i = e.x;
        int j = e.y;
        V_ENTR.add(e);

        if (!VISITED[i][j]) {
            if (MAP[i][j] == '*') continue;
            if (MAP[i][j] == '.' || MAP[i][j] == '$' || isEnglishAlphabet(MAP[i][j])) {

              result += dfs(i, j);
            }
        }
      }
    }
    return result;
  }

  private static int dfs(int i, int j) {
    VISITED[i][j] = true;

    int cnt = 0;
    if (MAP[i][j] == '*') {
      return cnt;
    }
    if (isUpperAlphabet(MAP[i][j])) {
      if (!KEY.contains(Character.toLowerCase(MAP[i][j]))) return cnt;
      MAP[i][j] = '.';
    }
    if (isLowerAlphabet(MAP[i][j])) {
      KEY.add(MAP[i][j]);
      MAP[i][j] = '.';
      VISITED = new boolean[H][W];
      V_ENTR.clear();
    }
    if (MAP[i][j] == '$') {
      cnt += 1;
      MAP[i][j] = '.';
    }

    if (MAP[i][j] == '.') {
      for (int[] d: D) {
        int nh = i + d[0];
        int nw = j + d[1];
        if (nh >= 0 && nh < H && nw >= 0 && nw < W && !VISITED[nh][nw]) {
          cnt += dfs(nh, nw);
        }
      }
    }
    return cnt;
  }

  private static boolean isLowerAlphabet(char c) {
    // 소문자 범위 (a: 97, z: 122)
    return (c >= 'a' && c <= 'z');
  }

  private static boolean isUpperAlphabet(char c) {
    // 대문자 범위 (A: 65, Z: 90)
    return (c >= 'A' && c <= 'Z');
  }


  private static boolean isEnglishAlphabet(char c) {
    return isLowerAlphabet(c) || isUpperAlphabet(c);
  }

  static class Pos {
    int x, y;

    Pos(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }
}
