import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int TC;
    static int N, M, W;
    static List<Edge> ROAD;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        TC = Integer.parseInt(br.readLine());

        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < TC; i++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());

            ROAD = new ArrayList<>();

            int s, e, t;
            for (int j = 0; j < M + W; j++) {
                st = new StringTokenizer(br.readLine());
                s = Integer.parseInt(st.nextToken()) - 1;
                e = Integer.parseInt(st.nextToken()) - 1;
                t = Integer.parseInt(st.nextToken());

                if (j < M) {
                    ROAD.add(new Edge(s, e, t));
                    ROAD.add(new Edge(e, s, t));
                } else {
                    ROAD.add(new Edge(s, e, -t));
                }
            }

            sb.append(bf()? "YES": "NO").append("\n");
        }
        System.out.println(sb);
    }

    private static boolean bf() {

        int[] distance =  new int[N];
        Arrays.fill(distance, 1_000_000_000);

        distance[0] = 0;
        for (int i = 0; i < N; i++) {
            boolean updatable = false;
            for (Edge e : ROAD) {
                if (distance[e.des] > distance[e.dep] + e.w) {
                    distance[e.des] = distance[e.dep] + e.w;
                    updatable = true;
                    if (i == N-1) {
                        return true;
                    }
                }
            }
            if (!updatable) break;
        }
        return false;
    }
    
    static class Edge {
        int dep;
        int des;
        int w;

        public Edge(int dep, int des, int w) {
            this.dep = dep;
            this.des = des;
            this.w = w;
        }
    }
}
