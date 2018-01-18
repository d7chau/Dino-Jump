/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class ScrMenu implements Screen, InputProcessor {

    SpriteBatch batch;
    Texture txtbackground, txtBtnInfo, txtBtnShop, txtBtnPlay;
    Sprite sprBtnPlay;
    int nBtnPlayX = 180, nBtnPlayY = 400, nBtnPlayWidth = 250, nBtnPlayHeight = 250;
    boolean isClick = false;
    GdxGame game;

    public ScrMenu(GdxGame game) {
        this.game = game;
        batch = new SpriteBatch();
        Gdx.input.setInputProcessor((this));
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
        batch.draw(sprBtnPlay, sprBtnPlay.getX(), sprBtnPlay.getY(), sprBtnPlay.getWidth(),sprBtnPlay.getHeight());
        batch.draw(txtBtnShop, 50, 200, 175, 175);
        batch.draw(txtBtnInfo, 380, 200, 175, 175);
        batch.end();
        if (isClick) {
            System.out.println("clicked");
        }
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (screenX > sprBtnPlay.getX() && screenX < sprBtnPlay.getX() + sprBtnPlay.getWidth() && screenY > sprBtnPlay.getY() && screenY > sprBtnPlay.getY() + sprBtnPlay.getHeight()) {
            isClick = true;
        }
        return true;
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
