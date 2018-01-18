package gdx.game;

import com.badlogic.gdx.Game;

public class GamMain extends Game {
    ScrMenu ScrMenu;
    ScrPlay ScrPlay;

    @Override
    public void create() {
        ScrMenu = new ScrMenu(this);
        ScrPlay = new ScrPlay(this);
        setScreen(ScrMenu);
    }

    @Override
    public void render() {
        super.render();
    }
}

