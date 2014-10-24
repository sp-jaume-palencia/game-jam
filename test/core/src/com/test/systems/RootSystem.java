package com.test.systems;

import com.test.game.TestGame;
import com.test.screens.MenuScreen;

public class RootSystem
{
    public static TestGame game;
    public static AssetsSystem assets;
    public static ScreenSystem screens;
	public static CoordSystem coords;
	public static ConstantsSystem constants;
    
    public void loadSystems(TestGame game)
    {
        RootSystem.game = game;

        coords = new CoordSystem();
        coords.load();
        
        assets = new AssetsSystem();
        assets.load();
        
        screens = new ScreenSystem();
        screens.load();
        
        constants = new ConstantsSystem();
        constants.load();
    }

}