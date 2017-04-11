package com.emeraldpowder.flatland.view;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

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


        pixmap.setColor(.2f, .2f, .2f, .2f);
        pixmap.fill();

        pixmap.setColor(1, 1, 1, .8f);
        pixmap.drawRectangle(0, 0, getWidth(), getHeight());
    }

    public void drawCircle(Vector2 center, float radius)
    {
        center = worldToMiniMap(center);

        pixmap.fillCircle(MathUtils.round(center.x),
                MathUtils.round(center.y),
                MathUtils.round(radius));
    }

    public void drawLine(Vector2 start, Vector2 end)
    {
        start = worldToMiniMap(start);
        end = worldToMiniMap(end);

        pixmap.drawLine(MathUtils.round(start.x),
                MathUtils.round(start.y),
                MathUtils.round(end.x),
                MathUtils.round(end.y));
    }

    public Vector2 worldToMiniMap(Vector2 world)
    {
        return new Vector2(world.x, /*getHeight() -*/ world.y);
    }
}
