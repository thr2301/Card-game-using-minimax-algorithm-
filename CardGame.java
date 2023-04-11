import java.util.Arrays;
import java.util.Scanner;

public class CardGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter number of groups :");
        int groups = scanner.nextInt();
        int[] A = new int[groups];// numOfCards
        int[] B = new int[groups];// numOfRem
        System.out.println("Enter number of cards in group  :");
        for (int i = 0; i < A.length; i++) {
            A[i] = scanner.nextInt();
        }
        System.out.println("Enter number of cards that are removable from the group  :");
        for (int i = 0; i < A.length; i++) {
            B[i] = scanner.nextInt();
        }
        System.out.print("\nThe game starts\n");

        CardAi Ai = new CardAi(groups, A, B);
        int[] state = Arrays.copyOf(A, groups);
        int player = 1;
        while (true/* Ai.estimate(state) == 0 */) {
            if (player == 1) { // ai turn
                System.out.println("Ai's turn");
                int[] aiMove = Ai.minimax(state, 1, 5);
                state[aiMove[1]] -= aiMove[2];
                System.out.println("Ai removes " + aiMove[2] + " cards from group " + aiMove[1] + "!!!");
                player = -1;
            } else { // player turn
                System.out.println("Human player's turn");
                System.out.println("Enter the group and number of cards you wish to remove :");
                int group = scanner.nextInt();
                int numOfCards = scanner.nextInt();
                state[group] -= numOfCards;
                player = 1;
            }
            System.out.println(Arrays.toString(state));
            int result = Ai.estimate(state);
            if (result == 1) {
                System.out.println("AI wins");
                break;
            } else if (result == -1) {
                System.out.println("Human player wins");
                break;
            }
        }
    }
}