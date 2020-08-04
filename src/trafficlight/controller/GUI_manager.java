package trafficlight.controller;

import java.util.ArrayList;

import trafficlight.model.Light;
import trafficlight.view.*;

public class GUI_manager {
    private ArrayList<GUI> views;
    private static GUI_manager instance;

    /**
     * Constructeur de la classe
     * Initialise la liste des vues
     */
    private GUI_manager() {
        views = new ArrayList<GUI>();
    }


    /**
     * Définit ou non l'unique instance de la classe
     * @return l'instance de la classe
     */
    public static GUI_manager getInstance() {
        if (instance == null) {
            instance = new GUI_manager();
        }
        return instance;
    }


    /**
     * Créer et retourne une nouvelle vue textuelle
     * @param f le feu associé à la vue
     * @return un nouvelle objet GUI_text
     * @see GUI_text.GUI_text()
     */
    public GUI_text createTextualGUI(Light f) {
        GUI_text gt = new GUI_text(f);
        views.add(gt);
        return gt;
    }
    

    /**
     * Créer et retourne une nouvelle vue graphique
     * @param f le feu associé à la vue
     * @return un nouvelle objet GUI_graphic
     * @see GUI_graphic.GUI_graphic()
     */
    public GUI_graphic createGraphicalGUI(Light f) {
        GUI_graphic gg = new GUI_graphic(f);
        views.add(gg);
        return gg;
    }


    /**
     * Supprime un GUI (vue) de la liste des vues et supprime l'objet physiquement
     * @param title le titre de la vue
     */
    public void removeGUI(String title) {
        for(GUI gui : new ArrayList<GUI>(this.views)) {
            if (gui.getTitle().contains(title)) {
                gui.dispose();
                this.views.remove(gui);
            }
        }
    }


    /**
     * Créer et retourne une nouvelle vue piétonne pour un feu
     * @param parentTitle le nom de la vue parente
     * @return un nouvelle objet Pedestrian
     * @see Pedestrian.Pedestrian()
     */
    public Pedestrian createPedestrian(String parentTitle) {
        GUI guiParent = searchGUI(parentTitle);

        if (guiParent != null) {
            Pedestrian p = new Pedestrian(guiParent, parentTitle);
            
            views.remove(guiParent);
            views.add(p);
            return p;
        }
        return null;
    }


    /**
     * Créer et retourne une nouvelle vue tourner à droite pour un feu
     * @param parentTitle le nom de la vue parente
     * @return un nouvelle objet RightTurn
     * @see RightTurn.RightTurn()
     */
    public RightTurn createRightTurn(String parentTitle) {
        GUI guiParent = searchGUI(parentTitle);

        if (guiParent != null) {
            RightTurn r = new RightTurn(guiParent, parentTitle);
            
            views.remove(guiParent);
            views.add(r);
            return r;
        }
        return null;
    }


    /**
     * Recherche une vue parmi la liste des vues de la classe
     * @param title le nom de la vue à rechercher
     * @return l'objet / la vue recherchée ou null s'il ne trouve pas
     */
    public GUI searchGUI(String title) {
        for (int i = 0; i < this.views.size(); i++){
            if (this.views.get(i).getTitle().contains(title)) {
                return this.views.get(i);
            }
        }
        return null;
    }


    /**
     * 
     * @param f le feu supprimé ou à supprimer
     */
    public void removeGUIsWithLight(Light f) {
        for (GUI g : new ArrayList<GUI>(this.views)) {
            if (g.getLight().getName().contains(f.getName())){
                g.dispose();
                this.views.remove(g);
            }
        }
    }
    
}