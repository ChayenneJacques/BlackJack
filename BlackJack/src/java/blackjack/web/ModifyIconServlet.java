/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack.web;

import blackjack.model.Icon;
import blackjack.model.User;
import blackjack.services.IconService;
import blackjack.services.UserService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Wouter
 */
public class ModifyIconServlet extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");

        String iconname = request.getParameter("newIconName");
        String nickname = request.getParameter("nickname");

        User user = UserService.getUserByNickname(nickname);
        
        Icon icon=IconService.getIconByName(iconname);
        user.setIcon(icon);
        UserService.editUser(user);
        
        //als de hoofdgebruiker niet ingelogd is, wordt deze niet weergegeven in de lijst van de gebruikers
        //op die manier kunnen de gewone gebruikers niet spelen met het account van de hoofdgebruiker
        HttpSession session=request.getSession();
        String headusername=(String)session.getAttribute("headusername");
        List<User> users;
        if(headusername==null)
        {
            users=UserService.getUsersExcludingHeadUser();
        }
        else
        {
            users=UserService.getUsers();
        }
        request.getServletContext().setAttribute("users", users);
        
        RequestDispatcher view = request.getRequestDispatcher("SelectPlayersServlet");
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
