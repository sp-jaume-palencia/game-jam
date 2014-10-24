package com.test.systems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class AssetsSystem
{
	public Skin UISkin;
	public Texture caca;
	public Texture gameplayBackground1;
	
	public void load()
	{
		UISkin = new Skin(Gdx.files.internal("assets/uiskin.json"));
		caca = new Texture(Gdx.files.internal("assets/jjinyar.png"));
		gameplayBackground1 = new Texture(Gdx.files.internal("assets/bg-mockup.png"));
		
	}

}
