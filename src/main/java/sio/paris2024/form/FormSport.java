package sio.paris2024.form;

import jakarta.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import sio.paris2024.model.Sport;

/**
 *
 * @author zakina
 */
public class FormSport {

    private String resultat;
    private Map<String, String> erreurs = new HashMap<String, String>();

    public String getResultat() {
        return resultat;
    }

    public void setResultat(String resultat) {
        this.resultat = resultat;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }

    public void setErreurs(Map<String, String> erreurs) {
        this.erreurs = erreurs;
    }

    // Méthode de validation du champ de saisie nom
    private void validationNom(String nom) throws Exception {
        if (nom == null || nom.trim().length() < 3) {
            throw new Exception("Le nom du sport doit contenir au moins 3 caractères.");
        }
    }

    private void validationIdSport(String idSportStr) throws Exception {
        if (idSportStr == null) {
            throw new Exception("Le sport doit être sélectionné.");
        }
        try {
            Integer.parseInt(idSportStr);
        } catch (NumberFormatException e) {
            throw new Exception("L'identifiant du sport est invalide.");
        }
    }

    private void setErreur(String champ, String message) {
        erreurs.put(champ, message);
    }

    private static String getDataForm(HttpServletRequest request, String nomChamp) {
        String valeur = request.getParameter(nomChamp);
        if (valeur == null || valeur.trim().length() == 0) {
            return null;
        } else {
            return valeur.trim();
        }
    }

    public Sport ajouterSport(HttpServletRequest request) {

        Sport spo = new Sport();

        String nom = getDataForm(request, "nom");
        String idSportStr = getDataForm(request, "idSport");

        try {
            validationNom(nom);
        } catch (Exception e) {
            setErreur("nom", e.getMessage());
        }
        spo.setNom(nom);

        try {
            validationIdSport(idSportStr);
        } catch (Exception e) {
            setErreur("idSport", e.getMessage());
        }

        if (erreurs.isEmpty()) {
            resultat = "Succès de l'ajout.";
        } else {
            resultat = "Échec de l'ajout.";
        }

        return spo;
    }

}
