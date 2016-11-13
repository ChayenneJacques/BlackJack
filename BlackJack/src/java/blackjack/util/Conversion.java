package blackjack.util;

import blackjack.model.Headuser;
import blackjack.model.History;
import blackjack.model.Icon;
import blackjack.model.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Chayenne Jacques
 */
public class Conversion {

    /*
    Maakt een lijst van gebruikers op basis van een meegegeven resultset.
    */
    public static List<User> convertResultsetToListUsers(ResultSet rs) {
        List<User> list = new ArrayList();
        String nickname, iconname, iconpath;
        int balance;

        try {
            while (rs.next()) {
                /*
                Vraagt voor elke result in de resultset de benodigde gegevens op.
                Maakt de User-en Icon-objecten aan en schrijft ze weg in de lijst.
                */
                nickname = rs.getString("nickname");
                balance = rs.getInt("balance");
                iconname = rs.getString("icon.name");
                iconpath = rs.getString("icon.path");
                Icon icon = new Icon(iconname, iconpath);
                User user = new User(nickname, balance, icon);
                list.add(user);
            }
        } catch (SQLException ex) {

        }
        return list;
    }
    /*
    Converteert een resultset naar een Headuser-object.
    */
    public static Headuser convertResultSetToHeaduser(ResultSet rs) {
        Headuser headuser = null;
        Icon icon = null;
        String nickname, iconname, iconpath, email, password;
        int balance;
        try {
            while (rs.next()) {
                /*
                Vraagt voor elke result in de resultset de benodigde gegevens op.
                Maakt de Headuser-en Icon-objecten aan en schrijft ze weg in de lijst.
                Doordat we maar 1 hoofdgebruiker hebben, zal deze lus maar 1 keer overlopen worden.
                */
                nickname = rs.getString("Nickname");
                iconname = rs.getString("icon.Name");
                iconpath = rs.getString("icon.Path");
                email = rs.getString("Email");
                password = rs.getString("Password");
                balance = rs.getInt("Balance");
                icon = new Icon(iconname, iconpath);
                headuser = new Headuser(nickname, balance, password, email, icon);

            }
        } catch (SQLException ex) {

        }
        return headuser;

    }
 /*
    Converteert een resultset naar een lijst van iconen.
    */
    public static List<Icon> convertResultSetToListIcons(ResultSet rs) {
        List<Icon> list = new ArrayList();
        String iconname, iconpath;

        try {
            while (rs.next()) {
                iconname = rs.getString("icon.name");
                iconpath = rs.getString("icon.path");
                Icon icon = new Icon(iconname, iconpath);
                list.add(icon);
            }
        } catch (SQLException ex) {

        }
        return list;
    }
    /*
    Converteert een resultset naar een id.
    */
    public static int convertResultSetToId(ResultSet rs) {
        int res = -1;
        try {
            while (rs.next()) {
                res = rs.getInt("Id");
            }
        } catch (SQLException ex) {
                System.out.println("error convertresultsettoId");
        }
        return res;
    }
/*
    converteert een resultset naar een enkele gebruiker.
    */
    public static User convertResultSetToUser(ResultSet rs) {
        String nickname, iconname, iconpath;
        int balance;
        User user = null;
        Icon icon = null;
        try {
            while (rs.next()) {
                /*
                Deze lus zal maar 1 keer overlopen worden.
                */
                nickname = rs.getString("Nickname");
                balance = rs.getInt("Balance");
                iconname = rs.getString("icon.Name");
                iconpath = rs.getString("icon.Path");
                icon = new Icon(iconname, iconpath);
                user = new User(nickname, balance, icon);
            }
        } catch (SQLException ex) {
        }
        return user;
    }
/*
    Converteert een resultset naar een lijst van History-objecten.
    */
    public static List<History> convertResultsetToHistoryList(ResultSet rs) {
        List<History> list = new ArrayList();
        String nickname, iconname, iconpath, date, gamestate;
        int balance, bet, gameid, gamestateid;

        try {
            while (rs.next()) {
                /*
                Voor elke result in de resultset worden de benodigde gegevens opgevraagd.  
                */
                gameid = rs.getInt("game.Id");
                date = rs.getString("Date");
                gamestate = rs.getString("gamestate.Name");
                nickname = rs.getString("Nickname");
                balance = rs.getInt("history.Balance");
                iconname = rs.getString("icon.Name");
                iconpath = rs.getString("icon.Path");
                bet = rs.getInt("Bet");
                 /*
                Deze gegevens worden weggeschreven in objecten.
                Het History-oject wordt toegevoegd aan de lijst.
                */
                Icon icon = new Icon(iconname, iconpath);
                User user = new User(nickname, balance, icon);
                user.setBet(bet);
                History history = new History(gameid, user, gamestate, date);
                
                list.add(history);
            }
        } catch (SQLException ex) {
         
        }
        return list;
    }
/*
    Converteert een resultset naar 1 enkele Icon.
    */
    public static Icon convertResultSetToIcon(ResultSet rs) {
        String iconname, iconpath;
        Icon icon=null;

        try {
            while (rs.next()) {
                iconname = rs.getString("icon.name");
                iconpath = rs.getString("icon.path");
                icon = new Icon(iconname, iconpath);
            }
        } catch (SQLException ex) {

        } return icon;
    }
    /*
    Converteert een resultset naar een integer.
    */
    public static int convertResultSetToInt(ResultSet rs)
    {
        int res = 0;
        try {
            while (rs.next()) {
                res = rs.getInt("result");
                
            }
        } catch (SQLException ex) {
        
        }
        return res;
    }
}
