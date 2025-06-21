import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] DP;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        DP = new int[3][2]; // min, max
        StringTokenizer st;
        for (int i = 1; i < N + 1; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            calc(a, b, c);
        }

        calc(0, 0, 0);
        System.out.println(DP[1][1] + " " +DP[1][0]);
    }

    private static void calc(int a, int b, int c) {

        int lmin = Math.min(DP[0][0], DP[1][0]);
        int rmin = Math.min(DP[1][0], DP[2][0]);
        int lmax = Math.max(DP[0][1], DP[1][1]);
        int rmax = Math.max(DP[1][1], DP[2][1]);

        DP[0][0] = a + lmin;
        DP[2][0] = c + rmin;
        DP[1][0] = b + Math.min(lmin, rmin);

        DP[0][1] = a + lmax;
        DP[2][1] = c + rmax;
        DP[1][1] = b + Math.max(lmax, rmax);
    }
}
