import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int V;
    static int S;
    static List<Map<Integer, Integer>> EDGES;
    static int[] DIST;
    static boolean[] VISITED;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(br.readLine()) - 1;
        DIST = new int[V];

        EDGES = new ArrayList<>(V);
        for (int i = 0; i < V; i++) {
            EDGES.add(i, new HashMap<>());
            DIST[i] = Integer.MAX_VALUE;
        }
        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;
            int w = Integer.parseInt(st.nextToken());
            EDGES.get(u).put(v, Math.min(w, EDGES.get(u).getOrDefault(v, Integer.MAX_VALUE)));
        }

        dijkstra();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < V; i++) {
            sb.append(i == S ? 0 : DIST[i]== Integer.MAX_VALUE ? "INF": DIST[i]).append("\n");
        }
        System.out.println(sb);
    }

    private static void dijkstra() {
        VISITED = new boolean[V];

        int[] start = {0, S};
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparing(x -> x[0]));
        pq.add(start);

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int w = cur[0];
            int u = cur[1];

            if (VISITED[u]) continue;
            DIST[u] = w;
            VISITED[u] = true;

            EDGES.get(u).forEach((key, value) -> {
                if (VISITED[key] || DIST[key] <= w + value) return;
                pq.add(new int[]{w + value, key});
                DIST[key] = w + value;
            });
        }
    }
}
