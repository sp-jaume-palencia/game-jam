package com.test.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class PlanetActor extends Actor {
	
	Texture _texture;
	
	public PlanetActor(Texture planetTexture, float x, float y)
	{
		_texture = planetTexture;
		setPosition(x, y);
	}
	
	@Override
    public void draw(Batch batch, float alpha)
    {
		batch.draw(_texture, getX(), getY());
    }
}
