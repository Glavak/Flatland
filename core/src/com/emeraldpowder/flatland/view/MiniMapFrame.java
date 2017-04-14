package com.emeraldpowder.flatland.view;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class MiniMapFrame implements IMiniMapFrame
{
    private int width;
    private int height;
    private Pixmap pixmap;

    MiniMapFrame(int width, int height)
    {
        this.width = width;
        this.height = height;

        pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
    }

    @Override
    public int getWidth()
    {
        return width;
    }

    @Override
    public int getHeight()
    {
        return height;
    }

    Pixmap getPixmap()
    {
        return pixmap;
    }

    void clear()
    {
        pixmap.setColor(Color.CLEAR);
        pixmap.fill();

        pixmap.setColor(ColorManager.miniMapBackground);
        pixmap.fill();

        pixmap.setColor(ColorManager.miniMapBorders);
        pixmap.drawRectangle(0, 0, getWidth(), getHeight());
    }

    @Override
    public void drawCircle(Vector2 center, float radius)
    {
        center = worldToMiniMap(center);

        pixmap.fillCircle(MathUtils.round(center.x),
                MathUtils.round(center.y),
                MathUtils.round(radius));
    }

    @Override
    public void drawLine(Vector2 start, Vector2 end)
    {
        start = worldToMiniMap(start);
        end = worldToMiniMap(end);

        pixmap.drawLine(MathUtils.round(start.x),
                MathUtils.round(start.y),
                MathUtils.round(end.x),
                MathUtils.round(end.y));
    }

    @Override
    public Vector2 worldToMiniMap(Vector2 world)
    {
        return new Vector2(world.x, /*getHeight() -*/ world.y);
    }

    @Override
    public void setColor(Color color)
    {
        pixmap.setColor(color);
    }
}
