package edu.mermet.tp8.actions;

import java.awt.event.ActionEvent;
import java.util.Locale;

import edu.mermet.tp8.Application;
import edu.mermet.tp8.Ressource;
import edu.mermet.tp8.RessourceManager;

/**
*
* @author lucaslemarchand
*/
public class ActionLangue extends AbstractActionTraduisible {
	private Locale lieu;
	
	public ActionLangue(Ressource clef, Locale lieu, Application appli) {
		super(clef, appli);
		this.lieu = lieu;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		application.setVisible(false);
		RessourceManager.definirLieu(lieu);
		application.setVisible(true);
	}
	
}
