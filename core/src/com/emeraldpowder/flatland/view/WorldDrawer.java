package com.emeraldpowder.flatland.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.emeraldpowder.flatland.world.Camera;
import com.emeraldpowder.flatland.world.objects.IWorldObject;
import com.emeraldpowder.flatland.world.shapes.IViewShape;

import java.util.List;

class WorldDrawer
{
    private Pixmap pixmap;
    private WorldFrame visibleFrame;

    WorldDrawer(int length)
    {
        pixmap = new Pixmap(length, 1, Pixmap.Format.RGB888);
        visibleFrame = new WorldFrame(length);
    }

    void createFrame(List<IWorldObject> objects, Camera camera)
    {
        visibleFrame.clear();
        for (IWorldObject object : objects)
        {
            IViewShape shape = object.getViewShape();
            if (shape != null)
            {
                shape.drawOnView(visibleFrame, camera);
            }
        }

        for (int i = 0; i < visibleFrame.getLength(); i++)
        {
            pixmap.drawPixel(i, 0, visibleFrame.getColorBuffer()[i]);

            // Blend with z buffer a little:
            pixmap.drawPixel(i, 0, Color.rgba8888(
                    1, 1, 1, visibleFrame.getZBuffer()[i] * .2f + .2f));
        }
    }

    void drawFrame(SpriteBatch batch)
    {
        final float height = 10;

        batch.draw(new Texture(pixmap),
                0, (Gdx.graphics.getHeight() - height) / 2,
                Gdx.graphics.getWidth(), height);
    }
}
