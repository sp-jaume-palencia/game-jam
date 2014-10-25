package com.test.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
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
    Image background;

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
        stage.setViewport(viewport);
        
    	Gdx.input.setInputProcessor(stage);
        
        button1 = new TextButton("New game", RootSystem.assets.UISkin);
        button1.setSize(RootSystem.coords.button1Size.x, RootSystem.coords.button1Size.y);
        button1.setPosition(RootSystem.coords.button1Pos.x, RootSystem.coords.button1Pos.y);
        button1.addListener(new InputListener()
        {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
            	onNewGame();
            	//RootSystem.net.setAsClient();
                return true;
            }
        });
        
        button2 = new TextButton("Server stuff", RootSystem.assets.UISkin);
        button2.setSize(RootSystem.coords.button2Size.x, RootSystem.coords.button2Size.y);
        button2.setPosition(RootSystem.coords.button2Pos.x, RootSystem.coords.button2Pos.y);
        button2.addListener(new InputListener()
        {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                onServerStuff();
                return true;
            }
        });
        
        background = new Image(RootSystem.assets.menuBackground);
        stage.addActor(background);
        stage.addActor(button1);
        stage.addActor(button2);
    }
    
    public void onNewGame()
    {
    	RootSystem.screens.showScreen(RootSystem.screens.lobby, stage);
    }
    
    public void onServerStuff()
    {
    	RootSystem.net.setAsServer();
    	RootSystem.screens.showScreen(RootSystem.screens.server, stage);
    }
    
    public void onExit()
    {
        Gdx.app.exit();
    }
    
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
		stage.act(delta);
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
        camera.update();
        viewport.update(width, height);
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
