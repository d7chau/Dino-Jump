package gdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import gdx.game.Buttons;

/**
 *
 * @author chaud2180
 */
public class ScrMenu implements Screen, InputProcessor {

    SpriteBatch batch;
    Texture txtbackground;
    int nBtnPlayX = 180, nBtnPlayY = 400, nBtnPlayWidth = 250, nBtnPlayHeight = 250;
    Buttons btnPlay, btnLeaderBoard, btnInfo;
    GamMain game;

    public ScrMenu(GamMain game) {
        this.game = game;
        batch = new SpriteBatch();
        txtbackground = new Texture("menuscreen.png");
        btnPlay = new Buttons(200, 300, 250, 250, "playbutton.png");
        btnLeaderBoard = new Buttons(200, 300, 180, 180, "leaderboardbutton.png");
        btnInfo = new Buttons(200, 300, 180, 180, "infobutton.png");
    }

    @Override
    public void show() {
        return;
    }

    @Override
    public void render(float delta) {
        batch.begin();
        batch.draw(txtbackground, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        btnPlay.draw(batch);
        btnLeaderBoard.draw(batch);
        btnInfo.draw(batch);
        
        batch.end();
        if (Gdx.input.isTouched()) {
            if (btnPlay.isMousedOver()) {
                game.setScreen(new ScrPlay(game));
            }
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

    @Override
    public boolean keyDown(int keycode) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean keyUp(int keycode) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean keyTyped(char character) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean scrolled(int amount) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
