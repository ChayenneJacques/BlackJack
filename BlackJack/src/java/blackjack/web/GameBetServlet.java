/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack.web;

import blackjack.model.Game;
import blackjack.model.User;
import blackjack.services.UserService;
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
public class GameBetServlet extends HttpServlet {

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
        
        Game game = (Game) request.getServletContext().getAttribute("game");
        game.getDeck().clear();
        game.getDeck().fillDeck();
        game.getDeck().shuffle();
        game.getDealer().getHand().clear();
        
        //alle inzetten bijhouden
        List<String> bets = new ArrayList(request.getParameterMap().keySet());
        ArrayList<User> players = game.getPlayers();
        Iterator<User> it = players.iterator();
        User user = null;
        int i = 0;
        //voor elke speler wordt de inzet afgetrokken van zijn saldo
        while (it.hasNext()) {
            user = it.next();
            user.getHand().clear();
            user.setBet(Integer.parseInt(request.getParameter(bets.get(i))));
            user.setBalance(user.getBalance() - user.getBet());
            UserService.editUser(user);
            i++;

        }
        game.cardDistribution();

        request.getServletContext().setAttribute("game", game);

        RequestDispatcher view = request.getRequestDispatcher("GameDistribute.jsp");
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
