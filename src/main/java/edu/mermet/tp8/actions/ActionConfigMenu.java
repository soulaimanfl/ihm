package edu.mermet.tp8.actions;

import edu.mermet.tp8.dialogs.DialogConfigMenu;
import java.awt.event.ActionEvent;

import edu.mermet.tp8.Application;
import edu.mermet.tp8.Ressource;
import javax.swing.SwingUtilities;

public class ActionConfigMenu extends AbstractActionTraduisible {
	public ActionConfigMenu(Application appli) {
		super(Ressource.ACTION_CONFIG_MENU, appli);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		SwingUtilities.invokeLater(() -> new DialogConfigMenu(application));
	}
}
