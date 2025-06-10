import java.util.*;

class Solution {
    
  private ArrayDeque<Integer> stack = new ArrayDeque<>();
  
  public int solution(int[] order) {
    int box =1;
    int idx = 0;

    while (box < order.length + 1) {
      stack.add(box++);
        
      while (stack.getLast() == order[idx]) {
    
        stack.pollLast();
        idx++;
          
        if (stack.isEmpty()) break;
      }
    }

    return idx;
  }
}