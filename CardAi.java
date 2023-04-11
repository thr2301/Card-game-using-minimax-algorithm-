public class CardAi {
    // private int cards;// Μ
    private int groups;// Κ
    private int[] numOfCards;// Α
    private int[] numOfRem;// Β

    public CardAi(int groups, int[] numOfCards, int[] numOfRem) {
        // this.cards = cards;
        this.groups = groups;
        this.numOfCards = numOfCards;
        this.numOfRem = numOfRem;
    }

    /*
     * public int getCards() {
     * return cards;
     * }
     */

    public int getGroups() {
        return groups;
    }

    public int[] getNumOfCards() {
        return numOfCards;
    }

    public int[] getNumOfRem() {
        return numOfRem;
    }

    public int estimate(int[] x) {
        int sum = 0;
        boolean y = true;
        for (int i = 0; i < x.length; i++) {
            sum = sum + x[i];
            if (x[i] > 1) {
                y = false;
            }
        }
        if (y) {
            return -1; // human player wins
        } else if (sum == 0) {
            return 1; // AI wins
        } else {
            return 0;
        }
    }

    public int[] minimax(int[] state, int player, int depth) {
        int score = estimate(state);
        if (score != 0 || depth == 0) {
            return new int[] { score, -1, -1 };
        }

        if (player == 1) { // MAX player
            int best_score = Integer.MIN_VALUE;
            int[] best_move = { -1, -1 };
            for (int i = 0; i < groups; i++) {
                for (int j = 1; j <= Math.min(state[i], numOfRem[i]); j++) {
                    int[] new_state = state.clone();
                    new_state[i] -= j;
                    int[] move_score = minimax(new_state, -player, depth - 1);
                    if (move_score[0] > best_score) {
                        best_score = move_score[0];
                        best_move[0] = i;
                        best_move[1] = j;
                    }
                }
            }
            return new int[] { best_score, best_move[0], best_move[1] };
        } else { // MIN player
            int best_score = Integer.MAX_VALUE;
            int[] best_move = { -1, -1 };
            for (int i = 0; i < groups; i++) {
                for (int j = 1; j <= Math.min(state[i], numOfRem[i]); j++) {
                    int[] new_state = state.clone();
                    new_state[i] -= j;
                    int[] move_score = minimax(new_state, -player, depth - 1);
                    if (move_score[0] < best_score) {
                        best_score = move_score[0];
                        best_move[0] = i;
                        best_move[1] = j;
                    }
                }
            }
            return new int[] { best_score, best_move[0], best_move[1] };
        }
    }

}
