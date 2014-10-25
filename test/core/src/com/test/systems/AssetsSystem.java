package com.test.systems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class AssetsSystem
{
	public Skin UISkin;
	public Texture caca;
	public Texture gameplayBackground1;
	public Texture planet1, planet2, planet3, planet4, neutralPlanet;
	public Texture cursor;
	public Texture spaceShip;
	public Texture units;
	public Texture bases;
	public Texture target;
	public Texture menuBackground;
	public Texture lobbyBackground;
	
	public void load()
	{
		UISkin = new Skin(Gdx.files.internal("uiskin.json"));
		caca = new Texture(Gdx.files.internal("textures/jjinyar.png"));
		gameplayBackground1 = new Texture(Gdx.files.internal("textures/bg-gameplayBackground1.png"));		
		cursor = new Texture(Gdx.files.internal("textures/cursor.png"));
		spaceShip = new Texture(Gdx.files.internal("textures/spaceship.png"));
		planet1 = new Texture(Gdx.files.internal("textures/planet1.png"));
		planet2 = new Texture(Gdx.files.internal("textures/planet2.png"));
		planet3 = new Texture(Gdx.files.internal("textures/planet3.png"));
		planet4 = new Texture(Gdx.files.internal("textures/planet4.png"));
		neutralPlanet = new Texture(Gdx.files.internal("textures/neutralPlanet.png"));
		units = new Texture(Gdx.files.internal("textures/units.png"));
		bases = new Texture(Gdx.files.internal("textures/bases.png"));
		target = new Texture(Gdx.files.internal("textures/target.png"));
		menuBackground = new Texture(Gdx.files.internal("textures/bg-menu.jpg"));
		lobbyBackground = new Texture(Gdx.files.internal("textures/bg-lobby.jpg"));
	}
}
