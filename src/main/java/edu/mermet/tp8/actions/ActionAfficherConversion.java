package edu.mermet.tp8.actions;

import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.KeyStroke;

import edu.mermet.tp8.Application;
import edu.mermet.tp8.Ressource;

public class ActionAfficherConversion extends AbstractActionTraduisible {
	public ActionAfficherConversion(Application application) {
		super(Ressource.ACTION_CONVERSION, application);
		
        putValue(Action.ACCELERATOR_KEY,KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK));
        putValue(Action.MNEMONIC_KEY,KeyEvent.VK_C);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        application.afficherConversion();
    }
}
