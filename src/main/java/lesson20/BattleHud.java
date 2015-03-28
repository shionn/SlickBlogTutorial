package lesson20;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.MouseOverArea;

import shionn.slick.ui.TextArea;
import shionn.slick.ui.align.VerticalAlignement;

/**
 * Code sous licence GPLv3 (http://www.gnu.org/licenses/gpl.html)
 * 
 * @author <b>Shionn</b>, shionn@gmail.com <i>http://shionn.org</i><br>
 *         GCS d- s+:+ a+ C++ UL/M P L+ E--- W++ N K- w-- M+ t+ 5 X R+ !tv b+ D+ G- e+++ h+ r- y+
 */
public class BattleHud implements ComponentListener {

	private static final int Y_PADDING = 3;
	private static final int X_PADDING = 13;
	private static final int SPACE = 5;
	private BattleController controller;
	private MouseOverArea attackButton;
	private MouseOverArea fleeButton;
	private MouseOverArea defendButton;
	private TextArea log;

	public BattleHud(BattleController controller) {
		this.controller = controller;
	}

	public void init(GameContainer container) throws SlickException {
		Image buttonImage = new Image("hud/button.png");
		attackButton = new MouseOverArea(container, buttonImage, SPACE, container.getHeight()
				- (buttonImage.getHeight() + SPACE) * 3, this);
		defendButton = new MouseOverArea(container, buttonImage, SPACE, container.getHeight()
				- (buttonImage.getHeight() + SPACE) * 2, this);
		fleeButton = new MouseOverArea(container, buttonImage, SPACE, container.getHeight()
				- (buttonImage.getHeight() + SPACE) * 1, this);
		log = new TextArea(SPACE + attackButton.getWidth() + SPACE, attackButton.getY(),
				container.getWidth() - attackButton.getWidth() - SPACE * 3, buttonImage.getHeight()
						* 3 + SPACE * 2);
		log.setBottomUp(true);
		log.setDefaultFont(container.getDefaultFont());
	}

	public void render(GameContainer container, Graphics g) {
		g.setColor(Color.black);
		attackButton.render(container, g);
		g.drawString("Attaquer", attackButton.getX() + X_PADDING, attackButton.getY() + Y_PADDING);
		defendButton.render(container, g);
		g.drawString("Defendre", defendButton.getX() + X_PADDING, defendButton.getY() + Y_PADDING);
		fleeButton.render(container, g);
		g.drawString("Fuire", fleeButton.getX() + X_PADDING, fleeButton.getY() + Y_PADDING);
		log.render();
	}

	@Override
	public void componentActivated(AbstractComponent source) {
		if (source == attackButton) {
			controller.controlPressed(BattleCommand.ATTACK);
		} else if (source == defendButton) {
			controller.controlPressed(BattleCommand.DEFEND);
		} else if (source == fleeButton) {
			controller.controlPressed(BattleCommand.FLEE);
		}
	}

	public void addLog(String text) {
		log.addFirstText(text, VerticalAlignement.LEFT);
	}
}
