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
	public Texture spaceShip;
	public Texture gold;
	public Texture crono;
	
	public void load()
	{
		UISkin = new Skin(Gdx.files.internal("uiskin.json"));
		caca = new Texture(Gdx.files.internal("textures/jjinyar.png"));
		gameplayBackground1 = new Texture(Gdx.files.internal("textures/bg-gameplayBackground1.png"));
		planet1 = new Texture(Gdx.files.internal("textures/planet1.png"));
		cursor = new Texture(Gdx.files.internal("textures/cursor.png"));
		spaceShip = new Texture(Gdx.files.internal("textures/spaceship.png"));
		gold = new Texture(Gdx.files.internal("textures/gold.png"));
		crono = new Texture(Gdx.files.internal("textures/crono.png"));
	}

}
