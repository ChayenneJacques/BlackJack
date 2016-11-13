/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack.services;

import blackjack.dao.HeaduserDAO;
import blackjack.model.Headuser;
import blackjack.util.Conversion;

/**
 *
 * @author Wouter
 */
public class HeaduserService {

    /*
    Retourneert de headuser uit de database.
     */
    public static Headuser getHeaduser() {
        return Conversion.convertResultSetToHeaduser(HeaduserDAO.getHeadUser());
    }

    /*
    Roept een methode uit de HeaduserDAO op die het wachtwoord van de hoofdgebruiker kan wijzigen.
    Deze functionaliteit is uitgewerkt voor 1 enkele hoofdgebruiker
     */
    public static void editPassword(String password) {
        HeaduserDAO.editHeaduserPassword(password);
    }

    /*
    Deze service controleert of de hoofdgebruiker dit e-mailadres en wachtwoord heeft.
    Deze methode kan in principe wél werken met meerdere hoofdgebruikers.
     */
    public static boolean isAuthenticatedHeaduser(String email, String password) {
        boolean authenticated = false;
        int i = Conversion.convertResultSetToInt(HeaduserDAO.authenticateHeaduser(email, password));
        
        if (i > 0) {
            authenticated = true;
        }
        return authenticated;
    }
}
