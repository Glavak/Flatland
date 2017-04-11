package com.emeraldpowder.flatland.world.shapes;

import com.badlogic.gdx.math.Rectangle;

public interface IPhysicsShape
{
    boolean isCollides(IPhysicsShape other);

    Rectangle getBoundingBox();

    boolean isTrigger();
    void setTrigger(boolean trigger);
}
