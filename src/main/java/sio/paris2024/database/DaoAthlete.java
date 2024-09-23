package sio.paris2024.database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sio.paris2024.model.Athlete;
import sio.paris2024.model.Pays;
import sio.paris2024.model.Sport;

public class DaoAthlete {

    static PreparedStatement requeteSql = null;
    static ResultSet resultatRequete = null;

    public static ArrayList<Athlete> getLesAthletes(Connection cnx) {
        ArrayList<Athlete> lesAthletes = new ArrayList<>();
        try {
            requeteSql = cnx.prepareStatement("SELECT a.id AS a_id, a.nom AS a_nom, p.id AS p_id, p.nom AS p_nom, a.prenom AS a_prenom, a.datenaiss AS a_datenaiss " +
                    "FROM athlete a INNER JOIN pays p ON a.pays_id = p.id");
            
            resultatRequete = requeteSql.executeQuery();

            while (resultatRequete.next()) {
                Athlete a = new Athlete();
                a.setId(resultatRequete.getInt("a_id"));
                a.setNom(resultatRequete.getString("a_nom"));
                a.setPrenom(resultatRequete.getString("a_prenom"));

                Date date = resultatRequete.getDate("a_datenaiss");
                a.setDateNaiss(date.toLocalDate());

                Pays p = new Pays();
                p.setId(resultatRequete.getInt("p_id"));
                p.setNom(resultatRequete.getString("p_nom"));

                a.setPays(p);

                lesAthletes.add(a);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("La requête de getLesAthletes a généré une erreur");
        } finally {
            try {
                if (resultatRequete != null) resultatRequete.close();
                if (requeteSql != null) requeteSql.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return lesAthletes;
    }

    public static Athlete getAthleteById(Connection cnx, int idAthlete) {
        Athlete a = new Athlete();
        try {
            requeteSql = cnx.prepareStatement("SELECT a.id AS a_id, a.nom AS a_nom, p.id AS p_id, p.nom AS p_nom, a.prenom AS a_prenom, a.datenaiss AS a_datenaiss " +
                    "FROM athlete a INNER JOIN pays p ON a.pays_id = p.id WHERE a.id = ?");
            requeteSql.setInt(1, idAthlete);

            resultatRequete = requeteSql.executeQuery();

            if (resultatRequete.next()) {
                a.setId(resultatRequete.getInt("a_id"));
                a.setNom(resultatRequete.getString("a_nom"));
                a.setPrenom(resultatRequete.getString("a_prenom"));

                Date date = resultatRequete.getDate("a_datenaiss");
                a.setDateNaiss(date.toLocalDate());

                Pays p = new Pays();
                p.setId(resultatRequete.getInt("p_id"));
                p.setNom(resultatRequete.getString("p_nom"));

                a.setPays(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("La requête de getAthleteById a généré une erreur");
        } finally {
            try {
                if (resultatRequete != null) resultatRequete.close();
                if (requeteSql != null) requeteSql.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return a;
    }

    public static Athlete addAthlete(Connection connection, Athlete ath) {
        int idGenere = -1;
        try {
            requeteSql = connection.prepareStatement("INSERT INTO athlete (nom, pays_id) VALUES (?,?)", requeteSql.RETURN_GENERATED_KEYS);
            requeteSql.setString(1, ath.getNom());
            requeteSql.setInt(2, ath.getPays().getId());

            requeteSql.executeUpdate();

            resultatRequete = requeteSql.getGeneratedKeys();
            if (resultatRequete.next()) {
                idGenere = resultatRequete.getInt(1);
                ath.setId(idGenere);

                ath = DaoAthlete.getAthleteById(connection, ath.getId());
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur lors de l'ajout de l'athlète");
        } finally {
            try {
                if (resultatRequete != null) resultatRequete.close();
                if (requeteSql != null) requeteSql.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return ath;
    }
    
      public static ArrayList<Athlete> getAthletesBySport(Connection cnx, int idSport) {
        ArrayList<Athlete> athletes = new ArrayList<>();
        PreparedStatement requeteSql = null;
        ResultSet resultatRequete = null;
        
        try {
            // Préparer la requête SQL pour récupérer les athlètes en fonction du sport
            requeteSql = cnx.prepareStatement(
                "SELECT a.id as a_id, a.nom as a_nom, a.prenom as a_prenom, a.datenaiss as a_datenaiss, p.id as p_id, p.nom as p_nom " +
                "FROM athlete a " +
                "JOIN sport s ON a.sport_id = s.id " +
                "JOIN pays p ON a.pays_id = p.id " +
                "WHERE s.id = ?"
            );
            // Assigner le nom du sport au paramètre de la requête
            requeteSql.setInt(1, idSport);
            
            // Exécuter la requête
            resultatRequete = requeteSql.executeQuery();
            
            // Parcourir les résultats et ajouter les athlètes à la liste
            while (resultatRequete.next()) {
                Athlete athlete = new Athlete();
                athlete.setId(resultatRequete.getInt("a_id"));
                athlete.setNom(resultatRequete.getString("a_nom"));
                athlete.setPrenom(resultatRequete.getString("a_prenom"));
                
                Date date = resultatRequete.getDate("a_datenaiss");
                athlete.setDateNaiss(date.toLocalDate());
                
                Pays unpays = new Pays();
                unpays.setId(resultatRequete.getInt("p_id"));
                unpays.setNom(resultatRequete.getString("p_nom"));
                
                athlete.setPays(unpays);
                // Ajouter l'athlète à la liste des athlètes
                athletes.add(athlete);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Fermeture des ressources pour éviter les fuites de mémoire
            try {
                if (resultatRequete != null) resultatRequete.close();
                if (requeteSql != null) requeteSql.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        // Retourner la liste des athlètes
        return athletes;
    }
}
