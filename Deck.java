/* Deck Class
 * 
 * @author: Aditi Patil
 * @date: 10/28/18
 * 
*/

import java.util.Collections;
import java.util.Arrays;

public class Deck {
	
	private Card[] cards;
	private int top; // the index of the top of the deck

	// add more instance variables if needed
	
	public Deck(){
        cards = new Card[52];
        int count = 0;
        for(int suit = 1; suit <= 4; suit++) {
            for (int rank = 1; rank <= 13; rank++) {
                cards[count] = new Card(suit, rank);
                count++;
            }
        }
    }
	
	public void shuffle(){
		Collections.shuffle(Arrays.asList(cards));
        top = 0;
	}
	
	public Card deal(){
        return cards[top++];
	}
	
    public String toString() {
        String s = "";
        for (int i = 0; i < cards.length; i++) {
            s += cards[i].toString();
            if (i == cards.length - 1)
                s += ".";
            else
                s += ", ";
        }
        return s;
    }
}
