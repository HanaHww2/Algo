import java.util.*;
class Solution {
    public String[] solution(String[] files) {
        Arrays.sort(files, this::comparator);
        return files;
    }
    
    private int comparator(String s1, String s2) {
        String[] s1Arr = this.splitFileName(s1); 
        String[] s2Arr = this.splitFileName(s2); 
        
        int headOrder = s1Arr[0].compareToIgnoreCase(s2Arr[0]);
        if (headOrder != 0) return headOrder;
        
        int numberOrder = Integer.parseInt(s1Arr[1]) - Integer.parseInt(s2Arr[1]);
        if (numberOrder != 0) return numberOrder;
        
        return 0; // 순서를 유지하기 위해 0을 반환해야 한다. 자바가 내부적으로 퀵정렬을 썼던가
    }
    
    private String[] splitFileName(String name) {
        String[] part = new String[3];
        int headIndex = 0, numberIndex = 0;
        boolean isHead = true;
        
        for (int i = 0; i < name.length(); i++) {
            char c = name.charAt(i);
            
            if (isHead && '0' <= c  && c <= '9') {
                isHead = false;
                headIndex = i;
            }
            if (!isHead 
                && ((c < '0' || c > '9') || i == headIndex + 5)) {
                numberIndex = i;
                break;
            }
        }
        
        part[0] = name.substring(0, headIndex);
        if (numberIndex == 0) {
            part[1] = name.substring(headIndex, name.length());
            return part;
        }
        part[1] = name.substring(headIndex, numberIndex);
        part[2] = name.substring(numberIndex, name.length());
      
        return part;
    }
}