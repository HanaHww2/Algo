package me.study.programmers;

import java.util.*;
import static java.util.stream.Collectors.*;

// 레벨2
public class sol42578 {

    // 과거에 풀었던 풀이
    // 배열을 활용함. 하지만 매번 키값을 순회한다.
    int typeIndex = 0;
    String[] types;
    int[] counts;

    public int solution(String[][] clothes) {
        int answer = 1;
        int len = clothes.length;
        this.types = new String[len];
        this.counts = new int[len];

        for(int i = 0; i < len; i++){
            setKeyVal(clothes[i][1]);
        }

        for(int i = 0; i < typeIndex; i++){
            // 배열 초기화 값이 0이었으므로, 2를 더한다.
            answer *= (counts[i]+2);
        }
        return answer - 1;
    }

    public void setKeyVal(String type){
        for (int i = 0; i < types.length; i++) {
            // 동일한 타입이 있으면 카운트 추가
            if(type.equals(types[i])){
                counts[i]++; 
                return;
            }
        }
        // 동일한 타입이 없으면 새롭게 생성, counts[typeIndex]는 0 에서 시작
        types[typeIndex] = type;
        typeIndex++;
        return;
    }

    /*
     * 2023.05.06
     * 간단한 해시맵 활용
     */
    Map<String, Integer> map = new HashMap<>();

    public int solution2(String[][] clothes) {
        for (String[] item : clothes) {
            map.put(item[1], map.getOrDefault(item[1], 0)+1);
        }
        Integer answer = 1;
        for (Integer cnt : map.values()) {
            answer *= (cnt+1);
        }
        return answer-1;
    }

    // 다른 사람 풀이
    // groupingBy, reduce  
    public int solution3(String[][] clothes) {
        return Arrays.stream(clothes)
                .collect(groupingBy(p -> p[1], mapping(p -> p[0], counting())))
                .values()
                .stream()
                .reduce(1L, (x, y) -> x * (y + 1)).intValue() - 1;
    }
}