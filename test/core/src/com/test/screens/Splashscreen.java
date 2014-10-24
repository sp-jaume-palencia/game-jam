package com.test.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.test.systems.RootSystem;

public class Splashscreen implements Screen
{
    Camera camera;
    Viewport viewport;
    Stage stage;
    
	SpriteBatch batch;
	Texture img;

    public Splashscreen()
    {
        camera = new OrthographicCamera(RootSystem.coords.width, RootSystem.coords.height);
        camera.position.set(RootSystem.coords.width/2, RootSystem.coords.height/2, 0);
        viewport = new StretchViewport(RootSystem.coords.width, RootSystem.coords.height, camera);
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
		viewport.update(width, height, true);
	}

	@Override
	public void show() {
		stage = new Stage();
        stage.setViewport(viewport);
        Gdx.input.setInputProcessor(stage);

        //wait all loaded
        Image splashImage = new Image(RootSystem.assets.caca);
        splashImage.setPosition((RootSystem.coords.width - splashImage.getWidth())/2, (RootSystem.coords.height - splashImage.getHeight())/2);
        stage.addAction(Actions.fadeOut(0f));
        stage.addActor(splashImage);
        stage.addAction(Actions.sequence(Actions.fadeIn(0.5f), Actions.delay(1f), Actions.run(showMain)));
	}
	
	public Runnable showMain = new Runnable()
	{
		@Override
		public void run()
		{
			RootSystem.screens.showScreen(RootSystem.screens.menu, stage);
		}
	};

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
		stage.dispose();
	}

}
