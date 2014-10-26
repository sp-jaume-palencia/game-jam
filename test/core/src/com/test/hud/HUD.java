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
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.test.data.PlayerState;
import com.test.systems.RootSystem;

public class HUD extends Group {
	
	Slider timeScroll;
	Button attackButton;
	Button upgradeButton;
	
	Label turnLabel;
	
	Array<Label> arrTroopsLabels;
	Array<Label> arrPointsLabels;
	
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
		
		userBackground = new Image(RootSystem.assets.gradient);		
		userBackground.setSize(RootSystem.coords.width, RootSystem.coords.hudResourceSize.y);
		userBackground.setPosition(0, RootSystem.coords.height - RootSystem.coords.hudResourceSize.y);
	 	addActor(userBackground);
		
		unitsIcon = new Image(RootSystem.assets.units);
		unitsIcon.setSize(RootSystem.coords.hudResourceSize.x, RootSystem.coords.hudResourceSize.y);
		unitsIcon.setPosition(RootSystem.coords.hudResourceOrigPos.x, RootSystem.coords.hudResourceOrigPos.y);
		addActor(unitsIcon);
		
		arrTroopsLabels = new Array<Label>();
		for(int i = 0; i < 4; i++)
		{
			Label troopsLabel = new Label("troops" + i, RootSystem.assets.UISkin);
			Label troopsLabel = new Label("3", RootSystem.assets.UISkin);
			troopsLabel.setColor(PlayerState.getPlayerColor(i + 1));
			troopsLabel.setSize(RootSystem.coords.hudLabelSize.x, RootSystem.coords.hudLabelSize.y);
			troopsLabel.setPosition(RootSystem.coords.hudResourceOrigPos.x + RootSystem.coords.hudResourceSize.x + 10, RootSystem.coords.height - 10 - RootSystem.coords.hudLabelSize.y * (i+1)/2);
			addActor(troopsLabel);
			
			arrTroopsLabels.add(troopsLabel);
		}
		
		arrPointsLabels = new Array<Label>();
		for(int i = 0; i < 4; i++)
		{
			Label pointsLabel = new Label("points" + i, RootSystem.assets.UISkin);
			Label pointsLabel = new Label("1", RootSystem.assets.UISkin);
			pointsLabel.setColor(PlayerState.getPlayerColor(i + 1));
			pointsLabel.setSize(RootSystem.coords.hudLabelSize.x, RootSystem.coords.hudLabelSize.y);
			pointsLabel.setPosition(RootSystem.coords.hudResourceOrigPos.x + RootSystem.coords.hudResourceSize.x * 3 + 10, RootSystem.coords.height - 10 - RootSystem.coords.hudLabelSize.y * (i+1)/2);
			addActor(pointsLabel);
			
			arrPointsLabels.add(pointsLabel);
		}
		
		basesIcon = new Image(RootSystem.assets.bases);
		basesIcon.setSize(RootSystem.coords.hudResourceSize.x, RootSystem.coords.hudResourceSize.y);
		basesIcon.setPosition(RootSystem.coords.hudResourceOrigPos.x + RootSystem.coords.hudResourceSize.x * 2, RootSystem.coords.hudResourceOrigPos.y);
		addActor(basesIcon);
		
		timeLabel = new Label("00:00", RootSystem.assets.UISkin);
		timeLabel.setColor(1f, 1f, 1f, 1f);
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
	 	
	 	setActionsVisible(false);
	}
	
	public void setPlayerColor()
	{
		userBackground.setColor(PlayerState.getPlayerColor(RootSystem.data.playerState.id));
	}
	
	public void startTurnBar(Color color)
	{
		turnBar.setColor(color);
		turnBar.clearActions();
		turnBar.setScale(1.0f);
		turnBar.addAction(Actions.scaleTo(RootSystem.coords.width, 1.0f, RootSystem.constants.turnTime/1000f));
	}
	
	public void setActionsVisible(Boolean visible)
	{
		//disabled
		attackButton.setVisible(false);
		upgradeButton.setVisible(false);
	}
	
	public void startTurn(int playerId)
	{
		String text = RootSystem.data.gameState.isPlayerTurn()? "YOUR TURN!" : "ENEMY " + playerId + " TURN";
		Color playerColor = PlayerState.getPlayerColor(playerId);		
		
		turnLabel.setText(text);
		turnLabel.setVisible(true);
		TextBounds bound = turnLabel.getTextBounds();
		turnLabel.setY(RootSystem.coords.height/2);
		turnLabel.setX((RootSystem.coords.width - bound.width)/2);
		turnLabel.setColor(playerColor);
		
		turnLabel.addAction(Actions.sequence(Actions.fadeIn(0.25f), Actions.delay(0.5f), Actions.fadeOut(1.0f)));
		
		startTurnBar(playerColor);
	}
	
	public void showGameOver(int playerWinner, Runnable exitCallback)
	{
		String text = RootSystem.data.playerState.id == playerWinner? "YOU WON!" : "ENEMY " + playerWinner + " WON!";
		Color playerColor = PlayerState.getPlayerColor(playerWinner);		
		playerColor.a = 0.0f;
	
		turnLabel.clearActions();
		turnLabel.setText(text);
		turnLabel.setVisible(true);
		TextBounds bound = turnLabel.getTextBounds();
		turnLabel.setY(RootSystem.coords.height/2);
		turnLabel.setX((RootSystem.coords.width - bound.width)/2);
		turnLabel.setColor(playerColor);
		
		turnLabel.addAction(Actions.sequence(Actions.fadeIn(0.25f), Actions.delay(2.5f), Actions.fadeOut(0.5f), Actions.run(exitCallback)));
	}
	
	@Override
	public void act(float delta)
	{
		super.act(delta);
		
		long time = (RootSystem.constants.gameTime - (TimeUtils.millis() - RootSystem.data.gameState.initTimestamp))/1000; 
		
		String timeString = String.format("%02d:%02d", time/60, time%60);
		timeLabel.setText(timeString);
	}
	
	public void setTroops(int player, int troops)
	{
		arrTroopsLabels.get(player).setText(String.valueOf(troops));
	}
	
	public void setPoints(int player, int points)
	{
		arrPointsLabels.get(player).setText(String.valueOf(points));
	}
}
