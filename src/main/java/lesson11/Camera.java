/**
 * 
 */
package lesson11;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

/**
 * Code sous licence GPLv3 (http://www.gnu.org/licenses/gpl.html)
 * 
 * @author <b>Shionn</b>, shionn@gmail.com <i>http://shionn.org</i><br>
 *         GCS d- s+:+ a+ C++ UL/M P L+ E--- W++ N K- w-- M+ t+ 5 X R+ !tv b+ D+ G- e+++ h+ r- y-
 */
public class Camera {

    private Player player;
    private float xCamera, yCamera;

    public Camera(Player player) {
        this.player = player;
        this.xCamera = player.getX();
        this.yCamera = player.getY();
    }

    public void place(GameContainer container, Graphics g) {
        g.translate(container.getWidth() / 2 - (int) this.xCamera, container.getHeight() / 2
                - (int) this.yCamera);
    }

    public void update(GameContainer container) {
        int w = container.getWidth() / 4;
        if (this.player.getX() > this.xCamera + w) {
            this.xCamera = this.player.getX() - w;
        } else if (this.player.getX() < this.xCamera - w) {
            this.xCamera = this.player.getX() + w;
        }
        int h = container.getHeight() / 4;
        if (this.player.getY() > this.yCamera + h) {
            this.yCamera = this.player.getY() - h;
        } else if (this.player.getY() < this.yCamera - h) {
            this.yCamera = this.player.getY() + h;
        }
    }

}
