package lesson17;

import java.util.Random;

import org.newdawn.slick.Input;
import org.newdawn.slick.KeyListener;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Code sous licence GPLv3 (http://www.gnu.org/licenses/gpl.html)
 * 
 * @author <b>Shionn</b>, shionn@gmail.com <i>http://shionn.org</i><br>
 *         GCS d- s+:+ a+ C++ UL/M P L+ E--- W++ N K- w-- M+ t+ 5 X R+ !tv b+ D+ G- e+++ h+ r- y+
 */
public class BattleController implements KeyListener {

	private BattlePlayer player;
	private BattleEnnemy ennemy;
	private StateBasedGame game;
	private Random random = new Random();

	public BattleController(BattlePlayer player, BattleEnnemy ennemy, StateBasedGame game) {
		this.player = player;
		this.ennemy = ennemy;
		this.game = game;
	}

	/**
	 * on redirige vers la bonne méthode en fonction de la touche préssé
	 */
	@Override
	public void keyReleased(int key, char c) {
		switch (c) {
		case 'a':
			attack();
			break;
		case 'd':
			defend();
			break;
		case 'f':
			flee();
			break;
		}
	}

	/**
	 * Si le joueur attaque : Il inflige entre 7 et 10 points dégâts avec 10% de chance de faire un
	 * critique (+50% de dégât) L'ennemi contre attaque en infligeant entre 5 et 9 dégâts.
	 */
	private void attack() {
		int playerAttack = 7 + random.nextInt(4);
		if (random.nextDouble() < .1) {
			playerAttack += playerAttack / 2;
		}
		ennemy.setPv(ennemy.getPv() - playerAttack);
		if (ennemy.getPv() <= 0) {
			game.enterState(MapGameState.ID);
		} else {
			int ennemyAttack = 5 + random.nextInt(5);
			player.setPv(player.getPv() - ennemyAttack);
			if (player.getPv() <= 0) {
				game.enterState(MainScreenGameState.ID);
			}
		}
	}

	/**
	 * Si le joueur défend :
	 * <ul>
	 * <li>L'ennemi attaque et inflige entre 5 et 9 dégâts. Mais la moitié des dégâts sont prévenu.</li>
	 * <li>Le joueur contre attaque en infligeant entre 7 et 10 points de dégâts sans possibilité de
	 * faire des critiques.</li>
	 * </ul>
	 */
	private void defend() {
		int ennemyAttack = (5 + random.nextInt(5)) / 2;
		player.setPv(player.getPv() - ennemyAttack);
		if (player.getPv() <= 0) {
			game.enterState(MainScreenGameState.ID);
		} else {
			int playerAttack = 7 + random.nextInt(4);
			ennemy.setPv(ennemy.getPv() - playerAttack);
			if (ennemy.getPv() <= 0) {
				game.enterState(MapGameState.ID);
			}
		}
	}

	/**
	 * Si le joueur fuit :
	 * <ul>
	 * <li>L'ennemi attaque et inflige entre 5 et 9 dégâts.</li>
	 * <li>Le joueur quitte le combat.</li>
	 * </ul>
	 */
	private void flee() {
		int ennemyAttack = 5 + random.nextInt(5);
		player.setPv(player.getPv() - ennemyAttack);
		if (player.getPv() <= 0) {
			game.enterState(MainScreenGameState.ID);
		} else {
			game.enterState(MapGameState.ID);
		}
	}

	@Override
	public boolean isAcceptingInput() {
		return true;
	}

	@Override
	public void setInput(Input input) {
	}

	@Override
	public void inputEnded() {
	}

	@Override
	public void inputStarted() {
	}

	@Override
	public void keyPressed(int key, char c) {
	}

}
