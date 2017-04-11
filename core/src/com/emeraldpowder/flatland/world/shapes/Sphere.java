package com.emeraldpowder.flatland.world.shapes;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.emeraldpowder.flatland.data.Angle;
import com.emeraldpowder.flatland.data.Camera;
import com.emeraldpowder.flatland.view.MiniMapFrame;
import com.emeraldpowder.flatland.world.ObjectBounds;
import com.emeraldpowder.flatland.world.ObjectProjection;

public class Sphere implements IViewShape, IMiniMapShape
{
    /**
     * Position of sphere in world coordinates
     */
    private Vector2 position;
    /**
     * Radius of circle in world coordinates
     */
    private float radius;

    public Sphere(Vector2 position, float radius)
    {
        this.position = position;
        this.radius = radius;
    }

    @Override
    public float[] getDepth(Camera camera)
    {
        ObjectBounds objectBounds = getObjectBounds(camera);
        float distanceToCenter = camera.getPosition().cpy().sub(position).len();

        int circleStart = (int) (objectBounds.getXStart() * camera.getScreenLength());
        int circleEnd = (int) (objectBounds.getXEnd() * camera.getScreenLength());
        int circleLength = circleEnd - circleStart;

        float[] result = new float[camera.getScreenLength()];
        for (int i = Math.max(circleStart, 0); i < Math.min(circleEnd, camera.getScreenLength()); i++)
        {
            float position0to1 = (float) (i - circleStart) / circleLength;
            float angle = (float) Math.acos(position0to1 * 2 - 1);
            float depth = MathUtils.sin(angle) * radius;
            //TODO: clamp
            result[i] = 1f - (distanceToCenter - depth) / camera.getFarCullingLine();
        }
        return result;
    }

    @Override
    public ObjectBounds getObjectBounds(Camera camera)
    {
        ObjectProjection projection = getObjectProjection(camera.getPosition());

        Angle objectStartRelativeToViewingAngle = new Angle(
                -camera.getAngle().getRadians() + projection.getAngleStart().getRadians()
        );
        Angle objectEndRelativeToViewingAngle = new Angle(
                -camera.getAngle().getRadians() + projection.getAngleEnd().getRadians()
        );

        float xStart = 0.5f + objectStartRelativeToViewingAngle.getRadians() / camera.getFieldOfView();
        float xEnd = 0.5f + objectEndRelativeToViewingAngle.getRadians() / camera.getFieldOfView();
        return new ObjectBounds(xStart, xEnd);
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
    public void draw(MiniMapFrame miniMapFrame)
    {
        miniMapFrame.drawCircle(position, radius);
    }
}
