package com.emeraldpowder.flatland.world;

import com.badlogic.gdx.math.Vector2;
import com.emeraldpowder.flatland.data.Angle;
import com.emeraldpowder.flatland.data.IShape1D;
import com.emeraldpowder.flatland.data.ObjectProjection;

public interface IWorldObject
{
    Vector2 getPosition();
    void setPosition(Vector2 position);

    void act(float deltaTime);

    ObjectProjection getObjectProjection(Vector2 viewerPosition);

    IShape1D getShape1D(Vector2 viewerPosition, Angle viewingAngle, float fieldOfView);
}
