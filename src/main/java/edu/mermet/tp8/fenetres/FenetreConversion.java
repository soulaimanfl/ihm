package edu.mermet.tp8.fenetres;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;

import edu.mermet.tp8.Application;
import edu.mermet.tp8.Ressource;
import edu.mermet.tp8.RessourceManager;
import edu.mermet.tp8.actions.AbstractActionTraduisible;

/**
 *
 * @author brunomermet
 */
public class FenetreConversion extends AbstractFenetreInterne {
    private JTextField champCelsius;
    private JTextField champFarenheit;
    private JButton boutonConvertir;
    private Action actionConvertir;
    private boolean celsiusAFocus;
    public FenetreConversion(Application appli, Action action) {
        super(
			appli, action, 
			RessourceManager.getRessourceManager(RessourceManager.MAIN_TRAD_FILE).getString(Ressource.FEN_CONV_TITRE)
		);
        
        this.setSize(new Dimension(100,50));
        this.setLayout(new GridLayout(3,1));

        JPanel ligneCelsius = new JPanel();
        ligneCelsius.setLayout(new FlowLayout(FlowLayout.TRAILING));
        JLabel labCelsius = new JLabel("Celsius :");
        champCelsius = new JTextField(15);
        labCelsius.setLabelFor(champCelsius);

        ImageIcon question;
        try {
            question = new ImageIcon(getClass().getClassLoader().getResource("./images/question.png"));
        } catch(Exception e) {
            question = null;
        }

        JButton btnCelsius = new JButton(question);
        btnCelsius.setPreferredSize(new Dimension(16, 16));
        btnCelsius.addActionListener(ae -> afficherAide(Ressource.FEN_CONV_AIDE_CELSIUS));
        ligneCelsius.add(labCelsius);
        ligneCelsius.add(champCelsius);
        ligneCelsius.add(btnCelsius);
        this.add(ligneCelsius);
        celsiusAFocus = true;
        champCelsius.addFocusListener(new EcouteurFocus(true));

        JPanel ligneFarenheit = new JPanel();
        ligneFarenheit.setLayout(new FlowLayout(FlowLayout.TRAILING));
        JLabel labFarenheit = new JLabel("Farenheit :");
        champFarenheit = new JTextField(15);
        JButton btnFarenheit = new JButton(question);
        btnFarenheit.setPreferredSize(new Dimension(16, 16));
        btnFarenheit.addActionListener(ae -> afficherAide(Ressource.FEN_CONV_AIDE_FARENHEIT));
        labFarenheit.setLabelFor(champFarenheit);
        ligneFarenheit.add(labFarenheit);
        ligneFarenheit.add(champFarenheit);
        ligneFarenheit.add(btnFarenheit);
        this.add(ligneFarenheit);
        champFarenheit.addFocusListener(new EcouteurFocus(false));
        JPanel ligneValider = new JPanel();
        ligneValider.setLayout(new FlowLayout(FlowLayout.CENTER));
        actionConvertir = new ActionConvertir(appli);
        boutonConvertir = new JButton(actionConvertir);
        ligneValider.add(boutonConvertir);
        this.add(ligneValider);
        pack();
        getRootPane().setDefaultButton(boutonConvertir);
        this.addMouseListener(new ControleSouris());

        traduire();
    }

    public void afficherAide(Ressource res) {
        RessourceManager rm = RessourceManager.getRessourceManager(RessourceManager.MAIN_TRAD_FILE);
        JOptionPane.showMessageDialog(
                this,  rm.getString(res),
                rm.getString(Ressource.FEN_AIDE_TITRE), JOptionPane.INFORMATION_MESSAGE
        );
    }

    private class EcouteurFocus implements FocusListener {
        private boolean aStocker;

        public EcouteurFocus(boolean b) {
            aStocker = b;
        }

        @Override
        public void focusGained(FocusEvent fe) {
            celsiusAFocus = aStocker;
        }

        @Override
        public void focusLost(FocusEvent fe) {
            return;
        }
    }

    private class ActionConvertir extends AbstractActionTraduisible {

        public ActionConvertir(Application appli) {
            super(Ressource.FEN_CONV_CONVERTIR, appli);
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            double tempCelsius = 0;
            double tempFarenheit = 0;
            if (celsiusAFocus) {
                try {
                    tempCelsius = Double.parseDouble(champCelsius.getText());
                tempFarenheit = 9./5*tempCelsius+32;
                champFarenheit.setText(""+tempFarenheit);
                }
                catch (NumberFormatException nfe) {
                    champFarenheit.setText(
                    	RessourceManager.getRessourceManager(RessourceManager.MAIN_TRAD_FILE)
							.getString(Ressource.FEN_CONV_ERR_FORMAT)
                    );
                }
                }
            else {
                try {
                    tempFarenheit = Double.parseDouble(champFarenheit.getText());
                    tempCelsius = (tempFarenheit - 32) *5./9;
                    champCelsius.setText(""+tempCelsius);
                }
                catch (NumberFormatException nfe) {
                    champCelsius.setText(
                    	RessourceManager.getRessourceManager(RessourceManager.MAIN_TRAD_FILE)
							.getString(Ressource.FEN_CONV_ERR_FORMAT)
                    );
                }
                
            }
        }
    }

    private class ControleSouris extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            if (e.isPopupTrigger()) {
                JPopupMenu popup = new JPopupMenu();

                JMenuItem itemCelsius = new JMenuItem("Celsius");
                itemCelsius.addActionListener(ae -> afficherAide(Ressource.FEN_CONV_AIDE_CELSIUS));

                JMenuItem itemFarenheit = new JMenuItem("Farenheit");
                itemFarenheit.addActionListener(ae -> afficherAide(Ressource.FEN_CONV_AIDE_FARENHEIT));

                popup.add(itemCelsius);
                popup.add(itemFarenheit);
                popup.show(e.getComponent(), e.getX(), e.getY());
            }
        }
    }

	@Override
	public void traduire() {
        RessourceManager rm = RessourceManager.getRessourceManager(RessourceManager.MAIN_TRAD_FILE);

		setTitle(rm.getString(Ressource.FEN_CONV_TITRE));
        champCelsius.setToolTipText(rm.getString(Ressource.FEN_CONV_AIDE_CELSIUS));
        champFarenheit.setToolTipText(rm.getString(Ressource.FEN_CONV_AIDE_FARENHEIT));
    }

}
