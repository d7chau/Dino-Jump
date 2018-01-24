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
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
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
    Texture txtdino, txtbackground, txtplatform, txtspring, txttrampoline;
    ScrMenu main;
    Rectangle rectDino, rectPlatform, rectSpring, rectTrampoline;
    Sprite sprDino, sprPlatform, sprSpring, sprTrampoline;
    ShapeRenderer shapeRenderer;
    BitmapFont bmFontScore;
    int nYDinoX = 100, nYDinoY = 200, nYDinoWidth = 75, nYDinoHeight = 100, nPlatWidth = 200, nPlatHeight = 50, nSpringHeight = 35, nSpringWidth = 100, nTrampHeight = 35, nTrampWidth = 150;
    int nSpriteSpeed = 5, nCountJump = 0, nCountOverlap = 0, nCountScore = 0;
    Platform arnPlatform[] = new Platform[5];
    boolean bCanFall = true, bCanJump = true;
    double dGravity = 0.5, dFallSpeed = 0, dJumpSpeed = 20;
    Random random = new Random();
    int nRNG = random.nextInt(4 + 1 - 1) + 1, nTrampOrSpring = random.nextInt(2 + 1 - 1) + 1; //for a range of numbers not starting at 0 (max + 1 - min) + min; 
    String sScore;

    public ScrPlay(Game game) {
        game = game;
        batch = new SpriteBatch();
        txtdino = new Texture("yellowdinoleft.png");
        txtbackground = new Texture("playscreen.png");
        txtplatform = new Texture("platform.png");
        txtspring = new Texture("spring.png");
        txttrampoline = new Texture("trampoline.png");
        sprDino = new Sprite(txtdino);
        sprPlatform = new Sprite(txtplatform);
        sprSpring = new Sprite(txtspring);
        sprTrampoline = new Sprite(txttrampoline);
        main = new ScrMenu();
        shapeRenderer = new ShapeRenderer();
        arnPlatform = CreatePlatforms();
        bmFontScore = new BitmapFont(Gdx.files.internal("fonts/score.fnt"));
        bmFontScore.setColor(Color.GRAY);
        sScore = "0";
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
        for (int i = 1; i < arnPlatform.length; i++) { // platforms
            batch.draw(txtplatform, arnPlatform[i].nX, arnPlatform[i].nY, arnPlatform[i].nWidth, arnPlatform[i].nHeight);
        }
        if (nTrampOrSpring == 1) {
            batch.draw(txtspring, arnPlatform[nRNG].nX, arnPlatform[nRNG].nY + 50, nSpringWidth, nSpringHeight); //spring
        }
        if (nTrampOrSpring == 2) {
            batch.draw(txttrampoline, arnPlatform[nRNG].nX, arnPlatform[nRNG].nY + 50, nTrampWidth, nTrampHeight);  //trampoline
        }
        GlyphLayout glScore = new GlyphLayout(bmFontScore, sScore);       // Learning how to use bitmap fonts:
        bmFontScore.draw(batch, glScore, 190, 984);                       //https://www.youtube.com/watch?v=cGqq59-Kd7Y
        batch.end();                                                      //https://www.youtube.com/watch?v=xMxtf0sJvr4&t=9s

        HandleKeys();
        ScreenWrap();
        PlatHD();                //HD stands for hit detection
        HandleJumping();
        HandleFalling();
        SpringHD();              //HD stands for hit detection
        TrampHD();               //HD stands for hit detection
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

    public Platform[] CreatePlatforms() {
        Platform[] arnNewPlatforms = new Platform[5];
        for (int i = 0; i < arnNewPlatforms.length; i++) {
            Random random = new Random();
            int nPlatformRNG = random.nextInt(Gdx.graphics.getWidth() - 200);
            arnNewPlatforms[i] = new Platform(nPlatformRNG, 200 * i, nPlatHeight, nPlatWidth);
        }
        return arnNewPlatforms;
    }

    public void PlatHD() {
        sprDino.setSize(nYDinoWidth, nYDinoHeight);
        sprDino.setPosition(nYDinoX, nYDinoY);
        rectDino = new Rectangle(sprDino.getBoundingRectangle());

        for (int i = 0; i < arnPlatform.length; i++) {
            boolean isOverlapping = rectDino.overlaps(arnPlatform[i].rectPlatform);
            if (isOverlapping) {
                bCanJump = true;
                bCanFall = false;
                dFallSpeed = 0;
                arnPlatform[i].nJumpedOn += 1;
            }
            if (isOverlapping && arnPlatform[i].nJumpedOn == 1) { //if it is overlapping and hasnt been touched before
                nCountScore += 10;
                sScore = "" + nCountScore;
            }

        }
    }

    public void HandleJumping() {
        if (bCanJump) {
            nCountJump++;
            nYDinoY += dJumpSpeed;
            dJumpSpeed -= dGravity;
            if (nCountJump >= 40) {
                bCanJump = false;
                bCanFall = true;
                dJumpSpeed = 20;
                nCountJump = 0;
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

    public void SpringHD() {
        if (nTrampOrSpring == 1) {
            sprSpring.setSize(nSpringWidth, nSpringHeight);
            sprSpring.setPosition(arnPlatform[nRNG].nX, arnPlatform[nRNG].nY + 50);
            rectSpring = new Rectangle(sprSpring.getBoundingRectangle());

            boolean isOverlapping = rectDino.overlaps(rectSpring);
            if (isOverlapping) {
                bCanJump = true;
                bCanFall = false;
                dFallSpeed = 0;
                dJumpSpeed = 25;
            }
        }
    }

    public void TrampHD() {
        if (nTrampOrSpring == 2) {
            sprTrampoline.setSize(nTrampWidth, nTrampHeight);
            sprTrampoline.setPosition(arnPlatform[nRNG].nX, arnPlatform[nRNG].nY + 50);
            rectTrampoline = new Rectangle(sprTrampoline.getBoundingRectangle());

            boolean isOverlapping = rectDino.overlaps(rectTrampoline);
            if (isOverlapping) {
                bCanJump = true;
                bCanFall = false;
                dFallSpeed = 0;
                dJumpSpeed = 30;
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
        batch.dispose();
        bmFontScore.dispose();
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
