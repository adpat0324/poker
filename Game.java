/* Game Class
 * 
 * @author: Aditi Patil
 * @date: 10/28/18
 * 
*/
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Game {
	
	private Player p;
	private Deck cards;
    private final String RF = "Royal Flush!"; // Payout: 250
    private final String SF = "Straight Flush!"; // Payout: 50
    private final String FOK = "Four of a Kind!"; // Payout: 25
    private final String FH = "Full House!"; // Payout: 6
    private final String F = "Flush!"; // Payout: 5
    private final String S = "Straight!"; // Payout: 4
    private final String TOK = "Three of a Kind!"; // Payout: 3
    private final String TP = "Two Pairs!"; // Payout: 2
    private final String OP = "One Pair!"; // Payout: 1
    private final String NP = "No pair."; // Payout: 0
	private final Scanner sc = new Scanner(System.in);
	
	public Game(String[] testHand){
		
        resetDeck();
		p = new Player();
        
		for(int i = 0; i < testHand.length; i++) {
            Card c = new Card(testHand[i]);
            p.addCard(c);
        }
	}
	
	public Game(){
		// This no-argument constructor is to actually play a normal game
        resetDeck();
        
		p = new Player();
        dealCards(5);
	}
    
    private void dealCards(int c) {
        for (int i = 0; i < c; i++) {
            p.addCard(cards.deal());
        }
    }
	
    private void resetDeck() {
        cards = new Deck();
        cards.shuffle();
    }
    
    private void getBet() {
        System.out.println("How much would you like to bet?");
        int bet = sc.nextInt();
        p.bets(bet);
    }
    
    private void swapCards() { 
        System.out.println("How many cards would you like to swap?");
        int swap = sc.nextInt();
        for(int i = 0; i < swap; i++) {
            System.out.println("Which card would you like to swap? (e.g. s1 = ace of spades)");
            String card = sc.next();
            p.removeCard(new Card(card));
        }
        dealCards(swap);
        if(swap > 0)
            System.out.println("Your new hand is " + p.getHand());
    }
    
    private boolean replayGame() {
        System.out.println("Do you want to play again? (y/n)");
        String answer = sc.next();
        if(answer.charAt(0) != 'y') {
            return false;
        }
        p.reset();
        resetDeck();
        dealCards(5);
        return true;
    }
    
	public void play(){
        System.out.println("Hello! Welcome to video poker.");
        boolean playAgain = true;
        while(playAgain) {
            System.out.println("Your current bankroll is " + p.getBankroll());
            getBet();
            System.out.println("Your cards currently are " + p.getHand());
            swapCards();
            doWinnings();
            playAgain = replayGame();
        }
        System.out.println("Thanks for playing!");
    }
    
    private void doWinnings() {
        String ch = checkHand();
        System.out.println("You got a " + ch);
        switch(ch) {
            case RF:
                p.winnings(250);
                break;
            case SF: 
                p.winnings(50);
                break;
            case FOK: 
                p.winnings(25);
                break;
            case FH: 
                p.winnings(6);
                break;
            case F: 
                p.winnings(5);
                break;
            case S:
                p.winnings(4);
                break;
            case TOK: 
                p.winnings(3);
                break;
            case TP: 
                p.winnings(2);
                break;
            case OP: 
                p.winnings(1);
                break;
            default: 
                p.winnings(0);
                break;
        }
    }
    
    public String checkHand() {
        return checkHand(p.getHand());
    }
	
	public String checkHand(ArrayList<Card> hand){
		// this method should take an ArrayList of cards
		// as input and then determine what winning combo it evaluates 
		// to and returns that as a String
		p.sortHand();
		if(royalFlush(hand)) {
            return "Royal Flush!";
        }
        else if(straightFlush(hand)) {
            return "Straight Flush!";
        }
        else if(fourOfAKind(hand)) {
            return "Four of a Kind!";
        }
        else if(fullHouse(hand)) {
            return "Full House!";
        }
		else if(flush(hand)) {
            return "Flush!";
        }
        else if(straight(hand)) {
            return "Straight!";
        }
        else if(threeOfAKind(hand)) {
            return "Three of a Kind!";
        }
        else if(twoPairs(hand)) {
            return "Two Pairs!";
        }
        else if(pair(hand)) {
            return "One Pair!";
        }
        else {
            return "No Pair.";
        }
	}
	
	private boolean royalFlush(ArrayList<Card> hand) { 
        if(hand.get(0).getRank() == 1 &&
           hand.get(1).getRank() == 10 &&
           hand.get(2).getRank() == 11 &&
           hand.get(3).getRank() == 12 && hand.get(4).getRank() == 13)
            if(hand.get(0).getSuit() == hand.get(1).getSuit() 
               && hand.get(1).getSuit() == hand.get(2).getSuit() 
               && hand.get(2).getSuit() == hand.get(3).getSuit() 
               && hand.get(3).getSuit() == hand.get(4).getSuit())
                return true;
            else
                return false;
        else
            return false;
    }
    
    private boolean straightFlush(ArrayList<Card> hand) { 
        return(straight(hand) && flush(hand));
    }
    
    private boolean fourOfAKind(ArrayList<Card> hand) { 
        boolean check1 = hand.get(0).getRank() == hand.get(1).getRank() 
            && hand.get(1).getRank() == hand.get(2).getRank() 
            && hand.get(2).getRank() == hand.get(3).getRank();
        boolean check2 = hand.get(1).getRank() == hand.get(2).getRank() 
            && hand.get(2).getRank() == hand.get(3).getRank() 
            && hand.get(3).getRank() == hand.get(4).getRank();
        return (check1 || check2);
    }
    
    private boolean fullHouse(ArrayList<Card> hand) {
        boolean check1 = hand.get(0).getRank() == hand.get(1).getRank() 
            && hand.get(1).getRank() == hand.get(2).getRank() 
            && hand.get(3).getRank() == hand.get(4).getRank();
        boolean check2 = hand.get(0).getRank() == hand.get(1).getRank() 
            && hand.get(2).getRank() == hand.get(3).getRank() 
            && hand.get(3).getRank() == hand.get(4).getRank();
        return (check1 || check2);
    }
    
    private boolean flush(ArrayList<Card> hand) {
       return (hand.get(0).getSuit() == hand.get(1).getSuit() 
              && hand.get(1).getSuit() == hand.get(2).getSuit() 
              && hand.get(2).getSuit() == hand.get(3).getSuit() 
              && hand.get(3).getSuit() == hand.get(4).getSuit());
    }
    
    private boolean straight(ArrayList<Card> hand) {
        for (int i = 0; i < hand.size() - 1; i++) {
            if (hand.get(i).getRank() != hand.get(i+1).getRank() - 1)
                return false;
        }
        return true;
        
    }
    
    private boolean threeOfAKind(ArrayList<Card> hand) {
        // check for x x x a b
        boolean check1 = hand.get(0).getRank() == hand.get(1).getRank() 
            && hand.get(1).getRank() == hand.get(2).getRank() 
            && hand.get(0).getRank() != hand.get(3).getRank()
            && hand.get(0).getRank() != hand.get(4).getRank()
            && hand.get(3).getRank() != hand.get(4).getRank();
        // check for a x x x b
        boolean check2 = hand.get(0).getRank() != hand.get(1).getRank() 
            && hand.get(0).getRank() == hand.get(4).getSuit()
            && hand.get(1).getRank() == hand.get(2).getSuit() 
            && hand.get(2).getRank() == hand.get(3).getSuit()
            && hand.get(4).getRank() != hand.get(1).getSuit();
        // check for a b x x x
        boolean check3 = hand.get(2).getRank() == hand.get(3).getRank()
            && hand.get(3).getRank() == hand.get(4).getRank() 
            && hand.get(0).getRank() != hand.get(2).getRank()
            && hand.get(1).getRank() != hand.get(2).getRank()
            && hand.get(0).getRank() != hand.get(1).getRank();
        return (check1 || check2 || check3);
    }
    
    private boolean twoPairs(ArrayList<Card> hand) {
        if (fourOfAKind(hand) || fullHouse(hand) || threeOfAKind(hand)) {
            return false; 
        }
        // check for a a b b x
        boolean check1 = hand.get(0).getRank() == hand.get(1).getRank()
            && hand.get(2).getRank() == hand.get(3).getRank();
        // check for a a x b b
        boolean check2 = hand.get(0).getRank() == hand.get(1).getRank()
            && hand.get(3).getRank() == hand.get(4).getRank();
        // check for x a a b b 
        boolean check3 = hand.get(1).getRank() == hand.get(2).getRank() 
            && hand.get(3).getRank() == hand.get(4).getRank();
        return (check1 || check2 || check3);

         
    }
    
    private boolean pair(ArrayList<Card> hand) {
        if (fourOfAKind(hand) || fullHouse(hand) || threeOfAKind(hand) || twoPairs(hand)) {
            return false; 
        }
        // check for a a x y z
        boolean check1 = hand.get(0).getRank() == hand.get(1).getRank();
        // check for x a a y z
        boolean check2 = hand.get(1).getRank() == hand.get(2).getRank();
        // check for x y a a z
        boolean check3 = hand.get(2).getRank() == hand.get(3).getRank();
        // check for x y z a a 
        boolean check4 = hand.get(3).getRank() == hand.get(4).getRank();
        return (check1 || check2 || check3 || check4);

    }
    
    private boolean noPair(ArrayList<Card> hand) {
       return (!(pair(hand) || twoPairs(hand) 
                 || threeOfAKind(hand) || straight(hand)
                 || flush(hand) || fullHouse(hand) 
                 || fourOfAKind(hand) || straightFlush(hand)
                 || royalFlush(hand)));
    }
}
