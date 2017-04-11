package com.emeraldpowder.flatland.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.List;

public class MiniMapDrawer
{
    private MiniMapFrame miniMapFrame;

    public MiniMapDrawer(int width, int height)
    {
        miniMapFrame = new MiniMapFrame(width, height);
    }

    public void createFrame(List<IDrawableOnMiniMap> objects)
    {
        miniMapFrame.clear();

        for (IDrawableOnMiniMap object : objects)
        {
            object.draw(miniMapFrame);
        }
    }

    public void drawFrame(SpriteBatch batch)
    {
        batch.draw(new Texture(miniMapFrame.getPixmap()),
                (Gdx.graphics.getWidth() - miniMapFrame.getWidth()) / 2,
                Gdx.graphics.getHeight() / 4 - miniMapFrame.getHeight() / 2,
                miniMapFrame.getWidth(), miniMapFrame.getHeight());
    }
}
