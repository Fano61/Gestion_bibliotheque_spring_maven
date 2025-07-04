package com.maBibliotheque.model;

import java.util.Date;

public class Adherent {
    private int idAdherent;
    private String nom;
    private String prenom;
    private Date dtn;
    private String identifiant;
    private int idTypeAdherent;
    private int idStatut;
    private boolean abonnementPaye;
    private Date sanctionJusqua;


    
    public Adherent(int idAdherent, String nom, String prenom, Date dtn, String identifiant, int idTypeAdherent,
            int idStatut, boolean abonnementPaye, Date sanctionJusqua) {
        this.idAdherent = idAdherent;
        this.nom = nom;
        this.prenom = prenom;
        this.dtn = dtn;
        this.identifiant = identifiant;
        this.idTypeAdherent = idTypeAdherent;
        this.idStatut = idStatut;
        this.abonnementPaye = abonnementPaye;
        this.sanctionJusqua = sanctionJusqua;
    }
    // Getters et setters
    public int getIdAdherent() { return idAdherent; }
    public void setIdAdherent(int idAdherent) { this.idAdherent = idAdherent; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public Date getDtn() { return dtn; }
    public void setDtn(Date dtn) { this.dtn = dtn; }

    public String getIdentifiant() { return identifiant; }
    public void setIdentifiant(String identifiant) { this.identifiant = identifiant; }

    public int getIdTypeAdherent() { return idTypeAdherent; }
    public void setIdTypeAdherent(int idTypeAdherent) { this.idTypeAdherent = idTypeAdherent; }

    public int getIdStatut() { return idStatut; }
    public void setIdStatut(int idStatut) { this.idStatut = idStatut; }

    public boolean isAbonnementPaye() { return abonnementPaye; }
    public void setAbonnementPaye(boolean abonnementPaye) { this.abonnementPaye = abonnementPaye; }

    public Date getSanctionJusqua() { return sanctionJusqua; }
    public void setSanctionJusqua(Date sanctionJusqua) { this.sanctionJusqua = sanctionJusqua; }
}
