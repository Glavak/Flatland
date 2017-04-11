package com.emeraldpowder.flatland.view;

import com.badlogic.gdx.graphics.Color;

/**
 * Object with this interface can be passed to IViewShape, which will draw itself
 * on the view (1d-screen) using such object
 */
public interface IWorldFrame
{
    void drawPixel(int x, float depth);

    void setColor(Color color);

    int getLength();
}
