package com.test.hud;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.utils.TimeUtils;
import com.test.systems.RootSystem;

public class HUD extends Group {
	
	Slider timeScroll;
	Button attackButton;
	Button upgradeButton;
	
	Label goldLabel;
	Label pointsLabel;
	Label timeLabel;
	Image goldIcon;
	Image pointsIcon;
	
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
		
		goldIcon = new Image(RootSystem.assets.units);
		goldIcon.setSize(RootSystem.coords.hudResourceSize.x, RootSystem.coords.hudResourceSize.y);
		goldIcon.setPosition(RootSystem.coords.hudResourceOrigPos.x, RootSystem.coords.hudResourceOrigPos.y);
		addActor(goldIcon);
		
		goldLabel = new Label("gld", RootSystem.assets.UISkin);
		goldLabel.setColor(1f, 1f, 0f, 1f);
		goldLabel.setSize(RootSystem.coords.hudResourceSize.x, RootSystem.coords.hudResourceSize.y);
		goldLabel.setPosition(RootSystem.coords.hudResourceOrigPos.x + RootSystem.coords.hudResourceSize.x, RootSystem.coords.hudResourceOrigPos.y);
		addActor(goldLabel);
		
		pointsIcon = new Image(RootSystem.assets.bases);
		pointsIcon.setSize(RootSystem.coords.hudResourceSize.x, RootSystem.coords.hudResourceSize.y);
		pointsIcon.setPosition(RootSystem.coords.hudResourceOrigPos.x + RootSystem.coords.hudResourceSize.x * 2, RootSystem.coords.hudResourceOrigPos.y);
		addActor(pointsIcon);
		
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
	}
	
	public void setActionsVisible(Boolean visible)
	{
		attackButton.setVisible(visible);
		upgradeButton.setVisible(visible);
	}
	
	@Override
	public void act(float delta)
	{
		long tics = (TimeUtils.millis() - RootSystem.data.initTimestamp)/RootSystem.constants.serverTic;
		
		timeLabel.setText(new Integer((int) tics).toString());
	}
}
