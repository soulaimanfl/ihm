package edu.mermet.tp8;


import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Locale;

import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.SwingUtilities;

import edu.mermet.tp8.actions.ActionAfficherBoutons;
import edu.mermet.tp8.actions.ActionAfficherConversion;
import edu.mermet.tp8.actions.ActionAfficherDiaporama;
import edu.mermet.tp8.actions.ActionAfficherTexte;
import edu.mermet.tp8.actions.ActionCommentFaire;
import edu.mermet.tp8.actions.ActionConfigMenu;
import edu.mermet.tp8.actions.ActionLangue;
//import edu.mermet.tp8.actions.ActionLangue;
import edu.mermet.tp8.actions.ActionQuitter;
import edu.mermet.tp8.dialogs.DialogConfigMenu;
import edu.mermet.tp8.fenetres.AbstractFenetreInterne;
import edu.mermet.tp8.fenetres.FenetreBoutons;
import edu.mermet.tp8.fenetres.FenetreConversion;
import edu.mermet.tp8.fenetres.FenetreDiaporama;
import edu.mermet.tp8.fenetres.FenetreTexte;

/**
 *
 * @author brunomermet
 */
public class Application extends JFrame implements Traduisible {
    AbstractFenetreInterne conversion;
    AbstractFenetreInterne texte;
    AbstractFenetreInterne diaporama;
    AbstractFenetreInterne boutons;
    private Action actionQuitter;
    private Action actionAfficherConversion;
    private Action actionAfficherTexte;
    private Action actionAfficherDiaporama;
    private Action actionAfficherBoutons;
	private Action actionCommentFaire;
	private Action actionConfigMenu;
    private ActionLangue actionLangueParDefaut;
    private ActionLangue actionLangueFrancais;
    private ActionLangue actionLangueAnglais;
    private ActionLangue actionLangueJaponais;
    private JMenuItem itemConversion;
    private JMenuItem itemTexte;
    private JMenuItem itemDiaporama;
    private JMenuItem itemBoutons;
    private JMenu menuFichier;
    private JMenu menuApplications;
    private JMenu menuLangues;
	private JMenu menuAide;
    RessourceManager ressourceManager;
    
    public Application() {
        this.setContentPane(new JDesktopPane());
        
        ressourceManager = RessourceManager.getRessourceManager(RessourceManager.MAIN_TRAD_FILE);
        // ****** Barre de menu ******
        JMenuBar barre = new JMenuBar();
        // ------ menu Fichier ------
        menuFichier = new JMenu();
        menuFichier.setMnemonic(KeyEvent.VK_F);
        actionQuitter = new ActionQuitter(this);
        JMenuItem quitter = new JMenuItem(actionQuitter);
        menuFichier.add(quitter);
        barre.add(menuFichier);
        this.setJMenuBar(barre);
        // ------ menu Applications ------
        menuApplications = new JMenu();
        menuApplications.setMnemonic(KeyEvent.VK_A);
        actionAfficherConversion = new ActionAfficherConversion(this);
        itemConversion = new JMenuItem(actionAfficherConversion);
        menuApplications.add(itemConversion);
        actionAfficherTexte = new ActionAfficherTexte(this);
        itemTexte = new JMenuItem(actionAfficherTexte);
        menuApplications.add(itemTexte);
        actionAfficherDiaporama = new ActionAfficherDiaporama(this);
        itemDiaporama = new JMenuItem(actionAfficherDiaporama);
        menuApplications.add(itemDiaporama);
        actionAfficherBoutons = new ActionAfficherBoutons(this);
        itemBoutons = new JMenuItem(actionAfficherBoutons);
        menuApplications.add(itemBoutons);
        barre.add(menuApplications);
        // ------ menu Langues ------
        menuLangues = new JMenu();
        menuLangues.setMnemonic(KeyEvent.VK_L);
        actionLangueParDefaut = new ActionLangue(Ressource.LANGUE_DEFAUT, Locale.getDefault(), this);
        JMenuItem itemLangueDefaut = new JMenuItem(actionLangueParDefaut);
        menuLangues.add(itemLangueDefaut);
        actionLangueFrancais = new ActionLangue(Ressource.LANGUE_FRANCAIS, Locale.FRENCH, this);
        JMenuItem itemLangueFrancais = new JMenuItem(actionLangueFrancais);
        menuLangues.add(itemLangueFrancais);
        actionLangueAnglais = new ActionLangue(Ressource.LANGUE_ANGLAIS, Locale.ENGLISH, this);
        JMenuItem itemLangueAnglais = new JMenuItem(actionLangueAnglais);
        menuLangues.add(itemLangueAnglais);
        actionLangueJaponais = new ActionLangue(Ressource.LANGUE_JAPONAIS, Locale.JAPAN, this);
        JMenuItem itemLangueJaponais = new JMenuItem(actionLangueJaponais);
        menuLangues.add(itemLangueJaponais);
        barre.add(menuLangues);
		// ------ menu Aide ------
		menuAide = new JMenu();
		menuAide.setMnemonic(KeyEvent.VK_A);
		actionCommentFaire = new ActionCommentFaire(this);
		JMenuItem itemCommentFaire = new JMenuItem(actionCommentFaire);
		menuAide.add(itemCommentFaire);
		actionConfigMenu = new ActionConfigMenu(this);
		JMenuItem itemConfigMenu = new JMenuItem(actionConfigMenu);
		menuAide.add(itemConfigMenu);
		barre.add(menuAide);
        // ****** Fin barre de menu ******
        
        // ****** Création des fenêtres ******
        // ------ fenêtre conversion ------
        conversion = new FenetreConversion(this, actionAfficherConversion);
        this.add(conversion);
        // ------ fenêtre texte ------
        texte = new FenetreTexte(this, actionAfficherTexte);
        this.add(texte);
        // ------ fenêtre diaporama ------
        diaporama = new FenetreDiaporama(this, actionAfficherDiaporama);
        this.add(diaporama);
        // ------ fenêtre boutons ------
        boutons = new FenetreBoutons(this,actionAfficherBoutons);
        this.add(boutons);

        // ****** Fin création fenêtres ******
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600,300);
        	
