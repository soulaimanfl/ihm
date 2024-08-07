package edu.mermet.tp8;

public enum Ressource {
	TITRE("titre"),
	ACTION_QUITTER("actionQuitter"),
	ACTION_DIAPORAMA("actionDiaporama"),
	ACTION_BOUTONS("actionBoutons"),
	ACTION_TEXTE("actionTexte"),
	ACTION_CONVERSION("actionConversion"),
	ACTION_COMMENT_FAIRE("actionCommentFaire"),
	ACTION_CONFIG_MENU("actionConfigMenu"),
	MENU_FICHIER("menuFichier"),
	MENU_APPLICATIONS("menuApplications"),
	MENU_LANGUES("menuLangues"),
	MENU_AIDE("menuAide"),
	LANGUE_DEFAUT("langueDefaut"),
	LANGUE_FRANCAIS("langueFrancais"),
	LANGUE_ANGLAIS("langueAnglais"),
	LANGUE_JAPONAIS("langueJaponais"),
	FEN_TEXTE_TITRE("fenTexteTitre"),
	FEN_TEXTE_GRAS("fenTexteGras"),
	FEN_TEXTE_ROUGE("fenTexteRouge"),
	FEN_TEXTE_STYLE("fenTexteStyle"),
    FEN_BOUTONS_TITRE("fenBoutonsTitre"),
	FEN_CONV_TITRE("fenConvTitre"),
	FEN_CONV_ERR_FORMAT("fenConvErrFormat"),
	FEN_CONV_CONVERTIR("fenConvConvertir"),
	FEN_CONV_AIDE_CELSIUS("fenConvAideCelsius"),
	FEN_CONV_AIDE_FARENHEIT("fenConvAideFarenheit"),
	FEN_DIAPO_TITRE("fenDiapoTitre"),
	FEN_COMMENTFAIRE_TITRE("fenCommentFaireTitre"),
	FEN_CONFIGMENU_TITRE("fenConfigMenu"),
	FEN_AIDE_TITRE("fenAideTitre"),
	CONFIG_AUTO("configAuto"),
	CONFIG_AFFICHE("configAffiche"),
	CONFIG_CACHE("configCache"),
	BTN_VALIDER("btnValider"),
	BTN_ANNULER("btnAnnuler");


	
	private String nom;
	Ressource(String leNom) {
		nom = leNom;
	}

	public String getNom() {
		return nom;
	}
}
