package com.test.systems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class AssetsSystem
{
	public Texture caca;
	public Texture backSpace;
	
	public void load()
	{
		caca = new Texture(Gdx.files.internal("assets/jjinyar.png"));
		backSpace = new Texture(Gdx.files.internal("assets/bg-mockup.png"));
		
	}

}