        setVisible(true);
        
        // Traduction
        ressourceManager.addTraduisible(this);
        traduire();
        majMenu();
    }

    public void enableConversion(boolean b) {
        actionAfficherConversion.setEnabled(b);
    }

    public void enableTexte(boolean b) {
        actionAfficherTexte.setEnabled(b);
    }

    public void enableDiaporama(boolean b) {
        actionAfficherDiaporama.setEnabled(b);
    }

    public void enableBoutons(boolean b) {
        actionAfficherBoutons.setEnabled(b);
    }

    public Action getActionAfficherConversion() {
        return actionAfficherConversion;
    }

    public Action getActionAfficherTexte() {
        return actionAfficherTexte;
    }

    public Action getActionAfficherDiaporama() {
        return actionAfficherDiaporama;
    }
    
	public void afficherBoutons() {
		boutons.setVisible(true);
	}
	public void afficherConversion() {
		conversion.setVisible(true);
	}
	public void afficherDiaporama() {
		diaporama.setVisible(true);
	}
	public void afficherTexte() {
		texte.setVisible(true);
	}

	@Override
	public void traduire() {
        JOptionPane.setDefaultLocale(RessourceManager.getLieu());

		setTitle(ressourceManager.getString(Ressource.TITRE));
		menuFichier.setText(ressourceManager.getString(Ressource.MENU_FICHIER));
		menuApplications.setText(ressourceManager.getString(Ressource.MENU_APPLICATIONS));
		menuLangues.setText(ressourceManager.getString(Ressource.MENU_LANGUES));
		menuAide.setText(ressourceManager.getString(Ressource.MENU_AIDE));
	}

    public void majMenu() {
        PropertieManager pm = PropertieManager.getInstance();
        itemConversion.setVisible(
                !pm.getProperty(Propertie.CONVERSION, DialogConfigMenu.OPTION_AUTO).equals(DialogConfigMenu.OPTION_CACHE));
        itemTexte.setVisible(
                !pm.getProperty(Propertie.SAISIE    , DialogConfigMenu.OPTION_AUTO).equals(DialogConfigMenu.OPTION_CACHE));
        itemDiaporama.setVisible(
                !pm.getProperty(Propertie.DIAPORAMA , DialogConfigMenu.OPTION_AUTO).equals(DialogConfigMenu.OPTION_CACHE));
        itemBoutons.setVisible(
                !pm.getProperty(Propertie.BOUTONS   , DialogConfigMenu.OPTION_AUTO).equals(DialogConfigMenu.OPTION_CACHE));
    }

	public static void main(String[] args) {
        if (args.length > 0)
            PropertieManager.getInstance().setLogin(args[0]);
        PropertieManager.getInstance().chargerPreferences();

        SwingUtilities.invokeLater(Application::new);
    }
}
