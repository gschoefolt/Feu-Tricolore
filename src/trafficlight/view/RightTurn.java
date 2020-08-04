package trafficlight.view;

import java.awt.Color;

import javax.swing.Timer;
import javax.swing.JLabel;

import trafficlight.model.Light;

public class RightTurn extends Decorator {
    private static int numero = 0;
    private Timer timer;
    private boolean isOrange = false;
    private GUI guiParent;

    /**
     * Constructeur de la classe
     * @param parent est la vue parente de la vue piétonne actuellement créée
     * @param firstParent le nom du premier parent 
     */
    public RightTurn(GUI parent, String firstParent) {
        super();
        numero++;
        this.guiParent = parent;
        this.title = firstParent + ", Tourner à droite " + numero;
        this.setLight(guiParent.getLight());   
        
        int delay = 750; 
        timer = new Timer(delay, e -> flicker());
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
            if (timer != null && timer.isRunning()) {
                timer.stop();
            }
        }
        else {
            switch (light.getColor()) {
                case 0:
                    isOrange = false;
                    timer.start();
                    break;
                default:
                    timer.stop();
                    jlInfo.setBackground(Color.gray);
                    isOrange = false;
                    break;
            }
        }
    }

    /**
     * Permet de faire clignoter la vue quand le feu maitre est rouge
     */
    public void flicker() {
        if (isOrange) {
            getJlInfo().setBackground(Color.gray);
        }
        else {
            getJlInfo().setBackground(Color.orange);
        }
        isOrange = !isOrange;
    }

}
