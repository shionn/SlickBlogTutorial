package lesson17;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Code sous licence GPLv3 (http://www.gnu.org/licenses/gpl.html)
 * 
 * @author <b>Shionn</b>, shionn@gmail.com <i>http://shionn.org</i><br>
 *         GCS d- s+:+ a+ C++ UL/M P L+ E--- W++ N K- w-- M+ t+ 5 X R+ !tv b+ D+ G- e+++ h+ r- y+
 */
public class BattlePlayer {

	private int pv = 50;
	private Image hero;

	public void init() throws SlickException {
		this.hero = new Image("battle/hero.png").getScaledCopy(2);
	}

	public void render(GameContainer container, Graphics g) {
		hero.drawCentered(container.getWidth() * 1 / 4, container.getHeight() / 2);
		Font font = g.getFont();
		String text = Integer.toString(pv);
		font.drawString(container.getWidth() * 1 / 4 - font.getWidth(text) / 2,
				container.getHeight() / 2 - hero.getHeight() / 2 - font.getLineHeight(), text,
				Color.white);
	}

	public int getPv() {
		return pv;
	}

	public void setPv(int pv) {
		this.pv = pv;
	}
}
