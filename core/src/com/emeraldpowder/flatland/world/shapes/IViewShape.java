package com.emeraldpowder.flatland.world.shapes;

import com.badlogic.gdx.math.Vector2;
import com.emeraldpowder.flatland.view.ViewFrame;
import com.emeraldpowder.flatland.world.Camera;
import com.emeraldpowder.flatland.world.ObjectProjection;

public interface IViewShape
{
    /**
     * Draws this object on given viewFrame, using given camera
     */
    void drawOnView(ViewFrame viewFrame, Camera camera);

    /**
     * @param viewerPosition camera position
     * @return ObjectProjection object, containing angle bounds of object
     */
    ObjectProjection getObjectProjection(Vector2 viewerPosition);
}
