package com.emeraldpowder.flatland.view;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.emeraldpowder.flatland.world.GameWorld;

public interface IViewDrawer
{
    void setFrameLength(int length);

    void createFrame(GameWorld world);

    void drawFrame(SpriteBatch batch);
}
