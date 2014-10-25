package com.test.systems;

import com.test.game.TestGame;
import com.test.screens.GameMapStage;

public class RootSystem
{
    public static TestGame game;
    public static GameMapStage mapStage;
    public static AssetsSystem assets;
    public static ScreenSystem screens;
	public static CoordSystem coords;
	public static NetSystem net;
	public static ConstantsSystem constants;
	public static DataSystem data;
	public static CommandSystem commands;
    
    public void loadSystems(TestGame game)
    {
        RootSystem.game = game;

        coords = new CoordSystem();
        coords.load();
        
        assets = new AssetsSystem();
        assets.load();
        
        screens = new ScreenSystem();
        screens.load();
        
        net = new NetSystem();
        net.load();
        constants = new ConstantsSystem();
        constants.load();
        
        data = new DataSystem();
        data.load();
        
        commands = new CommandSystem();
        commands.load();
    }

}
