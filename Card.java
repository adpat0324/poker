/* Card Class
 * 
 * @author: Aditi Patil
 * @date: 10/28/18
 * 
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Card implements Comparable<Card>{
	
	private int suit; // use integers 1-4 to encode the suit (clubs [1], diamonds [2], hearts[3], spades[4])
	private int rank; // use integers 1-13 to encode the rank (A, 2-10, J, Q, K)
	
	public Card(int s, int r){
		suit = s;
        rank = r;
	}
	
    public Card(char s, int r) {
        if(s == 'c')
            suit = 1;
        else if(s == 'd')
            suit = 2;
        else if(s == 'h')
            suit = 3;
        else if(s == 's')
            suit = 4;
        rank = r;
    }
    
    public Card(String str) {
        this(str.charAt(0), Integer.parseInt(str.substring(1)));
    }
    
	public int compareTo(Card c){
		if (this.rank == c.rank) {
            return this.suit - c.suit;
        } 
        return this.rank - c.rank;
	}
    
    public boolean equals(Object o) { //ArrayList.remove() is not removing an object
        if (!(o instanceof Card))
            return false;
        return compareTo((Card) o) == 0;
    }
    
	public int getRank() {
        return rank;
    }
    
    public int getSuit() {
        return suit;
    }
    
	public String toString() {
		String card = " ";
        
        if (rank > 1 && rank < 11) {
           card = Integer.toString(rank);

        }
        else if (rank == 1) {
            card = "A";
        }
        else if(rank == 11) {
            card = "J";
        }
        else if(rank == 12) {
            card = "Q";
        }
        else if(rank == 13) {
            card = "K";
        }
        if (suit == 1) 
            card = card + " of clubs";
        if (suit == 2) 
            card = card + " of diamonds";
        if (suit == 3)
            card = card + " of hearts";
        if (suit == 4) 
            card = card + " of spades";
        
        return card;
	}

}
