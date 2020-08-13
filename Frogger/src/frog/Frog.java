package frog;

import gameCommons.Case;
import gameCommons.Direction;
import gameCommons.Game;
import gameCommons.IFrog;

public class Frog implements IFrog {

    private boolean fin;
	private Game game;
	private Case uneCase;
	private Direction uneDirection;

	public Frog(Game game){
		this.game = game;
		this.uneCase = new Case(13,0);
		this.uneDirection = Direction.up;
        this.fin = false;
	}

	 /**
	 * Donne la position actuelle de la grenouille
	 * @return
	 */
	public Case getPosition(){
		return this.uneCase;
	}

	 /**
	 * Donne la direction de la grenouille, c'est � dire de son dernier mouvement
	 * @return
	 */
	public Direction getDirection() {
		return this.uneDirection;
	}

	/**
	 * D�place la grenouille dans la direction donn�e et teste la fin de partie
	 * @param key
	 */
	public void move (Direction key){


	    if(this.game.testLose() || this.game.testWin()) this.fin = true;
	    if(fin) return;
        if (this.uneCase.ord == this.game.height-1) return;
        if (this.uneCase.ord == 0 && key == Direction.down) return;
        if (this.uneCase.absc == this.game.width-1 && key == Direction.right) return;
        if (this.uneCase.absc == 0 && key == Direction.left) return;
		if (key == Direction.up)  this.uneCase = new Case (this.uneCase.absc, this.uneCase.ord +1) ;
		if (key == Direction.down) this.uneCase = new Case (this.uneCase.absc, this.uneCase.ord -1) ;
		if (key == Direction.right) this.uneCase = new Case (this.uneCase.absc+1, this.uneCase.ord );
		if (key == Direction.left) this.uneCase = new Case (this.uneCase.absc-1, this.uneCase.ord );
		this.uneDirection = key;
		//this.game.update();
	}
}
