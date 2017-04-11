package com.emeraldpowder.flatland.view;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

/**
 * Object with this interface can be passed to IMiniMapShape, which will draw itself
 * on the mimi map using such object
 */
public interface IMiniMapFrame
{
    void drawCircle(Vector2 center, float radius);

    void drawLine(Vector2 start, Vector2 end);

    int getWidth();

    int getHeight();

    Vector2 worldToMiniMap(Vector2 world);

    void setColor(Color color);
}
