package com.emeraldpowder.flatland.world.objects;

import com.emeraldpowder.flatland.world.GameWorld;
import com.emeraldpowder.flatland.world.shapes.IMiniMapShape;
import com.emeraldpowder.flatland.world.shapes.IPhysicsShape;
import com.emeraldpowder.flatland.world.shapes.IViewShape;

public interface IWorldObject
{
    void act(float deltaTime);

    void setWorld(GameWorld world);

    GameWorld getWorld();

    /**
     * @return IViewShape, which can be used to draw this object on screen
     */
    IViewShape getViewShape();

    /**
     * @return IMiniMapShape, which can be used to draw this object on mini map
     */
    IMiniMapShape getMiniMapShape();

    /**
     * @return IPhysicsShape, which can be used to check collision with other objects
     */
    IPhysicsShape getPhysicsShape();
}
