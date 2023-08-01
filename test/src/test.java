import java.io.*;
import java.util.*;

public class test {
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

        List<Integer> list = new ArrayList<>();
        String[] lineArr = br.readLine().split(" ");
        for (int i = 0; i < lineArr.length; i++) {
            list.add(Integer.parseInt(lineArr[i]));
        }

        Collections.sort(list);

        List<Sit> sitList = new ArrayList<>();
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = i + 1; j < list.size(); j++) {
                if (Math.abs(list.get(i) - list.get(j)) < min) {
                    sitList.add(new Sit(list.get(i), list.get(j)));
                    min = Math.abs(list.get(i) - list.get(j));
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