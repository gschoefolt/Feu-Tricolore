package trafficlight.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import trafficlight.model.Light;
import trafficlight.controller.GUI_manager;
import trafficlight.controller.LightManager;

public class GUI_menu extends JFrame {
    protected static Font f = new Font("Arial", Font.PLAIN, 22);
    private GUI_manager viewsManager;
    private LightManager lightManager;
    private JMenu jmenuRemoveLight;
    private JMenu jmenuRemoveView;
    private JMenu jmenuSides;
    private JMenu jmenuDeco;
    private JMenu jmenuCreateView;
    
    /**
     * Constructeur de la classe
     * Initialise un JFrame comportant un JMenuBar comportant quatres JMenu
     * 1) "Créer fenêtre/feu", 2) "Supprimer", 3) "Décorer" et 4) "Changer de rive"
     * 
     * 1) Deux sous-menus pour la création de feu et la création d'une vue pour un feu donné
     * 2) Deux sous-menus pour la suppression d'un feu ou la suppression d'une vue pour un feu donné
     * 3) Décoration d'une vue pour un feu donné
     * 4) Changer de rive pour un feu donné
    */
    public GUI_menu() {
        /* Création des managers */
        viewsManager = GUI_manager.getInstance();
        lightManager = LightManager.getInstance();


        /* Barre de menu principale */
        JMenuBar jmenuBar = new JMenuBar();
        jmenuBar.setFont(f);


        /* Onglet de création des vues et des fenêtres */
        JMenu jmenuCreate = new JMenu(" Créer fenêtre/feu ");
        jmenuCreate.setFont(f);
        jmenuBar.add(jmenuCreate);


        /* JMenuItem pour la création de feu(x) dans l'onglet de création */
        JMenuItem jmenuCreateLight = new JMenuItem(" Feu ");
        jmenuCreateLight.setFont(f);
        jmenuCreate.add(jmenuCreateLight);
        jmenuCreateLight.addActionListener(e -> createLight());


        /* JMenu pour la création des vues dans l'onglet de création */
        jmenuCreateView = new JMenu(" Vue ");
        jmenuCreateView.setFont(f);
        jmenuCreate.add(jmenuCreateView);

       
        /* JMenu pour la suppression des vues et des feux */
        JMenu jmenuRemove = new JMenu(" Supprimer ");
        jmenuRemove.setFont(f);
        jmenuBar.add(jmenuRemove);

        jmenuRemoveLight = new JMenu(" Feu(x) ");
        jmenuRemoveLight.setFont(f);
        jmenuRemove.add(jmenuRemoveLight);

        jmenuRemoveView = new JMenu(" Vue(s) ");
        jmenuRemoveView.setFont(f);
        jmenuRemove.add(jmenuRemoveView);


        /* JMenu pour la décoration des vues */
        jmenuDeco = new JMenu(" Décorer ");
        jmenuDeco.setFont(f);
        jmenuBar.add(jmenuDeco);


        /* JMenu pour le changement de rives */
        jmenuSides = new JMenu(" Rives ");
        jmenuSides.setFont(f);
        jmenuBar.add(jmenuSides);


        /* Personnalisation de l'apparence du JFrame */
        this.setJMenuBar(jmenuBar);
        this.setContentPane(new JDesktopPane());
        this.getContentPane().setBackground(Color.white);
            
        this.setSize(680, 512);
        this.setLocation(300, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }


    /**
     * Crée un nouveau feu en appelant le gestionnaire des feux
     * @see LightManager.createLight()
     * @see GUI_menu.addLightToMenus(String title)
     */
    private void createLight() {
        addLightToMenus(lightManager.createLight().getName());
    }


    /**
     * Ajoute des JMenu ou JMenuItem aux menus de décoration, suppression, création et rives
     * correspondant au feu tout juste créé
     * @param light Nom du feu 
     */
    private void addLightToMenus(String light) {
        /* Ajout du feu au menu décoration */
        JMenu jmDeco = new JMenu(light);
        jmDeco.setFont(f);
        jmenuDeco.add(jmDeco);


        /* Ajout du feu au menu suppression sous le JMenu "Feu(x)" */
        JMenuItem jmiRemove = new JMenuItem(light);
        jmiRemove.setFont(f);
        jmenuRemoveLight.add(jmiRemove);


        /* Ajout du feu au menu suppresion sous le JMenu "Vue(s)" */
        JMenu jmRemove = new JMenu(light);
        jmRemove.setFont(f);
        jmenuRemoveView.add(jmRemove);


        /* Ajout du feu au menu création sous le JMenu "Vue(s)" */
        JMenu jmCreate = new JMenu(light);
        jmCreate.setFont(f);

        JMenuItem jmenuItemGraphic = new JMenuItem("GUI graphique");        
        JMenuItem jmenuItemText = new JMenuItem("GUI texte");
        jmenuItemGraphic.setFont(f);
        jmenuItemText.setFont(f);

        jmCreate.add(jmenuItemGraphic);
        jmCreate.add(jmenuItemText);

        jmenuCreateView.add(jmCreate);


        /* Ajout du feu au menu des rives */
        JMenu jmRives = new JMenu(light);
        jmRives.setFont(f);

        JMenuItem jmiStras = new JMenuItem("Strasbourg (Actuel)");
        jmiStras.setFont(f);
        JMenuItem jmiKehl = new JMenuItem("Kehl");
        jmiKehl.setFont(f);

        jmRives.add(jmiStras);
        jmRives.add(jmiKehl);
        jmenuSides.add(jmRives);


        /* Ajout des évènements de clique */

        // Clique pour créer une vue graphique
        jmenuItemGraphic.addActionListener(e -> createGraphicalGUI(jmDeco, jmRemove)); 

        // Clique pour créer une vue textuelle
        jmenuItemText.addActionListener(e -> createTextualGUI(jmDeco, jmRemove)); 

        // Clique pour supprimer le feu
        jmiRemove.addActionListener(e -> removeLight(jmiRemove, jmRemove, jmDeco, jmCreate, jmRives)); 

        // Clique pour changer la stratégie du feu
        jmiStras.addActionListener(e -> changeSide(jmiStras, jmiKehl, jmiStras.getText(), light));
        jmiKehl.addActionListener(e -> changeSide(jmiStras, jmiKehl, jmiKehl.getText(), light));
    }


    /**
     * Supprime le feu de tous les menus et demande la suppression de toutes les vues liées
     * @param jmiRemove JMenuItem du feu du menu de suppression sous l'onglet "Feu(x)"
     * @param jmRemove JMenu du feu du menu de suppression sous l'onget "Vue(s)"
     * @param jmDeco JMenu du feu du menu de décoration
     * @param jmCreate JMenu du feu du menu de création sous l'onglet "Vue(s)"
     * @param jmRives JMenu du feu du menu des rives
     * @see LightManager.remove(String title)
     * @see GUI_manager.removeGUIsWithLight(Light l)
    */
    private void removeLight(JMenuItem jmiRemove, JMenu jmRemove, JMenu jmDeco, JMenu jmCreate, JMenu jmRives) { 
        jmenuRemoveLight.remove(jmiRemove);
        jmenuRemoveView.remove(jmRemove);
        jmenuDeco.remove(jmDeco);
        jmenuCreateView.remove(jmCreate);
        jmenuSides.remove(jmRives);
        viewsManager.removeGUIsWithLight(lightManager.searchLight(jmiRemove.getText()));
        lightManager.removeLight(jmiRemove.getText());
    }


    /**
     * Demande la création d'une vue textuelle et la rajoute à GUI_menu
     * Demande l'ajout de cette vue aux menus de suppression et de décoration
     * @param jmDeco JMenu du feu correspondant pour la décoration
     * @param jmRemove JMenu du feu correspondant pour la suppression sous l'onglet "Vue(s)"
     * @see GUI_manager.createTextualGUI(Light l)
     * @see GUI_menu.addViewToMenus(JMenu jmDeco, String title)
    */
    private void createTextualGUI(JMenu jmDeco, JMenu jmRemove) {
        GUI_text g = viewsManager.createTextualGUI(lightManager.searchLight(jmDeco.getText()));
        getContentPane().add(g);
        
        addViewToMenus(jmDeco, jmRemove, g.getTitle());
    }


    /**
     * Demande la création d'une vue graphique et la rajoute à GUI_menu
     * Demande l'ajout de cette vue aux menus de suppression et de décoration
     * @param jmDeco JMenu du feu correspondant pour la décoration
     * @param jmRemove JMenu du feu correspondant pour la suppression sous l'onglet "Vue(s)"
     * @see GUI_manager.createGraphicalGUI(Light l)
     * @see GUI_menu.addViewToMenus(JMenu jmDeco, String title)
     */
    private void createGraphicalGUI(JMenu jmDeco, JMenu jmRemove) {
        GUI_graphic g = viewsManager.createGraphicalGUI(lightManager.searchLight(jmDeco.getText()));
        getContentPane().add(g);
        
        addViewToMenus(jmDeco, jmRemove, g.getTitle());
    }


    /**
     * Ajoute la vue aux menus de suppression et de décoration
     * Ajoute la permission de créer une vue piéton ou tourner à droite depuis le menu décoration
     * @param jmDeco JMenu correspondant au JMenu du feu pour la décoration
     * @param jmRemove JMenu correspondant au JMenu du feu pour la suppression
     * @param title Nom de la vue
     * @see GUI_menu.removeGUI(JMenuItem item)
     */
    private void addViewToMenus(JMenu jmDeco, JMenu jmRemove, String title) {
        /* Ajout de la vue au menu de décoration */
        JMenu jmText = new JMenu(title.split(",")[1]);
        jmText.setFont(f);
        jmDeco.add(jmText);

        JMenuItem jmiPieton = new JMenuItem(" Pieton ");
        jmiPieton.setFont(f);
        jmText.add(jmiPieton);

        JMenuItem jmiRightTurn = new JMenuItem(" Tourner à droite ");
        jmiRightTurn.setFont(f);
        jmText.add(jmiRightTurn);

        jmiPieton.addActionListener(e -> addPedestrian(title));
        jmiRightTurn.addActionListener(e -> addRightTurn(title));


        /* Ajout de la vue au menu de suppression */
        JMenuItem jmi = new JMenuItem(title.split(",")[1]);
        jmi.setFont(f);
        jmRemove.add(jmi);

        jmi.addActionListener(e -> removeGUI(jmi)); // Clique pour supprimer la vue
    }


    /**
     * Supprime la vue des menus et demande la suppression de la vue au gestionnaire de vues
     * @param jmi JMenuItem correspondant à la vue
     * @see GUI_manager.removeGUI(String title)
     */
    private void removeGUI(JMenuItem jmi) {
        viewsManager.removeGUI(jmi.getText());
        jmenuRemoveView.remove(jmi);
        
        // Supprime la vue dans le menu de décoration
        Component[] c = jmenuDeco.getMenuComponents();
        for(int i = 0; i < c.length; i++) {
            if (((JMenu)c[i]).getText().equals(jmi.getText())) {
                jmenuDeco.remove(c[i]);
            }
        } 
    }


    /**
     * Ajoute une vue piétonne pour une vue donnée
     * @param title le nom de la vue parente
     * @see GUI_manager.createPedestrian(String title)
     */
    private void addPedestrian(String title) {
        Pedestrian p = viewsManager.createPedestrian(title);
        getContentPane().add(p);
    }


    /**
     * Ajoute une vue "tourner à droite" pour une vue donnée
     * @param title le nom de la vue parente
     * @see GUI_manager.createRightTurn(String title)
     */
    private void addRightTurn(String title) {
        RightTurn r = viewsManager.createRightTurn(title);
        getContentPane().add(r);
    }


    /**
     * Change la rive pour un feu donné (Strasbourg ou Kehl)
     * @param jmiStras JMenuItem correspondant à la rive Strasbourg
     * @param jmiKehl JMenuItem correspondant à la rive Kehl
     * @param rive la stratégie à adopter peut être soit "Strasbourg", soit "Kehl"
     * @param light le feu concerné par le changement de stratégie
     * @see LightManager.searchLight(String title)
     * @see Light.changeSide(String strategy)
     */
    private void changeSide(JMenuItem jmiStras, JMenuItem jmiKehl, String rive, String light) {
        lightManager.searchLight(light).changeSide(rive);
        switch (rive) {
            case "Kehl":
                jmiKehl.setText("Kehl (Actuel)");
                jmiStras.setText("Strasbourg");
                break;
            default:
                jmiKehl.setText("Kehl");
                jmiStras.setText("Strasbourg (Actuel)");
                break;
        }
    }

}
