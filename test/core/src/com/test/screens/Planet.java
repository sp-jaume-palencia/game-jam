package com.test.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
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
		_troopsLabel.setColor(1f, 1f, 1f, 1f);
		addActor(_troopsLabel);

		_selected = false;

		_sprite = new Image(texture);
		_sprite.setOrigin(_sprite.getWidth()/2, _sprite.getHeight()/2);
		_sprite.setPosition(getX(), getY());
		addActor(_sprite);

		_actualPlayerId = -1;
		_playerSprites = new Image[]{new Image(RootSystem.assets.player1), new Image(RootSystem.assets.player2), new Image(RootSystem.assets.player3), new Image(RootSystem.assets.player4)};
		for (int i=0; i<_playerSprites.length; i++) {
			_playerSprites[i].setPosition(getX()+(_sprite.getWidth()-_playerSprites[i].getWidth())/2, getY()+(_sprite.getHeight()-_playerSprites[i].getHeight())/2);
			addActor(_playerSprites[i]);
			_playerSprites[i].setVisible(false);
		}
		
		_target = new Image(RootSystem.assets.target);
		float tarMarginX = (getWidth() - _target.getWidth())/2;
		float tarMarginY = (getHeight() - _target.getHeight())/2;
		_target.setPosition(getX() + tarMarginX, getY() + tarMarginY);
		_target.setOrigin(_target.getWidth()/2, _target.getHeight()/2);
		_target.addAction(Actions.fadeOut(0f));
		addActor(_target);
	}
	
	public Vector2 getPosition()
	{
		return new Vector2(getX(), getY());
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
		_target.addAction(Actions.sequence(Actions.parallel(Actions.fadeIn(0.25f), Actions.scaleTo(1.5f, 1.5f, 0.25f)), Actions.parallel(Actions.scaleTo(1.0f, 1.0f, 0.25f), Actions.fadeOut(0.25f))));
	}
		
	public void attackTo(Vector2 attackPos)
	{
		if(_attacking)
		{
			return;
		}
		
		// Center to planet
		float marginX = getWidth()/2 - _spaceship.getWidth()/2;
		float marginY = getHeight()/2 - _spaceship.getHeight()/2;
		
		float origX = getX() + marginX;
		float origY = getY() + marginY;
		
		attackPos.x = attackPos.x + marginX;
		attackPos.y = attackPos.y + marginY;
		
		_attacking = true;
		_targetPos = attackPos;		
		_spaceship.clearActions();
		
		float midX = (attackPos.x + origX)/2;
		float midY = (attackPos.y + origY)/2;
		
		_spaceship.setPosition(origX, origY);
		
		_spaceship.addAction(Actions.moveTo(midX, midY, 1.5f));		
		_spaceship.addAction(Actions.forever(Actions.sequence(Actions.scaleTo(1.25f, 1.25f, 0.5f), Actions.scaleTo(1.0f, 1.0f, 0.5f))));
		_spaceship.setVisible(true);
	}
	
	public void finishAttack()
	{
		_attacking = false;
		_spaceship.clearActions();
		_spaceship.addAction(Actions.sequence(Actions.parallel(Actions.moveTo(_targetPos.x, _targetPos.y, 0.5f), Actions.fadeOut(0.5f)), Actions.run(new Runnable() {

			@Override
			public void run() 
			{
				_spaceship.setVisible(false);
			}
			
		})));
	}
			
	@Override
	public void act(float dt)
	{
		super.act(dt);
		
		BaseState baseState = RootSystem.data.mapState.getBaseState(_id);		
		setPlayerSprite(baseState.ownerId);
		
		_troopsLabel.setText(String.valueOf(baseState.numTroops));
		TextBounds bounds = _troopsLabel.getTextBounds();
		_troopsLabel.setPosition(getX() + getWidth()/2 - bounds.width/2, getY() + getHeight()/3);
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
