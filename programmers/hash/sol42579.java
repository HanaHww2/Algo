package me.study.programmers.hash;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// 레벨 3
public class sol42579 {

    public int[] solution(String[] genres, int[] plays) {
        Map<String, ArrayList> map = new HashMap<>();
        int len = genres.length;

        for (int i = 0; i < len; i++) {
            ArrayList<Integer> tmp = map.getOrDefault(genres[i], new ArrayList<Integer>());
            tmp.add(i);
            map.put(genres[i], tmp);
        }

        List<String> sorted = map.keySet().stream().sorted((o1, o2) -> {
                    int sum1 = map.get(o1).stream().mapToInt(i -> plays[(int) i]).sum();
                    int sum2 = (int) map.get(o2).stream().mapToInt(i -> plays[(int) i]).sum();
                    return sum2 - sum1;
        })
                .collect(Collectors.toList());

        List<Integer> list = new LinkedList<>();
        for (String key: sorted) {
            ArrayList<Integer> tmp = map.get(key);
            List<Integer> sortedList = tmp.stream()
                    .sorted(Comparator.comparingInt(a -> -plays[a]))
                    .limit(2)
                    .collect(Collectors.toList());
            list.addAll(sortedList);
        }
        //list.forEach(System.out::println);
        //list.toArray(new Object[list.size()]);

        int[] answer = new int[list.size()];
        int index = 0;
        for (Integer el : list) {
            answer[index] = (int) el;
            index++;
        }
        return answer;
    }

    // 2023.05.06
    // 그루핑을 통해서, 장르별 재생횟수 비교 정렬
    // 장르 내 노래 재생횟수 비교하여 정렬 (2개까지)
    //
    // 내부 클래스와 스트림을 이용해서 처리해보았다.
    // 우선순위 큐도 가져왔다ㅎㅎ;
    // 속도 면에서 좀 아쉽다.
    static class Genre {
        int count;
        PriorityQueue<int[]> songs = new PriorityQueue<>((m1, m2) -> {
                if (m1[1] == m2[1]) return m1[0] - m2[0];
                return m2[1] - m1[1];
        }); // Collections.reverseOrder()

        Genre(int count) {
            this.count = count;
        }
        Genre addNewSong(int[] song) {
            this.songs.offer(song);
            return this;
        }
        Genre addCount(int count) {
            this.count += count;
            return this;
        }
    }

    public int[] solution2(String[] genres, int[] plays) {
        Map<String, Genre> map = new HashMap<>();
        int len = genres.length;

        for (int i = 0; i < len; i++) {
            map.put(genres[i], map.getOrDefault(genres[i], new Genre(0))
                    .addCount(plays[i])
                    .addNewSong(new int[] {i, plays[i]}));
        }

        List<Integer> sorted = map.values()
                .stream()
                .sorted(Comparator.comparingInt(o -> -o.count))
                .map(genre -> Stream.generate(genre.songs::poll)
                        // 사이즈를 명확히 정하지 않으면 리미트 크기 만큼 null 값을 전달한다. npe 유발
                        .limit(genre.songs.size()< 2? genre.songs.size() : 2)
                        .map(arr -> arr[0])
                        .collect(Collectors.toList()))
                .reduce(new ArrayList<Integer>(), (x, y) -> {
                    x.addAll(y);
                    return x;
                });

        //sorted.forEach(System.out::println);
        int[] answer = new int[sorted.size()];
        int index = 0;
        for (Integer el : sorted) {
            answer[index] = (int) el;
            index++;
        }
        return answer;
    }

    public static void main(String[] args) {
        new sol42579().solution2(new String[] {"classic", "pop", "classic", "classic"},
                new int[] {800, 600, 150, 800});
    }
}
