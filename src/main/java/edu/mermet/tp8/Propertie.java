package edu.mermet.tp8;

public enum Propertie {
    CONVERSION("conversion"),
    SAISIE("saisie"),
    DIAPORAMA("diaporama"),
    BOUTONS("boutons");

    private String nom;
    Propertie(String leNom) {
        nom = leNom;
    }

    public String getNom() {
        return nom;
    }
}
