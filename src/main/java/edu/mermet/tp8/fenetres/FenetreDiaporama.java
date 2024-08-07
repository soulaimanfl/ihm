package edu.mermet.tp8.fenetres;

import java.awt.BorderLayout;
import java.awt.Image;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import edu.mermet.tp8.Application;
import edu.mermet.tp8.Ressource;
import edu.mermet.tp8.RessourceManager;

/**
 *
 * @author brunomermet
 */
public class FenetreDiaporama extends AbstractFenetreInterne {
    ImageIcon [] images;
    String[] textes;
    JLabel affichage;
    Defilement defilement;
    private int indiceCourant = 0;
    
    public FenetreDiaporama(Application appli, Action action) {
        super(
			appli, action, 
			RessourceManager.getRessourceManager(RessourceManager.MAIN_TRAD_FILE).getString(Ressource.FEN_DIAPO_TITRE)
		);
        
        images = new ImageIcon[3];
        try {
            images[0] = new ImageIcon(new ImageIcon(new URL("https://media.istockphoto.com/id/1446633520/fr/photo/petit-%C3%A2ne-nord-am%C3%A9ricain-blanc-brun-debout-sur-une-prairie-fleurie-sur-fond-darbres-verts.jpg?s=612x612&w=0&k=20&c=7S8BPpbDaICKw1Iqk2S_RH76rozt8-4-Ci3TZbI_1_c=")).getImage().getScaledInstance(300, -1, Image.SCALE_DEFAULT));
            images[1] = new ImageIcon(new ImageIcon(new URL("https://media.istockphoto.com/id/1385186062/fr/photo/jeune-%C3%A2ne-mangeant-de-lherbe.jpg?s=612x612&w=0&k=20&c=a4WuOlck6wzYAqqq1W-_vjZ1AuL7yI0wIKa9g6ydIPE=")).getImage().getScaledInstance(300, -1, Image.SCALE_DEFAULT));
            images[2] = new ImageIcon(new ImageIcon(new URL("https://media.istockphoto.com/id/106465758/fr/photo/samuser-les-burros.jpg?s=612x612&w=0&k=20&c=FpbJwFvHcsSPQVr5NHjM11-5LsabaB14DwObqJbUAA0=")).getImage().getScaledInstance(300, -1, Image.SCALE_DEFAULT));
        } catch (MalformedURLException ex) {
            images[0] = null;
            images[1] = null;
            images[2] = null;
        }
        
        JPanel panneauTexte = new JPanel();
        affichage = new JLabel();
        panneauTexte.add(affichage);
        affichage.setIcon(images[0]);
        JScrollPane ascenseurs = new JScrollPane(affichage);
        add(ascenseurs,BorderLayout.CENTER);
        setSize(300,300);
        
    }
    class Defilement implements Runnable {
        private boolean arrete;
        public Defilement() {
            arrete = false;
        }
        @Override
        public void run() {
            while (!arrete) {
                try {
                    Thread.sleep(2000);
                }
                catch (InterruptedException iex) {
                }
                indiceCourant++;
                indiceCourant = indiceCourant % 3;
                affichage.setIcon(images[indiceCourant]);
            }
        }
        public void arreter() {
            arrete = true;
        }
    }
    
    @Override
    public void setVisible(boolean b) {
        super.setVisible(b);
        if (b) {
            defilement = new Defilement();
            Thread thread = new Thread(defilement);
            thread.start();
        }
        else {
            if (defilement != null) {
                defilement.arreter();
            }
        }
    }

	@Override
	public void traduire() {
		setTitle(
			RessourceManager.getRessourceManager(RessourceManager.MAIN_TRAD_FILE).getString(Ressource.FEN_DIAPO_TITRE)
		);
	}

}
