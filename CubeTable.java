import java.util.*;

public class CubeTable {

    private int N;// number of cubes
    private int K;// number of rows
    private int[][] state;// current state of the table

    public CubeTable(int N, int K, int[][] state) {
        this.N = N;
        this.K = K;
        this.state = new int[N][K];
        for (int i = 0; i < N; i++) {
            this.state[i] = state[i].clone();
        }
    }

    public int getN() {
        return N;
    }

    public int getK() {
        return K;
    }

    public int[][] getState() {
        return state;
    }

    public boolean isFreeCube(int cube) {
        int index = getCubeIndex(cube);
        int N = state.length;
        int x = index / N;
        int y = index % N;
        return (y == N - 1) || (state[x][y + 1] == 0);
    }

    public int getCubeIndex(int cube) {
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

    public double moveCost(int[] currPos, int[] nextPos) {
        // int currX = currPos[0];
        int currY = currPos[1];
        // int nextX = nextPos[0];
        int nextY = nextPos[1];
        double cost = 0.0;
        if (nextY > currY) {
            cost = nextY - currY;
        } else if (nextY < currY) {
            cost = 0.5 * (currY - nextY);
        } else {
            cost = 0.75;
        }
        return cost;
    }

    public boolean isValidState() {
        for (int i = 0; i < N; i++) {
            int y = state[i][1];
            int above = state[i][2];
            if (y == 0 && above != 1) {
                return false;
            } else if (y > 0 && above == -1) {
                return false;
            } else if (y > 0 && above >= i) {
                return false;
            }

        }
        for (int i = 0; i < N; i++) {
            int x = state[i][0];
            int y = state[i][1];
            if (x < 1 || x > K || y < 0 || y > 3) {
                return false;
            } else if (y == 0 && x != i + 1) {
                return false;
            }
        }
        return true;
    }

    public List<Node> getNeighbors(Node node) {
        List<Node> neighbors = new ArrayList<>();
        CubeTable currState = node.getState();

        // CubeTable currState = node.getState();
        for (int i = 0; i < N; i++) {
            if (isFreeCube(i)) {
                int currIndex = getCubeIndex(i);
                int currX = currIndex / N;
                int currY = currIndex % N;
                for (int j = 0; j < N; j++) {
                    if (j != currIndex) {
                        int nextX = j / N;
                        int nextY = j % N;
                        if (currState.state[nextX][nextY] == 0 || currState.state[nextX][nextY] == i) {
                            int[][] nextState = new int[N][K];
                            for (int k = 0; k < N; k++) {
                                nextState[k] = currState.state[k].clone();
                            }
                            nextState[nextX][nextY] = i;
                            if (currY == 0) {
                                nextState[currX][currY] = 0;
                            } else {
                                int above = nextState[currX][currY + 1];
                                nextState[currX][currY] = above;
                                if (above == 0) {
                                    nextState[currX][currY + 1] = -1;
                                } else {
                                    nextState[currX][currY + 1] = currState.state[currX][currY + 1];
                                }
                            }
                            CubeTable neighborState = new CubeTable(N, K, nextState);
                            double cost = moveCost(new int[] { currX, currY }, new int[] { nextX, nextY });
                            Node neighborNode = new Node(neighborState, node, node.getCost() + cost);
                            // Node neighborNode = new Node(neighborState, node, node.getCost() + cost);
                            neighbors.add(neighborNode);
                        }
                    }
                }
            }
        }
        return neighbors;
    }
}