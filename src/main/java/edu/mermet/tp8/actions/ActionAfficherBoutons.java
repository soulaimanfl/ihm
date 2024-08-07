package edu.mermet.tp8.actions;

import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.KeyStroke;

import edu.mermet.tp8.Application;
import edu.mermet.tp8.Ressource;

public class ActionAfficherBoutons extends AbstractActionTraduisible {
    public ActionAfficherBoutons(Application application) {
        super(Ressource.ACTION_BOUTONS, application);
        
        putValue(Action.ACCELERATOR_KEY,KeyStroke.getKeyStroke(KeyEvent.VK_B, InputEvent.CTRL_DOWN_MASK));
        putValue(Action.MNEMONIC_KEY,KeyEvent.VK_B);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        application.afficherBoutons();
    }
    
}
