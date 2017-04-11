package com.emeraldpowder.flatland.world.shapes;

import com.badlogic.gdx.math.Vector2;
import com.emeraldpowder.flatland.data.Camera;
import com.emeraldpowder.flatland.world.ObjectBounds;
import com.emeraldpowder.flatland.world.ObjectProjection;

public interface IViewShape
{
    /**
     * @param camera
     * @return depth buffer, containing distances from object to viewer for each pixel
     */
    float[] getDepth(Camera camera);

    /**
     * @param camera
     * @return ObjectBounds object, containing screen bounds of object
     */
    ObjectBounds getObjectBounds(Camera camera);

    /**
     * @param viewerPosition camera position
     * @return ObjectProjection object, containing angle bounds of object
     */
    ObjectProjection getObjectProjection(Vector2 viewerPosition);
}
