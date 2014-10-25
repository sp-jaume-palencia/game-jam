package com.test.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.test.hud.HUD;
import com.test.network.Network.GameActionID;
import com.test.systems.RootSystem;

public class GameScreen implements Screen 
{	
    Camera camera;
    Viewport viewport;    
    public GameMapStage mapStage;
    Stage hudLayer;
    long gameTime;
	
	public GameScreen()
	{
        camera = new OrthographicCamera(RootSystem.coords.width, RootSystem.coords.height);
        camera.position.set(RootSystem.coords.width/2, RootSystem.coords.height/2, 0);
        viewport = new StretchViewport(RootSystem.coords.width, RootSystem.coords.height, camera);
	}

	@Override
	public void show() 
	{
		hudLayer = new Stage();
		hudLayer.setViewport(viewport);
		HUD hud = new HUD();
		hudLayer.addActor(hud);
		mapStage = new GameMapStage(hud);
		
		InputMultiplexer multiplexer = new InputMultiplexer();
		multiplexer.addProcessor(hudLayer);
		multiplexer.addProcessor(new GestureDetector(mapStage));
		Gdx.input.setInputProcessor(multiplexer);
	}
	
	@Override
	public void render(float delta) 
	{
		Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        mapStage.act(delta);
        mapStage.draw();
		hudLayer.act(delta);
		hudLayer.draw();
	}
	
	@Override
	public void resize(int width, int height) {
        camera.update();
        viewport.update(width, height);
        hudLayer.getViewport().update(width, height);
        mapStage.getViewport().update(width, height);
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
}
