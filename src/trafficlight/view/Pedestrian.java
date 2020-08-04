package trafficlight.view;

import java.awt.Color;
import javax.swing.JLabel;

import trafficlight.model.Light;

public class Pedestrian extends Decorator {
    private static int numero = 0;
    private GUI guiParent;

    /**
     * Constructeur de la classe
     * @param parent est la vue parente de la vue piétonne actuellement créée
     * @param firstParent le nom du premier parent 
     */
    public Pedestrian(GUI parent, String firstParent) {
        super();
        numero++;
        this.guiParent = parent;
        this.title = firstParent + ", Piéton " + numero;
        this.setLight(guiParent.getLight());        
    }


    /**
     * Redéfinition de la méthode dispose pour qu'elle puisse à la fois détruire la vue piétonne actuelle
     * mais aussi la vue de son parent
     */
    @Override
    public void dispose() {
        this.guiParent.dispose();
        super.dispose();
    }


    /**
     * Change la couleur de fond en fonction du feu
     */
    @Override
    public void update() {
        JLabel jlInfo = getJlInfo();
    	Light light = getLight();
    	
        if (!light.isRunning()) {
            jlInfo.setBackground(Color.gray);
        }
        else {
            switch (light.getColor()) {
                case 0:
                    jlInfo.setBackground(Color.green);
                    break;
                default:
                    jlInfo.setBackground(Color.red);
                    break;
            }
        }
    }
    
}