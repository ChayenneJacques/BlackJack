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
public class GameDAO {
 /**
  * Vraagt de id op van de laatst aangemaakte record op uit een meegegeven tabel.
  * @param table
  * @return
  */
    public static ResultSet selectLargestId(String table) {
        String query = "SELECT max(Id) as Id from " + table;
        Connection con = DatabaseSingleton.getDatabaseSingleton().getConnection(true);
        ResultSet rs = null;
        Statement stmt = null;

        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
    /**
     * Voegt een game toe aan de tabel game met een meegegeven datum
     * @param date 
     */
    public static void addGame(String date)
    {
        String query="insert into game (Date) values ('"+date+"')";
        Connection con = DatabaseSingleton.getDatabaseSingleton().getConnection(true);
        Statement stmt = null;

        try {
            stmt = con.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println("addgame error");
        }
    }
}
