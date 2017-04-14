package com.emeraldpowder.flatland.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;

class ColorManager
{
    static final Color miniMapBorders;
    static final Color miniMapBackground;

    static final int viewClearColor;

    private static final Color clearColor;
    private static final Color viewFogColor;

    // Dark:
    static
    {
        miniMapBackground = new Color(.2f, .2f, .2f, .2f);
        miniMapBorders = new Color(1, 1, 1, .8f);

        viewClearColor = Color.rgba8888(Color.BLACK);

        viewFogColor = new Color(1,1,1, 1);
        clearColor = new Color(.1f, .1f, .1f, 1);
    }

    // Light:
//    static
//    {
//        miniMapBackground = new Color(0f, 0f, 0f, .8f);
//        miniMapBorders = new Color(.1f, .1f, .1f, .8f);
//
//        viewClearColor = Color.rgba8888(Color.BLACK);
//
//        viewFogColor = new Color(.5f,.5f,.5f, 1);
//        clearColor = new Color(1f, 1f, 1f, 1);
//    }

    static int getFogColor(float alpha)
    {
        return Color.rgba8888(viewFogColor.r, viewFogColor.g, viewFogColor.b, alpha);
    }

    static void glClear()
    {
        Gdx.gl.glClearColor(clearColor.r, clearColor.g, clearColor.b, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }
}
