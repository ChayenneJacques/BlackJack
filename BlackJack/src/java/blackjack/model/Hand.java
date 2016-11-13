
package blackjack.model;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Chayenne Jacques
 */
public class Hand {
    
    private ArrayList<Card> cards;
    private Handstate state;

    public Hand() {
        this.cards = new ArrayList();
    }
    
    /**
     * Card c toevoegen aan de Hand na de kaartverdeling of Hit
     * @param c 
     */
    public void addCard(Card c){
        cards.add(c);
    }
    /**
     * Evalueren of de state van een hand verandert. (na elke hit, ook in kaartverdeling)
     */
    public void evaluate(){
        blEvaluate();
    }
    
    private void blEvaluate()
    {
        int value=this.getValue();
        if(value>21)
        {
            boolean foundReduceableAce=false;
            Card c;
           Iterator<Card> it=this.cards.iterator();
           /*
           Wanneer de som van de kaarten meer dan 21 is, 
           moet er nog gecontroleerd worden of er een Ace(met waarde 11) in de hand zit.
           */
           while(it.hasNext())
           {
               c=it.next();
               if(c.getValue()==11)
               {
                   /*
                   Wanneer er een Ace gevonden is, wordt deze gewijzigt naar een SmallAce(met waarde 1)
                   */
                   c.setRankToSmallAce();
                   foundReduceableAce=true;
                  
                   break;
               }
               
           }
           /*
           Als er geen Ace gevonden is, is de speler uiteraard busted.
           */
           if(!foundReduceableAce)
           {
            this.setState(Handstate.Busted);
           }
        }
        /*
        Controleert of de speler blackjack heeft (21 met 2 kaarten).
        */
        else if(value==21 && this.cards.size()==2)
        {
           this.setState(Handstate.Blackjack);
        }
        /*
        Controleert of de speler 21 heeft met meer dan  2 kaarten.
        */
        else if(value==21 && this.cards.size()>2)
        {
           this.setState(Handstate.Stand);
        }
    }
    /*
    Deze methode leegt de arraylist van kaarten (nodig om sneller en beter te kunnen testen).
    */
    public void clear()
    {
        this.cards=new ArrayList<>();
        this.state=null;
    }
   
    public void setState(Handstate hs){
        this.state = hs;
    }
    
    public Handstate getState(){
        return this.state;
    }
    
    public ArrayList<Card> getCards(){
        return cards;
    }
    
    /*
    Retourneert de som van de waardes van alle kaarten in de hand.
    */
    public int getValue(){
        return blGetValue();
    }
    
    private int blGetValue()
    {
     int value = 0;
        Iterator <Card> it = cards.iterator();
        while (it.hasNext()){
            value+= it.next().getValue();
        }
        return value;   
    }
    
}
