package com.emeraldpowder.flatland.world.shapes;

import com.badlogic.gdx.graphics.Color;
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
        if (distanceToCenter - radius > camera.getFarCullingLine() + 1) return;

        float angularSize = projection.getAngleSize().getRadians();

        int pixelStart = (int) (objectBounds.getXStart() * viewFrame.getLength());
        int pixelEnd = (int) (objectBounds.getXEnd() * viewFrame.getLength());
        int pixelLength = pixelEnd - pixelStart;

        viewFrame.setColor(color);
        for (int i = Math.max(pixelStart, 0); i < Math.min(pixelEnd, viewFrame.getLength()); i++)
        {
            // TODO: it's symmetrical, can be sped up twice
            double position0to1 = (double) (i - pixelStart) / pixelLength;
            float distance = getDistanceToPoint(position0to1, angularSize, distanceToCenter);

            viewFrame.drawPixel(i, 1f - distance / camera.getFarCullingLine());
        }
    }

    /**
     * @param x pixel x 0 to 1, where 0 is left side of circle projection to view line, and 1 is right side
     */
    private float getDistanceToPoint(double x, float angularSize, float distanceToCenter)
    {
        // NOTE: double is really required here, using float causes awful distortion
        double positionMinus1to1 = x * 2 - 1;
        double thetta = Math.abs(positionMinus1to1) * angularSize / 2;

        if (thetta == 0) return distanceToCenter - radius;

        double sinThetta = Math.sin(thetta);
        double sinOmega = distanceToCenter / radius * sinThetta;
        double omega = (Math.PI - Math.asin(sinOmega));
        double epsilon = (Math.PI - omega - thetta);
        return (float) (Math.sin(epsilon) / sinThetta * radius);
    }

    @Override
    public ObjectProjection getObjectProjection(Vector2 viewerPosition)
    {
        Vector2 positionRelativeToViewer = position.cpy().sub(viewerPosition);
        float angleToCenter = positionRelativeToViewer.angleRad();

        float angularSizeHalf = (float) Math.asin(radius / positionRelativeToViewer.len());

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

            if (otherAsLine.getPositionStart().dst(position) < radius) return true;
            if (otherAsLine.getPositionEnd().dst(position) < radius) return true;

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
