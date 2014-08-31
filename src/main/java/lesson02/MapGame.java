package lesson02;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

/**
 * Code sous licence GPLv3 (http://www.gnu.org/licenses/gpl.html)
 * 
 * -Djava.library.path=target/natives
 * 
 * @author <b>Shionn</b>, shionn@gmail.com <i>http://shionn.org</i><br>
 *         GCS d- s+:+ a C++ UL/M P L+ E--- W++ N K- w-- M+ t+ 5 X R+ !tv b+ D+ G- e+++ h+ r- y+
 */
public class MapGame extends BasicGame {

    private GameContainer container;
    private TiledMap map;

    public static void main(String[] args) throws SlickException {
        new AppGameContainer(new MapGame(), 800, 600, false).start();
    }

    public MapGame() {
        super("Lesson 2 :: MapGame");
    }

    @Override
    public void init(GameContainer container) throws SlickException {
        this.container = container;
        this.map = new TiledMap("map/exemple.tmx");
    }

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {
        this.map.render(0, 0);
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
    }

    @Override
    public void keyReleased(int key, char c) {
        if (Input.KEY_ESCAPE == key) {
            this.container.exit();
        }
    }

}
