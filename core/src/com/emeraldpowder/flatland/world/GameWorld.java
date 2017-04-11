package com.emeraldpowder.flatland.world;

import com.emeraldpowder.flatland.world.objects.IWorldObject;
import com.emeraldpowder.flatland.world.objects.Player;

import java.util.ArrayList;
import java.util.List;

public class GameWorld
{
    private List<IWorldObject> objects = new ArrayList<IWorldObject>();
    private Player player;
    private Camera camera;

    public GameWorld(Camera camera)
    {
        this.camera = camera;
        player = new Player(this.camera);
        spawnObject(player);
    }

    public Camera getCamera()
    {
        return camera;
    }

    public List<IWorldObject> getObjects()
    {
        return objects;
    }

    public Player getPlayer()
    {
        return player;
    }

    public void spawnObject(IWorldObject object)
    {
        object.setWorld(this);
        objects.add(object);
    }

    public boolean isAnythingCollidesWithObject(IWorldObject targetObject)
    {
        if (targetObject.getPhysicsShape() == null) return false;
        if (targetObject.getPhysicsShape().isTrigger()) return false;

        for (IWorldObject worldObject : objects)
        {
            if (worldObject.equals(targetObject)) continue;

            if (targetObject.getPhysicsShape().isCollides(worldObject.getPhysicsShape()))
            {
                return true;
            }
        }
        return false;
    }
}
