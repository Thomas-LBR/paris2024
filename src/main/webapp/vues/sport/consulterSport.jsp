<%-- 
    Document   : listerAthletes
    Created on : 14 août 2024, 12:27:51
    Author     : zakina
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="sio.paris2024.model.Athlete"%>
<%@page import="sio.paris2024.model.Pays"%>
<%@page import="sio.paris2024.model.Sport"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>PARIS 2024</title>
    </head>
    <body>
        <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>PARIS 2024</title>
    </head>
    <body>
        <%
                Sport s = (Athlete)request.getAttribute("sSport");
        %>
        <h1><%  out.println(s.getNom());%></h1>
            
                         
            <table>
            <tr>
                <td>Id: </td><td><%  out.println(s.getId());%></td>
            </tr>
        </table>
</html>
