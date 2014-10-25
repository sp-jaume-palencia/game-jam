package com.test.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.test.systems.RootSystem;

public class ServerScreen implements Screen
{
    Camera camera;
    Viewport viewport;
    Stage stage;
    Label label1;

    public ServerScreen()
    {
        camera = new OrthographicCamera(RootSystem.coords.width, RootSystem.coords.height);
        camera.position.set(RootSystem.coords.width/2, RootSystem.coords.height/2, 0);
        viewport = new StretchViewport(RootSystem.coords.width, RootSystem.coords.height, camera);
    }
    
    @Override
    public void show()
    {
    	stage = new Stage();
    	
        camera = new OrthographicCamera(RootSystem.coords.width, RootSystem.coords.height);
        camera.position.set(RootSystem.coords.width/2, RootSystem.coords.height/2, 0);
        viewport = new StretchViewport(RootSystem.coords.width, RootSystem.coords.height, camera);
        
        stage.setViewport(viewport);
        
    	Gdx.input.setInputProcessor(stage);
        
        label1 = new Label("YOU ARE THE SERVER, FUCK YEAH!", RootSystem.assets.UISkin);
        label1.setPosition(RootSystem.coords.label1Pos.x, RootSystem.coords.label1Pos.y);
                
        stage.addActor(label1);
    }
    
    protected void onServer()
    {
		RootSystem.net.setAsServer();
	}

	protected void onClient()
	{
		RootSystem.net.setAsClient();
		
	}
    
	@Override
	public void render(float delta)
	{
		Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.act(delta);
		stage.draw();
	}

	@Override
	public void resize(int width, int height)
	{
        camera.update();
        viewport.update(width, height);
	}

	@Override
	public void hide()
	{
		
	}

	@Override
	public void pause()
	{
		
	}

	@Override
	public void resume()
	{
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	public void setMsg(String text) {
		// TODO Auto-generated method stub
		label1.setText(text);
	}

}
