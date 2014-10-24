package com.test.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.test.systems.RootSystem;

public class TestGame extends Game
{
	SpriteBatch batch;
	Texture img;
	
	public RootSystem rootSystem;
	
	@Override
	public void create()
	{
		rootSystem = new RootSystem();
		rootSystem.loadSystems(this);
		setScreen(RootSystem.screens.gameplay);
	}

}
