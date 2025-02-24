class UserSolution {
    private int N, M;
    private int[][] map;

    public void init(int N, int M, int[][] Map) {
        this.N = N;
        this.M = M;
        this.map = new int[N][N];

        for (int i = 0; i < N; i++) {
            System.arraycopy(Map[i], 0, this.map[i], 0, N);
        }
    }

    public Solution.Result findTreasureChest(int[][] Pieces) {
        Solution.Result result = new Solution.Result();

        outer:
        for (int startY = 0; startY <= N - M; startY++) {
            for (int startX = 0; startX <= N - M; startX++) {
                for (int rotation = 0; rotation < 4; rotation++) {
                    if (matchesPattern(startY, startX, Pieces, rotation)) {
                        result.y = startY;
                        result.x = startX;
                        break outer;
                    }
                }
            }
        }

        return result;
    }

    private boolean matchesPattern(int startY, int startX, int[][] Pieces, int rotation) {
        int centerY = -1, centerX = -1;

        // 보물 찾기
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                if (getRotatedValue(Pieces, i, j, rotation) == 9) {
                    centerY = i;
                    centerX = j;
                    break;
                }
            }
            if (centerY != -1) break;
        }

        if (centerY == -1 || centerX == -1) return false;

        // 지도에 맞는지 확인
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                int rotatedValue = getRotatedValue(Pieces, i, j, rotation);
                if (rotatedValue > 0) {
                    int mapY = startY + i - centerY;
                    int mapX = startX + j - centerX;

                    // 경계 체크
                    if (mapY < 0 || mapY >= N || mapX < 0 || mapX >= N) {
                        return false;
                    }

                    // 일치하는지 체크
                    if (map[mapY][mapX] != 1) {
                        return false;
                    }
                }
            }
        }

        return true; // 모든 조건 만족 시 성공
    }


    private int getRotatedValue(int[][] Pieces, int i, int j, int rotation) {
        switch (rotation) {
            case 0:
                return Pieces[i][j];
            case 1:
                return Pieces[M - 1 - j][i];
            case 2:
                return Pieces[M - 1 - i][M - 1 - j];
            case 3:
                return Pieces[j][M - 1 - i];
            default:
                return 0;
        }
    }
}
