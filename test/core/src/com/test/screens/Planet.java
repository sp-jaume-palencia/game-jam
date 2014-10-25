package com.test.screens;

import com.badlogic.gdx.graphics.Color;
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
import com.test.data.AttackState;
import com.test.data.BaseData;
import com.test.model.net.CommandAction.ActionId;
import com.test.network.Network.GameActionID;
import com.test.systems.RootSystem;

public class Planet extends Group {
	
	Spaceship _spaceship;
	
	// Status
	int _playerOwnerId = -1;
	int _baseId;
	int[] _annexedBasesIds;
	
	int START_HP = 5;	
	int _hitPoints = START_HP;
	
	// Textures
	Image _sprite;
	Image _cursor;
	boolean _selected;
	
	public Planet(int baseId, int playerId, int[] annexedBases, float dataX, float dataY)
	{
		_annexedBasesIds = annexedBases;
		
		float x = dataX - RootSystem.coords.planetSize.x/2;
		float y = dataY - RootSystem.coords.planetSize.y/2;
		
		setPosition(x, y);
		setBounds(getX(),getY(), RootSystem.coords.planetSize.x, RootSystem.coords.planetSize.y);
					
		
		_cursor = new Image(RootSystem.assets.cursor);
		float marginX = (getWidth() - _cursor.getWidth())/2;
		_cursor.setPosition(getX() + marginX, getY() - _cursor.getHeight());
		addActor(_cursor);
		
		_spaceship = new Spaceship(RootSystem.assets.spaceShip, this);
		addActor(_spaceship);
		
		_selected = false;
		
		_baseId = baseId;
		setPlayerId(playerId);
	}
	
	@Override
	public void act(float dt)
	{
		// Update base status
		BaseData data = RootSystem.data.map.getBase(_baseId);		
		setPlayerId(data.ownerId);
		
		// Update attacks
		Integer attackId = RootSystem.data.mapState.attackState.getAttackingBaseId(_baseId);
		
		if(attackId != null)
		{
			Planet targetPlanet = RootSystem.mapStage._planets.get(attackId - 1);
			_spaceship.attack(targetPlanet);
		}
		
		super.act(dt);
	}
	
	public void setPlayerId(int playerId)
	{
		if(_playerOwnerId == playerId)
		{
			return;
		}
		
		_playerOwnerId = playerId;
		Texture t = null;
		
		switch(_playerOwnerId)
		{
			 case 1:
				 t = RootSystem.assets.planet1;
				 break;
			 case 2:
				 t = RootSystem.assets.planet2;
				 break;
			 case 3:
				 t = RootSystem.assets.planet3;
				 break;
			 case 4:
				 t = RootSystem.assets.planet4;
				 break;
			 default:
			 {
				 t = RootSystem.assets.neutralPlanet;
				 break;
			 }
		}
						
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
	
	public void receiveDamage(Spaceship enemySpaceship)
	{
		_hitPoints -= enemySpaceship.getDamage();
		
		if(_hitPoints < 0)
		{
			_hitPoints = START_HP;			
			setPlayerId(enemySpaceship.getPlayerOwnerId());
		}
	}
	
	public int getPlayerOwnerId()
	{
		return _playerOwnerId;
	}
	
	public void attackTo(Planet targetPlanet)
	{
		if(isPlanetAnnexed(targetPlanet.getPlayerOwnerId()))
		{		
			// Attack!!!!
			_spaceship.attack(targetPlanet);
			RootSystem.commands.sendAttack(_baseId, targetPlanet.getPlayerOwnerId(), 9898, GameActionID.BASEATACKBASE);
		}
	}
	
	public boolean isPlanetAnnexed(int id)
	{
		System.out.println("Planet actual: " + id + " attackando a: ");
		for(int annexedId : _annexedBasesIds)
		{
			System.out.println(annexedId);
			if(annexedId == id)
			{
				return true;
			}
		}
		
		return false;
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
