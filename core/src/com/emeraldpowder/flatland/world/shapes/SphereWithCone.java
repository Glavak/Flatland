package com.emeraldpowder.flatland.world.shapes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.emeraldpowder.flatland.data.Angle;
import com.emeraldpowder.flatland.view.IMiniMapFrame;

public class SphereWithCone implements IMiniMapShape
{
    private Vector2 position;
    private float radius;
    /**
     * Angle and size at which center of cone points
     */
    private Vector2 coneDirection;
    /**
     * Size (width) of cone in radians
     */
    private float coneWidth;
    private Color color;

    public SphereWithCone(Vector2 position, float radius, float coneSize, Angle viewingDirection, float coneWidth, Color color)
    {
        this.position = position;
        this.radius = radius;
        this.coneDirection = viewingDirection.getDirection().scl(coneSize);
        this.coneWidth = coneWidth;
        this.coneDirection.rotateRad(-coneWidth / 2);
        this.color = color;
    }

    @Override
    public void drawOnMiniMap(IMiniMapFrame miniMapFrame)
    {
        miniMapFrame.setColor(color);
        miniMapFrame.drawCircle(position, radius);

        miniMapFrame.drawLine(position, position.cpy().add(coneDirection));
        coneDirection.rotateRad(coneWidth);
        miniMapFrame.drawLine(position, position.cpy().add(coneDirection));
        coneDirection.rotateRad(-coneWidth);
    }
}
