package lesson18;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import shionn.slick.animation.BezierPath;
import shionn.slick.animation.PathAnimation;

/**
 * Code sous licence GPLv3 (http://www.gnu.org/licenses/gpl.html)
 * 
 * @author <b>Shionn</b>, shionn@gmail.com <i>http://shionn.org</i><br>
 *         GCS d- s+:+ a+ C++ UL/M P L+ E--- W++ N K- w-- M+ t+ 5 X R+ !tv b+ D+ G- e+++ h+ r- y+
 */
public class BattleEnnemy {
	private int pv = 12;
	private Image ennemy;
	private PathAnimation animation;

	public void init() throws SlickException {
		this.ennemy = new Image("battle/gobelin.png").getScaledCopy(2);
		this.animation = new PathAnimation(new BezierPath(0, 0, -400, 1, 50, 20, 0, 0), 2000);
	}

	public void reset() {
		this.pv = 12;
	}

	public void render(GameContainer container, Graphics g) {
		Vector2f p = animation.currentLocation();
		this.ennemy.drawCentered(p.x + container.getWidth() * 3 / 4, p.y + container.getHeight()
				/ 2);
		Font font = g.getFont();
		String text = Integer.toString(pv);
		font.drawString(container.getWidth() * 3 / 4 - font.getWidth(text) / 2,
				container.getHeight() / 2 - ennemy.getHeight() / 2 - font.getLineHeight(), text,
				Color.white);
	}

	public void update(int delta) {
		animation.update(delta);
	}

	public void startAttack() {
		animation.start();
	}

	public void setPv(int pv) {
		this.pv = pv;
	}

	public int getPv() {
		return pv;
	}

}
