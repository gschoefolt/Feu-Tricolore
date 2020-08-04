package trafficlight.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import trafficlight.observer.Observer;
import trafficlight.model.Light;

public abstract class GUI extends JInternalFrame implements Observer {
    private static int id_stat = 0;
    private int id;
    private Light light;
    private JLabel jlInfo;  // JLabel montrant l'état du feu (graphiquement ou texte)

    /**
     * Constructeur de la classe
     * @param f le feu associé à la vue
     */
    public GUI(Light f) {
        id_stat++;
        this.id = id_stat;

        light = f;
        light.addObserver(this);

        // JLabel donnant l'état du feu
        jlInfo = new JLabel();
        jlInfo.setFont(GUI_menu.f);
        jlInfo.setPreferredSize(new Dimension(150, 60));
        jlInfo.setHorizontalAlignment(SwingConstants.CENTER);
        jlInfo.setOpaque(true);
	    
        // Boutons
        JButton jbOnOff = new JButton("Marche/Arret");
        JButton jbColor = new JButton("Changer couleur");
        jbOnOff.setFont(GUI_menu.f);
        jbColor.setFont(GUI_menu.f);
	
        // Panneaux
        JPanel jpInfo = new JPanel(new BorderLayout());
        jpInfo.add(jlInfo);
        jpInfo.setFont(GUI_menu.f);
        
        JPanel jpButtons = new JPanel(new BorderLayout());
        jpButtons.add(jbColor, BorderLayout.EAST);
        jpButtons.add(jbOnOff, BorderLayout.WEST);
        jpButtons.setFont(GUI_menu.f);

        getContentPane().add(jpInfo, BorderLayout.NORTH);
        getContentPane().add(jpButtons, BorderLayout.SOUTH);
	    
        // Fenêtre
        this.setLocation(150, 150);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.pack();
        setVisible(true);
        
        // Comportement
        jbColor.addActionListener(e -> changeColor());
        jbOnOff.addActionListener(e -> startStop());
        
        this.setFont(GUI_menu.f);

        // Mise à jour initiale de l'état du feu
	    update();
    }


    /**
     * Autre constructeur de la classe pour les vues piétonnes et tourner à droite
     */
    public GUI() {
        //this.id = id;

        // JLabel donnant l'état du feu
        jlInfo = new JLabel();
        jlInfo.setFont(GUI_menu.f);
        jlInfo.setPreferredSize(new Dimension(250, 80));
        jlInfo.setHorizontalAlignment(SwingConstants.CENTER);
        jlInfo.setOpaque(true);        

        JPanel jpInfo = new JPanel(new BorderLayout());
        jpInfo.add(jlInfo);
        jpInfo.setFont(GUI_menu.f);

        getContentPane().add(jpInfo, BorderLayout.NORTH);

        // Fenêtre
        this.setLocation(150, 150);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.pack();
        setVisible(true);

        this.setFont(GUI_menu.f);
    }


    /**
     * @return l'objet JLabel correspondant à l'état du feu
     */
    protected JLabel getJlInfo() {
        return jlInfo;
    }


    /**
     * @return le feu associé à la vue
     */
    public Light getLight() {
        return light;
    }


    /**
     * Définit le feu pour la vue
     * @param f le feu a associé
     */
    public void setLight(Light f) {
        this.light = f;
        this.light.addObserver(this);
        
        // Mise à jour initiale de l'état du feu
	    update();
    }

    
    /**
     * Permet de démarrer ou d'arrêter le feu
     */
    private void startStop() {
        light.startStop();
    }


    /**
     * Permet de changer la couleur du feu
     */
    private void changeColor() {
        light.change();
    }


    /**
     * @return l'identifiant unique correspondant à la vue
     */
    public int getId(){
        return this.id;
    }
    
}
