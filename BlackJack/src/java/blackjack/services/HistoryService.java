package blackjack.services;

import blackjack.dao.GameDAO;
import blackjack.dao.GamestateDAO;
import blackjack.dao.HistoryDAO;
import blackjack.dao.UserDAO;
import blackjack.model.Game;
import blackjack.model.History;
import blackjack.model.User;
import blackjack.util.Conversion;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Chayenne Jacques
 */
public class HistoryService {

    /**
     * Schrijft historiek weg naar de historiektabel.
     *
     * @param game
     */
    public static void addHistory(Game game) {
        int userid, bet, balance, gamestateId, gameId;
        List<User> users = game.getPlayers();
        Iterator<User> it = users.iterator();

        // Schrijft de game(enkel de datum wordt gevraagd) weg naar de database.
        GameService.addGame(game.getDate());

        // Vraagt de id op van de laatst toegevoegde game.
        gameId = Conversion.convertResultSetToId(GameDAO.selectLargestId("Game"));

        // Overloopt elke Speler.
        while (it.hasNext()) {
            User user = it.next();

            //Vraagt de id op van de speler (wordt gezocht op nickname).
            userid = Conversion.convertResultSetToId(UserDAO.getUserIdByNickname(user.getNickname()));
            bet = user.getBet();
            balance = user.getBalance();
            
            //Vraagt de id op van de gamestate die de gebruiker heeft.
            gamestateId = Conversion.convertResultSetToId(GamestateDAO.getIdByGameState(user.getState().name()));

            // Historiek wordt weggeschreven naar de database.
            HistoryDAO.addHistory(userid, gameId, bet, balance, gamestateId);
        }
    }

    public static List<History> getAllHistory() {
        return Conversion.convertResultsetToHistoryList(HistoryDAO.getAllHistory());
    }

    public static List<History> getHistoryByDate(String date) {
        return Conversion.convertResultsetToHistoryList(HistoryDAO.getHistoryByDate(date));
    }

    public static List<History> getHistoryByUser(String nickname) {
        return Conversion.convertResultsetToHistoryList(HistoryDAO.getHistoryByUser(nickname));
    }

}
