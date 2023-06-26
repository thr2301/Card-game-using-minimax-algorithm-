## Card-game-using-minimax-algorithm-for a University project

# The problem 
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

#The explanation of the solution

