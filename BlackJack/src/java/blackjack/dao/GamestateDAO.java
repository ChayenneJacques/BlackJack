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
public class GamestateDAO {
/**
 * Vraagt de gamestatus op uit de tabel gamestate op basis van de meegegeven id.
 * @param id
 * @return 
 */
    public static ResultSet getGameStateById(int id) {
        String query = "SELECT name from gamestate WHERE id = " + id;
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
 * Vraagt de id op uit de tabel gamestate op basis van de meegegeven gamestatus.
 * @param gamestate
 * @return 
 */
public static ResultSet getIdByGameState(String gamestate) {
        String query = "SELECT Id from Gamestate WHERE name = '" + gamestate+"'";
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
}
