/**
 * Code sous licence GPLv3 (http://www.gnu.org/licenses/gpl.html)
 *
 * @author <b>Shionn</b>, shionn@gmail.com <i>http://shionn.org</i><br>
 * GCS d- s+:+ a- C++ UL/M P L+ E--- W++ N K- w-- M+ t+ 5 X R+ !tv b+ D+ G- e+++ h+ r- !y-
 */
package lesson10;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
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
public class ObjectsGame extends BasicGame {

    private GameContainer container;
    private TiledMap map;
    private float xCamera = 0, yCamera = 0;
    private boolean onStair;
    private Player player = new Player();

    public static void main(String[] args) throws SlickException {
        new AppGameContainer(new ObjectsGame(), 800, 600, false).start();
    }

    public ObjectsGame() {
        super("Lesson 10 :: ObjectsGame");
    }

    @Override
    public void init(GameContainer container) throws SlickException {
        this.container = container;
        this.map = new TiledMap("map/exemple-change-map.tmx");
        this.player.init();
        Music background = new Music("sound/lost-in-the-meadows.ogg");
        background.loop();
    }


    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {
        g.translate(container.getWidth() / 2 - (int) xCamera, container.getHeight() / 2
                - (int) yCamera);
        this.map.render(0, 0, 0);
        this.map.render(0, 0, 1);
        this.map.render(0, 0, 2);
        this.player.render(g);
        this.map.render(0, 0, 3);
        this.map.render(0, 0, 4);
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        updateTrigger();
        updateCharacter(delta);
        updateCamera(container);
    }

    private void updateTrigger() throws SlickException {
        this.onStair = false;
        for (int objectID = 0; objectID < this.map.getObjectCount(0); objectID++) {
            if (isInTrigger(objectID)) {
                if ("teleport".equals(this.map.getObjectType(0, objectID))) {
                    teleport(objectID);
                } else if ("stair".equals(this.map.getObjectType(0, objectID))) {
                    this.onStair = true;
                } else if ("change-map".equals(this.map.getObjectType(0, objectID))) {
                    changeMap(objectID);
                }
            }
        }
    }

    private boolean isInTrigger(int id) {
        return this.player.getX() > this.map.getObjectX(0, id)
                && this.player.getX() < this.map.getObjectX(0, id) + this.map.getObjectWidth(0, id)
                && this.player.getY() > this.map.getObjectY(0, id)
                && this.player.getY() < this.map.getObjectY(0, id)
                        + this.map.getObjectHeight(0, id);
    }

    private void teleport(int objectID) {
        this.player.setX(Float.parseFloat(this.map.getObjectProperty(0, objectID, "dest-x",
                Float.toString(this.player.getX()))));
        this.player.setY(Float.parseFloat(this.map.getObjectProperty(0, objectID, "dest-y",
                Float.toString(player.getY()))));
    }

    private void changeMap(int objectID) throws SlickException {
        teleport(objectID);
        String newMap = this.map.getObjectProperty(0, objectID, "dest-map", "undefined");
        if (!"undefined".equals(newMap)) {
            this.map = new TiledMap("map/" + newMap);
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

    private void updateCharacter(int delta) {
        if (this.player.isMoving()) {
            float futurX = getFuturX(delta);
            float futurY = getFuturY(delta);
            boolean collision = isCollision(futurX, futurY);
            if (collision) {
                this.player.setMoving(false);
            } else {
                this.player.setX(futurX);
                this.player.setY(futurY);
            }
        }
    }

    private boolean isCollision(float x, float y) {
        int tileW = this.map.getTileWidth();
        int tileH = this.map.getTileHeight();
        int logicLayer = this.map.getLayerIndex("logic");
        Image tile = this.map.getTileImage((int) x / tileW, (int) y / tileH, logicLayer);
        boolean collision = tile != null;
        if (collision) {
            Color color = tile.getColor((int) x % tileW, (int) y % tileH);
            collision = color.getAlpha() > 0;
        }
        return collision;
    }

    private float getFuturX(int delta) {
        float futurX = this.player.getX();
        switch (this.player.getDirection()) {
        case 1:
            futurX = this.player.getX() - .1f * delta;
            break;
        case 3:
            futurX = this.player.getX() + .1f * delta;
            break;
        }
        return futurX;
    }

    private float getFuturY(int delta) {
        float futurY = this.player.getY();
        switch (this.player.getDirection()) {
        case 0:
            futurY = this.player.getY() - .1f * delta;
            break;
        case 2:
            futurY = this.player.getY() + .1f * delta;
            break;
        case 1:
            if (this.onStair) {
                futurY = this.player.getY() + .1f * delta;
            }
            break;
        case 3:
            if (this.onStair) {
                futurY = this.player.getY() - .1f * delta;
            }

        }
        return futurY;
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
