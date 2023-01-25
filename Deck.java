import java.util.ArrayList;
import java.util.*;
import java.util.Scanner;

public class Deck {

    public  ArrayList<String[]> deck;

    private String suit;

    private Stack<String[]> deal;

    private ArrayList<String[]> playerHand;

    public ArrayList<String[]> dealerHand;

    private int playerTotal;

    private int dealerTotal;

    private Scanner input;

    public Deck(){

        deal = new Stack<>();
        input = new Scanner(System.in);
        playerHand = new ArrayList<>();
        dealerHand = new ArrayList<>();
    }

    // deals first hand to dealer and player.
    public void startGame() throws InterruptedException  {

        System.out.println("Dealers hand:");
        Thread.sleep(750);
        dealerHand.add(deal.pop());
        dealerHand.add(deal.pop());

        for (String[] object : dealerHand) {

            dealerTotal += dealerAddPoints(object);
        }

        System.out.println("Hole card");
        System.out.println(toString(dealerHand.get(1)));
        System.out.println();
        Thread.sleep(750);

        System.out.println("Your Hand:");
        playerHand.add(deal.pop());
        playerHand.add(deal.pop());

        for (String[] object : playerHand) {

            System.out.println(toString(object));
        }

        for (String[] object : playerHand) {

            playerTotal += addPoints(object);
        }

        System.out.println("Player total: " + playerTotal);
        Thread.sleep(750);
    }

    // checks if player has blackjack and tells who will win, lose, or tie
    public void checkBlackJack() throws InterruptedException {

        System.out.println();
        System.out.println("Black Jack!");
        Thread.sleep(750);
        System.out.println();
        System.out.println("Dealers hand:");
        System.out.println(toString(dealerHand.get(0)));
        System.out.println(toString(dealerHand.get(1)));
        System.out.println("Dealer Total: " + dealerTotal);
        Thread.sleep(750);

        if (dealerTotal != playerTotal) {

            System.out.println();
            System.out.println("congrats you win!");
        }

        else {

            System.out.println();
            System.out.println("Push!");
        }
    }

    // until 21 is reached the player will be asked to stand or hit
    public void standOrHit() throws InterruptedException {

        while(playerTotal < 21) {

            System.out.println();
            System.out.println("Stand or Hit (s/h)");
            // Stand procedure.

            if (input.next().equals("s")) {

                while (dealerTotal < 17) {

                    dealerHand.add(deal.pop());
                    dealerTotal += dealerAddPoints(dealerHand.get(dealerHand.size() - 1));
                }

                System.out.println();
                Thread.sleep(750);
                System.out.println("Dealers Hand");

                for (String[] object : dealerHand) {

                    System.out.println(toString(object));
                }

                System.out.println("Dealer Total: " + dealerTotal);
                System.out.println();
                Thread.sleep(750);
                System.out.println("Your Hand");

                for (String[] object : playerHand) {

                    System.out.println(toString(object));
                }

                System.out.println("Player total: " + playerTotal);
                Thread.sleep(750);

                if (dealerTotal <= 21) {

                    if (playerTotal == dealerTotal) {

                        System.out.println();
                        System.out.println("push");
                    }

                    else if (dealerTotal > playerTotal) {

                        System.out.println();
                        System.out.println("Dealer wins");
                    }

                    else {

                        System.out.println();
                        System.out.println("You win.");
                    }

                    return;
                }

                else if (dealerTotal > 21) {

                    System.out.println();
                    System.out.println("dealer busts");
                    System.out.println();
                    System.out.println("you win!");

                    return;
                }

                else {

                    if (dealerTotal > playerTotal) {

                        System.out.println();
                        System.out.println("dealer Wins!");
                    }

                    else {

                        System.out.println();
                        System.out.println("You win");
                    }
                }
            }

            // Hit
            else {

                playerHand.add(deal.pop());
                System.out.println();
                System.out.println("Your Hand");

                for (String[] object : playerHand) {

                    System.out.println(toString(object));
                }

                playerTotal += addPoints(playerHand.get(playerHand.size() - 1));
                System.out.println("Player total: " + playerTotal);
                Thread.sleep(750);

                if (playerTotal == 21) {
                    while (dealerTotal < 17) {

                        dealerHand.add(deal.pop());
                        dealerTotal += dealerAddPoints(dealerHand.get(dealerHand.size() - 1));
                    }
                    System.out.println();
                    System.out.println("Dealers Hand:");

                    for (String[] object : dealerHand) {

                        System.out.println(toString(object));
                    }

                    System.out.println("Dealer total:" + dealerTotal);
                    Thread.sleep(750);

                    if (dealerTotal != playerTotal) {

                        System.out.println();
                        System.out.println("you win!");
                    }

                    else {

                        System.out.println();
                        System.out.println("Dealers Hand:");

                        for (String[] object : dealerHand) {

                            System.out.println(toString(object));
                        }

                        System.out.println("Dealer total:" + dealerTotal);
                        System.out.println();
                        Thread.sleep(750);
                        System.out.println("Push!");
                    }

                }

                else if (playerTotal > 21) {

                    System.out.println();
                    System.out.println("You bust");

                }
            }
        }
    }

    public int getPlayerTotal(){

        return playerTotal;
    }

    // adds point value of card to hand total for player.
    public int addPoints(String[]Cards){

        switch(Cards[1]){

            case "Ace":

                System.out.println("choose 1 or 11");

                if(input.next().equals("1")) {

                    return 1;
                }

                else {

                    return 11;
                }
            case "2":
                return 2;
            case "3":
                return 3;
            case "4":
                return 4;
            case "5":
                return 5;
            case "6":
                return 6;
            case "7":
                return 7;
            case "8":
                return 8;
            case "9":
                return 9;
        }

        return 10;
    }

    // adds point value of card to dealer.
    public int dealerAddPoints(String[]Cards){

        switch(Cards[1]){

            case "Ace":

                if(dealerHand.size()<3){

                    return 11;
                }

                else {

                    if ((dealerTotal + 11) >= 17) {

                        return 11;
                    }
                    else {

                        return 1;
                    }
                }

            case "2":
                return 2;
            case "3":
                return 3;
            case "4":
                return 4;
            case "5":
                return 5;
            case "6":
                return 6;
            case "7":
                return 7;
            case "8":
                return 8;
            case "9":
                return 9;
        }

        return 10;
    }

    //Returns Stack. First makes a shuffled ArrayList, then adds each element into stack.
    public Stack makeDeck (){

        deck = new ArrayList<String[]>();
        //adding faces for each suit to arrayList

        for(int i = 1; i<=4; i++) {

            if(i==1) {
                suit = "diamonds";
            }
            else if(i == 2){
                suit = "hearts";
            }
            else if(i == 3){
                suit = "spades";
            }
            else if(i == 4){
                suit = "clubs";
            }

            deck.add(new Card(suit, "Ace").getCard());

            for(int k = 2; k<=10; k++){

                String face = k + "";
                deck.add(new Card(suit, face).getCard());
            }

            deck.add(new Card(suit, "Jack").getCard());
            deck.add(new Card(suit, "Queen").getCard());
            deck.add(new Card(suit, "King").getCard());
        }

        //shuffles
        Collections.shuffle(deck);
        //puts elements in stack

        for(String[]cards : deck){
            deal.push(cards);
        }

        return deal;
    }

    //Prints card out as String
    public String toString(String[]cards){

        return cards[1] + " of " + cards[0];
    }
}

