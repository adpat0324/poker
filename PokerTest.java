
public class PokerTest {

    //this class must remain unchanged
    //your code must work with this test class
 
    public static void main(String[] args){
        if (args.length<1){
            Game g = new Game(); //just an automatic game assuming they just wrote java PokerTest
            g.play();
        }
        else{
            Game g = new Game(args); //if the player wrote java PokerTest s1 c2 h3 d4 s5 or something
            g.play();
        }
    }

}
