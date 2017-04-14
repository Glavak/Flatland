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
    private final float movementSensitivity = 30f;
    private final float rotationSensitivity = .04f;

    private SpriteBatch batch;
    private IViewDrawer IViewDrawer;
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
        IViewDrawer = new ViewDrawerFlat(Gdx.graphics.getWidth());
        miniMapDrawer = new MiniMapDrawer(300, 200);

        Camera camera = new Camera();
        camera.getPosition().set(new Vector2(100, 100));
        camera.getAngle().set((float) Math.PI / 4);

        gameWorld = new GameWorld(camera);

        gameWorld.spawnObject(new StaticShape(new Sphere(new Vector2(50, 30), 10, Color.BLUE)));
        gameWorld.spawnObject(new StaticShape(new Sphere(new Vector2(10, 20), 3, Color.RED)));
        gameWorld.spawnObject(new StaticShape(new Line(new Vector2(15, 30), new Vector2(20, 39), Color.FOREST)));

        gameWorld.spawnObject(new StaticShape(new Line(new Vector2(15, 30), new Vector2(20, 150), Color.PURPLE)));
        gameWorld.spawnObject(new StaticShape(new Sphere(new Vector2(200,100),80, Color.BROWN)));

        Gdx.input.setInputProcessor(inputListener);
        Gdx.input.setCursorCatched(true);
    }

    @Override
    public void render(float delta)
    {
        ColorManager.glClear();

        act(delta);

        IViewDrawer.createFrame(gameWorld);
        miniMapDrawer.createFrame(gameWorld);

        batch.begin();
        IViewDrawer.drawFrame(batch);
        miniMapDrawer.drawFrame(batch);
        batch.end();
    }

    private void act(float deltaTime)
    {
        float playerRotation = inputListener.getDeltaX() * deltaTime * rotationSensitivity;
        Vector2 playerMovement = inputListener.getMovingDirection().scl(deltaTime * movementSensitivity);

        gameWorld.getPlayer().move(playerRotation, playerMovement.y, playerMovement.x);
        if(inputListener.isPlayerVisibilityToggled())
        {
            gameWorld.getPlayer().setVisible(!gameWorld.getPlayer().isVisible());
        }
    }

    @Override
    public void resize(int width, int height)
    {
        IViewDrawer.setFrameLength(width);

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
