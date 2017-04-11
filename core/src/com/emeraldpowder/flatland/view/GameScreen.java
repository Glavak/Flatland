package com.emeraldpowder.flatland.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.emeraldpowder.flatland.world.Camera;
import com.emeraldpowder.flatland.world.objects.IWorldObject;
import com.emeraldpowder.flatland.world.objects.Player;
import com.emeraldpowder.flatland.world.objects.StaticShape;
import com.emeraldpowder.flatland.world.shapes.IMiniMapShape;
import com.emeraldpowder.flatland.world.shapes.IViewShape;
import com.emeraldpowder.flatland.world.shapes.Line;
import com.emeraldpowder.flatland.world.shapes.Sphere;

import java.util.ArrayList;
import java.util.List;

public class GameScreen extends ScreenAdapter
{
    private final float movementSensitivity = 20f;
    private final float rotationSensitivity = .05f;
    private SpriteBatch batch;
    private WorldDrawer worldDrawer;
    private MiniMapDrawer miniMapDrawer;
    private List<IWorldObject> objects = new ArrayList<IWorldObject>();
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

        objects.add(new StaticShape(new Sphere(new Vector2(50, 30), 2, Color.RED)));
        objects.add(new StaticShape(new Sphere(new Vector2(10, 20), 2, Color.BLUE)));
        objects.add(new StaticShape(new Line(new Vector2(15, 30), new Vector2(20, 39), Color.YELLOW)));

        camera = new Camera(Gdx.graphics.getWidth());
        camera.getPosition().set(new Vector2(5, 0));
        camera.getAngle().set((float) Math.PI / 4);

        player = new Player(camera);
        objects.add(player);

        Gdx.input.setInputProcessor(inputListener);
        Gdx.input.setCursorCatched(true);
    }

    @Override
    public void render(float delta)
    {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        act(delta);

        worldDrawer.createFrame(objects, camera);
        miniMapDrawer.createFrame(objects);

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
