package com.emeraldpowder.flatland;

import com.badlogic.gdx.Game;
import com.emeraldpowder.flatland.view.GameScreen;

import java.util.Random;

public class MainGame extends Game
{
    @Override
    public void create()
    {
        ResourceManager.LoadResources();

        setScreen(new GameScreen());
    }
}
