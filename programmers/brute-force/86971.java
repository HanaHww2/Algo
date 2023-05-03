import java.util.*;
class Solution {
    private final Map<Integer, LinkedList<Integer>> tree = new HashMap<Integer, LinkedList<Integer>>();
    
    private int answer = 100;
    private int n;
    private boolean[] visited;
    
    public int solution(int n, int[][] wires) {
        this.n = n;
        this.visited = new boolean[n+1];
        this.buildTree(wires);
        
        // 루트 노드 지정
        for (int[] w : wires) {
            visited[w[0]] = true;
            visited[w[1]] = true;
            this.bfs(w[0]);
            Arrays.fill(this.visited, false);
        }
        
        return answer;
    }
    
    // 이중 배열로 푸는 게 나았으려나...?
    private void buildTree(int[][] wires) {
        for (int[] w : wires) {
            LinkedList<Integer> t1 = this.tree.getOrDefault(w[0], new LinkedList<Integer>());
            t1.add(w[1]);
            LinkedList<Integer> t2 = this.tree.getOrDefault(w[1], new LinkedList<Integer>());
            t2.add(w[0]);
            if (t1.size() == 1) this.tree.put(w[0], t1);
            if (t2.size() == 1) this.tree.put(w[1], t2);
        }
    }
    
    // bfs 로 구현
    private final void bfs(int start) {
        
        Queue<Integer> q = new ArrayDeque<>();
        int cnt = 1;
        
        q.add(start);
        while(!q.isEmpty()) {
            int curr = q.poll();
            for (Integer item : this.tree.get(curr)) {
                if (this.visited[item] == true) continue;
                q.add(item);
                this.visited[item] = true;
                cnt++;
            }    
        }
        this.answer = Math.min(this.answer, Math.abs(cnt-(this.n-cnt)));
    }
}