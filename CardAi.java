

import java.util.*;

public class CardAi {
    int M, K;
    int[] A, B;

    // constructor
    public CardAi(int M, int K, int[] A, int[] B) {
        this.M = M;
        this.K = K;
        this.A = A;
        this.B = B;
    }

    public int getM() {
        return M;
    }

    public int getK() {
        return K;
    }

    public int[] getA() {
        return A;
    }

    public int[] getB() {
        return B;
    }

    // generates the game tree
    private List<int[]> generateGameTree(int[] state) {
        List<int[]> moves = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            if (state[i] > 0) {
                for (int j = 1; j <= Math.min(B[i], state[i]); j++) {
                    moves.add(new int[] { i, j });
                }
            }
        }
        return moves;
    }

    // evaluates a given state of a game for a player with the given turn.
    private int evaluateWinner(int[] state, boolean turn) {
        int sum = 0;
        for (int i = 0; i < K; i++) {
            sum += state[i];
        }
        if (sum == 1) {
            if (turn) {
                return -1000;
            } else {
                return 1000;
            }
        } else {
            return 0;
        }
    }

    // initializes the minimax algorithm
    public int[] minimax(int[] state, boolean turn, int depth) {
        int[] bestMove = null;
        int bestScore;
        if (turn) {
            bestScore = -1000;
        } else {
            bestScore = 1000;
        }
        List<int[]> possibleMoves = generateGameTree(state);
        if (possibleMoves.isEmpty() || depth == 0) {
            return null;
        }
        for (int[] move : possibleMoves) {
            int[] nextState = Arrays.copyOf(state, K);
            nextState[move[0]] -= move[1];
            int score = evaluateWinner(nextState, turn);
            int[] nextMove = minimax(nextState, !turn, depth - 1);
            if (turn) {
                if (nextMove == null || score > bestScore) {
                    bestScore = score;
                    bestMove = move;
                }
            } else {
                if (nextMove == null || score < bestScore) {
                    bestScore = score;
                    bestMove = move;
                }
            }
        }
        return bestMove;
    }

}
