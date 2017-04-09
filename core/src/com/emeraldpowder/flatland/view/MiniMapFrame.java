package com.emeraldpowder.flatland.view;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;

/**
 * Created by glavak on Apr 9, 17.
 */
public class MiniMapFrame
{
    private int width;
    private int height;
    private Pixmap pixmap;

    public MiniMapFrame(int width, int height)
    {
        this.width = width;
        this.height = height;

        pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

    public Pixmap getPixmap()
    {
        return pixmap;
    }

    public void clear()
    {
        pixmap.setColor(Color.CLEAR);
        pixmap.fill();
    }
}
