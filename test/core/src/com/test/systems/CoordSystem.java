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
    public Coord button1Pos;
    public Coord button2Pos;
    
    public void load()
    {
        //Common
        width = 1000;
        height = 500;
        
        //Menuscreen
        button1Size = new Coord(100, 50);
        button2Size = new Coord(100, 50);
        button1Pos = new Coord((width - button1Size.x - button2Size.x - 10)/2, (height - button1Size.y)/2);
        button2Pos = new Coord((width - button1Size.x - button2Size.x - 10)/2 + button1Size.x + 10, (height - button1Size.y)/2);
    }
}