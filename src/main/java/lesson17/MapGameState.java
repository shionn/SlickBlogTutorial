package lesson17;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Code sous licence GPLv3 (http://www.gnu.org/licenses/gpl.html)
 *
 * @author <b>Shionn</b>, shionn@gmail.com <i>http://shionn.org</i><br>
 *         GCS d- s+:+ a+ C++ UL/M P L+ E--- W++ N K- w-- M+ t+ 5 X R+ !tv b+ D+ G- e+++ h+ r- y-
 */
public class MapGameState extends BasicGameState {
	public static final int ID = 2;

	private GameContainer container;
	private Map map = new Map();
	private MapPlayer player = new MapPlayer(map);
	private MapTriggerController triggers = new MapTriggerController(map, player);
	private MapCamera camera = new MapCamera(player);
	private MapPlayerController controller = new MapPlayerController(player);
	private MapHud hud = new MapHud();

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		this.container = container;
		Music background = new Music("sound/lost-in-the-meadows.ogg");
		background.loop();
		this.map.init();
		this.player.init();
		this.hud.init();
		this.controller.setInput(container.getInput());
		container.getInput().addKeyListener(controller);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		this.camera.place(container, g);
		this.map.renderBackground();
		this.player.render(g);
		this.map.renderForeground();
		this.hud.render(g);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		this.controller.update();
		this.triggers.update();
		this.player.update(delta);
		this.camera.update(container);
		if (Math.random() < 0.01 && player.isMoving()) {
			game.enterState(BattleGameState.ID);
		}
	}

	@Override
	public void keyReleased(int key, char c) {
		if (Input.KEY_ESCAPE == key) {
			this.container.exit();
		}
	}

	@Override
	public int getID() {
		return ID;
	}

}
