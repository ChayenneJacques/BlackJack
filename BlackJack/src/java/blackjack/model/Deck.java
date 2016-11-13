package blackjack.model;

import java.util.ArrayList;
import java.util.Collections;


/**
 *
 * @author Chayenne Jacques
 */
public class Deck {

    private ArrayList<Card> cards;

    public Deck() {
        this.cards = new ArrayList();
    }
    /*
    Leegt de arraylist.
    Dit was nodig om het spelsysteem sneller en beter te kunnen testen.
    */
    public void clear()
    {
        this.cards=new ArrayList<>();
    }
/**
 * Elke rank overlopen binnenin het overlopen van elke suit.
 * Een ace heeft standaard waarde 11, en een deck heeft slechts 4 aces (en 52 kaarten),
 * dus wordt SmallAce niet meegeteld als Rank om het deck te vullen.
 */
    public void fillDeck() {
        blFillDeck();
    }
    
    private void blFillDeck()
    {
        Card c;
        for (Suit s : Suit.values()) {
            for (Rank r : Rank.values()) {
                if(r!=Rank.SmallAce)
                {
                c = new Card(s, r);
                cards.add(c);
                }
            }
        }
    }
    
   /**
    * Collectieklasse wordt gebruikt om het deck door elkaar te schudden.
    */
    public void shuffle(){
        Collections.shuffle(cards);
    }
    
    /**
     * De eerste kaart van de stapel trekken.
     * @return 
     */
    public Card drawCard(){
        return cards.get(0);
    }
    
    /**
     * De getrokken kaart verwijderen van de stapel.
     * @param c 
     */
    public void removeCard(){
        cards.remove(0);
    }
    
    

}
