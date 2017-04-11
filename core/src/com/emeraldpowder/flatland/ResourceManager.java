package com.emeraldpowder.flatland;

import com.badlogic.gdx.graphics.Texture;

class ResourceManager
{
    //TODO: Refactor this awful singleton
    private static Texture pixel;

    static void LoadResources()
    {
        pixel = new Texture("pixel.png");
    }
}
