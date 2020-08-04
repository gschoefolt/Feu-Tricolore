package trafficlight.model;

public class Strasbourg implements Strategy {
    
    /**
     * Algorithme de changement de couleur pour la classe
     */
    public int change(int c) {
        return (c + 1) % 3;
    }
}