package com.emeraldpowder.flatland.view;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.emeraldpowder.flatland.data.Angle;
import com.emeraldpowder.flatland.data.IShape1D;
import com.emeraldpowder.flatland.world.IWorldObject;

public class WorldObject implements IDrawableOnView
{
    private final IWorldObject object;
    private final int objectColor = Color.rgba8888(Color.BLUE);

    public WorldObject(IWorldObject object)
    {
        this.object = object;
    }

    @Override
    public void draw(WorldFrame worldFrame, Vector2 viewerPosition, Angle viewingAngle)
    {
        IShape1D objectOnScreen = object.getShape1D(viewerPosition, viewingAngle, (float) Math.PI / 3);

        System.out.println(objectOnScreen.getXStart() + ", " + objectOnScreen.getXEnd());

        float[] zBuffer = objectOnScreen.getDepth(worldFrame.getLength(), 15);
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
}
