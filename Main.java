import java.util.ArrayList;
import java.util.Scanner;
public class Main {
    public static void main(String[]args){

        Scanner input = new Scanner(System.in);
        boolean doYouWantToPlay = true;

        //Plays one game of black jack. Asks to play again.
        while(doYouWantToPlay) {
            Deck myDeck = new Deck();
            //makes shuffled deck.
            myDeck.makeDeck();
            myDeck.startGame();
            if(myDeck.getPlayerTotal() == 21){
                myDeck.checkBlackJack();
            }
            else {
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