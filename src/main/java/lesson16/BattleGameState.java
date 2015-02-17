package lesson16;

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
	private StateBasedGame game;
	private Image background;
	private Image ennemy;
	private Image hero;

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		this.game = game;
		this.background = new Image("background/battle.png");
		this.ennemy = new Image("battle/gobelin.png").getScaledCopy(2);
		this.hero = new Image("battle/hero.png").getScaledCopy(2);

	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		background.draw(0, 0, container.getWidth(), container.getHeight());
		hero.drawCentered(container.getWidth() * 1 / 4, container.getHeight() / 2);
		ennemy.drawCentered(container.getWidth() * 3 / 4, container.getHeight() / 2);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
	}

	@Override
	public void keyPressed(int key, char c) {
		game.enterState(MapGameState.ID);
	}

	@Override
	public int getID() {
		return ID;
	}
}
