/**
 * Code sous licence GPLv3 (http://www.gnu.org/licenses/gpl.html)
 *
 * @author <b>Shionn</b>, shionn@gmail.com <i>http://shionn.org</i><br>
 * GCS d- s+:+ a- C++ UL/M P L+ E--- W++ N K- w-- M+ t+ 5 X R+ !tv b+ D+ G- e+++ h+ r- !y-
 */
package lesson10;

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
public class ObjectsGame extends BasicGame {

    private GameContainer container;
    private Map map = new Map();
    private Player player = new Player(map);
    private float xCamera = player.getX(), yCamera = player.getY();

    public static void main(String[] args) throws SlickException {
        new AppGameContainer(new ObjectsGame(), 800, 600, false).start();
    }

    public ObjectsGame() {
        super("Lesson 10 :: ObjectsGame");
    }

    @Override
    public void init(GameContainer container) throws SlickException {
        this.container = container;
        this.map.init();
        this.player.init();
        Music background = new Music("sound/lost-in-the-meadows.ogg");
        background.loop();
    }

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {
        g.translate(container.getWidth() / 2 - (int) xCamera, container.getHeight() / 2
                - (int) yCamera);
        this.map.renderBackground();
        this.player.render(g);
        this.map.renderForeground();
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        updateTrigger();
        this.player.update(delta);
        updateCamera(container);
    }

    private void updateTrigger() throws SlickException {
        this.player.setOnStair(false);
        for (int objectID = 0; objectID < this.map.getObjectCount(); objectID++) {
            if (isInTrigger(objectID)) {
                if ("teleport".equals(this.map.getObjectType(objectID))) {
                    teleport(objectID);
                } else if ("stair".equals(this.map.getObjectType(objectID))) {
                    this.player.setOnStair(true);
                } else if ("change-map".equals(this.map.getObjectType(objectID))) {
                    changeMap(objectID);
                }
            }
        }
    }

    private boolean isInTrigger(int id) {
        return this.player.getX() > this.map.getObjectX(id)
                && this.player.getX() < this.map.getObjectX(id) + this.map.getObjectWidth(id)
                && this.player.getY() > this.map.getObjectY(id)
                && this.player.getY() < this.map.getObjectY(id) + this.map.getObjectHeight(id);
    }

    private void teleport(int objectID) {
        this.player.setX(Float.parseFloat(this.map.getObjectProperty(objectID, "dest-x",
                Float.toString(this.player.getX()))));
        this.player.setY(Float.parseFloat(this.map.getObjectProperty(objectID, "dest-y",
                Float.toString(this.player.getY()))));
    }

    private void changeMap(int objectID) throws SlickException {
        teleport(objectID);
        String newMap = this.map.getObjectProperty(objectID, "dest-map", "undefined");
        if (!"undefined".equals(newMap)) {
            this.map.changeMap("map/" + newMap);
        }
    }

    private void updateCamera(GameContainer container) {
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

    @Override
    public void keyReleased(int key, char c) {
        this.player.setMoving(false);
        if (Input.KEY_ESCAPE == key) {
            this.container.exit();
        }
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
}
