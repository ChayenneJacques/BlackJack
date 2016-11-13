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
public class HeaduserDAO {
/**
 * Vraagt alle gegevens van de hoofdgebruiker op uit de tabellen user, headuser en icon.
 * Deze uitwerking kan enkel werken met 1 hoofdgebruiker.
 * @return 
 */
    public static ResultSet getHeadUser() {
        String query = "SELECT * from user,headuser, icon where user.Id=headuser.Id and icon.Id=user.IconId";
        Connection con = DatabaseSingleton.getDatabaseSingleton().getConnection(true);
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
        } catch (SQLException e) {
        }
        return rs;
    }
/**
 * Wijzigt het wachtwoord van de hoofdgebruiker.
 * Ook deze uitwerking kan enkel werken met 1 hoofdgebruiker.
 * @param password 
 */
    public static void editHeaduserPassword(String password) {
        String query = "UPDATE headuser SET password = '" + password + "'";
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
     * Controleert of de hoofdgebruiker het meegegeven e-mailadres en wachtwoord bezit.
     * Deze methode kan wél werken met meerdere hoofdgebruikers
     * @param email
     * @param password
     * @return 
     */
    public static ResultSet authenticateHeaduser(String email, String password)
    {
        String query = "SELECT count(*) as result from headuser where Email='"+email+"' and Password='"+password+"'";
        Connection con = DatabaseSingleton.getDatabaseSingleton().getConnection(true);
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
        } catch (SQLException e) {
            System.out.println("error authenticat dao");
        }
        return rs;
    }

}
