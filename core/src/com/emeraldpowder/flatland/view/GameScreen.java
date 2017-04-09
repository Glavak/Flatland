package com.emeraldpowder.flatland.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.emeraldpowder.flatland.data.Angle;
import com.emeraldpowder.flatland.world.StaticBall;

import java.util.ArrayList;
import java.util.List;

public class GameScreen extends ScreenAdapter
{
    private SpriteBatch batch;
    private WorldDrawer worldDrawer;

    private List<IDrawableOnView> objects = new ArrayList<IDrawableOnView>();

    private Vector2 viewerPosition;
    private Angle viewingAngle;

    public GameScreen()
    {
    }

    @Override
    public void show()
    {
        batch = new SpriteBatch();
        worldDrawer = new WorldDrawer(Gdx.graphics.getWidth());

        objects.add(new WorldObject(new StaticBall()
        {{
            setPosition(new Vector2(10, 10));
        }}));

        viewerPosition = new Vector2(5, 0);
        viewingAngle = new Angle((float) Math.PI / 4);

        Gdx.input.setInputProcessor(new MyInputListener());
        Gdx.input.setCursorCatched(true);
    }

    class MyInputListener extends InputAdapter
    {
        private int lastX;

        @Override
        public boolean mouseMoved(int screenX, int screenY)
        {
            viewingAngle.addRotation((screenX-lastX)/100f);

            lastX = screenX;
            return true;
        }
    }

    @Override
    public void render(float delta)
    {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        worldDrawer.createFrame(viewerPosition, viewingAngle, objects);
        batch.begin();
        worldDrawer.drawFrame(batch);
        batch.end();
    }

    @Override
    public void pause()
    {
        super.pause();
    }

    @Override
    public void resume()
    {
        super.resume();
    }
}
