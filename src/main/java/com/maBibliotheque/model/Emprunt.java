package com.maBibliotheque.model;

import java.util.Date;

public class Emprunt {
    private int idEmprunt;
    private int idAdherent;
    private int idExemplaire;
    private Date dateEmprunt;
    private Date dateRetourPrevue;
    private Date dateRetourEffective;

    
    public Emprunt(int idEmprunt, int idAdherent, int idExemplaire, Date dateEmprunt, Date dateRetourPrevue,
            Date dateRetourEffective) {
        this.idEmprunt = idEmprunt;
        this.idAdherent = idAdherent;
        this.idExemplaire = idExemplaire;
        this.dateEmprunt = dateEmprunt;
        this.dateRetourPrevue = dateRetourPrevue;
        this.dateRetourEffective = dateRetourEffective;
    }
    public int getIdEmprunt() {
        return idEmprunt;
    }
    public void setIdEmprunt(int idEmprunt) {
        this.idEmprunt = idEmprunt;
    }
    public int getIdAdherent() {
        return idAdherent;
    }
    public void setIdAdherent(int idAdherent) {
        this.idAdherent = idAdherent;
    }
    public int getIdExemplaire() {
        return idExemplaire;
    }
    public void setIdExemplaire(int idExemplaire) {
        this.idExemplaire = idExemplaire;
    }
    public Date getDateEmprunt() {
        return dateEmprunt;
    }
    public void setDateEmprunt(Date dateEmprunt) {
        this.dateEmprunt = dateEmprunt;
    }
    public Date getDateRetourPrevue() {
        return dateRetourPrevue;
    }
    public void setDateRetourPrevue(Date dateRetourPrevue) {
        this.dateRetourPrevue = dateRetourPrevue;
    }
    public Date getDateRetourEffective() {
        return dateRetourEffective;
    }
    public void setDateRetourEffective(Date dateRetourEffective) {
        this.dateRetourEffective = dateRetourEffective;
    }

    // Getters et setters (idem)
}
