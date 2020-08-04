package trafficlight.observer;

import java.util.ArrayList;
import java.util.Collection;

public abstract class Observee {	
	private Collection<Observer> observers;
	
	/**
	 * Constructeur de la classe
	 * Initialise la liste des observers
	 */
	public Observee() {
		observers = new ArrayList<Observer>();
	}
	

	/**
	 * Appel la méthode update de chaque observer de la liste
	 */
	public void notifyObservers() {
		for (Observer o: observers) {
			o.update();
		}
	}
	

	/**
	 * Ajoute un observer à la liste
	 * @param o l'observer à ajouter
	 */
	public void addObserver(Observer o) {
		observers.add(o);
	}
	

	/**
	 * Supprime l'observer à la liste
	 * @param o l'observer à supprimer
	 */
	public void removeObserver(Observer o) {
		observers.remove(o);
	}

}
