package environment;

import java.util.ArrayList;

import gameCommons.Case;
import gameCommons.Game;
import gameCommons.IEnvironment;

public class Environment implements IEnvironment {

    //TODO
    private Game game;
    private ArrayList<Lane> env ;

    public Environment(Game game) {
        this.game = game;
        this.env = new ArrayList<>();
        for (int i=1; i< game.height-1;i++) {
            this.env.add(i-1, new Lane(this.game, i));
        }
        //this.env.add(new Lane(this.game, this.game.height -1 ));
    }


        /**
         * Teste si une case est sure, c'est � dire que la grenouille peut s'y poser
         * sans mourir
         *
         * @param c
         *            la case � tester
         * @return vrai s'il n'y a pas danger
         */
        public boolean isSafe (Case c){
            if (c.ord==this.game.height-1) return true;
            for (Lane l : this.env){
                if (!l.isSafe(c)) return false;
            }
            return true;
        }

        /**
         * Teste si la case est une case d'arrivee
         *
         * @param c
         * @return vrai si la case est une case de victoire
         */
        public boolean isWinningPosition (Case c){
            if(c.ord == game.height-1) return true;
            return false;
        }

        /**
         * Effectue une �tape d'actualisation de l'environnement
         */
        public void update () {
            for ( Lane l : this.env){
                l.update();
            }
        }

    }
