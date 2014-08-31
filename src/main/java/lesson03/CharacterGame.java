package lesson03;

import org.newdawn.slick.Animation;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
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
 *         GCS d- s+:+ a C++ UL/M P L+ E--- W++ N K- w-- M+ t+ 5 X R+ !tv b+ D+ G- e+++ h+ r- y+
 */
public class CharacterGame extends BasicGame {

    private GameContainer container;
    private TiledMap map;

    private float x = 300, y = 300;
    private int direction = 2;
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

        SpriteSheet spriteSheet = new SpriteSheet("sprites/character.png", 64, 64);
        this.animations[0] = loadAnimation(spriteSheet, 0, 1, 0);
        this.animations[1] = loadAnimation(spriteSheet, 0, 1, 1);
        this.animations[2] = loadAnimation(spriteSheet, 0, 1, 2);
        this.animations[3] = loadAnimation(spriteSheet, 0, 1, 3);
        this.animations[4] = loadAnimation(spriteSheet, 1, 9, 0);
        this.animations[5] = loadAnimation(spriteSheet, 1, 9, 1);
        this.animations[6] = loadAnimation(spriteSheet, 1, 9, 2);
        this.animations[7] = loadAnimation(spriteSheet, 1, 9, 3);
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
        g.setColor(new Color(0, 0, 0, .5f));
        g.fillOval(x - 16, y - 8, 32, 16);
        g.drawAnimation(animations[direction + (moving ? 4 : 0)], x - 32, y - 60);
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        if (this.moving) {
            switch (this.direction) {
            case 0:
                this.y -= .1f * delta;
                break;
            case 1:
                this.x -= .1f * delta;
                break;
            case 2:
                this.y += .1f * delta;
                break;
            case 3:
                this.x += .1f * delta;
                break;
            }
        }
    }

    @Override
    public void keyReleased(int key, char c) {
        this.moving = false;
        if (Input.KEY_ESCAPE == key) {
            this.container.exit();
        }
    }

    @Override
    public void keyPressed(int key, char c) {
        switch (key) {
        case Input.KEY_UP:
            this.direction = 0;
            this.moving = true;
            break;
        case Input.KEY_LEFT:
            this.direction = 1;
            this.moving = true;
            break;
        case Input.KEY_DOWN:
            this.direction = 2;
            this.moving = true;
            break;
        case Input.KEY_RIGHT:
            this.direction = 3;
            this.moving = true;
            break;
        }
    }
}
