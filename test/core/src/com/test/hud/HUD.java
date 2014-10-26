package com.test.hud;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.test.systems.RootSystem;

public class HUD extends Group {
	
	Slider timeScroll;
	Button attackButton;
	Button upgradeButton;
	
	Label troopsLabel;
	Label pointsLabel;
	Label timeLabel;
	Image unitsIcon;
	Image basesIcon;
	Image turnBar;
	
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
	 	
	 	turnBar = new Image(RootSystem.assets.whitePixel);
	 	turnBar.setSize(1, RootSystem.coords.hudTurnBarSize.y);
	 	turnBar.setPosition(0, RootSystem.coords.hudResourceOrigPos.y - RootSystem.coords.hudResourceSize.y);
	 	addActor(turnBar);
	 	
	 	startTurn(new Color(1, 0, 0, 1));
	}
	
	public void startTurn(Color color)
	{
		turnBar.setColor(color);
		turnBar.addAction(Actions.scaleTo(RootSystem.coords.width, 1.0f, RootSystem.constants.turnTime/1000f));
	}
	
	public void setActionsVisible(Boolean visible)
	{
		attackButton.setVisible(visible);
		upgradeButton.setVisible(visible);
	}
	
	@Override
	public void act(float delta)
	{
		super.act(delta);
		String str = new String(RootSystem.data.gameState.currentTurn+" - "+RootSystem.data.gameState.currentPlayer);
		timeLabel.setText(str);
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
