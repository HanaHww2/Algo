import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int N;
    static int[] ARR;
    static int[] LIS;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        ARR = new int[N];
        for (int i = 0; i < N; i++) {
            ARR[i] = Integer.parseInt(br.readLine());
        }

        int idx = 0;
        LIS = new int[N+1];
        for (int i = 0; i < N; i++) {
            if (ARR[i] > LIS[idx]) {
                LIS[++idx] = ARR[i];
            }
            int pos = bs(i, idx);
            LIS[pos] = ARR[i];
        }

        System.out.println(N - idx);
    }

    private static int bs(int i, int idx) {
        int left = 0, right = idx;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (LIS[mid] < ARR[i]) left = mid + 1;
            else right = mid;
        }
        return right;
    }
}
