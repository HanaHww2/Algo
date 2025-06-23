import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};

    static int N;
    static char[][] HOME;
    static boolean[][] VISITED;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        HOME = new char[N][N];

        int[] door = new int[2];
        String s;
        for (int i = 0; i < N; i++) {
            s = br.readLine();
            for (int j = 0; j < N; j++) {
                HOME[i][j] = s.charAt(j);
                if (HOME[i][j] == '#') door = new int[]{i, j};
            }
        }
        br.close();

        VISITED = new boolean[N][N];
        int result = bfs(door[0], door[1]);
        System.out.println(result);
    }

    private static int bfs(int i, int j) {

        PriorityQueue<int[]> q = new PriorityQueue<>(Comparator.comparingInt(item -> item[3]));
        for (int k = 0; k < 4; k++) {
            q.offer(new int[] {i, j, k, 0}); // y, x, dir
        }
        VISITED[i][j] = true;

        while (!q.isEmpty()) {
            int[] from = q.poll();
            int ny = from[0] + dy[from[2]], nx = from[1] + dx[from[2]];
            if (ny < 0 || ny >= N || nx < 0 || nx >= N || HOME[ny][nx] == '*' || VISITED[ny][nx]) continue;

            //VISITED[ny][nx] = true;
            if (HOME[ny][nx] == '.') q.offer(new int[] {ny, nx, from[2], from[3]});
            if (HOME[ny][nx] == '!') {
                VISITED[ny][nx] = true;
                q.offer(new int[] {ny, nx, from[2], from[3]});
                int[] dirs = reflect(from[2]);
                for (int dir : dirs) {
                    q.offer(new int[] {ny, nx, dir, from[3] + 1});
                }
            }
            if (HOME[ny][nx] == '#') return from[3];
        }
        return -1;
    }

    private static int[] reflect(int i) {
        if (i > 1) {
            return new int[] {0, 1};
        }
        return new int[] {2, 3};
    }
}
