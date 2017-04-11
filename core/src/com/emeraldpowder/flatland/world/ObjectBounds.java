package com.emeraldpowder.flatland.world;

/**
 * Created by glavak on Apr 9, 17.
 */
public class ObjectBounds
{
    private float xStart;
    private float xEnd;

    /**
     * @return Coordinate of object's starting edge, in screen coordinates (0 to 1)
     */
    public float getXStart()
    {
        return xStart;
    }

    /**
     * @return Coordinate of object's ending edge, in screen coordinates (0 to 1)
     */
    public float getXEnd()
    {
        return xEnd;
    }

    public ObjectBounds(float xStart, float xEnd)
    {

        this.xStart = xStart;
        this.xEnd = xEnd;
    }
}
