package sio.paris2024.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import sio.paris2024.model.Sport;

public class DaoSport {

    static PreparedStatement requeteSql = null;
    static ResultSet resultatRequete = null;

    // Méthode pour récupérer la liste de tous les sports
    public static ArrayList<Sport> getLesSports(Connection cnx) {
        ArrayList<Sport> lesSports = new ArrayList<Sport>();
        try {
            requeteSql = cnx.prepareStatement("SELECT sport.id AS s_id, sport.nom AS s_nom FROM sport");
            resultatRequete = requeteSql.executeQuery();

            while (resultatRequete.next()) {
                Sport s = new Sport();
                s.setId(resultatRequete.getInt("s_id"));
                s.setNom(resultatRequete.getString("s_nom"));

                lesSports.add(s);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("La requête de getLesSports a généré une erreur");
        } finally {
            // Fermeture des ressources pour éviter les fuites
            try {
                if (resultatRequete != null) resultatRequete.close();
                if (requeteSql != null) requeteSql.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return lesSports;
    }

    // Méthode pour récupérer un sport par ID
    public static Sport getSportById(Connection cnx, int idSport) {
        Sport s = new Sport();
        try {
            requeteSql = cnx.prepareStatement("SELECT sport.id AS s_id, sport.nom AS s_nom FROM sport WHERE sport.id = ?");
            requeteSql.setInt(1, idSport);

            resultatRequete = requeteSql.executeQuery();

            if (resultatRequete.next()) {
                s.setId(resultatRequete.getInt("s_id"));
                s.setNom(resultatRequete.getString("s_nom"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("La requête de getSportById a généré une erreur");
        } finally {
            // Fermeture des ressources pour éviter les fuites
            try {
                if (resultatRequete != null) resultatRequete.close();
                if (requeteSql != null) requeteSql.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return s;
    }

}
