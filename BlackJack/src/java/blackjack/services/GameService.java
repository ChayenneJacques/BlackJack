/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack.services;

import blackjack.dao.GameDAO;

/**
 *
 * @author Wouter
 */
public class GameService {
    /*
    Roept de methode uit de GameDAO op die games kan toevoegen aan de database.
    */
    public static void addGame(String date)
    {
        GameDAO.addGame(date);
    }
}
