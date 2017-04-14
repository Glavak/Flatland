package com.emeraldpowder.flatland.view;

import com.badlogic.gdx.graphics.Color;

public class ViewFrame implements IViewFrame
{
    private float zBuffer[];
    private int colorBuffer[];
    private int colorToDraw;

    ViewFrame(int length)
    {
        resize(length);
    }

    void resize(int length)
    {
        zBuffer = new float[length];
        colorBuffer = new int[length];
    }

    void clear()
    {
        for (int i = 0; i < zBuffer.length; i++)
        {
            zBuffer[i] = 0;
            colorBuffer[i] = ColorManager.viewClearColor;
        }
    }

    float[] getZBuffer()
    {
        return zBuffer;
    }

    int[] getColorBuffer()
    {
        return colorBuffer;
    }

    @Override
    public void drawPixel(int x, float depth)
    {
        if (depth < 0f)
        {
            return;
        }
        else if (depth > 1f)
        {
            return;
        }

        if (zBuffer[x] < depth)
        {
            zBuffer[x] = depth;
            colorBuffer[x] = colorToDraw;
        }
    }

    @Override
    public void setColor(Color color)
    {
        colorToDraw = Color.rgba8888(color);
    }

    @Override
    public int getLength()
    {
        return zBuffer.length;
    }
}
