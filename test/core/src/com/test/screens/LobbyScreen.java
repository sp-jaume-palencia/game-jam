package com.test.screens;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.test.network.Network.RoomInfo;
import com.test.systems.RootSystem;

public class LobbyScreen implements Screen
{
    Camera camera;
    Viewport viewport;
    Stage stage;
    
    TextButton backButton;
    TextButton forcePlayButton;
    Label labelWaiting;
    Image background;
    
    static final int kNumRooms = 8;
    
    ArrayList<TextButton> roomButtons;
    Color buttonDefaultColor;

    public LobbyScreen()
    {
        camera = new OrthographicCamera(RootSystem.coords.width, RootSystem.coords.height);
        camera.position.set(RootSystem.coords.width/2, RootSystem.coords.height/2, 0);
        viewport = new StretchViewport(RootSystem.coords.width, RootSystem.coords.height, camera);
        
        roomButtons = new ArrayList<TextButton>();
    }
    
    @Override
    public void show()
    {
    	stage = new Stage();
        stage.setViewport(viewport);
        
    	Gdx.input.setInputProcessor(stage);
    	
    	background = new Image(RootSystem.assets.lobbyBackground);
    	stage.addActor(background);
    	
    	labelWaiting = new Label("Please select a room", RootSystem.assets.UISkin);
    	labelWaiting.setAlignment(Align.center);
    	labelWaiting.setSize(RootSystem.coords.lobbyWaitingSize.x, RootSystem.coords.lobbyWaitingSize.y);
    	labelWaiting.setPosition(RootSystem.coords.lobbyWaitingPos.x, RootSystem.coords.lobbyWaitingPos.y);
    	stage.addActor(labelWaiting);
    	
    	forcePlayButton = new TextButton("Force play", RootSystem.assets.UISkin);
    	forcePlayButton.setSize(RootSystem.coords.playButtonSize.x, RootSystem.coords.playButtonSize.y);
    	forcePlayButton.setPosition(RootSystem.coords.playButtonPos.x, RootSystem.coords.playButtonPos.y);
    	forcePlayButton.addListener(new InputListener()
    	{
    		@Override
    		public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
    		{
    			onForcePlay();
    			return true;
    		}
    	});
    	stage.addActor(forcePlayButton);
    	
    	backButton = new TextButton("Back", RootSystem.assets.UISkin);
        backButton.setSize(RootSystem.coords.backButtonSize.x, RootSystem.coords.backButtonSize.y);
        backButton.setPosition(RootSystem.coords.backButtonPos.x, RootSystem.coords.backButtonPos.y);
        backButton.addListener(new InputListener()
        {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
            	onBack();
                return true;
            }
        });
        stage.addActor(backButton);
        
        buttonDefaultColor = backButton.getColor();
        
    	for(int i = 0; i < kNumRooms; i++)
    	{
    		String roomInfo = "Room " + (i + 1);
    		TextButton butt = new TextButton(roomInfo, RootSystem.assets.UISkin);
    		butt.setSize(RootSystem.coords.lobbyRoomButtonSize.x, RootSystem.coords.lobbyRoomButtonSize.y);
    		butt.setPosition(RootSystem.coords.lobbyRoomButtonOrigPos.x, 
    				RootSystem.coords.lobbyRoomButtonOrigPos.y - i * RootSystem.coords.lobbyRoomButtonSize.y);
    		final int index = i;
    		butt.addListener(new InputListener()
            {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
                {
                	onJoinRoom(index);
                    return true;
                }
            });
    		stage.addActor(butt);
    		roomButtons.add(butt);
    	}
    	
        //RootSystem.net.client.askRoom();
    }
    
    public void onJoinRoom(int idx)
    {
    	for(TextButton butt : roomButtons)
    	{
    		butt.setColor(buttonDefaultColor);
    	}
    	roomButtons.get(idx).setColor(0.3f, 1.f, 0.3f, 1.0f);
    	
    	labelWaiting.setText("Waiting for the Room to be filled...");
    	
    	RootSystem.net.client.joinRoom(idx);
    }
    
    public void onForcePlay()
    {
    	RootSystem.screens.showScreen(RootSystem.screens.gameplay, stage);
    }
    
    public void onBack()
    {
    	RootSystem.screens.showScreen(RootSystem.screens.menu, stage);
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

	public void setRoomInfo(RoomInfo[] rooms)
	{
		for(RoomInfo room : rooms)
		{
			roomButtons.get(room.id).setText(new Integer(room.numPlayer).toString());
		}
	}

}
