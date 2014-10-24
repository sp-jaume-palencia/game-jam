package com.test.systems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class AssetsSystem
{
	public Texture caca;
	public Texture gameplayBackground1;
	public Texture planet1;
	
	public void load()
	{
		caca = new Texture(Gdx.files.internal("textures/jjinyar.png"));
		gameplayBackground1 = new Texture(Gdx.files.internal("textures/bg-gameplayBackground1.png"));
		planet1 = new Texture(Gdx.files.internal("textures/planet1.png"));
	}

}
