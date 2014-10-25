package com.test.screens;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Plane;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.test.data.BaseData;
import com.test.data.PlayerState;
import com.test.hud.HUD;
import com.test.systems.RootSystem;


public class GameMapStage extends Stage implements GestureListener {
	
	OrthographicCamera _camera;
    Viewport _viewport;    
    
    // GameStatus
    int _playerId;
    PlayerState _playerState;
    
    // Actors
    Image _background;
    public Array<Planet> _planets;
    Planet _selectedPlanet;
    
    HUD _hud;
	
    // Zoom
    float ZOOM_SPEED = 0.0225f;
    float INITIAL_ZOOM = 500.0f;
    float ZOOM_MAX = 1.5f;
    float ZOOM_MIN = 0.5f;
    float lastDistance;
	
	GameMapStage(HUD hud)
	{		
		RootSystem.mapStage = this;
		
		// Status
		_playerId = 1;
		_playerState = new PlayerState(_playerId, 0, 0);
				
		// Camera
		float centerX = RootSystem.coords.mapSize.x/2;
        float centerY = RootSystem.coords.mapSize.y/2;        
        _camera = new OrthographicCamera(RootSystem.coords.width, RootSystem.coords.height);
        _camera.position.set(centerX, centerY, INITIAL_ZOOM);
        
        _camera.lookAt(centerX, centerY, 0);     
        _camera.near = 1f;
        _camera.far = 3000f;
        _camera.update();
        
        _viewport = new ScreenViewport(_camera);
        _viewport.setScreenSize(RootSystem.coords.width, RootSystem.coords.height);
        setViewport(_viewport);
        
        // Actors        
		_background = new Image(RootSystem.assets.gameplayBackground1);
		_background.setSize(RootSystem.coords.mapSize.x, RootSystem.coords.mapSize.y);
		addActor(_background);
		
		createPlanets();
		
		_hud = hud;
		_hud.setActionsVisible(false);
	}
	
	public void logic(float dt)
	{
		super.act();
		super.draw();
		
		// Update player state
		_playerState = RootSystem.data.mapState.getPlayerState(_playerId);
		
		for(Planet planet : _planets)
		{
			planet.act(dt);
		}
	}
		
	private void createPlanets()
	{
		_planets = new Array<Planet>();
		Iterator<Entry<Integer, BaseData>> it = RootSystem.data.map.bases.entrySet().iterator();
		
	    while (it.hasNext()) 
	    {
	        Map.Entry<Integer, BaseData> pairs = (Map.Entry<Integer, BaseData>)it.next();
	        BaseData baseData = pairs.getValue();
	        Vector2 basePos = baseData.position;
	        int[] annexedBases = baseData.annexedBases;
	        
	        Planet planet = new Planet(baseData.baseId, baseData.ownerId, annexedBases, basePos.x, RootSystem.coords.mapSize.y - basePos.y);	        
			_planets.add(planet);
			addActor(planet);
	    }
		
		_selectedPlanet = null;
	}
	
	public Vector2 getTouchPos(float x, float y)
	{
		final Plane xyPlane = new Plane(new Vector3(0, 0, 1), 0);
        final Vector3 intersection = new Vector3();

        Ray pickRay = _camera.getPickRay(x, y);
        Intersector.intersectRayPlane(pickRay, xyPlane, intersection);
              
        return new Vector2(intersection.x, intersection.y);
	}
	
	@Override
	public boolean touchDown(float x, float y, int pointer, int button) 
	{
		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) 
	{	
        Vector2 touchPos = getTouchPos(x, y);
        boolean selectedPlanet = false;
        
        for(Planet planet : _planets)
        {
    		if(planet.isInside(touchPos.x, touchPos.y))
			{
				if(_selectedPlanet != planet && planet.getPlayerOwnerId() == _playerId)
				{
					// Select another owned planet
					if(_selectedPlanet != null)
					{
						_selectedPlanet.unselect();
					}
					
					_selectedPlanet = planet;
					_selectedPlanet.onSelect();
					
					_hud.setActionsVisible(true);
				}
				else
				{
					// Enemy planet
					if(_selectedPlanet != null)
					{
						_selectedPlanet.attackTo(planet);
					}
				}
				
				selectedPlanet = true;
	    		break;
			}
        }
                
        if(selectedPlanet)
        {
        	// Has selected a planet        	
        	return true;
        }
        else if(_selectedPlanet != null)
        {
        	_selectedPlanet.unselect();
        	_selectedPlanet = null;
        }
        
        _hud.setActionsVisible(false);
        
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
		
		if(!checkXLimits(moveX))
		{
			moveX = 0;
		}
		
		if(!checkYLimits(moveY))
		{
			moveY = 0;
		}
		
		_camera.translate(moveX, moveY, 0);
		return true;
	}
	
	public boolean checkXLimits(float moveX)
	{
		float limitXright = _background.getWidth() - _camera.zoom * RootSystem.coords.width/2;
		float limitXleft = _camera.zoom * RootSystem.coords.width/2;
		float nextX = _camera.position.x + moveX;
		if(nextX >= limitXright || nextX <= limitXleft)
		{
			_camera.position.x = ((limitXright - nextX) > 0f) ? limitXleft : limitXright;
			return false;
		}
		return true;
	}
	
	public boolean checkYLimits(float moveY)
	{
		float limitYtop = _background.getHeight() - _camera.zoom * RootSystem.coords.height/2;
		float limitYbottom = _camera.zoom * RootSystem.coords.height/2;
		float nextY = _camera.position.y + moveY;
		if(nextY >= limitYtop || nextY <= limitYbottom)
		{
			_camera.position.y = ((limitYtop - nextY) > 0f) ? limitYbottom : limitYtop;
			return false;
		}
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
