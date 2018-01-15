/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 *
 * @author chaud2180
 */
public class ScrMenu {

    SpriteBatch batch;
    Texture txtbackground, txtbuttoninfo, txtbuttonshop, txtbuttonplay;

    public void create() {
        batch = new SpriteBatch();
        txtbackground = new Texture("background1.png");
        txtbuttoninfo = new Texture("infobutton.png");
        txtbuttonshop = new Texture("shopbutton.png");
        txtbuttonplay = new Texture("playbutton.png");
        batch.begin();
        batch.draw(txtbackground, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(txtbuttonplay, Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2, 250, 250);
        batch.draw(txtbuttonshop, 150, 100, 100, 100 );
        batch.draw(txtbuttoninfo, 450, 100, 100, 100 );
        batch.end();
    }
}
