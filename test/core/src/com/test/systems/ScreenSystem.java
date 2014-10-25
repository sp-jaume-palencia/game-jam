package com.test.systems;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.test.screens.GameScreen;
import com.test.screens.LobbyScreen;
import com.test.screens.MenuScreen;
import com.test.screens.ServerScreen;
import com.test.screens.Splashscreen;

public class ScreenSystem {

	public Splashscreen splash;
	public GameScreen gameplay;
	public LobbyScreen lobby;
	public ServerScreen server;

	public MenuScreen menu;	
	
	public Screen currentScreen;
	public Screen nextScreen;
	
	float fadeOutTime = 0.33f;
    float fadeInTime = 0.33f;

	public void load()
	{
		splash = new Splashscreen();
		gameplay = new GameScreen();
		menu = new MenuScreen();
		lobby = new LobbyScreen();
		server = new ServerScreen();
	}
	
	public void showScreenInstantly(Screen screen)
	{
		currentScreen = screen;
		RootSystem.game.setScreen(screen);
	}
	
	public void showScreen(Screen screen, Stage stage)
	{
		nextScreen = screen;
		stage.addAction(Actions.sequence(Actions.fadeOut(fadeOutTime), Actions.run(RootSystem.screens.changeScreen), Actions.fadeIn(fadeInTime)));
	}
	
	public Runnable changeScreen = new Runnable()
	{
		@Override
		public void run()
		{
			showScreenInstantly(nextScreen);
			nextScreen = null;
		}
	};

}
