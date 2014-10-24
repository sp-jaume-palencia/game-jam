package com.test.systems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class AssetsSystem
{
	public Texture caca;
	
	public void load()
	{
		caca = new Texture(Gdx.files.internal("assets/caca.png"));
		
	}

}
