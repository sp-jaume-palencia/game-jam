package com.test.systems;

import com.test.utils.Coord;

public class CoordSystem
{
    //Common
    public int width;
    public int height;
    
    //Menuscreen
    public Coord button1Size;
    public Coord button2Size;
    public Coord button3Size;
    public Coord button4Size;
    public Coord button1Pos;
    public Coord button2Pos;
    public Coord button3Pos;
    public Coord button4Pos;
	public Coord label1Pos;
    
    //Lobby
    public Coord lobbyRoomButtonSize;
    public Coord lobbyRoomButtonOrigPos;
    
    public Coord lobbyWaitingSize;
    public Coord lobbyWaitingPos;
    
    public Coord backButtonSize;
    public Coord backButtonPos;
    
    public Coord playButtonSize;
    public Coord playButtonPos;
    
    // Gameplay
    public Coord mapSize;
    public Coord planetSize;
    
    //HUD
    public Coord hudScrollSize;
    public Coord hudScrollPos;
    
    public final int kNumButtons = 2;
    public Coord hudActionButtonSize;
    public Coord hudActionButtonOrigPos;
    
    public Coord hudResourceSize;
    public Coord hudResourceOrigPos;
    
    public Coord hudTurnBarSize;
    
    public void load()
    {
        //Common
        width = 500;
        height = 1000;
        
        //Menuscreen
        button1Size = new Coord(200, 100);
        button2Size = new Coord(200, 100);
        button3Size = new Coord(100, 50);
        button4Size = new Coord(100, 50);
        button1Pos = new Coord((width - button1Size.x - button2Size.x - 10)/2, (height - button1Size.y)/2);
        button2Pos = new Coord((width - button1Size.x - button2Size.x - 10)/2 + button1Size.x + 10, (height - button1Size.y)/2);
        button3Pos = new Coord((width - button1Size.x - button2Size.x - 10)/2, (height - button1Size.y)/4);
        button4Pos = new Coord((width - button1Size.x - button2Size.x - 10)/2 + button1Size.x + 10, (height - button1Size.y)/4);
        label1Pos = new Coord(100,500);
        
        //Lobby
        backButtonSize = new Coord(100, 50);
        backButtonPos = new Coord(width - backButtonSize.x - 20, height - backButtonSize.y - 20);
        
        lobbyRoomButtonSize = new Coord(300, 50);
        lobbyRoomButtonOrigPos = new Coord(100, backButtonPos.y - 20 - lobbyRoomButtonSize.y);
        
        lobbyWaitingSize = new Coord(100, 50);
        lobbyWaitingPos = new Coord((width - lobbyWaitingSize.x)/2, lobbyRoomButtonOrigPos.y - lobbyRoomButtonSize.y * 7 - 100);
        
        playButtonSize = new Coord(200, 100);
        playButtonPos = new Coord((width - playButtonSize.x)/2, lobbyWaitingPos.y - lobbyWaitingSize.y - 50);
        
        // Gameplay
        mapSize = new Coord(1200, 1920); //3000, 3000
        planetSize = new Coord(100, 100);
        
        //HUD
//        hudScrollSize = new Coord(width, 250);
        hudScrollSize = new Coord(0, 0);
        hudScrollPos = new Coord(0, 0);
        
        hudActionButtonSize = new Coord(50, 50);
        hudActionButtonOrigPos = new Coord((width - hudActionButtonSize.x * kNumButtons)/2, hudScrollSize.y + 20);
        
        hudResourceSize = new Coord(100, 100);
        hudResourceOrigPos = new Coord(0, height - hudResourceSize.y);
        
        hudTurnBarSize = new Coord(1, 10);
    }
}
