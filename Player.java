/* Player Class
 * 
 * @author: Aditi Patil
 * @date: 10/28/18
 * 
*/

import java.util.ArrayList;
import java.util.Collections;

public class Player {
	
		
	private ArrayList<Card> hand; // the player's cards
	private double bankroll;
    private double bet;

	// you may choose to use more instance variables
		
	public Player(){
	    bankroll = 100;
        bet = 0;
        hand = new ArrayList<Card>();
	}
    
    public void sortHand() {
        Collections.sort(hand);
    }
    
    public ArrayList<Card> getHand() {
        return hand; 
    }
    public void setBankroll(double b) {
        bankroll = b;
    }
    
    public void reset() {
        bet = 0;
        hand = new ArrayList<Card>();
    }
    
	public void addCard(Card c){
	    // add the card c to the player's hand
	    hand.add(c);
    }

	public void removeCard(Card c){
	    hand.remove(c);
    }
		
    public void bets(double amt){
        bankroll -= amt;
        bet += amt;
    }

    public void winnings(double odds){
        //	adjust bankroll if player wins
        bankroll += (bet * odds);
    }

    public double getBankroll(){
        return bankroll;
        // return current balance of bankroll
    }
    
    public String toString() {
        String s = "";
        for(int i = 0; i < hand.size(); i++) {
            s += hand.get(i).toString();
            if (i == hand.size() - 1)
                s += ".";
            else
                s += ", ";
        }
        return s;
    }
}



