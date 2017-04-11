package com.emeraldpowder.flatland.world.objects;

import com.emeraldpowder.flatland.world.GameWorld;
import com.emeraldpowder.flatland.world.shapes.IMiniMapShape;
import com.emeraldpowder.flatland.world.shapes.IPhysicsShape;
import com.emeraldpowder.flatland.world.shapes.IViewShape;

public class StaticShape implements IWorldObject
{
    private IViewShape viewShape;
    private IMiniMapShape miniMapShape;
    private IPhysicsShape physicsShape;

    private GameWorld parentWorld;

    public StaticShape(Object shape)
    {
        if (shape instanceof IViewShape)
        {
            viewShape = (IViewShape) shape;
        }
        if (shape instanceof IMiniMapShape)
        {
            miniMapShape = (IMiniMapShape) shape;
        }
        if (shape instanceof IPhysicsShape)
        {
            physicsShape = (IPhysicsShape) shape;
        }

        if (viewShape == null && miniMapShape == null && physicsShape == null)
        {
            throw new IllegalArgumentException(
                    "You should pass IViewShape, IMiniMapShape or IPhysicsShape to constructor");
        }
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

    @Override
    public IViewShape getViewShape()
    {
        return viewShape;
    }

    @Override
    public IMiniMapShape getMiniMapShape()
    {
        return miniMapShape;
    }

    @Override
    public IPhysicsShape getPhysicsShape()
    {
        return physicsShape;
    }
}
