package edu.mermet.tp8.actions;

import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.KeyStroke;

import edu.mermet.tp8.Application;
import edu.mermet.tp8.Ressource;

public class ActionAfficherDiaporama extends AbstractActionTraduisible {
    public ActionAfficherDiaporama(Application application) {
		super(Ressource.ACTION_DIAPORAMA, application);
		
        putValue(Action.ACCELERATOR_KEY,KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_DOWN_MASK));
        putValue(Action.MNEMONIC_KEY,KeyEvent.VK_D);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        application.afficherDiaporama();
    }
}
