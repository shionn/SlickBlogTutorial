package lesson11;

import org.newdawn.slick.Input;
import org.newdawn.slick.KeyListener;

/**
 * Code sous licence GPLv3 (http://www.gnu.org/licenses/gpl.html)
 * 
 * @author <b>Shionn</b>, shionn@gmail.com <i>http://shionn.org</i><br>
 *         GCS d- s+:+ a+ C++ UL/M P L+ E--- W++ N K- w-- M+ t+ 5 X R+ !tv b+ D+ G- e+++ h+ r- y-
 */
public class PlayerController implements KeyListener {

    private Player player;

    public PlayerController(Player player) {
        this.player = player;
    }

    @Override
    public void setInput(Input input) {

    }

    @Override
    public boolean isAcceptingInput() {
        return true;
    }

    @Override
    public void inputEnded() {

    }

    @Override
    public void inputStarted() {

    }

    @Override
    public void keyPressed(int key, char c) {
        switch (key) {
        case Input.KEY_UP:
            this.player.setDirection(0);
            this.player.setMoving(true);
            break;
        case Input.KEY_LEFT:
            this.player.setDirection(1);
            this.player.setMoving(true);
            break;
        case Input.KEY_DOWN:
            this.player.setDirection(2);
            this.player.setMoving(true);
            break;
        case Input.KEY_RIGHT:
            this.player.setDirection(3);
            this.player.setMoving(true);
            break;
        }
    }

    @Override
    public void keyReleased(int key, char c) {
        this.player.setMoving(false);
    }

}
