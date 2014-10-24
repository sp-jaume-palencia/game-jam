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

public class MenuScreen implements Screen
{
    Camera camera;
    Viewport viewport;
    Stage stage;
    TextButton button1;
    TextButton button2;
    TextButton button3;
    TextButton button4;
    Label label1;

    public MenuScreen()
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
        
        button1 = new TextButton("Ask rooms", RootSystem.assets.UISkin);
        button1.setSize(RootSystem.coords.button1Size.x, RootSystem.coords.button1Size.y);
        button1.setPosition(RootSystem.coords.button1Pos.x, RootSystem.coords.button1Pos.y);
        button1.addListener(new InputListener()
        {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
            	onAskRoom();
                return true;
            }
        });
        
        button2 = new TextButton("Join room", RootSystem.assets.UISkin);
        button2.setSize(RootSystem.coords.button2Size.x, RootSystem.coords.button2Size.y);
        button2.setPosition(RootSystem.coords.button2Pos.x, RootSystem.coords.button2Pos.y);
        button2.addListener(new InputListener()
        {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                onJoinRoom();
                return true;
            }
        });
        
        button3 = new TextButton("Client", RootSystem.assets.UISkin);
        button3.setSize(RootSystem.coords.button3Size.x, RootSystem.coords.button3Size.y);
        button3.setPosition(RootSystem.coords.button3Pos.x, RootSystem.coords.button3Pos.y);
        button3.addListener(new InputListener()
        {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
            	onClient();
                return true;
            }
        });
        
        button4 = new TextButton("Server", RootSystem.assets.UISkin);
        button4.setSize(RootSystem.coords.button4Size.x, RootSystem.coords.button4Size.y);
        button4.setPosition(RootSystem.coords.button4Pos.x, RootSystem.coords.button4Pos.y);
        button4.addListener(new InputListener()
        {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                onServer();
                return true;
            }
        });

        
        label1 = new Label("---", RootSystem.assets.UISkin);
        label1.setPosition(RootSystem.coords.label1Pos.x, RootSystem.coords.label1Pos.y);
        
        stage.addActor(button1);
        stage.addActor(button2);
        stage.addActor(button3);
        stage.addActor(button4);
        
        stage.addActor(label1);
        
    }
    
    protected void onServer() {
		// TODO Auto-generated method stub
		RootSystem.net.setAsServer();
	}

	protected void onClient() {
		// TODO Auto-generated method stub
		RootSystem.net.setAsClient();
		
	}

	public void onAskRoom()
    {
    	if(RootSystem.net.client != null)
    		RootSystem.net.client.askRoom();
    }
    
    public void onJoinRoom()
    {
    	if(RootSystem.net.client != null)
    		RootSystem.net.client.joinRoom(0);
    }
    
	@Override
	public void render(float delta)
	{
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
