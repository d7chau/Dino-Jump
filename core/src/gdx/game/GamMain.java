package gdx.game;

import com.badlogic.gdx.Game;

public class GamMain extends Game {

    @Override
    public void create() {
        this.setScreen(new ScrMenu(this));
    }

    @Override
    public void render() {
        super.render();
    }
}