package com.emeraldpowder.flatland.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.emeraldpowder.flatland.world.GameWorld;
import com.emeraldpowder.flatland.world.objects.IWorldObject;
import com.emeraldpowder.flatland.world.shapes.IViewShape;

class ViewDrawerLined implements IViewDrawer
{
    private final float lineThreshold = .02f;
    private Pixmap pixmap;
    private ViewFrame visibleFrame;

    ViewDrawerLined(int length)
    {
        pixmap = new Pixmap(length, 1, Pixmap.Format.RGB888);
        visibleFrame = new ViewFrame(length);
    }

    @Override
    public void setFrameLength(int length)
    {
        pixmap = new Pixmap(length, 1, Pixmap.Format.RGB888);
        visibleFrame.resize(length);
    }

    @Override
    public void createFrame(GameWorld world)
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

        float previousLineZ = visibleFrame.getZBuffer()[0];
        for (int i = 0; i < visibleFrame.getLength(); i++)
        {
            pixmap.drawPixel(i, 0, visibleFrame.getColorBuffer()[i]);

            // Blend with z buffer a little:
            float z = visibleFrame.getZBuffer()[i];
            pixmap.drawPixel(i, 0, ColorManager.getFogColor(.35f - z * .35f));

            float dZ = Math.abs(previousLineZ - z);

            if (dZ > lineThreshold)
            {
                float lineBrightness = (dZ - lineThreshold) / lineThreshold;
                if (lineBrightness > 1) lineBrightness = 1;
                lineBrightness = lineBrightness * .5f + .3f;
                pixmap.drawPixel(i, 0, Color.rgba8888(
                        1, 1, 0, lineBrightness));

                previousLineZ = z;
            }
        }
    }

    private float step(float value, int stepsAmount)
    {
        return (int) (value * stepsAmount) / (float) stepsAmount;
    }

    @Override
    public void drawFrame(SpriteBatch batch)
    {
        final float height = 15;

        batch.draw(new Texture(pixmap),
                0, (Gdx.graphics.getHeight() - height) / 2,
                Gdx.graphics.getWidth(), height);
    }
}
