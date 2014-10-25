package com.test.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Spaceship extends Image {
		
	float _cooldown = 2.5f;
	float _actCooldown;
	float _speed = 3f;
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
		
		float destX = _targetPlanet.getX() + _targetPlanet.getWidth()/2;
		float destY = _targetPlanet.getY() + _targetPlanet.getHeight()/2;
		float origX = _originPlanet.getX() + _originPlanet.getWidth()/2;
		float origY = _originPlanet.getY() + _originPlanet.getHeight()/2;
		
		float difX = destX - origX;
		float difY = destY - origY;
		float mod = (float) Math.sqrt(difX*difX + difY*difY);
		_dir.set(difX/mod, difY/mod);
		
		setX(_originPlanet.getX() + _originPlanet.getWidth() - getWidth()/2);
		setY(_originPlanet.getY() + _originPlanet.getHeight()/2 - getHeight()/2);
	}
	
	public int getDamage()
	{
		return _damage;
	}
	
	public int getPlayerOwnerId()
	{
		return _originPlanet.getPlayerOwnerId();
	}
	
	public boolean isAttacking()
	{
		return (_targetPlanet != null && _actCooldown <= 0.0f);
	}
	
	@Override
	public void act(float dt)
	{
		_actCooldown -= dt;
		
		if(!isAttacking())
		{
			return;
		}
		
		setPosition(getX() + _dir.x*_speed, getY() + _dir.y*_speed);
		
		if(_targetPlanet.isInside(getX(), getY()))
		{
			_targetPlanet.receiveDamage(this);
			resetAttack();
		}
	}
		
	private void resetAttack()
	{
		_actCooldown = _cooldown;
		
		setX(_originPlanet.getX() + _originPlanet.getWidth() - getWidth()/2);
		setY(_originPlanet.getY() + _originPlanet.getHeight()/2 - getHeight()/2);
	}
	
	@Override
	public void draw(Batch batch, float alpha)
	{
		if(isAttacking())
		{
			super.draw(batch, alpha);
		}
	}	
}
