package gdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 *
 * @author chaud2180
 */
public class ScrMenu implements Screen {

    SpriteBatch batch;
    Texture txtbackground, txtBtnInfo, txtBtnShop, txtBtnPlay;
    Sprite sprBtnPlay;
    int nBtnPlayX = 180, nBtnPlayY = 400, nBtnPlayWidth = 250, nBtnPlayHeight = 250;
    boolean isClick = false;
    GamMain game;

    public ScrMenu(GamMain game) {
        this.game = game;
        batch = new SpriteBatch();
        txtbackground = new Texture("menuscreen.png");
        txtBtnInfo = new Texture("infobutton.png");
        txtBtnShop = new Texture("shopbutton.png");
        txtBtnPlay = new Texture("playbutton.png");
        sprBtnPlay = new Sprite(txtBtnPlay);
        sprBtnPlay.setPosition(nBtnPlayX, nBtnPlayY);
        sprBtnPlay.setSize(nBtnPlayWidth, nBtnPlayHeight);
        sprBtnPlay.setX(180);
        sprBtnPlay.setY(400);
    }

    @Override
    public void show() {
        return;
    }

    @Override
    public void render(float delta) {
        batch.begin();
        batch.draw(txtbackground, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(sprBtnPlay, sprBtnPlay.getX(), sprBtnPlay.getY(), sprBtnPlay.getWidth(), sprBtnPlay.getHeight());
        batch.draw(txtBtnShop, 50, 200, 175, 175);
        batch.draw(txtBtnInfo, 380, 200, 175, 175);
        batch.end();
       if (Gdx.input.justTouched()) {
           
       }
    }

    @Override
    public void resize(int width, int height) {
        return;
    }

    @Override
    public void pause() {
        return;
    }

    @Override
    public void resume() {
        return;
    }

    @Override
    public void hide() {
        return;
    }

    @Override
    public void dispose() {
        return;
    }
}
