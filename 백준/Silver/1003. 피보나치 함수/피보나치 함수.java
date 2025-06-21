import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int[][] DP;
    static boolean[] VISITED;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        DP = new int[40+1][2];
        VISITED = new boolean[40+1];

        int n;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < T; i++) {
            n = Integer.parseInt(br.readLine());

            int[] result = fibonacciN(n);
            sb.append(result[0]).append(" ").append(result[1]).append("\n");
        }
        System.out.println(sb);
    }

    private static int[] fibonacciN(int n) {

        if (n < 0) return null;
        if (VISITED[n]) return DP[n];

        fibonacciN(n-1);

        if (n == 0) DP[0][0] = 1;
        else if (n == 1) DP[1][1] = 1;
        else {
            DP[n][0] = DP[n-1][0] + DP[n-2][0];
            DP[n][1] = DP[n-1][1] + DP[n-2][1];
        }
        VISITED[n] = true;
        return DP[n];
    }
}