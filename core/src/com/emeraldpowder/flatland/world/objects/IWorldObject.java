package com.emeraldpowder.flatland.world.objects;

import com.badlogic.gdx.math.Vector2;
import com.emeraldpowder.flatland.world.shapes.IMiniMapShape;
import com.emeraldpowder.flatland.world.shapes.IViewShape;

public interface IWorldObject
{
    Vector2 getPosition();
    void setPosition(Vector2 position);

    void act(float deltaTime);

    /**
     * @return IViewShape, which can be used to draw this object on screen
     */
    IViewShape getViewShape();

    /**
     * @return IViewShape, which can be used to draw this object on mini map
     */
    IMiniMapShape getMiniMapShape();
}
