// 가장 큰 수 복기
// Comparator 람다식 활용
import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        StringBuilder strBuilder = new StringBuilder();
        String[] strNums = new String[numbers.length];

        for (int i = 0; i < numbers.length; i++) {
            strNums[i] = String.valueOf(numbers[i]);
        }
        Arrays.sort(strNums, (n1, n2) -> (n2 + n1).compareTo(n1 + n2));
        
        for (String n : strNUms)
            strBuilder.append(n);
        
        String answer = strBuilder.toString();
        return answer.charAt(0) == '0' ? "0" : answer;
    }
}