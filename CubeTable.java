public class CubeTable {

    private final int N; // number of cubes
    private final int K; // number of rows
    private final int[][] state; // current state of the table

    public CubeTable(int N, int K, int[][] state) {
        this.N = N;
        this.K = K;
        this.state = new int[N][3];
        for (int i = 0; i < N; i++) {
            this.state[i] = state[i].clone();
        }
    }

    public static boolean is_valid_state() {
        // Check that each cube is placed on the table or on another cube
        for (int i = 0; i < N; i++) {
            int x = state[i][0];
            int y = state[i][1];
            int above = state[i][2];
            if (y == 0 && above != -1) {
                // Cube is on another cube but not on the table
                return false;
            }
            if (y > 0 && above == -1) {
                // Cube is on the table but not on another cube
                return false;
            }
            if (y > 0 && above >= i) {
                // Cube is on another cube but the index of the cube above it is
                // greater than or equal to its own index, indicating a cycle
                return false;
            }
        }
        // Check that the coordinates of each cube satisfy the constraints
        for (int i = 0; i < N; i++) {
            int x = state[i][0];
            int y = state[i][1];
            if (x < 1 || x > K || y < 0 || y > 3) {
                // Cube is outside the bounds of the table or the rows
                return false;
            }
            if (y == 0 && x != i + 1) {
                // Cube is on the table but not at its index + 1
                return false;
            }
        }
        // All constraints are satisfied
        return true;
    }

    public static boolean is_free_cube(int cube) {
        int index = getCubeIndex(state, cube);
        int x = index / N;
        int y = index % N;
        return (y == N - 1) || (state[x][y + 1] == 0);
    }

    public static int getCubeIndex(int cube) {
        int N = state.length;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (state[i][j] == cube) {
                    return i * N + j;
                }
            }
        }
        return -1; // cube not found
    }

    public static double move_cost(int[] curr_pos, int[] next_pos) {
        int curr_x = curr_pos[0], curr_y = curr_pos[1];
        int next_x = next_pos[0], next_y = next_pos[1];
        double cost = 0.0;
        if (next_y > curr_y) {
            cost = next_y - curr_y;
        } else if (next_y < curr_y) {
            cost = 0.5 * (curr_y - next_y);
        } else {
            cost = 0.75;
        }
        return cost;
    }

    // other methods and fields as needed
}
