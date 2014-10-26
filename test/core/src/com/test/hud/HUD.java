package com.test.hud;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.test.data.PlayerState;
import com.test.systems.RootSystem;

public class HUD extends Group {
	
	Slider timeScroll;
	Button attackButton;
	Button upgradeButton;
	
	Label turnLabel;
	Label troopsLabel;
	Label pointsLabel;
	Label timeLabel;
	Image unitsIcon;
	Image basesIcon;
	Image turnBar;
	Image userBackground;
	
	public HUD()
	{
//		timeScroll = new Slider(0, 100, 1, false, RootSystem.assets.UISkin);
//		timeScroll.setColor(0f, 1f, 0f, 1f);
//		timeScroll.setSize(RootSystem.coords.hudScrollSize.x, RootSystem.coords.hudScrollSize.y);
//		timeScroll.setPosition(RootSystem.coords.hudScrollPos.x, RootSystem.coords.hudScrollPos.y);
//		timeScroll.addListener(new InputListener()
//		{
//			@Override
//			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
//			{
//				System.out.println("time");
//				return true;
//			}
//		});
//		addActor(timeScroll);
		
		userBackground = new Image(RootSystem.assets.whitePixel);
		userBackground.setColor(PlayerState.getPlayerColor(RootSystem.data.playerState.id));
		userBackground.setSize(RootSystem.coords.width, RootSystem.coords.hudResourceSize.y);
		userBackground.setPosition(0, RootSystem.coords.height - RootSystem.coords.hudResourceSize.y);
	 	addActor(userBackground);
		
		unitsIcon = new Image(RootSystem.assets.units);
		unitsIcon.setSize(RootSystem.coords.hudResourceSize.x, RootSystem.coords.hudResourceSize.y);
		unitsIcon.setPosition(RootSystem.coords.hudResourceOrigPos.x, RootSystem.coords.hudResourceOrigPos.y);
		addActor(unitsIcon);
		
		troopsLabel = new Label("gld", RootSystem.assets.UISkin);
		troopsLabel.setColor(1f, 1f, 0f, 1f);
		troopsLabel.setSize(RootSystem.coords.hudResourceSize.x, RootSystem.coords.hudResourceSize.y);
		troopsLabel.setPosition(RootSystem.coords.hudResourceOrigPos.x + RootSystem.coords.hudResourceSize.x, RootSystem.coords.hudResourceOrigPos.y);
		addActor(troopsLabel);
		
		basesIcon = new Image(RootSystem.assets.bases);
		basesIcon.setSize(RootSystem.coords.hudResourceSize.x, RootSystem.coords.hudResourceSize.y);
		basesIcon.setPosition(RootSystem.coords.hudResourceOrigPos.x + RootSystem.coords.hudResourceSize.x * 2, RootSystem.coords.hudResourceOrigPos.y);
		addActor(basesIcon);
		
		pointsLabel = new Label("xp", RootSystem.assets.UISkin);
		pointsLabel.setColor(0f, 0.5f, 1f, 1f);
		pointsLabel.setSize(RootSystem.coords.hudResourceSize.x, RootSystem.coords.hudResourceSize.y);
		pointsLabel.setPosition(RootSystem.coords.hudResourceOrigPos.x + RootSystem.coords.hudResourceSize.x * 3, RootSystem.coords.hudResourceOrigPos.y);
		addActor(pointsLabel);
		
		timeLabel = new Label("00:00", RootSystem.assets.UISkin);
		timeLabel.setColor(1f, 0f, 1f, 1f);
		timeLabel.setSize(RootSystem.coords.hudResourceSize.x, RootSystem.coords.hudResourceSize.y);
		timeLabel.setPosition(RootSystem.coords.hudResourceOrigPos.x + RootSystem.coords.hudResourceSize.x * 4, RootSystem.coords.hudResourceOrigPos.y);
		addActor(timeLabel);
		
		attackButton = new Button(RootSystem.assets.UISkin);
		attackButton.setColor(1f, 0f, 0f, 1f);
		attackButton.setSize(RootSystem.coords.hudActionButtonSize.x, RootSystem.coords.hudActionButtonSize.y);
		attackButton.setPosition(RootSystem.coords.hudActionButtonOrigPos.x, RootSystem.coords.hudActionButtonOrigPos.y);
		attackButton.addListener(new InputListener()
        {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                System.out.println("hi");
                return true;
            }
        });
		addActor(attackButton);
		
