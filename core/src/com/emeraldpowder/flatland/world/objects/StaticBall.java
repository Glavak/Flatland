package com.emeraldpowder.flatland.world.objects;

import com.badlogic.gdx.math.Vector2;
import com.emeraldpowder.flatland.world.shapes.IMiniMapShape;
import com.emeraldpowder.flatland.world.shapes.IViewShape;
import com.emeraldpowder.flatland.world.shapes.Sphere;

public class StaticBall implements IWorldObject
{
    private Vector2 position = new Vector2();
    private float radius = 5;

    @Override
    public Vector2 getPosition()
    {
        return position;
    }

    @Override
    public void setPosition(Vector2 position)
    {
        this.position.set(position);
    }

    @Override
    public void act(float deltaTime)
    {

    }

    @Override
    public IViewShape getViewShape()
    {
        return new Sphere(getPosition(), radius);
    }

    @Override
    public IMiniMapShape getMiniMapShape()
    {
        return new Sphere(getPosition(), radius);
    }
}
