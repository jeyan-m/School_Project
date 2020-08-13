package environment;

import java.util.ArrayList;
import java.util.Random;
import gameCommons.Case;
import gameCommons.Game;

public class  Lane {

    private final Random randGen = new Random();
    private Game game;
	private int ord;
	private int speed;
	private ArrayList<Car> cars = new ArrayList<>();
	private boolean leftToRight;
	private double density ;
	private int tic;

	// TODO : Constructeur(s)
	public Lane(Game game, int ordo){
		this.game = game;
        this.ord = ordo;
        this.density = this.game.defaultDensity;
        this.leftToRight = game.randomGen.nextBoolean();
        this.speed = this.randGen.nextInt(game.minSpeedInTimerLoops) + 1;
        this.tic = 0;
	}


	//mise a jour de la lane
	public void update() {
		this.tic++;
		if (this.tic <= this.speed) {
				this.moveCars(false);
		} else {
				this.moveCars(true);
			this.tic = 0;
		}
		if (this.leftToRight) {
			if (this.isSafe(new Case(0, this.ord))) {
				this.mayAddCar(); //peut ajouter une voiture si la voie est vide
			}
		}
		else {
			if (this.isSafe(new Case(this.game.width-1, this.ord))) {
				this.mayAddCar();
			}
		}
		//this.removeCars();  //a remettre
	}

	//deplace toutes les voitures de la lane
	public void moveCars(boolean a){
		if (a) {
			for (Car c : this.cars) {
				if (this.canMove(c)) c.moveCar(1);
				//System.out.println("abs : " + c.getLeftPosition().absc +" ord : " + c.getLeftPosition().ord + " longueur : " + c.getLength());
			}
		} else {
			for (Car c : this.cars) {
				if (this.canMove(c)) c.moveCar(0); //actualise quand meme l etat de la lane sans deplacer la voiture
			}
		}

	}

	//regarde si la case apres la voiture est vide
	public boolean canMove(Car c){
		if (this.leftToRight){
			Case caseToSee = new Case(c.getLeftPosition().absc+c.getLength(), c.getLeftPosition().ord);
			if (this.isSafe(caseToSee)) return true;
		}
		else {
			Case caseToSee = new Case(c.getLeftPosition().absc -1, c.getLeftPosition().ord);
			if (this.isSafe(caseToSee)) return true;
		}
		return false;
	}

	//retire la voiture de l arraylist si elle n apparait pas
	public void removeCars(){
		for (Car c : this.cars){
			if (!c.appears()) this.cars.remove(c);
		}
	}

		// Toutes les voitures se d�placent d'une case au bout d'un nombre "tic
		// d'horloge" �gal � leur vitesse
		// Notez que cette m�thode est appel�e � chaque tic d'horloge
		// Les voitures doivent etre ajoutes a l interface graphique meme quand
		// elle ne bougent pas
		// A chaque tic d'horloge, une voiture peut �tre ajout�e


	// TODO : ajout de methodes

	//return true si la case n est pas occupee par une voiture
    public boolean isSafe(Case cas){

	    if (cas.ord!=this.ord)return true;
		for (Car c : this.cars){
			if(!c.isSafeCar(cas)) return false;
		}
	    return true;
    }


	/*
	 * Fourni : mayAddCar(), getFirstCase() et getBeforeFirstCase() 
	 */

	/**
	 * Ajoute une voiture au d�but de la voie avec probabilit� �gale � la
	 * densit�, si la premi�re case de la voie est vide
	 */
	private void mayAddCar() {
		if (isSafe(getFirstCase()) && isSafe(getBeforeFirstCase())) {
			if (game.randomGen.nextDouble() < density) {
				cars.add(new Car(game, getBeforeFirstCase(), leftToRight));

			}
		}
	}

	private Case getFirstCase() {
		if (leftToRight) {
			return new Case(0, ord);
		} else
			return new Case(game.width - 1, ord);
	}

	private Case getBeforeFirstCase() {
		if (leftToRight) {
			return new Case(-1, ord);
		} else
			return new Case(game.width, ord);
	}

}
