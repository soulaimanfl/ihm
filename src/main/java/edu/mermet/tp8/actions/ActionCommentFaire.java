package edu.mermet.tp8.actions;

import java.awt.event.ActionEvent;

import javax.swing.SwingUtilities;

import edu.mermet.tp8.Application;
import edu.mermet.tp8.Ressource;
import edu.mermet.tp8.dialogs.DialogCommentFaire;

public class ActionCommentFaire extends AbstractActionTraduisible {

	public ActionCommentFaire(Application appli) {
		super(Ressource.ACTION_COMMENT_FAIRE, appli);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		SwingUtilities.invokeLater(DialogCommentFaire::new);
	}
	
}
