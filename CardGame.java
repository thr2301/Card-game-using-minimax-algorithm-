import java.util.Arrays;
import java.util.Scanner;

public class CardGame {
    public static void main(String[] args) {
        int cards = 20;
        int groups = 4;
        int[] numOfCards = { 5, 5, 5, 5 };
        int[] numOfRem = { 2, 3, 4, 5 };

        CardAi ai = new CardAi(cards, groups, numOfCards, numOfRem);

        int[] state = { 5, 5, 5, 5 };
        int player = 1;
        int depth = 3;

        Scanner scanner = new Scanner(System.in);

        while (true) {
            if (player == 1) {
                // AI's turn
                int[] best_move = ai.minimax(state, player, depth);
                System.out.println("AI removes " + best_move[2] + " cards from group " + best_move[1]);
                state[best_move[1]] -= best_move[2];
                player = -1;
            } else {
                // Human's turn
                System.out.println("Current state: " + Arrays.toString(state));
                System.out.println("Enter group index (0 to " + (groups - 1) + "): ");
                int group = scanner.nextInt();
                System.out.println(
                        "Enter number of cards to remove (1 to " + Math.min(state[group], numOfRem[group]) + "): ");
                int cardsToRemove = scanner.nextInt();
                state[group] -= cardsToRemove;
                player = 1;
            }
            // Check if game is over
            int score = ai.estimate(state);
            if (score == -1) {
                System.out.println("Human wins!");
                break;
            } else if (score == 1) {
                System.out.println("AI wins!");
                break;
            }
        }

        scanner.close();
    }
}