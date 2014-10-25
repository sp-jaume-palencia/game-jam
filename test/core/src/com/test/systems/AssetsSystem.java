package com.test.systems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class AssetsSystem
{
	public Skin UISkin;
	public Texture caca;
	public Texture gameplayBackground1, paths;
	public Texture planet0, planet1, planet2, planet3, planet4, planet5, planet6, planet7, planet8, planet9;
	public Texture cursor;
	public Texture spaceShip;
	public Texture gold;
	public Texture crono;
	public Texture player1, player2, player3, player4;
	
	public void load()
	{
		UISkin = new Skin(Gdx.files.internal("uiskin.json"));
		caca = new Texture(Gdx.files.internal("textures/jjinyar.png"));
		gameplayBackground1 = new Texture(Gdx.files.internal("textures/background.png"));
		paths = new Texture(Gdx.files.internal("textures/paths.png"));
		cursor = new Texture(Gdx.files.internal("textures/cursor.png"));
		spaceShip = new Texture(Gdx.files.internal("textures/spaceship.png"));
		planet0 = new Texture(Gdx.files.internal("textures/planeta0.png"));
		planet1 = new Texture(Gdx.files.internal("textures/planeta1.png"));
		planet2 = new Texture(Gdx.files.internal("textures/planeta2.png"));
		planet3 = new Texture(Gdx.files.internal("textures/planeta3.png"));
		planet4 = new Texture(Gdx.files.internal("textures/planeta4.png"));
		planet5 = new Texture(Gdx.files.internal("textures/planeta5.png"));
		planet6 = new Texture(Gdx.files.internal("textures/planeta6.png"));
		planet7 = new Texture(Gdx.files.internal("textures/planeta7.png"));
		planet8 = new Texture(Gdx.files.internal("textures/planeta8.png"));
		planet9 = new Texture(Gdx.files.internal("textures/planeta9.png"));
		//neutralPlanet = new Texture(Gdx.files.internal("textures/neutralPlanet.png"));
		gold = new Texture(Gdx.files.internal("textures/gold.png"));
		crono = new Texture(Gdx.files.internal("textures/crono.png"));
		player1 = new Texture(Gdx.files.internal("textures/player1.png"));
		player2 = new Texture(Gdx.files.internal("textures/player2.png"));
		player3 = new Texture(Gdx.files.internal("textures/player3.png"));
		player4 = new Texture(Gdx.files.internal("textures/player4.png"));
	}
}
