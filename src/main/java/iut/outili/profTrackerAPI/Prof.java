package iut.outili.profTrackerAPI;

public class Prof {
    String nom;
    String prenom;
    String mail;

    public Prof(String nom, String prenom, String mail) {
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
    }

    public String toString() {
        return "Nom: " + nom + " Prenom: " + prenom + " Mail: " + mail;
    }

}
