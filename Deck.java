import java.util.ArrayList;
import java.util.*;
import java.util.Scanner;

public class Deck {
    public  ArrayList<String[]> deck;
    private String suit;
    public Stack<String[]> deal = new Stack<>();
    public ArrayList<String[]> playerHand;
    public ArrayList<String[]> dealerHand;
    private int playerTotal;
    private int dealerTotal;
    private Scanner input = new Scanner(System.in);

    //Method to run dialog between dealer and player. Right now just getting it to count hand totals properly. Next step is asking to hit or stand.
    public void startGame() {
        dealerTotal = 0;
        playerTotal = 0;
        playerHand = new ArrayList<>();
        dealerHand = new ArrayList<>();
        System.out.println("Dealers hand:");

        dealerHand.add(deal.pop());
        dealerHand.add(deal.pop());

        for (String[] object : dealerHand) {
            dealerTotal += dealerAddPoints(object);
        }
        System.out.println("Hole card");
        System.out.println(toString(dealerHand.get(1)));
        System.out.println();

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
    }

    public void checkBlackJack() {
        System.out.println();
        System.out.println("Black Jack!");
        System.out.println();
        System.out.println("Dealers hand:");
        System.out.println(toString(dealerHand.get(0)));
        System.out.println(toString(dealerHand.get(1)));
        System.out.println("Dealer Total: " + dealerTotal);
        if (dealerTotal != playerTotal) {
            System.out.println();
            System.out.println("congrats you win!");
        }
        else {
            System.out.println();
            System.out.println("Push!");
        }
    }

        // Stand and hit phase.
    public void standOrHit(){
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
                System.out.println("Dealers Hand");
                for (String[] object : dealerHand) {
                    System.out.println(toString(object));
                }
                System.out.println("Dealer Total: " + dealerTotal);
                System.out.println();
                System.out.println("Your Hand");
                for (String[] object : playerHand) {
                    System.out.println(toString(object));
                }
                System.out.println("Player total: " + playerTotal);
                if (dealerTotal <= 21) {
                    if (playerTotal == dealerTotal) {
                        System.out.println();
                        System.out.println("push");
                    } else if (dealerTotal > playerTotal) {
                        System.out.println();
                        System.out.println("Dealer wins");
                    }
                    else {
                        System.out.println();
                        System.out.println("You win.");
                    }
                    return;
                } else if (dealerTotal > 21) {
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
                if (playerTotal == 21) {
                    System.out.println();
                    System.out.println("Dealers Hand:");
                    for (String[] object : dealerHand) {
                        System.out.println(toString(object));
                    }
                    System.out.println("Dealer total:" + dealerTotal);
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

    // adds point value of card to hand total.
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
    public int dealerAddPoints(String[]Cards){
        switch(Cards[1]){
            case "Ace":
                if(dealerHand.size()<3){
                    return 11;
                }
                else {
                    if ((dealerTotal + 11) >= 17) {
                        return 11;
                    } else {
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

    //Prints card out in String form
    public String toString(String[]cards){
        return cards[1] + " of " + cards[0];
    }
}

