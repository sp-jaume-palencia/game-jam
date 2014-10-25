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
    
    public void load()
    {
        //Common
        width = 500;
        height = 1000;
        
        //Menuscreen
        button1Size = new Coord(100, 50);
        button2Size = new Coord(100, 50);
        button3Size = new Coord(100, 50);
        button4Size = new Coord(100, 50);
        button1Pos = new Coord((width - button1Size.x - button2Size.x - 10)/2, (height - button1Size.y)/2);
        button2Pos = new Coord((width - button1Size.x - button2Size.x - 10)/2 + button1Size.x + 10, (height - button1Size.y)/2);
        button3Pos = new Coord((width - button1Size.x - button2Size.x - 10)/2, (height - button1Size.y)/4);
        button4Pos = new Coord((width - button1Size.x - button2Size.x - 10)/2 + button1Size.x + 10, (height - button1Size.y)/4);
        label1Pos = new Coord(200,400);
        
        lobbyRoomButtonSize = new Coord(300, 50);
        lobbyRoomButtonOrigPos = new Coord(20, height - 20 - lobbyRoomButtonSize.y);
        
        lobbyWaitingSize = new Coord(100, 50);
        lobbyWaitingPos = new Coord(lobbyRoomButtonOrigPos.x + lobbyRoomButtonSize.x + 100, lobbyRoomButtonOrigPos.y);
        
        backButtonSize = new Coord(100, 50);
        backButtonPos = new Coord(width - backButtonSize.x - 20, height - backButtonSize.y - 20);
        
        playButtonSize = new Coord(200, 100);
        playButtonPos = new Coord(lobbyWaitingPos.x, lobbyWaitingPos.y - lobbyWaitingSize.y - 50);
        
        // Gameplay
        mapSize = new Coord(3000, 3000);
    }
}
