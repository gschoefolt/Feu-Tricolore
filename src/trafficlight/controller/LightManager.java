package trafficlight.controller;

import java.util.ArrayList;
import trafficlight.model.Light;

public class LightManager {
    private static LightManager instance;
    private ArrayList<Light> lights;

    /**
     * Constructeur de la classe
     * Initialise la liste des feux
    */
    private LightManager(){
        lights = new ArrayList<Light>();
    }


    /**
     * Définit ou non l'unique instance de la classe
     * @return l'instance de la classe
     */
    public static LightManager getInstance() {
        if (instance == null){
            instance = new LightManager();
        }
        return instance;
    }


    /**
     * Crée un feu et l'ajoute à la liste des feux de cette classe
     * @return le nouveau feu créé
     * @see Light.Light()
     */
    public Light createLight() {
        Light l = new Light();
        this.lights.add(l);
        return l;
    }


    /**
     * Cherche et retourne l'objet feu parmi la liste des feux de cette classe
     * @param l le nom du feu à rechercher
     * @return le feu recherché
     */
    public Light searchLight(String l) {
        for (int i = 0; i < this.lights.size(); i++) {
            if (this.lights.get(i).getName().equals(l)) {
                return this.lights.get(i);
            }
        }
        return null;
    }


    /**
     * Supprime le feu de la liste et supprime récursivement toutes les vues associées
     * @param f le nom du feu à supprimer
     */
    public void removeLight(String f) {
        this.lights.remove(searchLight(f));
    }

}
