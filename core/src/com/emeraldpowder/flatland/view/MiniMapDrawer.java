package com.emeraldpowder.flatland.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by glavak on Apr 9, 17.
 */
public class MiniMapDrawer
{
    private MiniMapFrame miniMapFrame;

    public MiniMapDrawer(int width, int height)
    {
        miniMapFrame = new MiniMapFrame(width, height);
    }

    public void createFrame()
    {
        miniMapFrame.clear();
    }

    public void drawFrame(SpriteBatch batch)
    {
        batch.draw(new Texture(miniMapFrame.getPixmap()),
                (Gdx.graphics.getWidth() - miniMapFrame.getWidth()) / 2,
                Gdx.graphics.getHeight() * 3/4,
                miniMapFrame.getWidth(), miniMapFrame.getHeight());
    }
}
