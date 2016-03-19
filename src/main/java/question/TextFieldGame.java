package question;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.TextField;

/**
 * Code sous licence GPLv3 (http://www.gnu.org/licenses/gpl.html)
 * 
 * -Djava.library.path=target/natives
 * 
 * @author <b>Shionn</b>, shionn@gmail.com <i>http://shionn.org</i><br>
 *         GCS d- s+:+ a C++ UL/M P L+ E--- W++ N K- w-- M+ t+ 5 X R+ !tv b+ D+ G- e+++ h+ r- y+
 */
public class TextFieldGame extends BasicGame {

	private GameContainer container;
	private TextField textField;

	public static void main(String[] args) throws SlickException {
		new AppGameContainer(new TextFieldGame(), 800, 600, false).start();
	}

	public TextFieldGame() {
		super("TextFieldGame");
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		this.container = container;
		Font font = container.getDefaultFont();
		this.textField = new TextField(container, font, 50, 50, 200, font.getLineHeight() + 5);
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		this.textField.render(container, g);
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		System.out.println(this.textField.getText());
	}

	@Override
	public void keyReleased(int key, char c) {
		if (Input.KEY_ESCAPE == key) {
			container.exit();
		}
	}

}
