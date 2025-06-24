import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static Tunnel TUNNEL = new Tunnel();
    static String NEW_LINE = "\n";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st;
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            st.nextToken();

            int j = 1;
            SortedMap<Tunnel, Tunnel> map = TUNNEL.downstairs;
            while(st.hasMoreTokens()){
                Tunnel next = new Tunnel(j++, st.nextToken());
                next = map.getOrDefault(next, next);
                map.put(next, next);
                map = next.downstairs;
            }
        }
        br.close();

        StringBuilder sb = new StringBuilder();
        readyToPrint(TUNNEL, sb, "");
        System.out.println(sb);
    }

    private static void readyToPrint(Tunnel tunnel, StringBuilder sb, String prefix) {

        tunnel.downstairs.values().forEach(next -> {
            sb.append(prefix).append(next.prey).append(NEW_LINE);
            readyToPrint(next, sb, "--" + prefix);
        });
    }

    static class Tunnel {
        int depth;
        String prey;
        SortedMap<Tunnel, Tunnel> downstairs;

        Tunnel(){
            this.depth = 0;
            this.prey = "";
            this.downstairs = new TreeMap<>((a, b) ->
                    a.depth == b.depth ? a.prey.compareTo(b.prey) : a.depth - b.depth);
        }

        Tunnel(int depth, String prey) {
            this.depth = depth;
            this.prey = prey;
            this.downstairs = new TreeMap<>((a, b) ->
                    a.depth == b.depth ? a.prey.compareTo(b.prey) : a.depth - b.depth);
        }

        @Override
        public boolean equals(Object obj) {
            return this.depth == ((Tunnel)obj).depth && this.prey.equals(((Tunnel)obj).prey);
        }

        @Override
        public int hashCode() {
            return this.depth + this.prey.hashCode();
        }
    }
}
