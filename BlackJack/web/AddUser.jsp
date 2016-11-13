<%-- 
    Document   : addUser
    Created on : 26-okt-2016, 11:24:02
    Author     : Chayenne Jacques
--%>
<%@page import="blackjack.services.UserService"%>
<%@page import="java.util.List"%>
<%@page import="blackjack.model.User"%>
<% String loggedin = (String) request.getSession().getAttribute("loggedin");
    if (loggedin != "loggedin") {
        RequestDispatcher view = request.getRequestDispatcher("LoginScreen.jsp?page=AddUser.jsp");
        view.forward(request, response);
    } else {
        String errormessage = (String) request.getAttribute("errormessage");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="game.css" rel="stylesheet" type="text/css" />
        <title>Gebruiker toevoegen</title>

    </head>
    <body> 
        <div id="container">
            <div class="userName"> <% if (errormessage == null) {
                    out.print("Gebruiker toevoegen");
                } else {
                    out.print(errormessage);
                }
                %></div>
            <form action="AddUserServlet" id="addform" method="post" class="formTop100">
                Naam: <input type="text" class="textFieldRight" size="15" id="nick" name="nickname" required /><br>
                Credits: <input type="number" class="textFieldRight" value="50" name="credits" required /><br>
                <br>
                <button class="regularButton" name="Terug" type="button" onclick="window.location = 'AccountManagement.jsp'">Terug</button><input class="regularButton" type="submit" value="Bevestigen" />
            </form>
        </div>
    </body>
</html>
<%}
%>