package edu.mermet.tp8.dialogs;

import java.util.Collections;

import java.awt.BorderLayout;
import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import edu.mermet.tp8.Ressource;
import edu.mermet.tp8.RessourceManager;
import edu.mermet.tp8.Traduisible;

public class DialogCommentFaire extends JDialog implements Traduisible {
	private String[] keys;

	private JList<String> lstTitres;
	private JEditorPane epTexte;

	public DialogCommentFaire() {
		setSize(800, 500);
		setLocationRelativeTo(null);

		keys = RessourceManager.getRessourceManager(RessourceManager.HOWTO_TITRES_FILE).getKeys();

		lstTitres = new JList<>();
		lstTitres.addListSelectionListener(ae -> afficherExplication());
		epTexte = new JEditorPane();
		epTexte.setContentType("text/html");
		epTexte.setEditable(false);

		JSplitPane splitpane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, lstTitres, epTexte);

		setContentPane(splitpane);
		setVisible(true);

		RessourceManager.getRessourceManager(RessourceManager.HOWTO_TITRES_FILE).addTraduisible(this);
		traduire();
	}

	public void afficherExplication() {
		int ind = lstTitres.getSelectedIndex();

		if(ind < 0 || ind >= keys.length)
			return;

		epTexte.setText(
			RessourceManager.getRessourceManager(RessourceManager.HOWTO_TEXTES_FILE)
				.getString(keys[ind])
		);
	}

	@Override
	public void traduire() {
		setTitle(
			RessourceManager.getRessourceManager(RessourceManager.MAIN_TRAD_FILE).getString(Ressource.FEN_COMMENTFAIRE_TITRE)
		);

		int ind = lstTitres.getSelectedIndex();
		DefaultListModel<String> lstModel = new DefaultListModel<>();
		for (String s : keys) 
			lstModel.addElement(RessourceManager.getRessourceManager(RessourceManager.HOWTO_TITRES_FILE).getString(s));
		
		lstTitres.setModel(lstModel);
		lstTitres.setSelectedIndex(ind);
	
		afficherExplication();
	}
}
