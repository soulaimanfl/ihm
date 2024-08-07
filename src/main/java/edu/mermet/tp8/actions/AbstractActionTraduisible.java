package edu.mermet.tp8.actions;

import javax.swing.AbstractAction;

import edu.mermet.tp8.Application;
import edu.mermet.tp8.Ressource;
import edu.mermet.tp8.RessourceManager;
import edu.mermet.tp8.Traduisible;

/**
*
* @author lucaslemarchand
*/
public abstract class AbstractActionTraduisible extends AbstractAction implements Traduisible {
	private static final long serialVersionUID = 1L;
	protected Ressource clef;
	protected Application application;
	
	public AbstractActionTraduisible(Ressource clef, Application appli) {
		this.clef = clef;
		this.application = appli;
		
		// traduction
		RessourceManager.getRessourceManager(RessourceManager.MAIN_TRAD_FILE).addTraduisible(this);
		traduire();
	}
	
	public void traduire() {
		putValue(NAME, RessourceManager.getRessourceManager(RessourceManager.MAIN_TRAD_FILE).getString(clef));
	}
}
