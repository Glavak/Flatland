package com.emeraldpowder.flatland.world.objects;

import com.badlogic.gdx.math.Vector2;
import com.emeraldpowder.flatland.data.Camera;
import com.emeraldpowder.flatland.world.shapes.Empty;
import com.emeraldpowder.flatland.world.shapes.IMiniMapShape;
import com.emeraldpowder.flatland.world.shapes.IViewShape;
import com.emeraldpowder.flatland.world.shapes.SphereWithCone;

public class Player implements IWorldObject
{
    final private float radius = 2;
    private Camera boundCamera;

    public Player(Camera boundCamera)
    {
        this.boundCamera = boundCamera;
    }

    @Override
    public Vector2 getPosition()
    {
        return boundCamera.getPosition();
    }

    @Override
    public void setPosition(Vector2 position)
    {
        boundCamera.setPosition(position);
    }

    @Override
    public void act(float deltaTime)
    {

    }

    public void move(float rotation, float forward, float right)
    {
        boundCamera.getAngle().addRotation(rotation);

        boundCamera.getPosition().add(boundCamera.getAngle().getDirection().cpy().scl(forward));
        boundCamera.getPosition().add(boundCamera.getAngle().getDirection().cpy().rotate90(1).scl(right));
    }

    @Override
    public IViewShape getViewShape()
    {
        return new Empty();
    }

    @Override
    public IMiniMapShape getMiniMapShape()
    {
        return new SphereWithCone(
                getPosition(),
                radius,
                boundCamera.getFarCullingLine(),
                boundCamera.getAngle(),
                boundCamera.getFieldOfView());
    }
}
