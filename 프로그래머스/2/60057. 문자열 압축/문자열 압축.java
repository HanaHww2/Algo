class Solution {
    private String s;
    private int len;

    public int solution(String s) {
        this.s = s;
        this.len = s.length();
        
        if (len == 1) return 1;
        
        int min = Integer.MAX_VALUE;
        for (int i = 1; i <= len / 2; i++) {
            min = Math.min(re(i), min);
        }

        return min;
    }

    private int re(int l) {
        StringBuilder result = new StringBuilder();
        int i = 0;
        
        while (i + l <= len) {
            String str = s.substring(i, i+l);
            int cnt = 1;
            i += l;

            while (i + l <= len && str.equals(s.substring(i, i+l))) {
                cnt++;
                i += l;
            }

            if (cnt > 1) {
                result.append(cnt);
            }
            result.append(str);
        }
        result.append(s, i, len);
        return result.toString().length();
    }
}