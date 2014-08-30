package lesson13;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Code sous licence GPLv3 (http://www.gnu.org/licenses/gpl.html)
 * 
 * @author <b>Shionn</b>, shionn@gmail.com <i>http://shionn.org</i><br>
 *         GCS d- s+:+ a+ C++ UL/M P L+ E--- W++ N K- w-- M+ t+ 5 X R+ !tv b+ D+ G- e+++ h+ r- y-
 */
public class Hud {

    private static final int P_BAR_X = 10;
    private static final int P_BAR_Y = 10;
    private static final int BAR_X = 84 + P_BAR_X;
    private static final int LIFE_BAR_Y = 4 + P_BAR_Y;
    private static final int MANA_BAR_Y = 24 + P_BAR_Y;
    private static final int XP_BAR_Y = 44 + P_BAR_Y;
    private static final int BAR_WIDTH = 80;
    private static final int BAR_HEIGHT = 16;

    private static final Color LIFE_COLOR = new Color(255, 0, 0);
    private static final Color MANA_COLOR = new Color(0, 0, 255);
    private static final Color XP_COLOR = new Color(0, 255, 0);

    private Image playerbars;

    public void init() throws SlickException {
        this.playerbars = new Image("hud/player-bar.png");
    }

    public void render(Graphics g) {
        g.resetTransform();
        g.setColor(LIFE_COLOR);
        g.fillRect(BAR_X, LIFE_BAR_Y, .9f * BAR_WIDTH, BAR_HEIGHT);
        g.setColor(MANA_COLOR);
        g.fillRect(BAR_X, MANA_BAR_Y, .8f * BAR_WIDTH, BAR_HEIGHT);
        g.setColor(XP_COLOR);
        g.fillRect(BAR_X, XP_BAR_Y, .2f * BAR_WIDTH, BAR_HEIGHT);
        g.drawImage(playerbars, P_BAR_X, P_BAR_Y);
    }

}
