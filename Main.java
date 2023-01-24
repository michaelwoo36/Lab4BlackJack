// Michael Woo
// IntelliJ
// CS 145 Criss
// Lab 4: Card Game
// Plays a game of blackjack between the computer and the player.

import java.util.Scanner;
public class Main {
    public static void main(String[]args) throws InterruptedException {

        Scanner input = new Scanner(System.in);
        boolean doYouWantToPlay = true;

        // Plays one game of black jack. Asks to play again.
        while(doYouWantToPlay) {
            Deck myDeck = new Deck();
            // makes shuffled deck.
            myDeck.makeDeck();
            myDeck.startGame();
            // checks for blackjack on first hand.
            if(myDeck.getPlayerTotal() == 21){
                myDeck.checkBlackJack();
            }
            else {
                // No blackjack means continuing the game to the stand or hit phase
                myDeck.standOrHit();
                System.out.println();
            }
            System.out.println("Play again? y/n");
            System.out.println();
            if(input.next().equals("n")){
                doYouWantToPlay = false;
            }
        }


    }
}