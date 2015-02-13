package lesson18;

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
public class BattleGameState extends BasicGameState {

    public static final int ID = 3;
    private Image background;
    private BattleEnnemy ennemy = new BattleEnnemy();
    private BattlePlayer player = new BattlePlayer();

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        this.background = new Image("background/battle.png");
        this.ennemy.init();
        this.player.init();
        BattleController controller = new BattleController(player, ennemy, game);
        container.getInput().addKeyListener(controller);
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g)
            throws SlickException {
        this.background.draw(0, 0, container.getWidth(), container.getHeight());
        this.player.render(container, g);
        this.ennemy.render(container, g);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta)
            throws SlickException {
    }

    @Override
    public void keyPressed(int key, char c) { }

    @Override
    public int getID() {
        return ID;
    }
}
