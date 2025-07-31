import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
  static int H, W;
  static int[] space;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    H = Integer.parseInt(st.nextToken());
    W = Integer.parseInt(st.nextToken());

    space = new int[W];

    st = new StringTokenizer(br.readLine());

    for (int i = 0; i < W; i++) {
      space[i] = Integer.parseInt(st.nextToken());
    }

    System.out.println(trap());
  }

  private static int trap() {

    ArrayDeque<Integer> stack = new ArrayDeque<>();
    int i = 0;
    int sum = 0;

    while (i < W) {
      if (!stack.isEmpty()) {
        while (space[stack.peekFirst()] < space[i]) {
          int mid = stack.pop();
          if (stack.isEmpty()) break;
          int minWall = Math.min(space[stack.peekFirst()], space[i]);
          sum += (minWall - space[mid]) * (i - stack.peekFirst() - 1);
        }
      }
      stack.push(i++);
    }
    return sum;
  }
}
