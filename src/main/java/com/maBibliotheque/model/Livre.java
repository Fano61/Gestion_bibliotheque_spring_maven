package com.maBibliotheque.model;

public class Livre {
    private int idLivre;
    private String titre;
    private String auteur;
    private String edition;
    private String langue;
    private String motsCles;
    private String description;
    private float note;
    private String commentaires;

    
    public Livre(int idLivre, String titre, String auteur, String edition, String langue, String motsCles,
            String description, float note, String commentaires) {
        this.idLivre = idLivre;
        this.titre = titre;
        this.auteur = auteur;
        this.edition = edition;
        this.langue = langue;
        this.motsCles = motsCles;
        this.description = description;
        this.note = note;
        this.commentaires = commentaires;
    }
    public int getIdLivre() {
        return idLivre;
    }
    public void setIdLivre(int idLivre) {
        this.idLivre = idLivre;
    }
    public String getTitre() {
        return titre;
    }
    public void setTitre(String titre) {
        this.titre = titre;
    }
    public String getAuteur() {
        return auteur;
    }
    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }
    public String getEdition() {
        return edition;
    }
    public void setEdition(String edition) {
        this.edition = edition;
    }
    public String getLangue() {
        return langue;
    }
    public void setLangue(String langue) {
        this.langue = langue;
    }
    public String getMotsCles() {
        return motsCles;
    }
    public void setMotsCles(String motsCles) {
        this.motsCles = motsCles;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public float getNote() {
        return note;
    }
    public void setNote(float note) {
        this.note = note;
    }
    public String getCommentaires() {
        return commentaires;
    }
    public void setCommentaires(String commentaires) {
        this.commentaires = commentaires;
    }

    // Getters et setters (idem)
}
