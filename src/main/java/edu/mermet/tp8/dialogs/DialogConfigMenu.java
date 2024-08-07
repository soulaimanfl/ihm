package edu.mermet.tp8.dialogs;

import edu.mermet.tp8.Application;
import edu.mermet.tp8.Propertie;
import edu.mermet.tp8.PropertieManager;
import edu.mermet.tp8.Ressource;
import edu.mermet.tp8.RessourceManager;
import edu.mermet.tp8.Traduisible;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class DialogConfigMenu extends JDialog implements Traduisible {
    public static final String OPTION_AUTO = "1";
    public static final String OPTION_AFFICHE = "2";
    public static final String OPTION_CACHE = "3";
    private Application appli;
    private PanelCongif pConversion;
    private PanelCongif pSaisie;
    private PanelCongif pDiaporama;
    private PanelCongif pBoutons;
    private JButton btnValider;
    private JButton btnAnnuler;

    public DialogConfigMenu(Application appli) {
        this.appli = appli;
        setLayout(new BorderLayout());

        JPanel pLstConfig = new JPanel(new GridLayout(4, 1));
        PropertieManager pm = PropertieManager.getInstance();
        pConversion = new PanelCongif(Ressource.FEN_CONV_TITRE, pm.getProperty(Propertie.CONVERSION, OPTION_AUTO));
        pSaisie     = new PanelCongif(Ressource.FEN_TEXTE_TITRE, pm.getProperty(Propertie.SAISIE, OPTION_AUTO));
        pDiaporama  = new PanelCongif(Ressource.FEN_DIAPO_TITRE, pm.getProperty(Propertie.DIAPORAMA, OPTION_AUTO));
        pBoutons    = new PanelCongif(Ressource.FEN_BOUTONS_TITRE, pm.getProperty(Propertie.BOUTONS, OPTION_AUTO));

        btnValider = new JButton("Valider");
        btnAnnuler = new JButton("Annuler");

        JPanel pBas = new JPanel();
        pBas.add(btnValider);
        pBas.add(btnAnnuler);

        pLstConfig.add(pConversion);
        pLstConfig.add(pSaisie);
        pLstConfig.add(pDiaporama);
        pLstConfig.add(pBoutons);

        add(pLstConfig, BorderLayout.CENTER);
        add(pBas, BorderLayout.SOUTH);

        pack();
        setVisible(true);

        btnValider.addActionListener(ae -> actionValider());
        btnAnnuler.addActionListener(ae -> dispose());

        RessourceManager.getRessourceManager(RessourceManager.MAIN_TRAD_FILE).addTraduisible(this);
        traduire();
    }

    public void actionValider() {
        PropertieManager pm = PropertieManager.getInstance();
        pm.setProperty(Propertie.CONVERSION, pConversion.getIdSelec());
        pm.setProperty(Propertie.SAISIE    , pSaisie    .getIdSelec());
        pm.setProperty(Propertie.DIAPORAMA , pDiaporama .getIdSelec());
        pm.setProperty(Propertie.BOUTONS   , pBoutons   .getIdSelec());
        pm.majPreferences();

        appli.majMenu();
        dispose();
    }

    @Override
    public void traduire() {
        RessourceManager rm = RessourceManager.getRessourceManager(RessourceManager.MAIN_TRAD_FILE);
        setTitle(rm.getString(Ressource.FEN_CONFIGMENU_TITRE));
        btnValider.setText(rm.getString(Ressource.BTN_VALIDER));
        btnAnnuler.setText(rm.getString(Ressource.BTN_ANNULER));

        pConversion.traduire();
        pSaisie.traduire();
        pDiaporama.traduire();
        pBoutons.traduire();
        pack();
    }

    private class PanelCongif extends JPanel {
        Ressource titre;
        JLabel lblTitre;
        JRadioButton rbAuto;
        JRadioButton rbAffiche;
        JRadioButton rbCacher;

        public PanelCongif(Ressource titre, String idSelec) {
            this.titre = titre;
            setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

            lblTitre = new JLabel();
            rbAuto = new JRadioButton();
            rbAffiche = new JRadioButton();
            rbCacher = new JRadioButton();

            ButtonGroup bg = new ButtonGroup();
            bg.add(rbAuto);
            bg.add(rbAffiche);
            bg.add(rbCacher);

            switch (idSelec) {
                default -> rbAuto.setSelected(true);
                case OPTION_AFFICHE -> rbAffiche.setSelected(true);
                case OPTION_CACHE -> rbCacher.setSelected(true);
            }

            add(Box.createHorizontalGlue());
            add(lblTitre);
            add(rbAuto);
            add(rbAffiche);
            add(rbCacher);
        }

        public String getIdSelec() {
            if (rbAffiche.isSelected())
                return OPTION_AFFICHE;

            if (rbCacher.isSelected())
                return OPTION_CACHE;

            return OPTION_AUTO;
        }

        public void traduire() {
            RessourceManager rm = RessourceManager.getRessourceManager(RessourceManager.MAIN_TRAD_FILE);
            lblTitre.setText(rm.getString(titre));
            rbAuto.setText(rm.getString(Ressource.CONFIG_AUTO));
            rbAffiche.setText(rm.getString(Ressource.CONFIG_AFFICHE));
            rbCacher.setText(rm.getString(Ressource.CONFIG_CACHE));
        }
    }
}
