package blackjack.model;

/**
 *
 * @author Chayenne Jacques
 */
public class Card {

    private Suit suit;
    private Rank rank;
    private boolean visible;
    private String cardimage;
    private String backimage;

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
        /* 
        het pad van de afbeelding wordt gegenereerd op basis van de namen van de suit en rank.
         */
        this.cardimage = "images/cards/" + this.rank.name().toLowerCase() + "_of_" + this.suit.name().toLowerCase() + ".png";

        this.backimage = "images/cards/backimage.png";

        visible = true;
    }

    /**
     * vraagt de naam van kaart op
     *
     * @return
     */
    public String getRankname() {
        return this.rank.name();
    }

    /**
     * vraagt de waarde van de kaart op
     *
     * @return
     */
    public int getValue() {
        return this.rank.getValue();
    }

    /**
     * Een ace kan waarde 11 en waarde 1 hebben. Om deze waarde te wijzigen heb
     * ik een nieuwe Rank met de naam SmallAce en de waarde 1.
     */
    public void setRankToSmallAce() {
        this.rank = Rank.SmallAce;
    }

    public String getSuitname() {
        return this.suit.name();
    }

    /**
     * Wijzigt welke kant van de kaart zichtbaar moet zijn. In ons programma
     * moet een kaart enkel van onzichtbaar naar zichtbaar kunnen gaan, dus kon
     * deze methode ook gewoon makeVisible zijn, zonder meegegeven parameter.
     *
     * @param visibility
     */
    public void setVisibility(boolean visibility) {
        visible = visibility;
    }

    public boolean isVisible() {
        return visible;
    }

    /**
     * Retourneert de afbeelding die zichtbaar is.
     *
     * @return
     */
    public String getVisibleImage() {
        return blGetVisibleImage();
    }

    private String blGetVisibleImage() {
        if (this.visible) {
            return getCardimage();
        } else {
            return getBackimage();
        }
    }

    public String getCardimage() {
        return cardimage;
    }

    public String getBackimage() {
        return backimage;
    }

}
