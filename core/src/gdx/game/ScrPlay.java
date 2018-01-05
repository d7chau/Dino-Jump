/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 *
 * @author chaud2180
 */
public class ScrPlay implements Screen, InputProcessor {

    Game game;
    SpriteBatch batch;
    Texture txtdino, txtback;
    ScrMenu main;
    int nX = 100, nY = 150, nWidth = 100, nSpriteSpeed = 5;

    public ScrPlay(Game game) {
        game = game;
        batch = new SpriteBatch();
        txtdino = new Texture("yellowdinoleft.png");
        txtback = new Texture("background1.png");
        main = new ScrMenu();

    }

    @Override
    public void show() {
        return;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(txtback, 0, 0, 600, 1000); // background
        batch.draw(txtdino, nX, nY, nWidth, 150); //sprite        
        batch.end();

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            nX += nSpriteSpeed;
            if (nWidth > 0) {
                nWidth *= -1;
                nX-=nWidth;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            nX -= nSpriteSpeed;
            if (nWidth < 0) {
                nWidth *= -1 ;
                nX-=nWidth;
            }
        }
        
        if (nX > 750) {
            nX = -150;
        }
        if (nX < -150) {
            nX = Gdx.graphics.getWidth() + 150;
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
        batch.dispose();
        txtdino.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
