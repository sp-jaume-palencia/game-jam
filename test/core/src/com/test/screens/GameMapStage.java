package com.test.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.test.systems.RootSystem;


public class GameMapStage extends Stage implements GestureListener {
	
	OrthographicCamera _camera;
    Viewport _viewport;    
    
    // Actors
    Image _background;
    Array<PlanetActor> _planets;
	
    // Zoom
    float ZOOM_SPEED = 0.0225f;
    float INITIAL_ZOOM = 500.0f;
    float ZOOM_MAX = 1.5f;
    float ZOOM_MIN = 0.5f;
    float lastDistance;
	
	GameMapStage()
	{
		// Camera
		float centerX = RootSystem.coords.width/2;
        float centerY = RootSystem.coords.height/2;        
        _camera = new OrthographicCamera(RootSystem.coords.width, RootSystem.coords.height);
        _camera.position.set(centerX, 0.0f, INITIAL_ZOOM);
        _camera.lookAt(centerX, centerY, 0);     
        _camera.near = 1f;
        _camera.far = 3000f;
        _camera.update();
        
        _viewport = new ScreenViewport(_camera);        
        setViewport(_viewport);
        
        // Actors        
		_background = new Image(RootSystem.assets.gameplayBackground1);
		_background.setSize(RootSystem.coords.width, RootSystem.coords.height);
		addActor(_background);
		
		createPlanets();
		
		// Input
		Gdx.input.setInputProcessor(new GestureDetector(this));
	}
	
	private void createPlanets()
	{
		_planets = new Array<PlanetActor>();
		_planets.add(new PlanetActor(RootSystem.assets.planet1, 100.0f, 100.0f));
		_planets.add(new PlanetActor(RootSystem.assets.planet1, 300.0f, 100.0f));
		_planets.add(new PlanetActor(RootSystem.assets.planet1, 500.0f, 100.0f));
		_planets.add(new PlanetActor(RootSystem.assets.planet1, 700.0f, 100.0f));
		
		for(PlanetActor planet : _planets)
		{
			addActor(planet);
		}
	}
	
	public Array<PlanetActor> getPlanets()
	{
		return _planets;
	}

	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean longPress(float x, float y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY) 
	{
		float moveX = -deltaX*_camera.zoom;
		float moveY = deltaY*_camera.zoom;

		_camera.translate(moveX, moveY, 0);

		return true;
	}

	@Override
	public boolean panStop(float x, float y, int pointer, int button) 
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean zoom(float initialDistance, float distance) 
	{	
		if(Math.abs(distance - lastDistance) < 5.0f)
		{
			return true;
		}
		
		float factor = (distance/lastDistance)*ZOOM_SPEED;
		
		int sign = lastDistance > distance? 1 : -1;
		float auxZoom = _camera.zoom + sign*factor;
		
		if(auxZoom > ZOOM_MAX)
		{
			auxZoom = ZOOM_MAX;
		}
		else if(auxZoom < ZOOM_MIN)
		{
			auxZoom = ZOOM_MIN;
		}
		
		_camera.zoom = auxZoom;		
		lastDistance = distance;
		
        return true;
	}

	@Override
	public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2,
			Vector2 pointer1, Vector2 pointer2) {
		// TODO Auto-generated method stub
		return false;
	}
}
