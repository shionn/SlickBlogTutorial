package lesson17;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Code sous licence GPLv3 (http://www.gnu.org/licenses/gpl.html)
 * 
 * @author <b>Shionn</b>, shionn@gmail.com <i>http://shionn.org</i><br>
 *         GCS d- s+:+ a+ C++ UL/M P L+ E--- W++ N K- w-- M+ t+ 5 X R+ !tv b+ D+ G- e+++ h+ r- y+
 */
public class BattleEnnemy {
    private Image ennemy;

    public void init() throws SlickException {
        this.ennemy = new Image("battle/gobelin.png").getScaledCopy(2);
    }

    public void render(GameContainer container) {
        this.ennemy.drawCentered(container.getWidth() * 3 / 4, container.getHeight() / 2);
    }
}
