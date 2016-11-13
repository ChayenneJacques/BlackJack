<%-- 
    Document   : EditPassword
    Created on : 31-okt-2016, 12:38:13
    Author     : Chayenne Jacques
--%>
<% String loggedin = (String) request.getSession().getAttribute("loggedin");
    if (loggedin != "loggedin") {
        RequestDispatcher view = request.getRequestDispatcher("LoginScreen.jsp?page=EditPassword.jsp");
        view.forward(request, response);
    } else {
        String errormessage = (String) request.getAttribute("errormessage");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="game.css" rel="stylesheet" type="text/css" />
        <title>Wachtwoord wijzigen</title>
    </head>
    <body>
        <div id="container">
            <div class="userName"><% if (errormessage == null) {
                    out.print("Wachtwoord wijzigen");
                } else {
                    out.print(errormessage);
                }
                %></div>
            <form action="EditPasswordServlet" method="post" class="formTop100">
                <label><b>Nieuw wachtwoord</b></label>
                <input class="textFieldRight" size="15" type="password" placeholder="Vul uw nieuw wachtwoord in" name="newPassword" required />
                <br>
                <label><b>Herhaal uw nieuw wachtwoord</b></label>
                <input class="textFieldRight" size="15" type="password" placeholder="Herhaal uw nieuw wachtwoord in" name="newPassword2" required />
                <br />
                <button class="regularButton" type="button" name="Terug" onclick="window.location = 'StartScreen.jsp'">Terug</button><input class="regularButton" name="edit" type="submit" value="Wijzigen" />

            </form>
        </div>
    </body>
</html>
<% }
%>