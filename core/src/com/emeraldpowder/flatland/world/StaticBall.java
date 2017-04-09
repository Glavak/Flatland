package com.emeraldpowder.flatland.world;

import com.badlogic.gdx.math.Vector2;
import com.emeraldpowder.flatland.data.Angle;
import com.emeraldpowder.flatland.data.Hemisphere1D;
import com.emeraldpowder.flatland.data.IShape1D;
import com.emeraldpowder.flatland.data.ObjectProjection;

public class StaticBall implements IWorldObject
{
    private Vector2 position;
    private float radius = 2;

    @Override
    public Vector2 getPosition()
    {
        return position;
    }

    @Override
    public void setPosition(Vector2 position)
    {
        this.position = position;
    }

    @Override
    public void act(float deltaTime)
    {

    }

    @Override
    public ObjectProjection getObjectProjection(Vector2 viewerPosition)
    {
        Vector2 positionRelativeToViewer = getPosition().cpy().sub(viewerPosition);
        float angleToCenter = positionRelativeToViewer.angleRad();

        float angularSizeHalf = (float) Math.atan2(radius, positionRelativeToViewer.len());

        return new ObjectProjection(
                new Angle(angleToCenter - angularSizeHalf),
                new Angle(angleToCenter + angularSizeHalf));
    }

    @Override
    public IShape1D getShape1D(Vector2 viewerPosition, Angle viewingAngle, float fieldOfView)
    {
        ObjectProjection projection = getObjectProjection(viewerPosition);

        Angle objectStartRelativeToViewingAngle = new Angle(
                -viewingAngle.getRadians() + projection.getAngleStart().getRadians()
        );
        Angle objectEndRelativeToViewingAngle = new Angle(
                -viewingAngle.getRadians() + projection.getAngleEnd().getRadians()
        );

        float xStart = 0.5f + objectStartRelativeToViewingAngle.getRadians() / fieldOfView;
        float xEnd = 0.5f + objectEndRelativeToViewingAngle.getRadians() / fieldOfView;

        return new Hemisphere1D(xStart, xEnd, viewerPosition.cpy().sub(getPosition()).len(), radius);
    }
}
