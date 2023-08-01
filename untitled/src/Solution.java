import java.util.*;

class Solution {
    public String[] solution(String[] board, int y, int x) {
        int n = board.length;
        int m = board[0].length();

        // 상하좌우 및 대각선 방향의 좌표 변화량
        int[] dy = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dx = {-1, 0, 1, -1, 1, -1, 0, 1};

        // 방문 여부를 저장하는 배열
        boolean[][] visited = new boolean[n][m];

        // 시작 위치를 큐에 추가하고 방문 표시
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{y, x});
        visited[y][x] = true;

        // BFS 탐색
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int cy = cur[0];
            int cx = cur[1];
            int count = 0;

            // 현재 위치와 인접한 8개의 칸 중에서 지뢰가 있는 개수를 세기
            for (int i = 0; i < 8; i++) {
                int ny = cy + dy[i];
                int nx = cx + dx[i];
                if (ny >= 0 && ny < n && nx >= 0 && nx < m && board[ny].charAt(nx) == 'M') {
                    count++;
                }
            }

            // 지뢰가 있으면 해당 위치를 'X'로 표시하고 종료
            if (board[cy].charAt(cx) == 'M') {
                board[cy] = board[cy].substring(0, cx) + 'X' + board[cy].substring(cx+1);
                break;
            }

            // 지뢰가 없으면 해당 위치를 'B'로 표시하고 인접한 빈 칸을 큐에 추가
            if (count == 0) {
                board[cy] = board[cy].substring(0, cx) + 'B' + board[cy].substring(cx+1);
                for (int i = 0; i < 8; i++) {
                    int ny = cy + dy[i];
                    int nx = cx + dx[i];
                    if (ny >= 0 && ny < n && nx >= 0 && nx < m && !visited[ny][nx]) {
                        q.offer(new int[]{ny, nx});
                        visited[ny][nx] = true;
                    }
                }
            }
            // 지뢰가 있으면 해당 위치에 지뢰 개수를 표시
            else {
                board[cy] = board[cy].substring(0, cx) + count + board[cy].substring(cx+1);
            }
        }

        // 모든 빈 칸에 대해서 BFS 탐색 수행
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(board[i].charAt(j) == 'E' && !visited[i][j]) {
                    Queue<int[]> q2 = new LinkedList<>();
                    q2.offer(new int[]{i, j});
                    visited[i][j] = true;

                    while (!q2.isEmpty()) {
                        int[] cur = q2.poll();
                        int cy = cur[0];
                        int cx = cur[1];
                        int count = 0;

                        // 현재 위치와 인접한 8개의 칸 중에서 지뢰가 있는 개수를 세기
                        for (int k = 0; k < 8; k++) {
                            int ny = cy + dy[k];
                            int nx = cx + dx[k];
                            if (ny >= 0 && ny < n && nx >= 0 && nx < m && board[ny].charAt(nx) == 'M') {
                                count++;
                            }
                        }

                        // 지뢰가 있으면 해당 위치에 지뢰 개수를 표시
                        if (count > 0) {
                            board[cy] = board[cy].substring(0, cx) + count + board[cy].substring(cx+1);
                        }
                        // 지뢰가 없으면 해당 위치를 'B'로 표시하고 인접한 빈 칸을 큐에 추가
                        else {
                            board[cy] = board[cy].substring(0, cx) + 'B' + board[cy].substring(cx+1);
                            for (int k = 0; k < 8; k++) {
                                int ny = cy + dy[k];
                                int nx = cx + dx[k];
                                if (ny >= 0 && ny < n && nx >= 0 && nx < m && !visited[ny][nx]) {
                                    q2.offer(new int[]{ny, nx});
                                    visited[ny][nx] = true;
                                }
                            }
                        }
                    }
                }
            }
        }

        // 결과값 반환
        return board;
    }
    public static void main(String[] args) {
        Solution sol = new Solution();

        // 예시 1
        String[] board1 = {"EEEEE", "EEMEE", "EEEEE", "EEEEE"};
        int y1 = 2;
        int x1 = 0;
        String[] answer1 = {"B1E1B", "B1E1B", "B1111B", "BBBBB"};
        String[] result1 = sol.solution(board1, y1, x1);
        System.out.println(Arrays.equals(answer1, result1)); // true

        // 예시 2
        String[] board2 = {"MME", "EEE", "EME"};
        int y2 = 0;
        int x2 = 0;
        String[] answer2 = {"XME", "EEE", "EME"};
        String[] result2 = sol.solution(board2, y2, x2);
        System.out.println(Arrays.equals(answer2, result2)); // true

        // 추가 테스트 케이스
        String[] board3 = {"EMMEE", "EEEEE", "EEMME", "EEEEE", "EEEEE"};
        int y3 = 2;
        int x3 = 2;
        String[] answer3 = {"EBB1E", "E332E", "B1M1E", "1111E", "00000"};
        String[] result3 = sol.solution(board3, y3, x3);
        System.out.println(Arrays.equals(answer3, result3)); // true

        String[] board4 = {"EMMMM", "EEEEE", "EEMME", "EEEEE", "EEEEE"};
        int y4 = 2;
        int x4 = 2;
        String[] answer4 = {"EBBBX", "E55E3", "B3M3E", "1113E", "00000"};
        String[] result4 = sol.solution(board4, y4, x4);
        System.out.println(Arrays.equals(answer4, result4)); // true

        String[] board5 = {"EEEEE", "EEEEE", "EEEEE", "EEEEE", "EEEEE"};
        int y5 = 2;
        int x5 = 2;
        String[] answer5 = {"00000", "00000", "00000", "00000", "00000"};
        String[] result5 = sol.solution(board5, y5, x5);
        System.out.println(Arrays.equals(answer5, result5)); // true
    }

}
