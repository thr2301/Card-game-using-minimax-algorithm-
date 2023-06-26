
//ATHANASIOS ROUDIS 5098 
//STYLIANOS SIMANTIRAKIS 5127
import java.util.*;

public class MainGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the depth :");
        int depth = scanner.nextInt();
        System.out.print("Enter the value of M: ");
        int M = scanner.nextInt();

        System.out.print("Enter the value of K: ");
        int K = scanner.nextInt();

        int[] A = new int[K];
        int[] B = new int[K];

        for (int i = 0; i < K; i++) {
            System.out.print("Enter the value of A[" + i + "]: ");
            A[i] = scanner.nextInt();
            do {
                System.out.print("Enter the value of B[" + i + "]: ");
                B[i] = scanner.nextInt();
            } while (B[i] >= A[i]);
        }
        CardGame game = new CardGame(M, K, A, B, depth);
        CardAi ai = new CardAi(M, K, A, B);
        game.play(ai);
    }

}
