package environment;

import java.awt.Color;

import gameCommons.Case;
import gameCommons.Game;
import graphicalElements.Element;

public class Car {
	private Game game;
	private Case leftPosition;
	private boolean leftToRight;
	private int length ;
	private final Color colorLtR = Color.black;
	private final Color colorRtL = Color.red;

	//TODO Constructeur(s)
    public Car(Game jeu,Case depart, boolean sens){
        this.game = jeu;
        this.leftToRight = sens;
        this.leftPosition = depart;
        this.length = jeu.randomGen.nextInt(3)+1; //de 0 a 2 ,+1


    }
	
	//TODO : ajout de methodes
    public Case getLeftPosition (){
        return this.leftPosition;
    }
    public int getLength(){
        return this.length;
    }

    //change les coordonees de la voiture et l ajoute au graphique
    public void moveCar(int t){

    	if (this.leftToRight) {
			this.leftPosition = new Case(this.leftPosition.absc + t, this.leftPosition.ord);
		} else {
			this.leftPosition = new Case(this.leftPosition.absc - t, this.leftPosition.ord);
		}
		this.addToGraphics();
    }

	//if la voiture est dans le graph return true
    public boolean appears(){
    	if(this.leftToRight) return this.leftPosition.absc < this.game.width;
    	else {
    		return this.leftPosition.absc + this.length > 0;
		}
	}

	//return faux si la @cas est situee entre la gauche et la droite de la voiture
	public boolean isSafeCar(Case cas){
    	return !(cas.absc >= this.leftPosition.absc && cas.absc < this.getLeftPosition().absc+this.length);
	}


	/* Fourni : addToGraphics() permettant d'ajouter un element graphique correspondant a la voiture*/
	private void addToGraphics() {
		for (int i = 0; i < length; i++) {
			Color color = colorRtL;
			if (this.leftToRight){
				color = colorLtR;
			}
			game.getGraphic()
					.add(new Element(leftPosition.absc + i, leftPosition.ord, color));
		}
	}


}
