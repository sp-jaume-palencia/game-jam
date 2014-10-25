package com.test.screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
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
import com.test.data.AttackState;
import com.test.data.BaseData;
import com.test.network.Network.GameActionID;
import com.test.systems.RootSystem;

public class Planet extends Group {
	
	int _id;
	boolean _selected;
	boolean _attacking;
	Vector2 _targetPos;
	
	// Textures
	Image _sprite;
	Image _cursor;
	Image _spaceship;
	
	
	public Planet(int id, Vector2 position)
	{
		_id = id;
		
		float x = position.x - RootSystem.coords.planetSize.x/2;
		float y = (RootSystem.coords.mapSize.y - position.y) - RootSystem.coords.planetSize.y/2;
		
		setPosition(x, y);
		setBounds(getX(),getY(), RootSystem.coords.planetSize.x, RootSystem.coords.planetSize.y);
							
		_cursor = new Image(RootSystem.assets.cursor);
		float marginX = (getWidth() - _cursor.getWidth())/2;
		_cursor.setPosition(getX() + marginX, getY() - _cursor.getHeight());
		addActor(_cursor);
		
		_spaceship = new Image(RootSystem.assets.spaceShip);
		_spaceship.setVisible(false);
		addActor(_spaceship);
		
		_selected = false;
	}
	
	public void setSprite(Texture t)
	{		
		_sprite = new Image(t);
		_sprite.setPosition(getX(), getY());
		addActor(_sprite);

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

	public int getId()
	{
		return _id;
	}
		
	public void attackTo(Vector2 attackPos)
	{
		if(_attacking)
		{
			return;
		}
		
		_attacking = true;
		_targetPos = attackPos;		
		_spaceship.clearActions();
		
		Vector2 actPos = new Vector2(getX(), getY());
		Vector2 midPos = attackPos.sub(actPos);
		
		midPos.set(actPos.x + midPos.x/2, actPos.y + midPos.y/2);
		_spaceship.addAction(Actions.moveTo(midPos.x, midPos.y, 1.5f));		
		_spaceship.addAction(Actions.forever(Actions.sequence(Actions.scaleTo(1.5f, 1.5f), Actions.scaleTo(1.0f, 1.0f))));
		_spaceship.setVisible(true);
	}
			
	@Override
    public void draw(Batch batch, float alpha)
    {
		if(_spaceship.isVisible())
		{
			_spaceship.draw(batch, alpha);
		}
		
		_sprite.draw(batch, alpha);
		
		if(_selected)
		{
			_cursor.draw(batch, alpha);
		}
    }
}
