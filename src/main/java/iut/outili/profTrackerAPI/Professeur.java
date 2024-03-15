package iut.outili.profTrackerAPI;

public class Professeur {
    String nom;
    String prenom;
    String mail;

    public Professeur(String nom, String prenom, String mail) {
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
    }

    public String toString() {
        return "Nom: " + nom + " Prenom: " + prenom + " Mail: " + mail;
    }

}
