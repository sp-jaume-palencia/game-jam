package com.test.systems;

import com.test.game.TestGame;

public class RootSystem
{
    public static TestGame game;
    public static AssetsSystem assets;
    public static ScreenSystem screens;
    
    public void loadSystems(TestGame game)
    {
        RootSystem.game = game;

        assets = new AssetsSystem();
        assets.load();

    }

}