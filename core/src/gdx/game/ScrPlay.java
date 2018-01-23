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
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import java.util.ArrayList;
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
    public OrthographicCamera camera;
    int nYDinoX = 100, nYDinoY = 200, nYDinoWidth = 75, nYDinoHeight = 100, nSpriteSpeed = 5, nPlatWidth = 200, nPlatHeight = 50, nCountJump = 0, nCountOverlap = 0;
    int arnPlatform[] = new int[5], arnTempPlatform[] = new int[5];
    boolean bCanFall = true, bCanJump = true;
    double dGravity = 0.5, dFallSpeed = 0, dJumpSpeed = 17;

    public ScrPlay(Game game) {
        game = game;
        batch = new SpriteBatch();
        txtdino = new Texture("yellowdinoleft.png");
        txtbackground = new Texture("background1.png");
        txtplatform = new Texture("platform.png");
        sprDino = new Sprite(txtdino);
        sprPlatform = new Sprite(txtplatform);
        main = new ScrMenu();
        camera = new OrthographicCamera();
        camera.setToOrtho(false);
        camera.position.set(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 0);
        shapeRenderer = new ShapeRenderer();
        arnPlatform = CreatePlatforms();
    }

    public int[] CreatePlatforms() {
        int[] arnNewPlatforms = new int[5];
        for (int i = 0; i < arnNewPlatforms.length; i++) {
            Random random = new Random();
            int nPlatformRNG = random.nextInt(Gdx.graphics.getWidth() - 150);
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
        if (nYDinoX > Gdx.graphics.getWidth() + 50) {
            nYDinoX = -50;
        }
        if (nYDinoX < -50) {
            nYDinoX = Gdx.graphics.getWidth() + 50;
        }
    }

    public void HitDetection() {
        sprDino.setSize(nYDinoWidth, nYDinoHeight);
        sprDino.setPosition(nYDinoX, nYDinoY);
        rectDino = new Rectangle(sprDino.getX(), sprDino.getY(), sprDino.getWidth(), sprDino.getHeight());
        for (int i = 1; i < arnPlatform.length; i++) {
            int nPlatformX = arnPlatform[i];
            int nPlatformY = 250 * i;
            Rectangle arnRectPlatform[] = new Rectangle[5];
            sprPlatform.setSize(nPlatWidth, nPlatHeight);
            sprPlatform.setPosition(nPlatformX, nPlatformY);
            rectPlatform = new Rectangle(sprPlatform.getX(), sprPlatform.getY(), sprPlatform.getWidth(), sprPlatform.getHeight());
            arnRectPlatform[i] = rectPlatform;
            boolean isOverlapping = rectDino.overlaps(arnRectPlatform[i]);
            if (isOverlapping) {
                bCanJump = true;
                bCanFall = false;
                dFallSpeed = 0;
            }
            while (rectPlatform.getY() <= camera.position.y - (Gdx.graphics.getHeight() / 2)) {
                nPlatformX = arnPlatform[i];
                nPlatformY = nPlatformY + 1000;
                sprPlatform.setSize(nPlatWidth, nPlatHeight);
                sprPlatform.setPosition(nPlatformX, nPlatformY);
                rectPlatform = new Rectangle(sprPlatform.getX(), sprPlatform.getY(), sprPlatform.getWidth(), sprPlatform.getHeight());
                arnRectPlatform[i] = rectPlatform;
                isOverlapping = rectDino.overlaps(arnRectPlatform[i]);
                if (isOverlapping) {
                    bCanJump = true;
                    bCanFall = false;
                    dFallSpeed = 0;
                }
            }
        }
    }

    public void HandleFalling() {
        if (bCanFall) {
            nYDinoY -= dFallSpeed;
            dFallSpeed += dGravity;
        }
        if (nYDinoY <= 100) {
            bCanJump = true;
            bCanFall = false;
            dFallSpeed = 0;
        }
    }

    public void HandleJumping() {
        if (bCanJump) {
            nCountJump++;
            nYDinoY += dJumpSpeed;
            dJumpSpeed -= dGravity;
            if (nYDinoY >= camera.position.y) {
                camera.position.y += dJumpSpeed;
            }
            if (nCountJump >= 35) {
                bCanJump = false;
                bCanFall = true;
                dJumpSpeed = 17;
                nCountJump = 0;
            }
        }
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.begin();
        batch.draw(txtbackground, 0, camera.position.y - 500, 600, 1000); //background
        batch.draw(txtdino, nYDinoX, nYDinoY, nYDinoWidth, nYDinoHeight); //yellow dino
        for (int i = 1; i < arnPlatform.length; i++) { // platforms
            int nPlatformX = arnPlatform[i];
            int nPlatformY = 250 * i;
            batch.draw(sprPlatform, nPlatformX, nPlatformY, nPlatWidth, nPlatHeight);
            while (nPlatformY <= camera.position.y - 500) {
                nPlatformX = arnPlatform[i];
                nPlatformY = nPlatformY + 1000;
                batch.draw(sprPlatform, nPlatformX, nPlatformY, nPlatWidth, nPlatHeight);
            }
        }
        batch.end();
        //while (nPlatformY <= camera.position.y - 500) {
        //}
        if (nYDinoY <= camera.position.y - 500) {
            System.out.println("Game Over");
        }
        batch.setProjectionMatrix(camera.combined);
        HandleKeys();
        ScreenWrap();
        HitDetection();
        HandleJumping();
        HandleFalling();
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
