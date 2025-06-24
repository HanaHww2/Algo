import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static Tunnel TUNNEL = new Tunnel();
    static String NEW_LINE = "\n";
    static String SEPARATOR = "--";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st;
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            st.nextToken();

            SortedMap<String, Tunnel> map = TUNNEL.downstairs;
            while(st.hasMoreTokens()){
                String s = st.nextToken();
                map.putIfAbsent(s, new Tunnel(s));
                map = map.get(s).downstairs;
            }
        }
        br.close();

        StringBuilder sb = new StringBuilder();
        TUNNEL.readyToPrint(sb, "");
        System.out.println(sb);
    }

    static class Tunnel {
        String prey;
        SortedMap<String, Tunnel> downstairs;

        Tunnel() {
            this.prey = "";
            this.downstairs = new TreeMap<>();
        }

        Tunnel(String prey) {
            this.prey = prey;
            this.downstairs = new TreeMap<>();
        }

        public void readyToPrint(StringBuilder sb, String prefix) {

            this.downstairs.keySet().forEach(next -> {
                sb.append(prefix).append(next).append(NEW_LINE);
                this.downstairs.get(next).readyToPrint(sb, SEPARATOR + prefix);
            });
        }
    }
}
