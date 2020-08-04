package trafficlight.model;

public class Kehl implements Strategy {

    /**
     * Algorithme de changement de couleur pour la classe
     */
    public int change(int c) {
        return (c + 1) % 4;
    }
}