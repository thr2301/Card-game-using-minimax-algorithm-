
import java.util.*;

public class CardGame {
    int M, K;
    int[] A, B;
    int depth;

    public CardGame(int M, int K, int[] A, int[] B, int depth) {
        this.M = M;
        this.K = K;
        this.A = A;
        this.B = B;
        this.depth = depth;
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

    public int getDepth() {
        return depth;
    }

    // this method return true if the game is over
    public boolean isGameOver(int[] state) {
        for (int i = 0; i < K; i++) {
            if (state[i] > 0) {
                return false;
            }
        }
        return true;
    }

    // this methods initializes the game
    public void play(CardAi ai) {
        boolean turn = true;
        int[] state = Arrays.copyOf(A, K);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            if (turn) {
                System.out.println("Current state: " + Arrays.toString(state));
                int[] move = ai.minimax(state, turn, depth);
                if (move == null) {
                    System.out.println("AI cannot make a move, the game ends with the human be winner.");
                    break;
                }
                System.out.println("AI removes " + move[1] + " cards from group " + (move[0] + 1));
                state[move[0]] -= move[1];
            } else {
                System.out.println("Current state: " + Arrays.toString(state));
                int group, numCards;
                do {
                    System.out.print("Enter group to remove cards from (1-" + K + "): ");
                    group = scanner.nextInt() - 1;
                } while (group < 0 || group >= K || state[group] == 0);
                do {
                    System.out.print("Enter number of cards to remove (1-" + Math.min(B[group], state[group]) + "): ");
                    numCards = scanner.nextInt();
                } while (numCards < 1 || numCards > Math.min(B[group], state[group]));
                System.out.println("You remove " + numCards + " cards from group " + (group + 1));
                state[group] -= numCards;
            }
            if (isGameOver(state)) {
                if (turn) {
                    System.out.println("Ai wins");
                } else {
                    System.out.println("Human wins");
                }
                break;
            }
            turn = !turn;
        }
    }

}
