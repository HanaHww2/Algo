import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int MOD = 1_000;
    static Map<Long, int[][]> RESULT;
    static Set<Long> CHECKED;
    static int N;
    static long B;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        B = Long.parseLong(st.nextToken());

        RESULT = new HashMap<>();
        CHECKED = new HashSet<>();

        int[][] arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        RESULT.put(1L, arr);
        CHECKED.add(1L);
        re(B);
        System.out.println(toPrint(B));
    }
    
    private static String toPrint(long B) {

        StringBuilder sb = new StringBuilder();
        int[][] arr = RESULT.get(B);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(arr[i][j] % MOD).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
    
    private static int[][] re(long cnt) {

        if (CHECKED.contains(cnt)) {
            return RESULT.get(cnt);
        }

        if (cnt % 2 == 0) {
            int[][] arr1 = re(cnt / 2);
            RESULT.put(cnt, matMul(arr1, arr1));
        } else {
            int[][] arr1 = re(cnt - 1);
            RESULT.put(cnt, matMul(arr1, RESULT.get(1L)));
        }
        CHECKED.add(cnt);
        return RESULT.get(cnt);
    }

    private static int[][] matMul(int[][] ARR1, int[][] ARR2) {

        int[][] result = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int temp = 0;
                for (int k = 0; k < N; k++) {
                    temp += ARR1[i][k] * ARR2[k][j] % MOD;
                }
                result[i][j] = temp;
            }
        }

        return result;
    }
}
