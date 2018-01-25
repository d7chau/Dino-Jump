
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gdx.game;

import com.badlogic.gdx.math.Rectangle;

/**
 *
 * @author Dennis
 */
public class Platforms { //stores data
    int nX, nY, nHeight, nWidth;
    int nJumpedOn;
    Rectangle rectPlatform;

    public Platforms(int nX, int nY, int nHeight, int nWidth) {
        this.nX = nX;
        this.nY = nY;
        this.nWidth = nWidth;
        this.nHeight = nHeight;       
        this.nJumpedOn = 0;
        this.rectPlatform = new Rectangle((float)this.nX, (float)this.nY,(float)this.nWidth,(float)this.nHeight);
    }     
}
