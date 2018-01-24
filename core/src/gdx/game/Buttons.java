/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 *
 * @author Dennis
 */
public class Buttons extends Sprite { //Learning how to implement buttons:
                                      //https://github.com/brauj1894/Risk/blob/master/core/src/gdx/game/objects/Button.java
    int nX, nY, nW, nH;

    public Buttons(int _nX, int _nY, int _nW, int _nH, String sFile) {
        super(new Texture(Gdx.files.internal(sFile)));
        nX = _nX;
        nY = _nY;
        nW = _nW;
        nH = _nH;
        setPosition(nX, nY);
        setFlip(false, false);
        setSize(nW, nH);
    }

    public boolean isMousedOver() { // Checks if the mouse is over the button, not whether the mouse was clicked
        if (Gdx.input.getX() > nX && Gdx.input.getX() < nX + nW) {
            if (Gdx.input.getY() * (-1) + Gdx.graphics.getHeight() > nY && Gdx.input.getY() * (-1) + Gdx.graphics.getHeight() < nY + nH) {
                return true;
            }
        }
        return false;
    }
}
