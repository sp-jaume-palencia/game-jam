package com.test.systems;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.test.game.TestGame;
import com.test.screens.MainMenu;
import com.test.screens.Splashscreen;

public class ScreenSystem {

	public Splashscreen splash;
	public MainMenu mainMenu;
	
	public Action showGame;
	public Action fadeIn;
	
	float fadeOutTime = 0.25f;
    float fadeInTime = 0.25f;

	public void load()
	{
		splash = new Splashscreen();
		mainMenu = new MainMenu();
		
		showGame = Actions.sequence(Actions.fadeOut(fadeOutTime), Actions.run(RootSystem.screens.onActionGame));
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
    
    public Runnable onActionGame = new Runnable()
    {
        @Override
        public void run()
        {
        	RootSystem.game.setScreen(RootSystem.screens.mainMenu);
            showGame = Actions.sequence(Actions.fadeOut(fadeOutTime), Actions.run(RootSystem.screens.onActionGame));
            fadeIn = Actions.sequence(Actions.fadeOut(0.001f), Actions.fadeIn(fadeInTime), Actions.run(RootSystem.screens.onFadeIn));
        }
    };

}
