package com.emeraldpowder.flatland.view;

import com.badlogic.gdx.graphics.Color;

/**
 * Created by glavak on Apr 8, 17.
 */
public class WorldFrame
{
    private float zBuffer[];
    private int colorBuffer[];

    public WorldFrame(int length)
    {
        zBuffer = new float[length];
        colorBuffer = new int[length];
    }

    public void clear()
    {
        for (int i = 0; i < zBuffer.length; i++)
        {
            zBuffer[i] = 0;
            colorBuffer[i] = Color.argb8888(Color.WHITE);
        }
    }

    public float[] getZBuffer()
    {
        return zBuffer;
    }

    public int[] getColorBuffer()
    {
        return colorBuffer;
    }

    public int getLength()
    {
        return zBuffer.length;
    }
}
