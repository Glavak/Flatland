package com.emeraldpowder.flatland.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.emeraldpowder.flatland.data.Angle;

import java.util.List;

/**
 * Created by glavak on Apr 8, 17.
 */
public class WorldDrawer
{
    private Pixmap pixmap;
    private WorldFrame visibleFrame;

    public WorldDrawer(int length)
    {
        pixmap = new Pixmap(length, 1, Pixmap.Format.RGB888);
        visibleFrame = new WorldFrame(length);
    }

    public void createFrame(Vector2 viewerPosition, Angle viewingAngle, List<IDrawableOnView> objects)
    {
        visibleFrame.clear();
        for (IDrawableOnView object : objects)
        {
            object.draw(
                    visibleFrame,
                    viewerPosition,
                    viewingAngle);
        }

        for (int i = 0; i < visibleFrame.getLength(); i++)
        {
            pixmap.drawPixel(i,0,visibleFrame.getColorBuffer()[i]);
        }
    }

    public void drawFrame(SpriteBatch batch)
    {
        final float height = 10;

        batch.draw(new Texture(pixmap),
                0, (Gdx.graphics.getHeight() - height) / 2,
                Gdx.graphics.getWidth(), height);
    }
}
