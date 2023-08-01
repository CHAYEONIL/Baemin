import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    static class Sit {
        int first = 0;
        int second = 0;

        public Sit(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        List<String> input = List.of(br.readLine().split(" "));

        List<Integer> newList = input.stream()
                .map(s -> Integer.parseInt(s))
                .collect(Collectors.toList());

        Collections.sort(newList);

        List<Sit> sitList = new ArrayList<>();
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < newList.size() - 1; i++) {
            for (int j = i + 1; j < newList.size(); j++) {
                if (Math.abs(newList.get(i) - newList.get(j)) < min) {
                    sitList.add(new Sit(newList.get(i), newList.get(j)));
                    min = Math.abs(newList.get(i) - newList.get(j));
                }
            }
        }

        Comparator<Sit> comparator = new Comparator<Sit>() {
            @Override
            public int compare(Sit o1, Sit o2) {
                return o1.first - o2.first;
            }
        };

        Collections.sort(sitList, comparator);
        bw.write(Integer.toString(sitList.get(0).first) + " " + Integer.toString(sitList.get(0).second));
        bw.flush();
        bw.close();
        br.close();
    }
}