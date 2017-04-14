package com.emeraldpowder.flatland.world.objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.emeraldpowder.flatland.world.Camera;
import com.emeraldpowder.flatland.world.GameWorld;
import com.emeraldpowder.flatland.world.shapes.*;

public class Player implements IWorldObject
{
    final private float radius = 2;
    private Camera boundCamera;
    private GameWorld parentWorld;
    private boolean visible = true;

    public Player(Camera boundCamera)
    {
        this.boundCamera = boundCamera;
    }

    public boolean isVisible()
    {
        return visible;
    }

    public void setVisible(boolean visible)
    {
        this.visible = visible;
    }

    @Override
    public void act(float deltaTime)
    {

    }

    @Override
    public GameWorld getWorld()
    {
        return parentWorld;
    }

    @Override
    public void setWorld(GameWorld world)
    {
        parentWorld = world;
    }

    public void move(float rotation, float forward, float right)
    {
        boundCamera.getAngle().addRotation(rotation);

        Vector2 forwardMove = boundCamera.getAngle().getDirection().cpy().scl(forward);
        Vector2 rightMove = boundCamera.getAngle().getDirection().cpy().rotate90(1).scl(right);

        boundCamera.getPosition().add(forwardMove);
        if (parentWorld.isAnythingCollidesWithObject(this))
        {
            boundCamera.getPosition().sub(forwardMove);
        }

        boundCamera.getPosition().add(rightMove);
        if (parentWorld.isAnythingCollidesWithObject(this))
        {
            boundCamera.getPosition().sub(rightMove);
        }
    }

    @Override
    public IViewShape getViewShape()
    {
        return null;
    }

    @Override
    public IMiniMapShape getMiniMapShape()
    {
        if (visible)
        {
            return new SphereWithCone(
                    boundCamera.getPosition(),
                    radius,
                    boundCamera.getFarCullingLine(),
                    boundCamera.getAngle(),
                    boundCamera.getFieldOfView(), Color.WHITE);
        }
        else
        {
            return null;
        }
    }

    @Override
    public IPhysicsShape getPhysicsShape()
    {
        return new Sphere(boundCamera.getPosition(), radius);
    }
}
