package lesson15;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Code sous licence GPLv3 (http://www.gnu.org/licenses/gpl.html)
 *
 * @author <b>Shionn</b>, shionn@gmail.com <i>http://shionn.org</i><br>
 *         GCS d- s+:+ a+ C++ UL/M P L+ E--- W++ N K- w-- M+ t+ 5 X R+ !tv b+ D+ G- e+++ h+ r- y-
 */
public class TutoGameState extends BasicGameState {
    public static final int ID = 2;

    private GameContainer container;
    private Map map = new Map();
    private Player player = new Player(map);
    private TriggerController triggers = new TriggerController(map, player);
    private Camera camera = new Camera(player);
    private PlayerController controller = new PlayerController(player);
    private Hud hud = new Hud();

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
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
    public void render(GameContainer container, StateBasedGame game, Graphics g)
            throws SlickException {
        this.camera.place(container, g);
        this.map.renderBackground();
        this.player.render(g);
        this.map.renderForeground();
        this.hud.render(g);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta)
            throws SlickException {
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
    public int getID() {
        return ID;
    }

}
