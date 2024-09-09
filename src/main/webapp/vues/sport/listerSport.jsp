<%-- 
    Document   : listerSport
    Created on : 9 sept. 2024, 10:27:02
    Author     : SIO2
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="sio.paris2024.model.Sport"%>
<%@page import="java.util.ArrayList"%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>PARIS 2024</title>
        <link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
        
        <title>Paris 2024</title>

        <style>
            body {
		padding-top: 50px;
            }
                .special {
		padding-top:50px;
	}
        </style>
    </head>
    <body>
      
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<a  href ='../ServletSport/lister' class="navbar-brand" href=".">Système de gestion des sports</a>
			</div>
		</div>
	</nav>
    <body>
       <div class="container special">
            <h2 class="h2">Liste des sports</h2>
		<div class="table-responsive">
                <%
                    ArrayList<Sport> lesSports = (ArrayList)request.getAttribute("sLesSports");
                %>
                <table class="table table-striped table-sm">  
                <thead>
                    <tr>             
                        <th>id</th>
                        <th>nom</th>               
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <%
                            for (Sport s : lesSports)
                            {              
                                out.println("<tr><td>");
                                out.println(s.getId());
                                out.println("</td>");

                                out.println("<td><a href =''>");
                                out.println(s.getNom());
                                out.println("</a></td>");                        
                     

                            }
                        %>
                    </tr>
                </tbody>
            </table>
        </body>
         </div>
       </div>
  
</html>
