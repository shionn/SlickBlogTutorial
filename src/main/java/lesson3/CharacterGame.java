/**
 * Code sous licence GPLv3 (http://www.gnu.org/licenses/gpl.html)
 *
 * @author <b>Shionn</b>, shionn@gmail.com <i>http://shionn.org</i><br>
 * GCS d- s+:+ a- C++ UL/M P L+ E--- W++ N K- w-- M+ t+ 5 X R+ !tv b+ D+ G- e+++ h+ r- !y-
 */
package lesson3;

import org.newdawn.slick.Animation;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.tiled.TiledMap;

/**
 * Code sous licence GPLv3 (http://www.gnu.org/licenses/gpl.html)
 * 
 * -Djava.library.path=target/natives
 * 
 * @author <b>Shionn</b>, shionn@gmail.com <i>http://shionn.org</i><br>
 *         GCS d- s+:+ a- C++ UL/M P L+ E--- W++ N K- w-- M+ t+ 5 X R+ !tv b+ D+
 *         G- e+++ h+ r- !y-
 */
public class CharacterGame extends BasicGame {

    private GameContainer container;
    private TiledMap map;

    private int direction = 0;
    private boolean moving = false;
    private Animation[] animations = new Animation[8];

    public static void main(String[] args) throws SlickException {
        new AppGameContainer(new CharacterGame(), 800, 600, false).start();
    }

    public CharacterGame() {
        super("Lesson 3 :: CharacterGame");
    }

    @Override
    public void init(GameContainer container) throws SlickException {
        this.container = container;
        this.map = new TiledMap("map/exemple.tmx");

        SpriteSheet spriteSheet = new SpriteSheet("sprite/character.png", 576 / 9, 256 / 4);
        animations[0] = loadAnimation(spriteSheet, 0, 1, 0);
        animations[1] = loadAnimation(spriteSheet, 0, 1, 1);
        animations[2] = loadAnimation(spriteSheet, 0, 1, 2);
        animations[3] = loadAnimation(spriteSheet, 0, 1, 3);
        animations[4] = loadAnimation(spriteSheet, 1, 9, 0);
        animations[5] = loadAnimation(spriteSheet, 1, 9, 1);
        animations[6] = loadAnimation(spriteSheet, 1, 9, 2);
        animations[7] = loadAnimation(spriteSheet, 1, 9, 3);
    }

    private Animation loadAnimation(SpriteSheet spriteSheet, int startX, int endX, int y) {
        Animation animation = new Animation();
        for (int x = startX; x < endX; x++) {
            animation.addFrame(spriteSheet.getSprite(x, y), 100);
        }
        return animation;
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
