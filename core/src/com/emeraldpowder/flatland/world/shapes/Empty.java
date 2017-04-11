package com.emeraldpowder.flatland.world.shapes;

import com.badlogic.gdx.math.Vector2;
import com.emeraldpowder.flatland.data.Camera;
import com.emeraldpowder.flatland.view.MiniMapFrame;
import com.emeraldpowder.flatland.world.ObjectBounds;
import com.emeraldpowder.flatland.world.ObjectProjection;

public class Empty implements IViewShape, IMiniMapShape
{
    @Override
    public void draw(MiniMapFrame miniMapFrame)
    {

    }

    @Override
    public float[] getDepth(Camera camera)
    {
        return new float[camera.getScreenLength()];
    }

    @Override
    public ObjectBounds getObjectBounds(Camera camera)
    {
        return null;
    }

    @Override
    public ObjectProjection getObjectProjection(Vector2 viewerPosition)
    {
        return null;
    }
}
