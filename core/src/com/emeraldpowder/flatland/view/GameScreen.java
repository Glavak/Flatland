package com.emeraldpowder.flatland.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.emeraldpowder.flatland.data.Camera;
import com.emeraldpowder.flatland.world.objects.Player;
import com.emeraldpowder.flatland.world.objects.StaticBall;

import java.util.ArrayList;
import java.util.List;

public class GameScreen extends ScreenAdapter
{
    private final float movementSensitivity = 20f;
    private final float rotationSensitivity = .05f;
    private SpriteBatch batch;
    private WorldDrawer worldDrawer;
    private MiniMapDrawer miniMapDrawer;
    private List<IDrawableOnView> worldObjects = new ArrayList<IDrawableOnView>();
    private List<IDrawableOnMiniMap> miniMapObjects = new ArrayList<IDrawableOnMiniMap>();
    private Camera camera;
    private Player player;
    private GameInputListener inputListener = new GameInputListener();

    public GameScreen()
    {
    }

    @Override
    public void show()
    {
        batch = new SpriteBatch();
        worldDrawer = new WorldDrawer(Gdx.graphics.getWidth());
        miniMapDrawer = new MiniMapDrawer(300, 200);

        spawnObject(new WorldObject(new StaticBall()
        {{
            setPosition(new Vector2(10, 20));
        }}));
        spawnObject(new WorldObject(new StaticBall()
        {{
            setPosition(new Vector2(50, 30));
        }})
        {{
            objectColor = Color.rgba8888(Color.RED);
        }});

        camera = new Camera(Gdx.graphics.getWidth());
        camera.getPosition().set(new Vector2(5, 0));
        camera.getAngle().set((float) Math.PI / 4);

        player = new Player(camera);
        spawnObject(new WorldObject(player));

        Gdx.input.setInputProcessor(inputListener);
        Gdx.input.setCursorCatched(true);
    }

    public void spawnObject(WorldObject o)
    {
        worldObjects.add(o);
        miniMapObjects.add(o);
    }

    @Override
    public void render(float delta)
    {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        act(delta);

        worldDrawer.createFrame(worldObjects, camera);
        miniMapDrawer.createFrame(miniMapObjects);

        batch.begin();
        worldDrawer.drawFrame(batch);
        miniMapDrawer.drawFrame(batch);
        batch.end();
    }

    private void act(float deltaTime)
    {
        float playerRotation = inputListener.getDeltaX() * deltaTime * rotationSensitivity;
        Vector2 playerMovement = inputListener.getMovingDirection().scl(deltaTime * movementSensitivity);

        player.move(playerRotation, playerMovement.y, playerMovement.x);
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
