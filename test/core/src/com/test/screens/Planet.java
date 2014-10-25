package com.test.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.ScaleToAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.test.systems.RootSystem;

public class Planet extends Group {
	
	Spaceship _spaceship;
	
	// Status
	int _playerOwnerId;
	int _baseId;
	int[] _annexedBasesIds;
	
	int START_HP = 5;	
	int _hitPoints = START_HP;
	
	// Textures
	Image _sprite;
	Image _cursor;
	boolean _selected;
	
	public Planet(int baseId, int playerId, int[] annexedBases, Texture planetTexture, float dataX, float dataY)
	{
		_annexedBasesIds = annexedBases;
		
		float x = dataX - planetTexture.getWidth()/2;
		float y = dataY - planetTexture.getHeight()/2;
		
		_baseId = baseId;
		_playerOwnerId = playerId;
		
		setPosition(x, y);
		setBounds(getX(),getY(), planetTexture.getWidth(), planetTexture.getHeight());
		
		_sprite = new Image(planetTexture);
		_sprite.setPosition(x, y);
		addActor(_sprite);				
		
		_cursor = new Image(RootSystem.assets.cursor);
		float marginX = (getWidth() - _cursor.getWidth())/2;
		_cursor.setPosition(getX() + marginX, getY() - _cursor.getHeight());
		addActor(_cursor);
		
		_spaceship = new Spaceship(RootSystem.assets.spaceShip, this);
		addActor(_spaceship);
		
		_selected = false;
	}
	
	public boolean isInside(float x, float y)
	{
		return x > getX() && x < getX() + getWidth() && y > getY() && y < getY() + getHeight();
	}
	
	public void onSelect()
	{
		if(_selected)
		{
			return;
		}
		
		_selected = true;		
		
		// Animation
		float marginX = (getWidth() - _cursor.getWidth())/2;
		_cursor.setPosition(getX() + marginX, getY() - _cursor.getHeight());
		
		_sprite.clearActions();
		_cursor.clearActions();
		_sprite.addAction(Actions.sequence(Actions.scaleTo(1.5f, 1.5f, 0.25f), Actions.scaleTo(1.0f, 1.0f, 0.25f)));
		_cursor.addAction(Actions.forever(Actions.sequence(Actions.moveBy(0.0f, 10.0f, 0.5f), Actions.moveBy(0.0f, -10.0f, 0.5f))));
	}
	
	public void unselect()
	{
		_selected = false;
	}
	
	public void receiveDamage(Spaceship enemySpaceship)
	{
		_hitPoints -= enemySpaceship.getDamage();
		
		if(_hitPoints < 0)
		{
			conquer();
			_playerOwnerId = enemySpaceship.getPlayerOwnerId();
			_hitPoints = START_HP;
		}
	}
	
	public int getPlayerOwnerId()
	{
		return _playerOwnerId;
	}
	
	public void attackTo(Planet targetPlanet)
	{
		_spaceship.attack(targetPlanet);
	}
	
	@Override
    public void draw(Batch batch, float alpha)
    {
		_spaceship.draw(batch, alpha);
		_sprite.draw(batch, alpha);
		
		if(_selected)
		{
			_cursor.draw(batch, alpha);
		}
    }
}
