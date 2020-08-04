package trafficlight.view;

import java.awt.Color;

import javax.swing.JLabel;

import trafficlight.model.Light;

public class GUI_graphic extends GUI {

    /**
     * Constructeur de la classe
     * @param f le feu associé à la vue
     * @see GUI.GUI()
     */
    public GUI_graphic(Light f){ 
        super(f);
        this.title = f.getName() + ", Graphique " + super.getId();
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
                    jlInfo.setBackground(Color.red);
                    break;
                case 2:
                    jlInfo.setBackground(Color.green);
                    break;
                default:
                    jlInfo.setBackground(Color.orange);
            }
        }
    }
    
}
