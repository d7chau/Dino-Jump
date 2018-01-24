/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
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
public class ScrPlay implements Screen {

    GamMain game;
    SpriteBatch batch;
    Texture txtdino, txtbackground, txtplatform, txtspring, txttrampoline;
    Rectangle rectDino, rectPlatform, rectSpring, rectTrampoline;
    Sprite sprDino, sprPlatform, sprSpring, sprTrampoline;
    ShapeRenderer shapeRenderer;
    public OrthographicCamera camera;
    BitmapFont bmFontScore;
    int nYDinoX = 100, nYDinoY = 200, nYDinoWidth = 75, nYDinoHeight = 100, nPlatWidth = 200, nPlatHeight = 50, nSpringHeight = 35, nSpringWidth = 100, nTrampHeight = 35, nTrampWidth = 150;
    int nSpriteSpeed = 5, nCountJump = 0, nCountOverlap = 0, nCountScore = 0;
    Platforms arnPlatform[] = new Platforms[5];
    boolean bCanFall = true, bCanJump = true;
    double dGravity = 0.5, dFallSpeed = 0, dJumpSpeed = 20;
    Random random = new Random();
    int nRNG = random.nextInt(4 + 1 - 1) + 1, nTrampOrSpring = random.nextInt(2 + 1 - 1) + 1; //for a range of numbers not starting at 0 (max + 1 - min) + min; 
    String sScore;

    public ScrPlay(GamMain game) {
        this.game = game;
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
        camera = new OrthographicCamera();
        camera.setToOrtho(false);
        camera.position.set(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 0);
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
        camera.update();
        batch.begin();
        batch.draw(txtbackground, 0, camera.position.y - 500, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()); //background
        batch.draw(txtdino, nYDinoX, nYDinoY, nYDinoWidth, nYDinoHeight); //yellow dino
        for (int i = 1; i < arnPlatform.length; i++) { // platforms
            batch.draw(txtplatform, arnPlatform[i].nX, arnPlatform[i].nY, arnPlatform[i].nWidth, arnPlatform[i].nHeight);
        if (arnPlatform[i].nY <= camera.position.y - 500) {
                batch.draw(sprPlatform, arnPlatform[i].nX, arnPlatform[i].nY + 1000, arnPlatform[i].nWidth, arnPlatform[i].nHeight);
            }
        }
        if (nTrampOrSpring == 1) {
            batch.draw(sprSpring, arnPlatform[nRNG].nX, arnPlatform[nRNG].nY + 50, nSpringWidth, nSpringHeight); //spring
            if (arnPlatform[nRNG].nY <= camera.position.y - 500) {
                batch.draw(sprSpring, arnPlatform[nRNG].nX, arnPlatform[nRNG].nY + 1050, nSpringWidth, nSpringHeight); //spring
            }
        }
        if (nTrampOrSpring == 2) {
            batch.draw(sprTrampoline, arnPlatform[nRNG].nX, arnPlatform[nRNG].nY + 50, nTrampWidth, nTrampHeight);  //trampoline
            if (arnPlatform[nRNG].nY <= camera.position.y - 500) {
                batch.draw(sprTrampoline, arnPlatform[nRNG].nX, arnPlatform[nRNG].nY + 1050, nTrampWidth, nTrampHeight);  //trampoline
            }
        }

        GlyphLayout glScore = new GlyphLayout(bmFontScore, sScore);
        bmFontScore.draw(batch, glScore, 190, camera.position.y + 483);
        batch.end();
        batch.setProjectionMatrix(camera.combined);
        if (nYDinoY <= camera.position.y - 500){
            game.setScreen(new ScrGameOver(game));
        }
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

    public Platforms[] CreatePlatforms() {
        Platforms[] arnNewPlatforms = new Platforms[5];
        for (int i = 0; i < arnNewPlatforms.length; i++) {
            Random random = new Random();
            int nPlatformRNG = random.nextInt(Gdx.graphics.getWidth() - 200);
            arnNewPlatforms[i] = new Platforms(nPlatformRNG, 250 * i, nPlatHeight, nPlatWidth);
        }
        return arnNewPlatforms;
    }

    public void PlatHD() {
        sprDino.setSize(nYDinoWidth, nYDinoHeight);
        sprDino.setPosition(nYDinoX, nYDinoY);
        rectDino = new Rectangle(sprDino.getBoundingRectangle());
        for (int i = 0; i < arnPlatform.length; i++) {
            Rectangle arnRectPlatform[] = new Rectangle[5];
            boolean isOverlapping = rectDino.overlaps(arnPlatform[i].rectPlatform);
            if (isOverlapping) {
                bCanJump = true;
                bCanFall = false;
                dFallSpeed = 0;
                arnPlatform[i].nJumpedOn += 1;
            }
            if (arnPlatform[i].nY <= camera.position.y - 500) {
                sprPlatform.setSize(arnPlatform[i].nWidth, arnPlatform[i].nHeight);
                sprPlatform.setPosition(arnPlatform[i].nX, arnPlatform[i].nY + 1000);
                rectPlatform = new Rectangle(sprPlatform.getBoundingRectangle());
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

    public void HandleJumping() {
        if (bCanJump) {
            nCountJump++;
            nYDinoY += dJumpSpeed;
            dJumpSpeed -= dGravity;
            if (nYDinoY >= camera.position.y) {
                camera.position.y += dJumpSpeed;
                nCountScore += 10;
                sScore = "" + nCountScore;
            }

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
            if (arnPlatform[nRNG].nY <= camera.position.y - 500) {
                sprSpring.setSize(nSpringWidth, nSpringHeight);
                sprSpring.setPosition(arnPlatform[nRNG].nX, arnPlatform[nRNG].nY + 1050);
                rectSpring = new Rectangle(sprSpring.getBoundingRectangle());
                isOverlapping = rectDino.overlaps(rectSpring);
                if (isOverlapping) {
                    bCanJump = true;
                    bCanFall = false;
                    dFallSpeed = 0;
                    dJumpSpeed = 25;
                }
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
            if (arnPlatform[nRNG].nY <= camera.position.y - 500) {
                sprSpring.setSize(nSpringWidth, nSpringHeight);
                sprSpring.setPosition(arnPlatform[nRNG].nX, arnPlatform[nRNG].nY + 1050);
                rectSpring = new Rectangle(sprSpring.getBoundingRectangle());
                isOverlapping = rectDino.overlaps(rectSpring);
                if (isOverlapping) {
                    bCanJump = true;
                    bCanFall = false;
                    dFallSpeed = 0;
                    dJumpSpeed = 30;
                }
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
        txtdino.dispose();
        txtbackground.dispose();
        txtplatform.dispose();
        txtspring.dispose();
        txttrampoline.dispose();
        bmFontScore.dispose();
    }
}
