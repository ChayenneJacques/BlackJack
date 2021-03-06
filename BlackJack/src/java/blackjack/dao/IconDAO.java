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
public class IconDAO {
/**
 * Vraagt alle iconen op uit de tabel icon.
 * @return 
 */
    public static ResultSet getIcons() {
        String query = "SELECT * from icon";
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
 * Voegt een icoon toe aan de tabel icon op basis van de naam en het pad.
 * @param name
 * @param path 
 */
    public static void addIcon(String name, String path) {
        String query = "INSERT into icon (name, path) VALUES ('" + name + "','" + path + "')";
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
 * Vraagt de id van een icoon op uit de tabel icon, op basis van een meegegeven icoonnaam.
 * @param iconname
 * @return 
 */
    public static ResultSet getIconIdByIconName(String iconname) {
        String query = "SELECT Id from icon WHERE Name = '" + iconname + "'";
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
 * Vraagt alle gegevens van een icoon op uit de tabel icon op basis van de meegegeven icoonnaam.
 * @param iconname
 * @return 
 */
    public static ResultSet getIconByName(String iconname) {
        String query = "SELECT * from icon WHERE Name = '" + iconname+"'";
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