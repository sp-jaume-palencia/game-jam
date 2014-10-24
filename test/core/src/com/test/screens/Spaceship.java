package com.test.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Spaceship extends Image {
		
	float _cooldown = 5.0f;
	float _actCooldown;
	float _speed = 50.0f;
	int _damage = 1;
	Vector2 _dir;
	Planet _originPlanet;
	Planet _targetPlanet;
	
	public Spaceship(Texture texture, Planet originPlanet)
	{
		super(texture);
		_actCooldown = 0.0f;		
		setSize(texture.getWidth(), texture.getHeight());
		_originPlanet = originPlanet;
		_dir = new Vector2();
	}

	public void attack(Planet planet)
	{
		if(_targetPlanet == planet)
		{
			return;
		}
		
		_targetPlanet = planet;
		
		float difX = _targetPlanet.getX() - _originPlanet.getX();
		float difY = _targetPlanet.getY() - _originPlanet.getY();
		float mod = (float) Math.sqrt(difX*difX + difY*difY);
		_dir.set(difX/mod, difY/mod);
	}
	
	public int getDamage()
	{
		return _damage;
	}
	
	public int getPlayerOwnerId()
	{
		return _originPlanet.getPlayerOwnerId();
	}
	
	@Override
	public void act(float dt)
	{
		_actCooldown -= dt;
		
		if(_targetPlanet == null || _actCooldown > 0.0f)
		{
			return;
		}
		
		setPosition(getX() + _dir.x*_speed, getY() + _dir.y*_speed);
		
		if(_targetPlanet.isInside(getX() + getWidth()/2, getY() + getHeight()/2))
		{
			_targetPlanet.receiveDamage(this);
			resetAttack();
		}
	}
		
	private void resetAttack()
	{
		_actCooldown = _cooldown;
		_targetPlanet = null;
	}
}
