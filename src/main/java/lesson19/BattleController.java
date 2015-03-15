package lesson19;

import java.util.Random;

import org.newdawn.slick.command.Command;
import org.newdawn.slick.command.InputProviderListener;
import org.newdawn.slick.state.StateBasedGame;

import shionn.slick.animation.AnimationListener;

/**
 * Code sous licence GPLv3 (http://www.gnu.org/licenses/gpl.html)
 * 
 * @author <b>Shionn</b>, shionn@gmail.com <i>http://shionn.org</i><br>
 *         GCS d- s+:+ a+ C++ UL/M P L+ E--- W++ N K- w-- M+ t+ 5 X R+ !tv b+ D+ G- e+++ h+ r- y+
 */
public class BattleController implements InputProviderListener {

	private StateBasedGame game;

	private BattlePlayer player;
	private BattleEnnemy ennemy;

	private Random random = new Random();

	private BattleCommand mode = BattleCommand.NONE;

	public BattleController(BattlePlayer player, BattleEnnemy ennemy,
			StateBasedGame game) {
		this.player = player;
		this.ennemy = ennemy;
		this.game = game;
		initAnimationListeners();
	}

	private void initAnimationListeners() {
		AnimationListener playerAssignDamage = new AnimationListener() {
			@Override
			public void on() {
				playerAsignDamage();
			}
		};
		AnimationListener endPlayerAttack = new AnimationListener() {
			@Override
			public void on() {
				endPlayerAttack();
			}
		};
		AnimationListener ennemiAssignDamage = new AnimationListener() {
			@Override
			public void on() {
				ennemyAsignDamage();
			}

		};
		AnimationListener endEnnemiAttack = new AnimationListener() {
			@Override
			public void on() {
				endEnnemyAttack();
			}

		};

		this.player.addAnimationListener(playerAssignDamage, endPlayerAttack);
		this.ennemy.addAnimationListener(ennemiAssignDamage, endEnnemiAttack);
	}

	@Override
	public void controlPressed(Command command) {
		if (mode == BattleCommand.NONE) {
			this.mode = (BattleCommand) command;
			startBattle();
		}
	}

	/**
	 * Si le joueur attack il a l'initiative, dans tous les autres cas l'ennemi frappe avant.
	 */
	private void startBattle() {
		switch (mode) {
		case ATTACK:
			player.startAttack();
			break;
		case DEFEND:
		case FLEE:
		default:
			ennemy.startAttack();
			break;
		}
	}

	/**
	 * Calcul des dégat infligé par le joueur à l'ennemi
	 * <em>dans le cas d'une attaque le joueur peut faire un critique et infligé +50% de dégat</em>
	 */
	private void playerAsignDamage() {
		int playerAttack = 7 + random.nextInt(4);
		if (mode == BattleCommand.ATTACK && random.nextDouble() < .1) {
			playerAttack += playerAttack / 2;
		}
		ennemy.setPv(ennemy.getPv() - playerAttack);
	}

	/**
	 * A la fin d'une attaque du joueur,
	 * <ul>
	 * <li>si l'ennemi n'as plus de pv le joueur à gagné, retour à la carte</li>
	 * <li>Sinon, on appel l'action suivant en fonction du mode.</li>
	 * </ul>
	 */
	private void endPlayerAttack() {
		if (ennemy.getPv() <= 0) {
			game.enterState(MapGameState.ID);
			mode = BattleCommand.NONE;
		} else {
			switch (mode) {
			// si le joueur attaquait, contre attaque de l'ennemy
			case ATTACK:
				ennemy.startAttack();
				break;
			// pas d'autre cas possible
			default:
				mode = BattleCommand.NONE;
				break;
			}
		}
	}

	/**
	 * Calcul des dégat infligé par l'ennemy au joueur
	 * <em>dans le cas d'une attaque le joueur peut faire un critique et infligé +50% de dégat</em>
	 */
	private void ennemyAsignDamage() {
		int ennemyAttack = 5 + random.nextInt(5);
		if (mode == BattleCommand.DEFEND) {
			ennemyAttack = ennemyAttack / 2;
		}
		player.setPv(player.getPv() - ennemyAttack);
	}

	/**
	 * A la fin d'une attaque de l'ennemi,
	 * <ul>
	 * <li>si le joueur n'as plus de pv le joueur à perdu, retour à l'écran titre</li>
	 * <li>Sinon, on appel l'action suivant en fonction du mode.</li>
	 * </ul>
	 */

	private void endEnnemyAttack() {
		if (player.getPv() <= 0) {
			game.enterState(MainScreenGameState.ID);
			mode = BattleCommand.NONE;
		} else {
			switch (mode) {
			case DEFEND:
				// si le joueur defend, contre attaque du joueur
				player.startAttack();
				break;
			case FLEE:
				game.enterState(MapGameState.ID);
			default:
				mode = BattleCommand.NONE;
				break;
			}
		}
	}

	@Override
	public void controlReleased(Command command) {
	}

}