		upgradeButton = new Button(RootSystem.assets.UISkin);
		upgradeButton.setColor(0f, 0f, 1f, 1f);
		upgradeButton.setSize(RootSystem.coords.hudActionButtonSize.x, RootSystem.coords.hudActionButtonSize.y);
		upgradeButton.setPosition(RootSystem.coords.hudActionButtonOrigPos.x + RootSystem.coords.hudActionButtonSize.x, RootSystem.coords.hudActionButtonOrigPos.y);
		upgradeButton.addListener(new InputListener()
        {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
            	System.out.println("bye");
                return true;
            }
        });
	 	addActor(upgradeButton);
	 	
		turnLabel = new Label("", RootSystem.assets.UISkin);
		turnLabel.setFontScale(3.5f);
		turnLabel.setVisible(false);
		addActor(turnLabel);

	 	turnBar = new Image(RootSystem.assets.whitePixel);
	 	turnBar.setSize(1, RootSystem.coords.hudTurnBarSize.y);
	 	turnBar.setPosition(0, RootSystem.coords.hudResourceOrigPos.y - RootSystem.coords.hudTurnBarSize.y);
	 	addActor(turnBar);
	}
	
	public void startTurnBar(Color color)
	{
		turnBar.setColor(color);
		turnBar.addAction(Actions.scaleTo(RootSystem.coords.width, 1.0f, RootSystem.constants.turnTime/1000f));
	}
	
	public void setActionsVisible(Boolean visible)
	{
		attackButton.setVisible(visible);
		upgradeButton.setVisible(visible);
	}
	
	public void startTurn(int playerId)
	{
		String text = RootSystem.data.gameState.isPlayerTurn()? "YOUR TURN!" : "ENEMY " + playerId + " TURN";
		Color playerColor = PlayerState.getPlayerColor(playerId);		
		playerColor.a = 0.0f;
		
		turnLabel.setText(text);
		turnLabel.setVisible(true);
		TextBounds bound = turnLabel.getTextBounds();
		turnLabel.setY(RootSystem.coords.height/2);
		turnLabel.setX((RootSystem.coords.width - bound.width)/2);
		turnLabel.setColor(playerColor);
		
		turnLabel.addAction(Actions.sequence(Actions.fadeIn(0.25f), Actions.delay(0.5f), Actions.fadeOut(1.0f)));
		
		startTurnBar(playerColor);
	}
	
	public void showGameOver(int playerWinner)
	{
		String text = RootSystem.data.gameState.isPlayerTurn()? "YOU WON!" : "ENEMY " + playerWinner + " WON!";
		Color playerColor = RootSystem.data.playerState.getPlayerColor(playerWinner);		
		playerColor.a = 0.0f;
		
		turnLabel.setText(text);
		turnLabel.setVisible(true);
		TextBounds bound = turnLabel.getTextBounds();
		turnLabel.setY(RootSystem.coords.height/2);
		turnLabel.setX((RootSystem.coords.width - bound.width)/2);
		turnLabel.setColor(playerColor);
		
		turnLabel.addAction(Actions.sequence(Actions.fadeIn(0.25f), Actions.delay(2.5f), Actions.fadeOut(0.5f)));
	}
	
	@Override
	public void act(float delta)
	{
		super.act(delta);
		timeLabel.setText(RootSystem.data.gameState.currentTurn+" - "+RootSystem.data.gameState.currentPlayer);
	}
	
	public void setTroops(int troops)
	{
		troopsLabel.setText(String.valueOf(troops));
	}
	
	public void setPoints(int points)
	{
		pointsLabel.setText(String.valueOf(points));
	}
}
