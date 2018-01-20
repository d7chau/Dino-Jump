/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import gdx.game.Buttons;

/**
 *
 * @author Dennis
 */
public class ScrInfo implements Screen, InputProcessor {

    GamMain game;
    SpriteBatch batch;
    Texture txtinfoscreen0,txtinfoscreen1, txtinfoscreen2, txtinfoscreen3;
    Buttons btnForward0, btnForward1, btnForward2, btnHome;
    int nScreen = 0;

    public ScrInfo(GamMain game) {
        this.game = game;
        batch = new SpriteBatch();
        txtinfoscreen0 = new Texture("infoscreen0.png");
        txtinfoscreen1 = new Texture("infoscreen1.png");
        txtinfoscreen2 = new Texture("infoscreen2.png");
        txtinfoscreen3 = new Texture("infoscreen3.png");
        btnForward0 = new Buttons(480, 900, 75, 75, "forwardbutton0.png");
        btnForward1 = new Buttons(480, 10, 75, 75, "forwardbutton1.png");
        btnForward2 = new Buttons(495, 900, 75, 75, "forwardbutton2.png");
        btnHome = new Buttons(480, 10, 75, 75, "homebutton.png");
    }

    @Override
    public void show() {
        return;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        if(nScreen == 0){
            batch.draw(txtinfoscreen0, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            btnForward0.draw(batch);
            if (Gdx.input.isTouched()) {
                if (btnForward0.isMousedOver()) {
                    nScreen = 1;
                }
            }
        } 
        
        if (nScreen == 1) {
            batch.draw(txtinfoscreen1, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            btnForward1.draw(batch);

            if (Gdx.input.isTouched()) {
                if (btnForward1.isMousedOver()) {
                    nScreen = 2;
                }
            }
        }

        if (nScreen == 2) {
            batch.draw(txtinfoscreen2, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            btnForward2.draw(batch);

            if (Gdx.input.isTouched()) {
                if (btnForward2.isMousedOver()) {
                    nScreen = 3;
                }
            }
        }

        if (nScreen == 3) {
            batch.draw(txtinfoscreen3, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            btnHome.draw(batch);
            
            if (Gdx.input.isTouched()) {
                if (btnHome.isMousedOver()) {
                    game.setScreen(new ScrMenu(game));
                }
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
        txtinfoscreen1.dispose();
        txtinfoscreen2.dispose();
        txtinfoscreen3.dispose();
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