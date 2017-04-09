package com.emeraldpowder.flatland;

import com.badlogic.gdx.graphics.Texture;

public class ResourceManager
{
    //TODO: Refactor this awful singiletone
    public static Texture pixel;

    public static void LoadResources()
    {
        pixel = new Texture("pixel.png");
    }
}
