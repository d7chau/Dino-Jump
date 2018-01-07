package gdx.game;

import com.badlogic.gdx.Game;

public class GdxGame extends Game {
	
	@Override
	public void create () {
            setScreen(new ScrPlay(this));
	}

	@Override
	public void render () {
		super.render();
	}	
	
}
