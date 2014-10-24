package com.test.systems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class AssetsSystem
{
	public Skin UISkin;
	public Texture caca;
	public Texture gameplayBackground1;
	public Texture planet1;
	public Texture cursor;
	
	public void load()
	{
		UISkin = new Skin(Gdx.files.internal("uiskin.json"));
		caca = new Texture(Gdx.files.internal("textures/jjinyar.png"));
		gameplayBackground1 = new Texture(Gdx.files.internal("textures/bg-gameplayBackground1.png"));
		planet1 = new Texture(Gdx.files.internal("textures/planet1.png"));
		cursor = new Texture(Gdx.files.internal("textures/cursor.png"));
	}

}
