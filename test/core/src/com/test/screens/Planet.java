package com.test.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.test.data.BaseState;
import com.test.systems.RootSystem;

public class Planet extends Group {
	
	int _id;
	int _group;
	boolean _selected;
	boolean _attacking;
	Vector2 _targetPos;
	
	// Textures
	Image _sprite;
	Image _cursor;
	Image _spaceship;
	Image _target;
	Label _troopsLabel;
	int _actualPlayerId;
	Image[] _playerSprites;
	
	
	public Planet(int id, Vector2 position, Texture texture, int group)
	{
		_id = id;
		_group = group;
		
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

		_troopsLabel = new Label("0", RootSystem.assets.UISkin);
		_troopsLabel.setFontScale(3.0f);
		_troopsLabel.setColor(1f, 0f, 1f, 1f);
		_troopsLabel.setSize(RootSystem.coords.planetSize.x, RootSystem.coords.planetSize.y);
		_troopsLabel.setPosition(x, y);
		addActor(_troopsLabel);

		_selected = false;

		_sprite = new Image(texture);
		_sprite.setScale(0.4f);
		_sprite.setPosition(getX(), getY());
		addActor(_sprite);

		_actualPlayerId = -1;
		_playerSprites = new Image[]{new Image(RootSystem.assets.player1), new Image(RootSystem.assets.player2), new Image(RootSystem.assets.player3), new Image(RootSystem.assets.player4)};
		for (int i=0; i<_playerSprites.length; i++) {
			_playerSprites[i].setPosition(getX()-getWidth()*0.05f, getY()-getHeight()*0.05f);
			_playerSprites[i].setScale(0.5f);
			addActor(_playerSprites[i]);
			_playerSprites[i].setVisible(false);
		}
		
		_target = new Image(RootSystem.assets.target);
		marginX = (getWidth() - _target.getWidth())/2;
		_target.setPosition(getX() + marginX, getY() - (getWidth() - _target.getHeight())/2);
//		_target.setVisible(false);
		_target.addAction(Actions.fadeOut(0f));
//		_target.setPosition(getX(), getY());
		addActor(_target);
	}
	
	public void setPlayerSprite(int ownerId)
	{
		if(ownerId > 0)
		{
			if(_actualPlayerId != -1)
			{
				_playerSprites[_actualPlayerId].setVisible(false);
			}
			
			_actualPlayerId = ownerId-1;
			_playerSprites[_actualPlayerId].setVisible(true);
		}
		else
		{
			_actualPlayerId = -1;
		}
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
	
	public void showTarget()
	{
		_target.addAction(Actions.sequence(Actions.fadeIn(0.2f), Actions.scaleTo(2.5f, 2.5f, 2.0f), Actions.scaleTo(1.0f, 1.0f, 2.0f), Actions.fadeOut(0.2f)));
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
		
		_spaceship.setRotation((float) Math.atan2(midPos.y, midPos.y));		
		midPos.set(actPos.x + midPos.x/2, actPos.y + midPos.y/2);
		
		_spaceship.addAction(Actions.moveTo(midPos.x, midPos.y, 1.5f));		
		_spaceship.addAction(Actions.forever(Actions.sequence(Actions.scaleTo(1.5f, 1.5f), Actions.scaleTo(1.0f, 1.0f))));
		_spaceship.setVisible(true);
	}
			
	@Override
	public void act(float dt)
	{
		super.act(dt);
		
		BaseState baseData = RootSystem.data.mapState.getBaseState(_id);
		
		_troopsLabel.setText(String.valueOf(baseData.numTroops));
		setPlayerSprite(baseData.ownerId);
	}
	
	@Override
    public void draw(Batch batch, float alpha)
    {
		if(_actualPlayerId != -1 && _playerSprites[_actualPlayerId].isVisible())
		{
			_playerSprites[_actualPlayerId].draw(batch, alpha);
		}

		if(_spaceship.isVisible())
		{
			_spaceship.draw(batch, alpha);
		}
		
		if(_target.isVisible())
		{
			_target.draw(batch, alpha);
		}
		
		_sprite.draw(batch, alpha);
		_troopsLabel.draw(batch, alpha);
		
		if(_selected)
		{
			_cursor.draw(batch, alpha);
		}
    }
}
