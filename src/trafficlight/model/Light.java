package trafficlight.model;

import trafficlight.observer.Observee;

public class Light extends Observee {
	private static int numero = 0;
	private boolean running;
	private int color;
	private Strategy strat;
	private String name;

	/**
	 * Constructeur de la classe
	 */
	public Light() {
		super();
		numero++;
		this.name = "Feu " + numero;
		strat = new Strasbourg();
	}


	/**
	 * @return vrai si le feu est en marche, faux sinon
	 */
	public boolean isRunning() {
		return running;
	}


	/**
	 * @return le nom unique du feu
	 */
	public String getName() {
		return this.name;
	}


	/**
	 * @return la couleur actuelle du feu
	 */
	public int getColor() {
		return color;
	}


	/**
	 * Définit la couleur actuelle du feu
	 * @param c la couleur à mettre
	 */
	public void setColor(int c) {
		this.color = c;
	}


	/**
	 * Permet de démarrer ou d'arrêter le feu
	 */
	public void startStop() {
		running = !running;
		color = 0;
		notifyObservers();
	}


	/**
	 * Appel la méthode correspondante à l'algorithme de changement de couleur
	 */
	public void change() {
		this.setColor(strat.change(this.getColor()));
		notifyObservers();
	}

	/**
	 * Change l'algorithme de changement de couleur
	 * @param strat le nom de la nouvelle stratégie/algorithme
	 */
	public void changeSide(String strat) {
		if (strat.contains("Kehl")) {
			this.strat = new Kehl();
		}
		else {
			this.strat = new Strasbourg();
		}
		if (this.running) {
			this.startStop();
		}
	}
	
}
