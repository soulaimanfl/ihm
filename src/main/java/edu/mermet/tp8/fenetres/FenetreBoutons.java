package edu.mermet.tp8.fenetres;

import java.awt.FlowLayout;

import javax.swing.Action;
import javax.swing.JButton;

import edu.mermet.tp8.Application;
import edu.mermet.tp8.Ressource;
import edu.mermet.tp8.RessourceManager;

/**
 *
 * @author brunomermet
 */
public class FenetreBoutons extends AbstractFenetreInterne {
    private JButton boutonTexte;
    private JButton boutonDiaporama;
    private JButton boutonDegres;
    
    public FenetreBoutons(Application appli, Action action) {
        super(
			appli, action, 
			RessourceManager.getRessourceManager(RessourceManager.MAIN_TRAD_FILE)
				.getString(Ressource.FEN_BOUTONS_TITRE)
		);
        
        setLayout(new FlowLayout());
        boutonTexte = new JButton(appli.getActionAfficherTexte());
        boutonDiaporama = new JButton(appli.getActionAfficherDiaporama());
        boutonDegres = new JButton(appli.getActionAfficherConversion());
        add(boutonDegres);
        add(boutonTexte);
        add(boutonDiaporama);
        pack();
    }

	@Override
	public void traduire() {
		setTitle(
			RessourceManager.getRessourceManager(RessourceManager.MAIN_TRAD_FILE)
				.getString(Ressource.FEN_BOUTONS_TITRE)
		);
	}

}
