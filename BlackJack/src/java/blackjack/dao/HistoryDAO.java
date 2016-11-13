package blackjack.dao;

import blackjack.db.DatabaseSingleton;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Chayenne Jacques
 */
public class HistoryDAO {
/**
 * Vraagt alle gegevens op uit history, game, gamestate, icon en user om er historiekobjecten van te kunnen maken.
 * @return 
 */
    public static ResultSet getAllHistory() {
        String query = "SELECT * from history, game, gamestate, icon, user where game.id = history.gameid and icon.id = user.iconid and user.id = history.userid and gamestate.id = history.gamestateid order by game.Id desc, date desc";
        Connection con = DatabaseSingleton.getDatabaseSingleton().getConnection(true);
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
        } catch (SQLException e) {
            System.out.println("error getallhistory");
        }
        return rs;
    }
/**
 * Voegt een record toe aan de tabel history met de meegegeven parameters.
 * @param userId
 * @param gameId
 * @param bet
 * @param balance
 * @param gamestateId 
 */
    public static void addHistory(int userId, int gameId, int bet, int balance, int gamestateId) {
        String query = "INSERT into history (UserId, GameId, Bet, Balance, GameStateId) VALUES ('" + userId + "','" + gameId + "','" + bet + "','" + balance + "','" + gamestateId + "')";
        Connection con = DatabaseSingleton.getDatabaseSingleton().getConnection(true);

        Statement stmt = null;
        try {
            stmt = con.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
/**
 * Vraagt alle historiek op op basis van de meegegeven datum.
 * @param date
 * @return 
 */
    public static ResultSet getHistoryByDate(String date) {
        String query = "SELECT * from history, game, gamestate, icon, user where game.id = history.gameid and icon.id = user.iconid  and user.id = history.userid and gamestate.id = history.gamestateid and game.date '" + date + "'";
        Connection con = DatabaseSingleton.getDatabaseSingleton().getConnection(true);
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
/**
 * Vraagt alle historiek op van een bepaalde gebruiker op basis van een meegegeven nickname.
 * @param nickname
 * @return 
 */
    public static ResultSet getHistoryByUser(String nickname) {
        String query = "SELECT * from history, game, icon, gamestate, user where game.id = history.gameid and icon.id = user.iconid and user.id = history.userid and gamestate.id = history.gamestateid and user.nickname '" + nickname + "'";

        Connection con = DatabaseSingleton.getDatabaseSingleton().getConnection(true);
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
        } catch (SQLException e) {
            System.out.println("error getallhistory");
        }
        return rs;
    }

}
