package com.emeraldpowder.flatland.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.emeraldpowder.flatland.world.Camera;
import com.emeraldpowder.flatland.world.GameWorld;
import com.emeraldpowder.flatland.world.objects.StaticShape;
import com.emeraldpowder.flatland.world.shapes.Line;
import com.emeraldpowder.flatland.world.shapes.Sphere;

public class GameScreen extends ScreenAdapter
{
    private final float movementSensitivity = 20f;
    private final float rotationSensitivity = .05f;

    private SpriteBatch batch;
    private ViewDrawer viewDrawer;
    private MiniMapDrawer miniMapDrawer;

    private GameWorld gameWorld;

    private GameInputListener inputListener = new GameInputListener();

    public GameScreen()
    {
    }

    @Override
    public void show()
    {
        batch = new SpriteBatch();
        viewDrawer = new ViewDrawer(Gdx.graphics.getWidth());
        miniMapDrawer = new MiniMapDrawer(300, 200);

        Camera camera = new Camera();
        camera.getPosition().set(new Vector2(5, 0));
        camera.getAngle().set((float) Math.PI / 4);

        gameWorld = new GameWorld(camera);

        gameWorld.spawnObject(new StaticShape(new Sphere(new Vector2(50, 30), 10, Color.NAVY)));
        gameWorld.spawnObject(new StaticShape(new Sphere(new Vector2(10, 20), 3, Color.MAROON)));
        gameWorld.spawnObject(new StaticShape(new Line(new Vector2(15, 30), new Vector2(20, 39), Color.FOREST)));

        Gdx.input.setInputProcessor(inputListener);
        Gdx.input.setCursorCatched(true);
    }

    @Override
    public void render(float delta)
    {
        Gdx.gl.glClearColor(.03f, .03f, .03f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        act(delta);

        viewDrawer.createFrame(gameWorld);
        miniMapDrawer.createFrame(gameWorld);

        batch.begin();
        viewDrawer.drawFrame(batch);
        miniMapDrawer.drawFrame(batch);
        batch.end();
    }

    private void act(float deltaTime)
    {
        float playerRotation = inputListener.getDeltaX() * deltaTime * rotationSensitivity;
        Vector2 playerMovement = inputListener.getMovingDirection().scl(deltaTime * movementSensitivity);

        gameWorld.getPlayer().move(playerRotation, playerMovement.y, playerMovement.x);
    }

    @Override
    public void resize(int width, int height)
    {
        viewDrawer.setFrameLength(width);

        batch.getProjectionMatrix().setToOrtho2D(0, 0, width, height);
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
