package com.test.systems;

import com.test.screens.GameScreen;
import com.test.screens.Splashscreen;

public class ScreenSystem {

	public Splashscreen splash;
	public GameScreen gameMap;

	public void load()
	{
		splash = new Splashscreen();
		gameMap = new GameScreen();
	}
	
	

}
