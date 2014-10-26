package lesson15;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Code sous licence GPLv3 (http://www.gnu.org/licenses/gpl.html)
 * 
 * @author <b>Shionn</b>, shionn@gmail.com <i>http://shionn.org</i><br>
 *         GCS d- s+:+ a+ C++ UL/M P L+ E--- W++ N K- w-- M+ t+ 5 X R+ !tv b+ D+ G- e+++ h+ r- y+
 */
public class MainScreenGameState extends BasicGameState {

    public static final int ID = 1;
    private Image background;
    private StateBasedGame game;

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        this.game = game;
        this.background = new Image("background/forest.png");
        
    }

    /**
     * Contenons nous d'afficher l'image de fond. .
     */
    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g)
            throws SlickException {
        background.draw(0, 0, container.getWidth(), container.getHeight());
        g.drawString("Appuyer sur une touche", 300, 300);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta)
            throws SlickException {
    }

    /**
     * Passer à l'ecran de jeu à l'appui de n'importe quel touche.
     */
    @Override
    public void keyReleased(int key, char c) {
        game.enterState(TutoGameState.ID);
    }

    /**
     * L'identifiant permet d'identifier les différentes boucles, pour passer de l'une à l'autre.
     */
    @Override
    public int getID() {
        return ID;
    }
}
