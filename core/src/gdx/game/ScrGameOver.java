/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 *
 * @author Dennis
 */
public class ScrGameOver implements Screen, InputProcessor {

    GamMain game;
    SpriteBatch batch;
    Texture txtgameoverscreen;
    Buttons btnHome, btnRetry;

    public ScrGameOver(GamMain game) {
        this.game = game;
        batch = new SpriteBatch();
        txtgameoverscreen = new Texture("gameoverscreen.png");
        btnHome = new Buttons(70, 350, 200, 200, "homebutton.png");
        btnRetry = new Buttons(330, 350, 200, 200, "retrybutton.png");
    }

    @Override
    public void show() {
        return;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(txtgameoverscreen, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        btnHome.draw(batch);
        if (Gdx.input.isTouched()) {
            if (btnHome.isMousedOver()) {
                game.setScreen(new ScrMenu(game));
            }
        }
        btnRetry.draw(batch);
        if (Gdx.input.isTouched()) {
                if (btnRetry.isMousedOver()) {
                    game.setScreen(new ScrPlay(game));
                }
          }
        batch.end();
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
