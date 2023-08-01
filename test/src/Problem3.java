import java.io.*;
import java.util.List;

class Problem3 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        List<String> list = List.of(br.readLine().split(""));
        List<String> input = List.of(br.readLine().split(""));
        int count = 0;

        for (int i = 0; i<input.size(); i++){
            for (int j =0;j<list.size();j++){
                if(input.get(i) == list.get(j)) {
                    count += 1;

            }
        }
        System.out.println(list);
        System.out.println((input));
        System.out.println(count);
    }
}