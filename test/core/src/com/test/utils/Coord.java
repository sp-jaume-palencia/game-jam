package com.test.utils;

public class Coord
{
    public int x;
    public int y;
    
    public Coord(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public Coord(Coord coord)
    {
        this.x = coord.x;
        this.y = coord.y;
    }

    @Override
    public int hashCode()
    {
        return y*1000 + x;
    }

    @Override
    public boolean equals(Object obj)
    {
       if (!(obj instanceof Coord))
            return false;
        if (obj == this)
            return true;

        Coord rhs = (Coord) obj;
        
        if(x != rhs.x)
            return false;
        if(y != rhs.y)
            return false;
        
        return true;
    }
}