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
public class UserDAO {
/**
 * Vraagt alle gebruikers, met hun icoongegevens op uit de tabel user en icon. (Ook de hoofdgebruiker)
 * @return 
 */
    public static ResultSet getUsers() {
        String query = "SELECT * from user, icon WHERE icon.Id = user.iconId order by nickname";
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
 * Voegt een gebruiker toe aan de tabel user op basis van de meegegeven parameters.
 * @param nickname
 * @param balance
 * @param iconId 
 */
    public static void addUser(String nickname, int balance, int iconId) {
        String query = "INSERT into user (nickname, balance, iconId) VALUES ('" + nickname + "','" + balance + "','" + iconId + "')";
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
 * verwijdert een gebruiker uit de tabel user op basis van de meegegeven nickname. 
 * De hoofdgebruiker kan programmatorisch gezien niet verwijderd worden.
 * @param nickname 
 */
    public static void removeUser(String nickname) {
        String query = "DELETE from user WHERE nickname =  '" + nickname + "'";
        Connection con = DatabaseSingleton.getDatabaseSingleton().getConnection(true);

        Statement stmt = null;
        try {
            stmt = con.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println("removeUserError: "+e);
        }
    }
/**
 * Wijzigt de balans en de id van het icoon op basis van de meegegeven nickname.
 * De nickname kan bijgevolg niet gewijzigd worden op deze manier.
 * @param nickname
 * @param balance
 * @param iconId 
 */
    public static void editUser(String nickname, int balance, int iconId) {
        String query = "UPDATE user SET balance = '" + balance + "', iconId= '" + iconId + "' WHERE nickname= '" + nickname + "'";
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
 * Vraagt de id van een gebruiker op uit de tabel user, op basis van de meegegeven nickname.
 * @param nickname
 * @return 
 */
    public static ResultSet getUserIdByNickname(String nickname) {
        String query = "SELECT Id from user WHERE nickname = '" + nickname+"'";
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
 * Vraagt alle gegevens van een bepaalde gebruiker op uit de tabel user, op basis van de meegegeven nickname.
 * @param nickname
 * @return 
 */
    public static ResultSet getUserByNickname(String nickname) {
        String query = "SELECT * from user, icon WHERE icon.Id = user.iconId and Nickname = '" + nickname + "'";
        Connection con = DatabaseSingleton.getDatabaseSingleton().getConnection(true);

        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
        } catch (SQLException e) {
            System.out.println("getuserByNickname probleem");
        }
        return rs;
    }
/**
 * Vraagt alle gebruikers, met hun icoongegevens op uit de tabel user en icon. (Uitgenomen de hoofdgebruiker)
 * @return 
 */
    public static ResultSet getUsersExcludingHeadUser() {
        String query = "SELECT * from user, icon WHERE icon.Id = user.iconId and user.Id NOT IN (SELECT Id from headuser) order by nickname";
        Connection con = DatabaseSingleton.getDatabaseSingleton().getConnection(true);
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
        } catch (SQLException e) {
           System.out.println("excludingheaduser error");
        }
        return rs;
    }
}
