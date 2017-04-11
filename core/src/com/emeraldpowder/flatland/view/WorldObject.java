package com.emeraldpowder.flatland.view;

import com.badlogic.gdx.graphics.Color;
import com.emeraldpowder.flatland.data.Camera;
import com.emeraldpowder.flatland.world.shapes.IViewShape;
import com.emeraldpowder.flatland.world.objects.IWorldObject;

// TODO: remove this class, it does nothing
public class WorldObject implements IDrawableOnView, IDrawableOnMiniMap
{
    private final IWorldObject object;
    public int objectColor = Color.rgba8888(Color.BLUE);

    public WorldObject(IWorldObject object)
    {
        this.object = object;
    }

    @Override
    public void draw(WorldFrame worldFrame, Camera camera)
    {
        // TODO: this should go to WorldFrame
        IViewShape objectOnScreen = object.getViewShape();

        float[] zBuffer = objectOnScreen.getDepth(camera);
        for (int i = 0; i < worldFrame.getLength(); i++)
        {
            if (worldFrame.getZBuffer()[i] < zBuffer[i])
            {
                // If our object's pixel closer than previous's
                worldFrame.getZBuffer()[i] = zBuffer[i];
                worldFrame.getColorBuffer()[i] = objectColor;
            }
        }
    }

    @Override
    public void draw(MiniMapFrame miniMapFrame)
    {
        // TODO: And color management to IWorldObject + ColorManager
        miniMapFrame.getPixmap().setColor(objectColor);
        object.getMiniMapShape().draw(miniMapFrame);
    }
}
