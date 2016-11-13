package blackjack.model;

import blackjack.services.HistoryService;
import blackjack.services.UserService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

/**
 *
 * @author Chayenne Jacques
 */
public class Game {

    public static Game game;
    private Dealer dealer;
    private ArrayList<User> players;
    private Deck deck;
    private Calendar date;
/**
 * Constructor is private. 
 * Enkel binnenin deze klasse kan een instantie van de klasse aangemaakt worden.
 */
    private Game() {

        this.dealer = new Dealer();
        this.deck = new Deck();
        this.date = Calendar.getInstance();
        /*
        De datum van vandaag wordt aangemaakt.
        */
        this.date.add(Calendar.DATE, 0);
        System.out.println("date of today: " + date.getTime());
    }

    public void setPlayerList(ArrayList<User> players) {
        this.players = players;
    }
 /**
  * Singletonpatroon wordt hier toegepast.
  * De statische methode kan van buiten de klasse worden aangeroepen.
  * Indien de variabele game leeg is, wordt er een nieuw gameobject in weggeschreven. 
  * Vervolgens wordt het gameobject geretourneerd.
  * @return 
  */
    public static Game getGame() {
        if (game == null) {
            game = new Game();
        }
        return game;
    }

    /**
     * Kaard wordt toegevoegd aan de arraylist van kaarten in de hand van de gebruiker.
     * De kaart wordt nadien verwijderd uit de arraylist van kaarten in deck.
     *
     * @param user
     */
    public void playerHit(User user) {
       blPlayerHit(user);
    }
    
    private void blPlayerHit(User user)
    {
         Card c = deck.drawCard();
        deck.removeCard();
        user.getHand().addCard(c);
        user.getHand().evaluate();
    }

    /**
     * Kaard wordt toegevoegd aan de arraylist van kaarten in de hand van de dealer.
     * De kaart wordt nadien verwijderd uit de arraylist van kaarten in deck.
     */
    public void dealerHit() {
       blDealerHit();
    }
    
    private void blDealerHit()
    {
         Card c = deck.drawCard();
        deck.removeCard();
        dealer.getHand().addCard(c);
        dealer.getHand().evaluate();
    }

    /**
     * Kaarten verdelenL: elke speler/dealer krijgt 2 kaarten, 
     * de 2e kaart van de dealer is onzichtbaar.
     */
    public void cardDistribution() {
        blCardDistribution();
    }
    
    private void blCardDistribution()
    {
        for (int i = 0; i < 2; i++) {
            Iterator<User> it = players.iterator();
            while (it.hasNext()) {
                User user = it.next();
                playerHit(user);
            }
            dealerHit();
            if (i == 1) {
                dealer.getHand().getCards().get(1).setVisibility(false);
            }
        }
    }

    public void playerStand(User user) {
        user.getHand().setState(Handstate.Stand);
    }

    public void dealerStand() {
        dealer.getHand().setState(Handstate.Stand);
    }

    /**
     * Vergelijkt de hands van de spelers tov de hand van de dealer.
     * Wijzigt de gamestatus van de spelers navenant.
     */
    public void evaluateGame() {
        blEvaluateGame();
    }
    
    private void blEvaluateGame()
    {
        Iterator<User> it = players.iterator();
        while (it.hasNext()) {
            User user = it.next();
            if (user.getHand().getState() == Handstate.Blackjack) {
                user.setGamestate(Gamestate.Win);
            } else if (dealer.getHand().getState() == Handstate.Blackjack) {
                user.setGamestate(Gamestate.Loss);
            } else if (user.getHand().getState() == Handstate.Busted) {
                user.setGamestate(Gamestate.Loss);
            } else if (dealer.getHand().getState() == Handstate.Busted) {
                user.setGamestate(Gamestate.Win);
            } else if (user.getHand().getValue() > dealer.getHand().getValue()) {
                user.setGamestate(Gamestate.Win);
            } else if (user.getHand().getValue() == dealer.getHand().getValue()) {
                user.setGamestate(Gamestate.Push);
            } else if (user.getHand().getValue() < dealer.getHand().getValue()) {
                user.setGamestate(Gamestate.Loss);
            }

        }
    }

