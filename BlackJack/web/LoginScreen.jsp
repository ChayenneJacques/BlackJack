<%-- 
    Document   : LoginScreen
    Created on : 1-nov-2016, 18:28:38
    Author     : Chayenne Jacques
--%>

<%
String page1=request.getParameter("page");
if(page1==null)
{
    page1="StartScreen.jsp";
}
String errorMessage=(String)request.getAttribute("errormessage");

%>
<html>
    <head>
        <title>Login</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="game.css" type="text/css" />
    </head>
    <body>
        <div id="container">
            <div class="userName"><% if(errorMessage==null){ out.print("Inloggen"); }else{ out.print(errorMessage); } %></div>
        <form method="post" action="LogInServlet" class="formTop100">
            <input type="hidden" name="page" value="<% out.print(page1); %>" />
            <label><b>E-mailadres</b></label>
            <input class="textFieldRight" size="15" type="text" placeholder="Vul uw e-mailadres in" name="email" required />
            <br>
            <label><b>Wachtwoord</b></label>
            <input class="textFieldRight" size="15" type="password" placeholder="Vul uw wachtwoord in" name="password" required />
            <br>
            <button type="button" name="Terug" onclick="window.location='StartScreen.jsp'">Terug</button>
            <button name="login" type="submit">Login</button>
        </form>
        </div>
    </body>
</html>

