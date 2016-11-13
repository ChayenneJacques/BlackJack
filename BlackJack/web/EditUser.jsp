<%-- 
    Document   : EditUser
    Created on : 26-okt-2016, 10:57:39
    Author     : Chayenne Jacques
--%>

<%@page import="blackjack.services.IconService"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="blackjack.model.Icon"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
  <% String loggedin = (String)request.getSession().getAttribute("loggedin");
            if (loggedin!="loggedin"){
                RequestDispatcher view = request.getRequestDispatcher("LoginScreen.jsp?page=AccountManagement.jsp");
        view.forward(request, response);
             } 
        else
{

%>
<% String nickname = request.getParameter("nickname");
    String iconname = request.getParameter("iconname");
    int balance = Integer.parseInt(request.getParameter("balance"));
        if(request.getServletContext().getAttribute("iconList")==null)
    {
        List<Icon> iconList=IconService.getIcons();
        request.getServletContext().setAttribute("iconList",iconList);
    }
    List<Icon> iconList = (List<Icon>)request.getServletContext().getAttribute("iconList"); %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="game.css" rel="stylesheet" type="text/css" />
        <title>Gebruiker wijzigen</title>
    </head>
    <body>
        <div id="container">
            <div class="userName"><% out.print("Credits van "+nickname+" wijzigen."); %></div>
        <form action="ModifyUserServlet" method="post" class="formTop100">
           <input class="textFieldRight" type="hidden" readonly value="<% out.print(nickname); %>" name="nickname" /><br>
            <img class="coin" src="images/coin.png" alt="coin" /> <input class="textFieldRight" size="15" type="number" min="0" autofocus placeholder="<% out.print(balance); %>" name="balance" required /><br>
            <input type="hidden" name="iconname" value="<% out.print(iconname); %>" />

            <br>
            <button type="button" class="regularButton" name="Terug" onclick="window.location='AccountManagement.jsp'">Terug</button><input class="regularButton" type="submit" value="Bevestigen" />
        </form>
        </div>
    </body>
</html>
<% } %>
