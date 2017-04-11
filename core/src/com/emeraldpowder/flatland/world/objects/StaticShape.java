package com.emeraldpowder.flatland.world.objects;

import com.emeraldpowder.flatland.world.shapes.IMiniMapShape;
import com.emeraldpowder.flatland.world.shapes.IViewShape;

public class StaticShape implements IWorldObject
{
    // TODO: It should be StaticShape class
    private IViewShape viewShape;
    private IMiniMapShape miniMapShape;

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

        if (viewShape == null && miniMapShape == null)
        {
            throw new IllegalArgumentException(
                    "You should pass IViewShape or IMiniMapShape to constructor of StaticShape");
        }
    }

    public StaticShape(IViewShape viewShape, IMiniMapShape miniMapShape)
    {
        this.viewShape = viewShape;
        this.miniMapShape = miniMapShape;
    }

    @Override
    public void act(float deltaTime)
    {

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
}
