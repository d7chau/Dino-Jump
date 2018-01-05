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
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import java.util.Random;

/**
 *
 * @author chaud2180
 */
public class ScrPlay implements Screen, InputProcessor {

    Game game;
    SpriteBatch batch;
    Texture txtdino, txtbackground, txtplatform;
    ScrMenu main;
    Rectangle rectDino, rectPlatform;
    Sprite sprDino, sprPlatform;
    ShapeRenderer shapeRenderer;
    int nYDinoX = 100, nYDinoY = 200, nYDinoWidth = 75, nYDinoHeight = 100, nSpriteSpeed = 5, nPlatWidth = 200, nPlatHeight = 50;
    int arnPlatform[] = new int[10];

    public ScrPlay(Game game) {
        game = game;
        batch = new SpriteBatch();
        txtdino = new Texture("yellowdinoleft.png");
        txtbackground = new Texture("background1.png");
        txtplatform = new Texture("platform.png");
        sprDino = new Sprite(txtdino);
        sprPlatform = new Sprite(txtplatform);
        main = new ScrMenu();
        shapeRenderer = new ShapeRenderer();
        arnPlatform = CreatePlatforms();
    }

    public int[] CreatePlatforms() {
        int[] arnNewPlatforms = new int[10];
        for (int i = 0; i < arnNewPlatforms.length; i++) {
            Random random = new Random();
            int nPlatformRNG = random.nextInt(600 - 200);
            arnNewPlatforms[i] = nPlatformRNG;
        }
        return arnNewPlatforms;
    }

    public void HandleKeys() {
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            nYDinoX += nSpriteSpeed;
            if (nYDinoWidth > 0) {
                nYDinoWidth *= -1;
                nYDinoX -= nYDinoWidth;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            nYDinoX -= nSpriteSpeed;
            if (nYDinoWidth < 0) {
                nYDinoWidth *= -1;
                nYDinoX -= nYDinoWidth;
            }
        }
    }

    public void ScreenWrap() {
        if (nYDinoX > 650) {
            nYDinoX = -50;
        }
        if (nYDinoX < -50) {
            nYDinoX = Gdx.graphics.getWidth() + 50;
        }
    }

    @Override
    public void show() {
        return;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(txtbackground, 0, 0, 600, 1000); //background
        batch.draw(txtdino, nYDinoX, nYDinoY, nYDinoWidth, nYDinoHeight); //yellow dino
        for (int i = 1; i < arnPlatform.length; i++) { //10 platforms
            int nPlatformX = arnPlatform[i];
            int nPlatformY = 200 * i;
            batch.draw(txtplatform, nPlatformX, nPlatformY, nPlatWidth, nPlatHeight);
        }
        batch.end();

        HandleKeys();
        ScreenWrap();
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
