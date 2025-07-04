import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int[][] D = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    static int R, C, T;
    static int[][][] ROOM;
    static int S, E;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        ROOM = new int[T+1][R][C];
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                ROOM[0][i][j] = Integer.parseInt(st.nextToken());
                if (j == 0 && ROOM[0][i][j] == -1) {
                    if (S != 0) {
                        E = i;
                        continue;
                    }
                    S = i;
                }
            }
        }
        br.close();

        int t = 1;
        while (t <= T) {

            spread(t);
            airPurified(t);
            t++;
        }

        int total = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (ROOM[T][i][j] <= 0) continue;
                total += ROOM[T][i][j];
            }
        }

        System.out.println(total);
    }

    private static void airPurified(int t) {
        int sy = S, sx = 0;
        int ey = E, ex = 0;
        int i = 0, j = 0;
        int[] cw = D[i];
        int[] ccw = D[(-j + D.length) % D.length];

        int sTemp = 0, eTemp = 0;
        while (sTemp != -1 || eTemp != -1) {

            if (sTemp != -1) {
                int nsy = sy + ccw[0], nsx = sx + ccw[1];
                while (nsy < 0 || nsy >= R || nsx < 0 || nsx >= C) {
                    if (++j >= D.length) break;
                    ccw = D[(-j + D.length) % D.length];
                    nsy = sy + ccw[0]; nsx = sx + ccw[1];
                }
                int temp1 = ROOM[t][nsy][nsx];
                if (temp1 != -1) {
                    ROOM[t][nsy][nsx] = sTemp;
                }
                sTemp = temp1;
                sy = nsy; sx= nsx;
            }

            if (eTemp != -1) {
                int ney = ey + cw[0], nex = ex + cw[1];
                while (ney < 0 || ney >= R || nex < 0 || nex >= C) {
                    if (++i >= D.length) break;
                    cw = D[i];
                    ney = ey + cw[0]; nex = ex + cw[1];
                }
                int temp2 = ROOM[t][ney][nex];
                if (temp2 != -1) {
                    ROOM[t][ney][nex] = eTemp;
                }
                eTemp = temp2;
                ey = ney; ex = nex;
            }
        }
    }

    private static void spread(int t) {

        ROOM[t][S][0] = -1;
        ROOM[t][E][0] = -1;

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (ROOM[t-1][i][j] <= 0) continue;
                int amount =  ROOM[t-1][i][j];
                int divide = amount / 5;

                int cnt = 0;
                for (int[] d : D) {
                    int ny = i + d[0], nx = j + d[1];
                    if (ny < 0 || ny >= R || nx < 0 || nx >= C) continue;
                    if (ROOM[t-1][ny][nx] == -1) continue;
                    ROOM[t][ny][nx] += divide;
                    cnt++;
                }
                ROOM[t][i][j] += amount - divide * cnt;
            }
        }
    }
}
