package com.emeraldpowder.flatland.world.shapes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.emeraldpowder.flatland.data.Angle;
import com.emeraldpowder.flatland.data.VectorUtils;
import com.emeraldpowder.flatland.view.IMiniMapFrame;
import com.emeraldpowder.flatland.view.ViewFrame;
import com.emeraldpowder.flatland.world.Camera;
import com.emeraldpowder.flatland.world.ObjectBounds;
import com.emeraldpowder.flatland.world.ObjectProjection;

public class Sphere implements IViewShape, IMiniMapShape, IPhysicsShape
{
    private boolean trigger = false;
    private Vector2 position;
    private float radius;
    private Color color;

    public Sphere(Vector2 position, float radius, Color color)
    {
        this.position = position;
        this.radius = radius;
        this.color = color;
    }

    public Sphere(Vector2 position, float radius)
    {
        this.position = position;
        this.radius = radius;
        this.color = Color.WHITE;
    }

    @Override
    public void drawOnView(ViewFrame viewFrame, Camera camera)
    {
        ObjectProjection projection = getObjectProjection(camera.getPosition());

        ObjectBounds objectBounds = camera.getObjectBounds(projection);
        float distanceToCenter = camera.getPosition().cpy().sub(position).len();

        int pixelStart = (int) (objectBounds.getXStart() * viewFrame.getLength());
        int pixelEnd = (int) (objectBounds.getXEnd() * viewFrame.getLength());
        int pixelLength = pixelEnd - pixelStart;

        viewFrame.setColor(color);
        for (int i = Math.max(pixelStart, 0); i < Math.min(pixelEnd, viewFrame.getLength()); i++)
        {
            float position0to1 = (float) (i - pixelStart) / pixelLength;
            float angle = (float) Math.acos(position0to1 * 2 - 1);
            float depth = MathUtils.sin(angle) * radius;

            viewFrame.drawPixel(i, 1f - (distanceToCenter - depth) / camera.getFarCullingLine());
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

    @Override
    public boolean isCollides(IPhysicsShape other)
    {
        if (!getBoundingBox().overlaps(other.getBoundingBox())) return false;

        if (other instanceof Sphere)
        {
            Sphere otherAsSphere = (Sphere) other;
            return position.dst(otherAsSphere.position) < (radius + otherAsSphere.radius);
        }

        if (other instanceof Line)
        {
            Line otherAsLine = (Line) other;

            if(otherAsLine.getPositionStart().dst(position) < radius) return true;
            if(otherAsLine.getPositionEnd().dst(position) < radius) return true;

            Vector2 AB = otherAsLine.getPositionEnd().cpy().sub(otherAsLine.getPositionStart());
            Vector2 AO = position.cpy().sub(otherAsLine.getPositionStart());

            Vector2 projection = VectorUtils.projectVectors(AB, AO);
            Vector2 closestPointOnLine = projection.add(otherAsLine.getPositionStart());

            return closestPointOnLine.dst(position) < radius &&
                    VectorUtils.onSegment(otherAsLine.getPositionStart(),
                            closestPointOnLine,
                            otherAsLine.getPositionEnd());
        }

        throw new RuntimeException("Not implemented shape types combination");
    }

    @Override
    public Rectangle getBoundingBox()
    {
        return new Rectangle(
                position.x - radius, position.y - radius,
                radius * 2, radius * 2);
    }

    @Override
    public boolean isTrigger()
    {
        return trigger;
    }

    @Override
    public void setTrigger(boolean trigger)
    {
        this.trigger = trigger;
    }
}
