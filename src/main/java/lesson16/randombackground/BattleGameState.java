package lesson16.randombackground;

import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import lesson16.MapGameState;

/**
 * Code sous licence GPLv3 (http://www.gnu.org/licenses/gpl.html)
 * 
 * @author <b>Shionn</b>, shionn@gmail.com <i>http://shionn.org</i><br>
 *         GCS d- s+:+ a+ C++ UL/M P L+ E--- W++ N K- w-- M+ t+ 5 X R+ !tv b+ D+ G- e+++ h+ r- y+
 */
public class BattleGameState extends BasicGameState {

	public static final int ID = 3;
	private Random random = new Random();
	private StateBasedGame game;
	private Image[] backgrounds = new Image[2]; // Les background
	private int chosenBackground = 0; // le background actif
	private Image ennemy;
	private Image hero;

	/**
	 * A l'initialisation du jeux je charge tous les backgrounds (ici que deux)
	 */
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		this.game = game;
		this.backgrounds[0] = new Image("background/battle.png");
		this.backgrounds[1] = new Image("background/battle-alternatif.png");
		this.ennemy = new Image("battle/gobelin.png").getScaledCopy(2);
		this.hero = new Image("battle/hero.png").getScaledCopy(2);
	}

	/**
	 * Quand je commence un combat (c'est à dire quand j'entre dans cette boucle) je choisis
	 * aléatoirement le fond. La méthode serait la même pour toutes autre chose.
	 */
	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException {
		this.chosenBackground = random.nextInt(backgrounds.length);
	}

	/**
	 * J'affiche le background choisi
	 */
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		backgrounds[chosenBackground].draw(0, 0, container.getWidth(), container.getHeight());
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
