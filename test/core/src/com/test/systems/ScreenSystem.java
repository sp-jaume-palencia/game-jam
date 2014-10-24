package com.test.systems;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.test.screens.GameScreen;
import com.test.screens.Splashscreen;

public class ScreenSystem {

	public Splashscreen splash;
	public GameScreen gameplay;
	
	public Action fadeIn;
	
	float fadeOutTime = 0.25f;
    float fadeInTime = 0.25f;

	public void load()
	{
		splash = new Splashscreen();
		gameplay = new GameScreen();
		
		fadeIn = Actions.sequence(Actions.fadeOut(0.001f), Actions.fadeIn(fadeInTime), Actions.run(RootSystem.screens.onFadeIn));
	}
	
	public Runnable onFadeIn = new Runnable()
    {
        @Override
        public void run()
        {
            fadeIn = Actions.sequence(Actions.fadeOut(0.001f), Actions.fadeIn(fadeInTime), Actions.run(RootSystem.screens.onFadeIn));
        }
    };

}
