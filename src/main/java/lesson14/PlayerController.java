package lesson14;

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
    private Input input;

    public PlayerController(Player player) {
        this.player = player;
    }

    public void update() {
        if (input.getControllerCount() > 0) {
            player.setDx(input.getAxisValue(0, 1));
            player.setDy(input.getAxisValue(0, 2));
        }
    }

    @Override
    public void setInput(Input input) {
        this.input = input;
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
            this.player.setDy(-1);
            break;
        case Input.KEY_LEFT:
            this.player.setDx(-1);
            break;
        case Input.KEY_DOWN:
            this.player.setDy(1);
            break;
        case Input.KEY_RIGHT:
            this.player.setDx(1);
            break;
        }
    }

    @Override
    public void keyReleased(int key, char c) {
        switch (key) {
        case Input.KEY_UP:
        case Input.KEY_DOWN:
            this.player.setDy(0);
            break;
        case Input.KEY_LEFT:
        case Input.KEY_RIGHT:
            this.player.setDx(0);
            break;
        }
    }


}
