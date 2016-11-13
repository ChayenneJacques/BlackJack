/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack.web;

import blackjack.model.Game;
import blackjack.model.Handstate;
import blackjack.model.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Wouter
 */
public class GameServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher view = request.getRequestDispatcher("Game.jsp");
        Game game = (Game) request.getServletContext().getAttribute("game");
        String currentPlayerIndex = (String) request.getServletContext().getAttribute("currentPlayerIndex");
        int index = Integer.parseInt(currentPlayerIndex);

        //Als currentPlayerIndex null is, is het aan de eerste speler. (en wordt currentPlayerIndex op 0 gezet)
        if (currentPlayerIndex == null) {
            request.getServletContext().setAttribute("currentPlayerIndex", "0");

            //Elke speler die blackjack heeft, wordt overgeslaan.
            //Ook moet je controleren of het aantal spelers groter is dan de index die verhoogd wordt met 1.
            while (game.getPlayers().size() > index + 1 && game.getPlayers().get(index).getHand().getState() == Handstate.Blackjack) {
                index += 1;
            }
            //contextattribuut currentPlayerIndex wordt aangepast
            request.getServletContext().setAttribute("currentPlayerIndex", index + "");

            //Als currentPlayerIndex niet null is en kleiner dan het aantal spalers, 
            //werd er sowieso een hit of stand gedaan in de jsp-pagina.
        } else if (Integer.parseInt(currentPlayerIndex) < game.getPlayers().size()) {

            //de actie (hit of stand) en de huidige speler worden opgevraagd.
            String action = (String) request.getParameter("action");
            User currentPlayer = game.getPlayers().get(index);

            //actie is hit:
            if (action.equals("hit")) {
                //kaart geven
                game.playerHit(currentPlayer);
                //hand evalueren
                currentPlayer.getHand().evaluate();
                //indien de handstatus busted of stand is, is het aan de volgende speler
                //index wordt dus verhoogd.
                if (currentPlayer.getHand().getState() == Handstate.Busted || currentPlayer.getHand().getState() == Handstate.Stand) {
                    index += 1;

                    //Elke volgende speler die blackjack heeft, wordt overgeslaan.
                    //Ook moet je controleren of het aantal spelers groter is dan de index die verhoogd wordt met 1.
                    while (game.getPlayers().size() > index + 1 && game.getPlayers().get(index).getHand().getState() == Handstate.Blackjack) {
                        index += 1;
                    }
                }

                //actie is stand:
            } else {
                //index wordt verhoogd en de handstate wordt op stand geplaatst.
                index += 1;
                currentPlayer.getHand().setState(Handstate.Stand);
            }

            //contextattribuut currentPlayerIndex wordt gewijzigd.
            request.getServletContext().setAttribute("currentPlayerIndex", index + "");

            //indien alle spelers gespeeld hebben is het aan de dealer.
            if (index >= game.getPlayers().size()) {
                //contextattribuut currentPlayerIndex wordt op null geplaatst
                request.getServletContext().setAttribute("currentPlayerIndex", null);
                //kaart die omgekeerd op tafel ligt, wordt omgedraaid.
                game.getDealer().getHand().getCards().get(1).setVisibility(true);
                //view wordt aangepast naar gameDealerServlet.
                //daar speelt de dealer.
                //ook de gamestates worden daar gewijzigd, en de winstverdeling wordt uitgevoerd.
                view = request.getRequestDispatcher("gameDealerServlet");
            }
        }

        //het game-object is gewijzigd en moet op contextniveau overschreven worden.
        request.getServletContext().setAttribute("game", game);

        view.forward(request, response);

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
