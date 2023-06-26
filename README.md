# Card-game-using-minimax-algorithm-for a University project

## The problem 
Build a program that will play the following game two against a user
players taking turns. There are M identical cards placed on a table which are
divided into K groups. Each group Oi (i=1,…,K) initially has Ai≥2 cards. Obviously the sum of
Ai is equal to M. The values of M, K and Ai are given at the start of the game.
During the game each player removes one or more cards of a single team.
For each group Oi is defined at the start of the game the maximum number of cards Bi (Bi<Ai) which
can be removed by a player each time they play.
The player who plays each time chooses one of the groups (let Oi) with a non-zero number of cards
(Νi>0) and can remove a number of cards of this group from 1 to min(Βi,Ni) (Ni is the
number of cards of group Oi at the time the player plays). The winner is the player who will
remove the last card from the table.

## The explanation of the solution

***• Game state***
The state of the game is defined by an array of integers called state. To define the initial state we must create an object of the CardAi class, with the total number of cards (int M), the number of groups (int K), the number of cards per group (int[] A) and the maximum number they can remove each time a player plays (int[] B). The initial state takes the values of array A. The final state exists when there is only one card in any position of the state array.

***• Value of final states***
The value of the final statements is defined by:
- In 1000 if the max player (computer) wins
- In -1000 if the min player (user) wins
- At 0 if there are more than one cards in the game, that means the game is not over yet.

***• Brief description of the code***

**- MainGame class:**
In this class there is only the main method. Initially, the user is asked to type in the depth he wishes the game tree to have (essentially the difficulty of the game is selected). He is then asked to enter the total number of cards (int M), the number of groups (int K), the number of cards per group (int[] A), and the maximum number that can be removed each time a player plays ( int[] B). Then the game is implemented using the play(CardAi ai) method of the CardGame class.
- CardGane class:
- Method isGameOver(int[] state):
Checks if the game has reached its end, returns true there is only one card left in the current state of the game.
- Play(CardAi ai) method:
Performs the computer move using the minimax(state, turn, depth) method of the CardAi class to find the best move. Then the human player makes his move, more specifically he chooses a valid group and number of cards he wants to remove. The above process is repeated until isGameOver() returns true . Then there is a check on who played last, i.e. who is the winner.

**- CardAi class:**
- Method evaluateWinner(int[] state, boolean turn):
If the total numerical value of the state table is equal to 1, this means that there is only one card left in the game. In this case, the method returns a value indicating the winner of the game, based on the player whose turn it is to play: if it is the maximum player (turn=true), then a negative value (-1000) is returned which indicates that the computer loses, while if it is the minimum player (turn=false), then a positive value (1000) is returned indicating that the computer wins. If there is more than one card in play, the evaluateWinner() method returns a null value, indicating that the state has no particular value for the player whose turn it is to play.
- generateGameTree(int[] state) method:
The method grows the game tree. More specifically, it accepts as input the current state of the game and returns a list of all possible moves that can be made.
- Method minimax(int[] state, boolean turn, int depth):
The method implements the minimax algorithm suitably modified for the specific game. The algorithm works by recursively generating all possible moves from the current game state and evaluating each possible state to determine its score. More specifically
takes a current state of the game, a binary variable representing who's turn it is, and a depth parameter that specifies how deep the algorithm should search in the game tree. The method then generates a list of all possible moves that can be made from the current state using the generateGameTree() method.
For each possible move, the method creates the next state of the game and evaluates the state using the evaluate method. evaluateWinner() returns a score indicating how good the situation is for the player whose turn it is. If the game ends, the score is either -1000 or 1000, depending on which player won. If the game is not over, the score is 0.
If the depth parameter is greater than zero and there are more possible moves, the minimax() method is called recursively on each possible next state with the depth parameter decremented by one and the turn parameter undone. This alternates the turn between the two players at each level of the game tree, allowing the algorithm to explore all possible moves and find the best move.
The algorithm picks the move with the highest (or lowest) score, depending on whose turn it is, and returns that move as the best move from the current state. The algorithm works on the assumption that the opponent will make the best possible move for their side, and therefore chooses the move that minimizes the maximum possible loss for the current player.


