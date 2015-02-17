/**
 * Code sous licence GPLv3 (http://www.gnu.org/licenses/gpl.html)
 *
 * @author <b>Shionn</b>, shionn@gmail.com <i>http://shionn.org</i><br>
 * GCS d- s+:+ a- C++ UL/M P L+ E--- W++ N K- w-- M+ t+ 5 X R+ !tv b+ D+ G- e+++ h+ r- !y-
 */
package lesson16;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Code sous licence GPLv3 (http://www.gnu.org/licenses/gpl.html)
 * 
 * -Djava.library.path=target/natives
 * 
 * @author <b>Shionn</b>, shionn@gmail.com <i>http://shionn.org</i><br>
 *         GCS d- s+:+ a C++ UL/M P L+ E--- W++ N K- w-- M+ t+ 5 X R+ !tv b+ D+ G- e+++ h+ r- y+
 */
public class BattleGame extends StateBasedGame {

	public static void main(String[] args) throws SlickException {
		new AppGameContainer(new BattleGame(), 800, 600, false).start();
	}

	public BattleGame() {
		super("Lesson 16 :: BattleGame");
	}

	/**
	 * Ici il suffit d'ajouter nos boucles de jeux. La première ajoutèe sera celle qui sera utilisée
	 * au début
	 */
	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		addState(new MainScreenGameState());
		addState(new MapGameState());
		addState(new BattleGameState());
	}

}
