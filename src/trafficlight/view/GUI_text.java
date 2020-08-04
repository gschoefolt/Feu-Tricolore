package trafficlight.view;

import javax.swing.JLabel;

import trafficlight.model.Light;

public class GUI_text extends GUI {  
   
    /**
     * Constructeur de la classe
     * @param f le feu associé à la vue
     */
    public GUI_text(Light f){
        super(f);
        this.title = f.getName() + ", Texte " + super.getId();
    }

    
    /**
     * Change le texte de fond en fonction du feu
     */
    @Override
    public void update() {
    	JLabel jlInfo = getJlInfo();
    	Light light = getLight();
    	
        if (!light.isRunning()) {
            jlInfo.setText("Feu arrêté");
        }
        else {
            switch (light.getColor()) {
                case 0:
                    jlInfo.setText("Stop");
                    break;
                case 2:
                    jlInfo.setText("Passez");
                    break;
                default:
                    jlInfo.setText("Attention");
            }
        }
    }

}
