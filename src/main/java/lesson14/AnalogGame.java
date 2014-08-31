/**
 * Code sous licence GPLv3 (http://www.gnu.org/licenses/gpl.html)
 *
 * @author <b>Shionn</b>, shionn@gmail.com <i>http://shionn.org</i><br>
 * GCS d- s+:+ a- C++ UL/M P L+ E--- W++ N K- w-- M+ t+ 5 X R+ !tv b+ D+ G- e+++ h+ r- !y-
 */
package lesson14;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;

/**
 * Code sous licence GPLv3 (http://www.gnu.org/licenses/gpl.html)
 * 
 * -Djava.library.path=target/natives
 * 
 * @author <b>Shionn</b>, shionn@gmail.com <i>http://shionn.org</i><br>
 *         GCS d- s+:+ a C++ UL/M P L+ E--- W++ N K- w-- M+ t+ 5 X R+ !tv b+ D+ G- e+++ h+ r- y+
 */
public class AnalogGame extends BasicGame {

    private GameContainer container;
    private Map map = new Map();
    private Player player = new Player(map);
    private TriggerController triggers = new TriggerController(map, player);
    private Camera camera = new Camera(player);
    private PlayerController controller = new PlayerController(player);
    private Hud hud = new Hud();

    public static void main(String[] args) throws SlickException {
        new AppGameContainer(new AnalogGame(), 800, 600, false).start();
    }

    public AnalogGame() {
        super("Lesson 14 :: AnalogGame");
    }

    @Override
    public void init(GameContainer container) throws SlickException {
        this.container = container;
        Music background = new Music("sound/lost-in-the-meadows.ogg");
        background.loop();
        this.map.init();
        this.player.init();
        this.hud.init();
        this.controller.setInput(container.getInput());
        container.getInput().addKeyListener(controller);
    }

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {
        this.camera.place(container, g);
        this.map.renderBackground();
        this.player.render(g);
        this.map.renderForeground();
        this.hud.render(g);
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        this.controller.update();
        this.triggers.update();
        this.player.update(delta);
        this.camera.update(container);
    }

    @Override
    public void keyReleased(int key, char c) {
        if (Input.KEY_ESCAPE == key) {
            this.container.exit();
        }
    }

    @Override
    public void keyPressed(int key, char c) {
    }
}
