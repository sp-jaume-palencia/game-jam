package com.test.systems;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.test.screens.Splashscreen;
//import com.test.screens.Menuscreen;

public class ScreenSystem {

	public Splashscreen splash;
//	public Menuscreen menu;
	
	public Action showGame;
	public Action fadeIn;
	
	float fadeOutTime = 0.5f;
    float fadeInTime = 0.5f;

	public void load()
	{
		splash = new Splashscreen();
		//menu = new Menuscreen();
		
		showGame = Actions.sequence(Actions.fadeOut(fadeOutTime), Actions.run(RootSystem.screens.onActionGame));
		fadeIn = Actions.sequence(Actions.fadeOut(0.0f), Actions.fadeIn(fadeInTime), Actions.run(RootSystem.screens.onFadeIn));
	}
	
	public Runnable onFadeIn = new Runnable()
    {
        @Override
        public void run()
        {
            fadeIn = Actions.sequence(Actions.fadeOut(0.0f), Actions.fadeIn(fadeInTime), Actions.run(RootSystem.screens.onFadeIn));
        }
    };
    
    public Runnable onActionGame = new Runnable()
    {
        @Override
        public void run()
        {
//        	RootSystem.game.setScreen(RootSystem.screens.menu);
            showGame = Actions.sequence(Actions.fadeOut(fadeOutTime), Actions.run(RootSystem.screens.onActionGame));
            fadeIn = Actions.sequence(Actions.fadeOut(0.001f), Actions.fadeIn(fadeInTime), Actions.run(RootSystem.screens.onFadeIn));
        }
    };

}
