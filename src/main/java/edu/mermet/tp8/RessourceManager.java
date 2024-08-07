package edu.mermet.tp8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
*
* @author lucaslemarchand
*/
public class RessourceManager {
	public static final String MAIN_TRAD_FILE = "textes";
	public static final String HOWTO_TEXTES_FILE = "HowTo/textes";
	public static final String HOWTO_TITRES_FILE = "HowTo/titres";

	private static HashMap<String, RessourceManager> gestionnaires;
	private static Locale lieu;
	private String fichier;
	private ResourceBundle ressources;
	private List<Traduisible> traduisibles;
	
	private RessourceManager(String lienFichier) {
		fichier = lienFichier;
		ressources = ResourceBundle.getBundle(fichier, lieu);
		traduisibles = new ArrayList<>();
	}
	
	public static RessourceManager getRessourceManager(String lienFichier) {
		if (gestionnaires == null) {
			gestionnaires = new HashMap<>();
			lieu = Locale.getDefault();
		}

		gestionnaires.computeIfAbsent(lienFichier, k -> new RessourceManager(lienFichier));
		
		return gestionnaires.get(lienFichier);
	}
	
	public String getString(Ressource res) {
		return ressources.getString(res.getNom());
	}

	public String getString(String key) {
		return ressources.getString(key);
	}

	public String[] getKeys() {
		List<String> lstKeys = Collections.list(ressources.getKeys());
		Collections.sort(lstKeys);
		return lstKeys.toArray(new String[0]);
	}

	
	public static void definirLieu(Locale nouveauLieu) {
		lieu = nouveauLieu;

		for (RessourceManager rm : gestionnaires.values())
			rm.changerRessources(nouveauLieu);
	}

	public void changerRessources(Locale lieu) {
		ressources = ResourceBundle.getBundle(fichier, lieu);
		faireTraduire();
	}
	
	public static Locale getLieu() {
		return lieu;
	}
	
	public void addTraduisible(Traduisible trad) {
		traduisibles.add(trad);
	}
	
	public void removeTraduisible(Traduisible trad) {
		traduisibles.remove(trad);
	}
	
	private void faireTraduire() {
		for (Traduisible trad : traduisibles) {
			trad.traduire();
		}
	}
}
