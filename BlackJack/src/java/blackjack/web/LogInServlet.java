/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack.web;

import blackjack.services.HeaduserService;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Chayenne Jacques
 */
public class LogInServlet extends HttpServlet {

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
        
        //sessieobject opvragen
        HttpSession session = request.getSession();
        
        String page1=request.getParameter("page");
        String newPage="";
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // als het ww en emailadres correct is, geeft het sessie-object de waarde "loggedin" en wordt er naar de opgevraagde pagina gegaan
        // indien het ww en emailadres niet correct is, wordt er terug naar het loginscherm gegaan
        if (HeaduserService.isAuthenticatedHeaduser(email, password)) {
            session.setAttribute("loggedin", "loggedin");
            newPage=page1;
        } else {
            request.setAttribute("errormessage","Verkeerd emailadres en/of wachtwoord. Probeer opnieuw!");
            newPage="LoginScreen.jsp?page="+page1;
        }
        RequestDispatcher view = request.getRequestDispatcher(newPage);
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
