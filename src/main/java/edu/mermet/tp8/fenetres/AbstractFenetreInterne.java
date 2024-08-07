package edu.mermet.tp8.fenetres;

import javax.swing.Action;
import javax.swing.JInternalFrame;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import edu.mermet.tp8.Application;
import edu.mermet.tp8.RessourceManager;
import edu.mermet.tp8.Traduisible;

/**
 *
 * @author brunomermet
 */
public abstract class AbstractFenetreInterne extends JInternalFrame implements Traduisible {
    private Action action;
	protected Application appli;
    public AbstractFenetreInterne(Application monAppli, Action monAction, String nom) {
        super(nom, true,true,true,true);
        action = monAction;
		appli = monAppli;
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.addInternalFrameListener(new EcouteurFenetre());
        
        RessourceManager.getRessourceManager(RessourceManager.MAIN_TRAD_FILE).addTraduisible(this);
    }
    
    private class EcouteurFenetre extends InternalFrameAdapter {
        @Override
        public void internalFrameClosing(InternalFrameEvent ife) {
            action.setEnabled(true);
        }
        @Override
        public void internalFrameActivated(InternalFrameEvent ife) {
        	action.setEnabled(false);
        }
    }
}
