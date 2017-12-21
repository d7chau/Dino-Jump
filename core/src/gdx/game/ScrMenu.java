/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 *
 * @author chaud2180
 */
public class ScrMenu {
    SpriteBatch batch;
    Texture texture;
    
    public void createshape() {
        batch = new SpriteBatch();
        texture = new Texture("badlogic.jpg");
        batch.begin();        
        batch.draw(texture, 100, 100);
        batch.draw(texture, 69, 420);
        batch.draw(texture, 663, 699);
        batch.draw(texture, 329, 165);
        batch.draw(texture, 544, 135);
        batch.end();
    }
}
