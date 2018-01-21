package gdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import gdx.game.Buttons;

/**
 *
 * @author chaud2180
 */
public class ScrMenu implements Screen, InputProcessor {

    SpriteBatch batch;
    Texture txtbackground;
    int nBtnPlayX = 180, nBtnPlayY = 400, nBtnPlayWidth = 250, nBtnPlayHeight = 250;
    Buttons btnPlay, btnLeaderBoard, btnInfo, btnGameOver;
    GamMain game;

    public ScrMenu(GamMain game) {
        this.game = game;
        batch = new SpriteBatch();
        txtbackground = new Texture("menuscreen1.png");
        btnPlay = new Buttons(175, 400, 250, 250, "playbutton.png");
        btnLeaderBoard = new Buttons(400, 220, 147, 147, "leaderboardbutton.png");
        btnInfo = new Buttons(30, 200, 180, 180, "infobutton.png");
        btnGameOver = new Buttons(250, 200, 120, 120, "gameoverbutton.png");
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
        btnGameOver.draw(batch);
        HandleButtons();
        batch.end();

    }

    public void HandleButtons() {
        if (Gdx.input.isTouched()) {
            if (btnPlay.isMousedOver()) {
                game.setScreen(new ScrPlay(game));
            } else if (btnInfo.isMousedOver()) {
                game.setScreen(new ScrInfo(game));
            } else if (btnLeaderBoard.isMousedOver()) {
                game.setScreen(new ScrLeaderBoard(game));
            } else if (btnGameOver.isMousedOver()) {
                game.setScreen(new ScrGameOver(game));
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
        txtbackground.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return true;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return true;
    }

    @Override
    public boolean scrolled(int amount) {
        return true;
    }
}
