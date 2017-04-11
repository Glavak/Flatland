package com.emeraldpowder.flatland.world.shapes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.emeraldpowder.flatland.data.Angle;
import com.emeraldpowder.flatland.view.IMiniMapFrame;
import com.emeraldpowder.flatland.view.WorldFrame;
import com.emeraldpowder.flatland.world.Camera;
import com.emeraldpowder.flatland.world.ObjectBounds;
import com.emeraldpowder.flatland.world.ObjectProjection;

public class Sphere implements IViewShape, IMiniMapShape
{
    private Vector2 position;
    private float radius;
    private Color color;

    public Sphere(Vector2 position, float radius, Color color)
    {
        this.position = position;
        this.radius = radius;
        this.color = color;
    }

    @Override
    public void drawOnView(WorldFrame worldFrame, Camera camera)
    {
        ObjectProjection projection = getObjectProjection(camera.getPosition());

        ObjectBounds objectBounds = camera.getObjectBounds(projection);
        float distanceToCenter = camera.getPosition().cpy().sub(position).len();

        int pixelStart = (int) (objectBounds.getXStart() * camera.getScreenLength());
        int pixelEnd = (int) (objectBounds.getXEnd() * camera.getScreenLength());
        int pixelLength = pixelEnd - pixelStart;


        worldFrame.setColor(color);
        for (int i = Math.max(pixelStart, 0); i < Math.min(pixelEnd, camera.getScreenLength()); i++)
        {
            float position0to1 = (float) (i - pixelStart) / pixelLength;
            float angle = (float) Math.acos(position0to1 * 2 - 1);
            float depth = MathUtils.sin(angle) * radius;

            worldFrame.drawPixel(i, 1f - (distanceToCenter - depth) / camera.getFarCullingLine());
        }
    }

    @Override
    public ObjectProjection getObjectProjection(Vector2 viewerPosition)
    {
        Vector2 positionRelativeToViewer = position.cpy().sub(viewerPosition);
        float angleToCenter = positionRelativeToViewer.angleRad();

        float angularSizeHalf = (float) Math.atan2(radius, positionRelativeToViewer.len());

        return new ObjectProjection(
                new Angle(angleToCenter - angularSizeHalf),
                new Angle(angleToCenter + angularSizeHalf));
    }

    @Override
    public void drawOnMiniMap(IMiniMapFrame miniMapFrame)
    {
        miniMapFrame.setColor(color);
        miniMapFrame.drawCircle(position, radius);
    }
}
