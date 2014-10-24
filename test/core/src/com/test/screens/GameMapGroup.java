package com.test.screens;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.test.systems.RootSystem;


public class GameMapGroup extends Group {
	
	Image background;
	
	GameMapGroup()
	{
		background = new Image(RootSystem.assets.gameplayBackground1);
	}

}
