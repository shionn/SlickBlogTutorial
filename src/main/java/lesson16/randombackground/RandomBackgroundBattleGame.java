/**
 * Code sous licence GPLv3 (http://www.gnu.org/licenses/gpl.html)
 *
 * @author <b>Shionn</b>, shionn@gmail.com <i>http://shionn.org</i><br>
 * GCS d- s+:+ a- C++ UL/M P L+ E--- W++ N K- w-- M+ t+ 5 X R+ !tv b+ D+ G- e+++ h+ r- !y-
 */
package lesson16.randombackground;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import lesson16.MainScreenGameState;
import lesson16.MapGameState;

/**
 * Code sous licence GPLv3 (http://www.gnu.org/licenses/gpl.html)
 * 
 * -Djava.library.path=target/natives
 * 
 * @author <b>Shionn</b>, shionn@gmail.com <i>http://shionn.org</i><br>
 *         GCS d- s+:+ a C++ UL/M P L+ E--- W++ N K- w-- M+ t+ 5 X R+ !tv b+ D+ G- e+++ h+ r- y+
 */
public class RandomBackgroundBattleGame extends StateBasedGame {

	public static void main(String[] args) throws SlickException {
		new AppGameContainer(new RandomBackgroundBattleGame(), 800, 600, false).start();
	}

	/**
	 * La seule difference etant au niveau de la classe de BattleGameState, tous le reste est
	 * identique à la leçon 16
	 */
	public RandomBackgroundBattleGame() {
		super("Lesson 16 Question de Lecteur :: RandomBackgroundBattleGame");
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
