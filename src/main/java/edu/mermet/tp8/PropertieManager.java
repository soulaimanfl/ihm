package edu.mermet.tp8;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class PropertieManager {
    private static PropertieManager instance;
    private String login;
    private Properties proprietes;

    private PropertieManager() {
        login = "login";
        proprietes = new Properties();
    }

    public static PropertieManager getInstance() {
        if (instance == null) {
            instance = new PropertieManager();
        }

        return instance;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getProperty(Propertie prop, String defaut) {
        return proprietes.getProperty(prop.getNom(), defaut);
    }


    public void setProperty(Propertie prop, String valeur) {
        proprietes.setProperty(prop.getNom(), valeur);
    }

    public void chargerPreferences() {
        String homeDir = System.getenv("HOME");
        if (homeDir == null || homeDir.isEmpty())
            homeDir = System.getenv("USERPROFILE");
        String fichier = homeDir + "/.ihm/" + login + ".xml";

        try (InputStream in = new FileInputStream(fichier)) {
            proprietes.loadFromXML(in);
        } catch(IOException ioe) {
            System.err.println("pas de fichier de pref");
        }
    }

    public void majPreferences() {
        String homeDir = System.getenv("HOME");
        if (homeDir == null || homeDir.isEmpty())
            homeDir = System.getenv("USERPROFILE");
        String repertoire = homeDir + "/.ihm";

        Path pathRepertoire = new File(repertoire).toPath();
        if(!Files.isDirectory(pathRepertoire)) {
            try {
                Files.createDirectory(pathRepertoire);
            }
            catch (IOException e) {
                System.err.println("Sauvegarde des préférences impossibles");
                System.err.println(e.getMessage());
            }
        }
        String fichier = repertoire + "/" + login + ".xml";
        try (OutputStream out = new FileOutputStream(fichier)) {
            proprietes.storeToXML(out, "propriétés");
        }
        catch(IOException ioe) {
            System.err.println(ioe.getMessage());
        }
    }
}
