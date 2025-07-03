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
        br.close();

        WATER[SHARK[0]][SHARK[1]] = 0;
        int sec = 0;
        int eatingCnt = 0;

        while (true) {
            Pos pos = search(SHARK[0], SHARK[1]);
            if (pos == null) break;

            sec += pos.dist;
            eatingCnt++;
            if (eatingCnt == size) {
                size++;
                eatingCnt = 0;
            };

            SHARK[0] = pos.y;
            SHARK[1] = pos.x;
        }

        System.out.println(sec);
    }

    private static Pos search(int sy, int sx) {

        PriorityQueue<Pos> pq = new PriorityQueue<>();
        pq.offer(new Pos(sy, sx, 0));

        boolean[][] visited = new boolean[N][N];
        visited[sy][sx] = true;

        while (!pq.isEmpty()) {
            Pos curr = pq.poll();
            int dist = curr.dist, y = curr.y, x = curr.x;

            if (WATER[y][x] > 0 && WATER[y][x] < size) {
                WATER[y][x] = 0;
                return new Pos(y, x, dist);
            }

            for (int i = 0; i < dy.length; i++) {
                int ny = y + dy[i], nx = x + dx[i];
                if (ny < 0 || ny >= N || nx < 0 || nx >= N) continue;
                if (WATER[ny][nx] > size || visited[ny][nx]) continue;
                pq.add(new Pos(ny, nx, dist+1));
                visited[ny][nx] = true;
            }
        }
        return null;
    }

    static class Pos implements Comparable<Pos> {

        int y, x, dist;

        public Pos(int y, int x, int dist) {
            this.y = y;
            this.x = x;
            this.dist = dist;
        }

        @Override
        public int compareTo(Pos o) {
            if (dist == o.dist) {
                if (y == o.y) return x - o.x;
                return y - o.y;
            }
            return dist - o.dist;
        }
    }
}
