class Solution {
    
    private int[] numbers;
    private int target;
    
    public int solution(int[] numbers, int target) {
        this.numbers = numbers;
        this.target = target;        
        
        return this.dfs(0, 0);
    }
    
    public int dfs(int curr, int idx) {
        
        if (idx == numbers.length) {
            if (target == curr) return 1;
            else return 0;
        }
        
        return dfs(curr + numbers[idx], idx + 1) + dfs(curr - numbers[idx], idx + 1);
    }
}