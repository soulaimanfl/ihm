package edu.mermet.tp8.fenetres;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

import edu.mermet.tp8.Application;
import edu.mermet.tp8.Ressource;
import edu.mermet.tp8.RessourceManager;
import edu.mermet.tp8.actions.AbstractActionTraduisible;

/**
 *
 * @author brunomermet
 */
public class FenetreTexte extends AbstractFenetreInterne {
    private JCheckBox gras;
    private JCheckBox rouge;
    private Action actionGras;
    private Action actionRouge;
    private JTextArea texte;
    private JMenu style;
    public FenetreTexte(Application appli, Action action) {
        super(
			appli, action, 
			RessourceManager.getRessourceManager(RessourceManager.MAIN_TRAD_FILE).getString(Ressource.FEN_TEXTE_TITRE)
		);
        
        actionGras = new ActionGras(appli);
        gras = new JCheckBox(actionGras);
        actionRouge = new ActionRouge(appli);
        rouge = new JCheckBox(actionRouge);
        JPanel panneauBouton = new JPanel();
        panneauBouton.add(gras);
        panneauBouton.add(rouge);
        add(panneauBouton,BorderLayout.NORTH);
        texte = new JTextArea(6,20);
        texte.setLineWrap(true);
        texte.setWrapStyleWord(true);
        JScrollPane panneauTexte = new JScrollPane(texte);
        add(panneauTexte,BorderLayout.CENTER);
        JMenuBar barre = new JMenuBar();
        style = new JMenu();
        JMenuItem itemGras = new JCheckBoxMenuItem(actionGras);
        style.add(itemGras);
        JMenuItem itemRouge = new JCheckBoxMenuItem(actionRouge);
        style.add(itemRouge);
        barre.add(style);
        this.setJMenuBar(barre);
        pack();
        traduire();
    }

    

    private class ActionGras extends AbstractActionTraduisible {
        private boolean gras;
        public ActionGras(Application appli) {
            super(Ressource.FEN_TEXTE_GRAS, appli);
            gras = false;
            putValue(Action.ACCELERATOR_KEY,KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.CTRL_DOWN_MASK));
            putValue(Action.SELECTED_KEY,false);
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            Font police = texte.getFont();
            if (!gras) {
                police = police.deriveFont(Font.BOLD);//|Font.ITALIC);
                //police = police.deriveFont((float)24.0);
            }
            else {
                police = police.deriveFont(Font.PLAIN);
            }
            gras = !gras;
            putValue(Action.SELECTED_KEY,gras);
            texte.setFont(police);
        }
    }

    private class ActionRouge extends AbstractActionTraduisible {
        private boolean rouge;
        public ActionRouge(Application appli) {
            super(Ressource.FEN_TEXTE_ROUGE, appli);
            rouge = false;
            putValue(Action.ACCELERATOR_KEY,KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK));
            putValue(Action.SELECTED_KEY,false);

        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (!rouge) {
                texte.setForeground(Color.RED);
            }
            else {
                texte.setForeground(Color.BLACK);
            }
            rouge = !rouge;
        }
    }

	@Override
	public void traduire() {
		setTitle(
			RessourceManager.getRessourceManager(RessourceManager.MAIN_TRAD_FILE).getString(Ressource.FEN_TEXTE_TITRE)
		);
		style.setText(
			RessourceManager.getRessourceManager(RessourceManager.MAIN_TRAD_FILE).getString(Ressource.FEN_TEXTE_STYLE)
		);
	}

}
