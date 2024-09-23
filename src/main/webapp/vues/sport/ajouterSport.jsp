<%-- 
    Document   : ajouterPompier
    Créé le    : 18 mars 2024, 13:30:47
    Auteur     : zakina
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="sio.paris2024.model.Sport"%>
<%@page import="sio.paris2024.form.FormSport"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Paris 2024</title>
    </head>
    <body>
        <h1>NOUVEAU SPORT</h1>
        
        <%
            FormSport form = (FormSport) request.getAttribute("form");
        %>
        
        <form class="form-inline" action="ajouter" method="POST">
            <label for="nom">NOM : </label>
            <input id="nom" type="text" name="nom" size="30" maxlength="30">
            <br/>

                                  
            <input type="submit" name="valider" id="valider" value="Valider"/>
        </form>
    </body>
</html>
