import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static int size = 2;

    static int[][] WATER;
    static int N;
    static int[] SHARK;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        WATER = new int[N][N];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                WATER[i][j] = Integer.parseInt(st.nextToken());
                if (WATER[i][j] == 9) SHARK = new int[]{i, j};
            }
        }

        System.out.println(search());
    }

    private static int search() {

        int sec = 0;
        int eatingCnt = 0;
        boolean[][] visited = new boolean[N][N];
        PriorityQueue<int[]> pq = new PriorityQueue<>(getPreyComparator());

        pq.offer(new int[]{0, SHARK[0], SHARK[1]});
        visited[SHARK[0]][SHARK[1]] = true;
        WATER[SHARK[0]][SHARK[1]] = 0;

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int dist = curr[0], y = curr[1], x = curr[2];

            if (WATER[y][x] > 0 && WATER[y][x] < size) {
                WATER[y][x] = 0;
                eatingCnt++;
                sec += dist;
                dist = 0;
                pq = new PriorityQueue<>(getPreyComparator());
                visited = new boolean[N][N];
            }
            if (eatingCnt == size) {
                size++;
                eatingCnt = 0;
            };

            for (int i = 0; i < dy.length; i++) {
                int ny = y + dy[i], nx = x + dx[i];
                if (ny < 0 || ny >= N || nx < 0 || nx >= N) continue;
                if (WATER[ny][nx] > size || visited[ny][nx]) continue;
                pq.add(new int[]{dist+1, ny, nx});
                visited[ny][nx] = true;
            }
        }
        return sec;
    }

    private static Comparator<int[]> getPreyComparator() {
        return (x, y) -> {
            if (x[0] - y[0] == 0) {
                if (x[1] - y[1] == 0) {
                    return x[2] - y[2];
                }
                return x[1] - y[1];
            }
            return x[0] - y[0];
        };
    }
}
