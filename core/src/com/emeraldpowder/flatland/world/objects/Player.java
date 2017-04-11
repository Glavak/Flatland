package com.emeraldpowder.flatland.world.objects;

import com.badlogic.gdx.graphics.Color;
import com.emeraldpowder.flatland.world.Camera;
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
        return null;
    }

    @Override
    public IMiniMapShape getMiniMapShape()
    {
        return new SphereWithCone(
                boundCamera.getPosition(),
                radius,
                boundCamera.getFarCullingLine(),
                boundCamera.getAngle(),
                boundCamera.getFieldOfView(), Color.WHITE);
    }
}
