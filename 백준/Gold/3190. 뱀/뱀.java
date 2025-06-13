import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.function.Function;

// https://www.acmicpc.net/problem/3190
public class Main {
  static int N;
  static int BOARD[][];
  static Map<Integer, Dir> DIR = new HashMap<>();
  static int[] D;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine());
    BOARD = new int[N][N];

    int k = Integer.parseInt(br.readLine());
    for (int i = 0; i < k; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      BOARD[Integer.parseInt(st.nextToken())-1][Integer.parseInt(st.nextToken())-1] = 1; // 사과
    }
    int l = Integer.parseInt(br.readLine());
    for (int i = 0; i < l; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      DIR.put(Integer.parseInt(st.nextToken()), Dir.valueOf(st.nextToken()));
    }

    // 뱀 초기화
    Deque<int[]> snail = new ArrayDeque(N *N);
    snail.add(new int[]{0, 0});
    BOARD[0][0] = 2;
    int time = 0;
    D = new int[]{0,1};

    while (true) {

      time++;
      int[] head = snail.getLast();
      int[] newHead = new int[] {head[0] + D[0], head[1] + D[1]};

      // 이동 가능 확인
      if (newHead[0] < 0 || newHead[0] >= N || newHead[1] < 0 || newHead[1] >= N
          || BOARD[newHead[0]][newHead[1]] == 2) {
        System.out.println(time);
        break;
      }

      // 사과가 없으면 꼬리 하나 제거
      if (BOARD[newHead[0]][newHead[1]] != 1) {
        int[] tail = snail.poll();
        BOARD[tail[0]][tail[1]] = 0;
      }

      BOARD[newHead[0]][newHead[1]] = 2; // 머리 이동
      snail.add(newHead);

      if (DIR.containsKey(time)) {
        Dir dir = DIR.get(time);
        D = dir.transform(D);
      }
    }
  }

  enum Dir {
    D(dir -> new int[] {dir[1], -dir[0]}),
    L(dir -> new int[] {-dir[1], dir[0]});

    private final Function<int[], int[]> transformation;

    Dir(Function<int[], int[]> transformation) {
      this.transformation = transformation;
    }

    public final int[] transform(int[] dir) {
      return this.transformation.apply(dir);
    }
  }
}
