package com.emeraldpowder.flatland.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.emeraldpowder.flatland.world.GameWorld;
import com.emeraldpowder.flatland.world.objects.IWorldObject;
import com.emeraldpowder.flatland.world.shapes.IViewShape;

class ViewDrawerPerspective implements IViewDrawer
{
    private final int height = 50;
    private final int minHeight = 5;
    private Pixmap pixmap;
    private ViewFrame visibleFrame;

    public ViewDrawerPerspective(int length)
    {
        pixmap = new Pixmap(length, height, Pixmap.Format.RGB888);
        pixmap.setFilter(Pixmap.Filter.BiLinear);
        visibleFrame = new ViewFrame(length);
    }

    @Override
    public void setFrameLength(int length)
    {
        pixmap = new Pixmap(length, height, Pixmap.Format.RGB888);
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

        for (int i = 0; i < visibleFrame.getLength(); i++)
        {
            int pixelHeight = (int) ((minHeight + (visibleFrame.getZBuffer()[i] * (height - minHeight))) / 2);

            pixmap.setColor(Color.rgba8888(0,0,0,1));
            pixmap.drawLine(i, 0, i, height);

            pixmap.setColor(visibleFrame.getColorBuffer()[i]);
            pixmap.drawLine(i, height / 2 - pixelHeight, i, height / 2 + pixelHeight);

            pixmap.setColor(Color.rgba8888(
                    1, 1, 1, .45f - visibleFrame.getZBuffer()[i] * .3f));
            pixmap.drawLine(i, height / 2 - pixelHeight, i, height / 2 + pixelHeight);
        }
    }

    @Override
    public void drawFrame(SpriteBatch batch)
    {
        batch.draw(new Texture(pixmap),
                0, (Gdx.graphics.getHeight() - height) / 2,
                Gdx.graphics.getWidth(), height);
    }
}
