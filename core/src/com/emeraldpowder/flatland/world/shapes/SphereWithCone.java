package com.emeraldpowder.flatland.world.shapes;

import com.badlogic.gdx.math.Vector2;
import com.emeraldpowder.flatland.data.Angle;
import com.emeraldpowder.flatland.view.MiniMapFrame;

public class SphereWithCone implements IMiniMapShape
{
    /**
     * Position of sphere in world coordinates
     */
    private Vector2 position;
    /**
     * Radius of circle in world coordinates
     */
    private float radius;
    /**
     * Angle and size at which center of cone points
     */
    private Vector2 coneDirection;
    /**
     * Size (width) of cone in radians
     */
    private float coneWidth;

    public SphereWithCone(Vector2 position, float radius, float coneSize, Angle viewingDirection, float coneWidth)
    {
        this.position = position;
        this.radius = radius;
        this.coneDirection = viewingDirection.getDirection().cpy().scl(coneSize);
        this.coneWidth = coneWidth;
        this.coneDirection.rotateRad(-coneWidth / 2);
    }

    @Override
    public void draw(MiniMapFrame miniMapFrame)
    {
        miniMapFrame.drawCircle(position, radius);

        miniMapFrame.drawLine(position, position.cpy().add(coneDirection));
        coneDirection.rotateRad(coneWidth);
        miniMapFrame.drawLine(position, position.cpy().add(coneDirection));
        coneDirection.rotateRad(-coneWidth);
    }
}
