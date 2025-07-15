import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static int N;
  static PriorityQueue<Village> pq;
  public static void main(String[] args) throws IOException {

    N = Integer.parseInt(br.readLine());
    pq = new PriorityQueue<>(N);

    long total = 0;
    for (int i = 0; i < N; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int dist = Integer.parseInt(st.nextToken());
      int pop = Integer.parseInt(st.nextToken());
      total += pop;

      pq.add(new Village(dist, pop));
    }

    long half = (total + 1) / 2;
    long sum = 0;
    int temp = 0;
    while (!pq.isEmpty() && half > sum) {
      Village village = pq.poll();
      sum += village.population;
      temp = village.distance;
    }
    if (sum >= half) System.out.println(temp);
  }

  static class Village implements Comparable<Village> {
    int distance;
    int population;

    Village(int distance, int population) {
      this.distance = distance;
      this.population = population;
    }

    @Override
    public int compareTo(Village o) {
      return this.distance - o.distance;
    }
  }
}

