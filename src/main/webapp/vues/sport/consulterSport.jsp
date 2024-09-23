<%-- 
    Document   : listerAthletes
    Créé le    : 14 août 2024, 12:27:51
    Auteur     : zakina
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="sio.paris2024.model.Athlete"%>
<%@page import="sio.paris2024.model.Pays"%>
<%@page import="sio.paris2024.model.Sport"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>PARIS 2024 - Sport et Athlètes</title>
    </head>
    <body>
        <%
            // Récupération de l'objet Sport depuis l'attribut de la requête
            Sport s = (Sport)request.getAttribute("sSport");
        %>
        
        <h1>Sport : <%= s.getNom() %></h1>
        
        <table border="1">
            <tr>
                <th>ID du Sport</th>
                <td><%= s.getId() %></td>
            </tr>
        </table>
            
            <h1>Sport : <%= s.getNom() %></h1>
        
        <table border="1">
            <tr>
                <th>ID du Sport</th>
                <td><%= s.getId() %></td>
            </tr>
        </table>
        
        <br/>
       
       <!-- Liste des athlètes associés au sport -->
        <h2>Liste des Athlètes</h2>
        <%
            // Supposons que vous avez une liste d'athlètes pour le sport
            List<Athlete> athlete = (List<Athlete>)request.getAttribute("listeAthletes");
            if (athletes != null && !athletes.isEmpty()) {
        %>
        <table border="1">
            <tr>
                <th>ID de l'Athlète</th>
                <th>Nom</th>
                <th>Pays</th>
            </tr>
            <%
                // Boucle sur les athlètes et affichage des détails
                for (Athlete athlete : athletes) {
            %>
            <tr>
                <td><%= athlete.getId() %></td>
                <td><%= athlete.getNom() %></td>
                <td><%= athlete.getPays().getNom() %></td>
            </tr>
            <%
                }
            %>
        </table>
        <%
            } else {
        %>
        <p>Aucun athlète trouvé pour ce sport.</p>
        <%
            }
        %>

    </body>
</html>