   /**
    * Credits aftrekken/toevoegen van de balance van de spelers obv hun gamestatus.
    * De balans van de gebruikers wijzigen in de tabel user.
    * Nadien wordt de history-tabel geupdated op basis van de gegevens van deze game.
    */
    public void distributePayment() {
        blDistributePayment();
    }
    
    private void blDistributePayment()
    {
        Iterator<User> it = players.iterator();
        while (it.hasNext()) {
            User user = it.next();
            if (user.getState().equals(Gamestate.Loss)) {
                //niets doen..
            } else if (user.getState().equals(Gamestate.Push)) {
                user.setBalance((int) (user.getBalance() + user.getBet()));
            } else if (user.getState().equals(Gamestate.Win)) {
                if (user.getHand().getState().equals(Handstate.Stand)) {
                    user.setBalance((int) (user.getBalance() + (user.getBet() * 2)));
                } else if (user.getHand().getState().equals(Handstate.Blackjack)) {
                    user.setBalance((int) (user.getBalance() + (user.getBet() * 2.5)));
                }
            }
            UserService.editUser(user);
        }
        HistoryService.addHistory(this);
    }
    /**
     * Om van het type Calendar naar het type String te gaan is een omzetting nodig.
     * @return 
     */
    public String getDate() {
        String formattedDate = new SimpleDateFormat("dd-MM-yyyy").format(date.getTime());
        System.out.println("Formatted date " + formattedDate);
        return formattedDate;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public ArrayList<User> getPlayers() {
        return players;
    }

    public Deck getDeck() {
        return deck;
    }

    /**
     * Het spelen van de dealer is (in tegenstelling tot de spelers) volledig afhankelijk van vooropgestelde regels.
     * Deze methode simuleert het spelen van de dealer.
     * Eerst wordt de correcte minimumHit vastgelegd.
     * Nadien krijgt de dealer kaarten tot wanneer hij meer heeft dan de minimumHit.
     */
    public void dealerPlay() {
        blDealerPlay();
    }
    
    private void blDealerPlay()
    {
        Iterator<User> users = players.iterator();
        /* 
        Boolean die bepaalt of er nog spelers in het spel zitten die niet geen Busted-of Blackjack-status hebben.
        */
        Boolean needToPlay = false;
        while (users.hasNext()) {
            if (users.next().getHand().getState() != Handstate.Busted) {
                needToPlay = true;
                break;
            }
        }
        if (needToPlay) {
            if (dealer.getHand().getState() != Handstate.Blackjack) {
                int minimumHit = 21;
                Iterator<User> it = this.players.iterator();
                while (it.hasNext()) {
                    User player = it.next();
                    /*
                    Wanneer de speler minder dan de minimumHit heeft, 
                    wordt de minimumHit gewijzigt in de waarde van de hand van deze speler.
                    */
                    if (player.getHand().getValue() < minimumHit) {
                        minimumHit = player.getHand().getValue();
                    }
                }
                /*
                Wanneer blijkt dat de minimumHit nog steeds 21 is, of kleiner dan 16, wordt de minimumHit op 16 geplaatst.
                Zo moet een dealer (volgens de regels) minstens 17 behalen.
                */
                if (minimumHit == 21) {
                    minimumHit = 16;
                } else if (minimumHit < 16) {
                    minimumHit = 16;
                }
                /*
                Dealer blijft kaarten krijgen zolang de hand kleiner of gelijk is aan de minimumHit
                */
                while (this.dealer.getHand().getValue() < minimumHit + 1) {
                    dealerHit();
                    dealer.getHand().evaluate();
                }
                /*
                wanneer de dealer meer heeft dan de minimumhit, is de handstatus nog niet gewijzigd.
                Deze moet dan op Stand gezet worden.
                */
                if (dealer.getHand().getValue() <= 21) {
                    dealer.getHand().setState(Handstate.Stand);
                }
            }
        }
    }
}
