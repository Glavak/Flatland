package com.emeraldpowder.flatland.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.emeraldpowder.flatland.world.GameWorld;
import com.emeraldpowder.flatland.world.objects.IWorldObject;
import com.emeraldpowder.flatland.world.shapes.IViewShape;

class ViewDrawer
{
    private Pixmap pixmap;
    private ViewFrame visibleFrame;

    ViewDrawer(int length)
    {
        pixmap = new Pixmap(length, 1, Pixmap.Format.RGB888);
        visibleFrame = new ViewFrame(length);
    }

    void setFrameLength(int length)
    {
        pixmap = new Pixmap(length, 1, Pixmap.Format.RGB888);
        visibleFrame.resize(length);
    }

    void createFrame(GameWorld world)
    {
        visibleFrame.clear();
        for (IWorldObject object : world.getObjects())
        {
            IViewShape shape = object.getViewShape();
            if (shape != null)
            {
                shape.drawOnView(visibleFrame, world.getCamera());
            }
        }

        for (int i = 0; i < visibleFrame.getLength(); i++)
        {
            pixmap.drawPixel(i, 0, visibleFrame.getColorBuffer()[i]);

            // Blend with z buffer a little:
            pixmap.drawPixel(i, 0, Color.rgba8888(
                    1, 1, 1, .45f - visibleFrame.getZBuffer()[i] * .3f));
        }
    }

    void drawFrame(SpriteBatch batch)
    {
        final float height = 15;

        batch.draw(new Texture(pixmap),
                0, (Gdx.graphics.getHeight() - height) / 2,
                Gdx.graphics.getWidth(), height);
    }
}
