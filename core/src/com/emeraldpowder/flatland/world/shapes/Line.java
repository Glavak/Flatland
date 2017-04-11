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

public class Line implements IViewShape, IMiniMapShape, IPhysicsShape
{
    private Vector2 positionStart;
    private Vector2 positionEnd;
    private Color color;
    private boolean trigger = false;

    public Line(Vector2 positionStart, Vector2 positionEnd, Color color)
    {
        this.positionStart = positionStart;
        this.positionEnd = positionEnd;
        this.color = color;
    }

    public Line(Vector2 positionStart, Vector2 positionEnd)
    {
        this.positionStart = positionStart;
        this.positionEnd = positionEnd;
        this.color = Color.WHITE;
    }

    @Override
    public void drawOnView(ViewFrame viewFrame, Camera camera)
    {
        LineObjectProjection projection = getObjectProjection(camera.getPosition());

        ObjectBounds objectBounds = camera.getObjectBounds(projection);
        float distanceToStart = camera.getPosition().cpy().sub(positionStart).len();
        float distanceToEnd = camera.getPosition().cpy().sub(positionEnd).len();

        int pixelStart = (int) (objectBounds.getXStart() * viewFrame.getLength());
        int pixelEnd = (int) (objectBounds.getXEnd() * viewFrame.getLength());
        int pixelLength = pixelEnd - pixelStart;

        viewFrame.setColor(color);
        for (int i = Math.max(pixelStart, 0); i < Math.min(pixelEnd, viewFrame.getLength()); i++)
        {
            float position0to1 = (float) (i - pixelStart) / pixelLength;
            float distance;

            if (projection.isInverted)
            {
                distance = distanceToEnd * (1 - position0to1) + distanceToStart * position0to1;
            }
            else
            {
                distance = distanceToEnd * position0to1 + distanceToStart * (1 - position0to1);
            }

            viewFrame.drawPixel(i, 1f - distance / camera.getFarCullingLine());
        }
    }

    @Override
    public LineObjectProjection getObjectProjection(Vector2 viewerPosition)
    {
        Vector2 positionStartRelativeToViewer = positionStart.cpy().sub(viewerPosition);
        Vector2 positionEndRelativeToViewer = positionEnd.cpy().sub(viewerPosition);

        Angle angleToStart = new Angle(positionStartRelativeToViewer);
        Angle angleToEnd = new Angle(positionEndRelativeToViewer);

        boolean isInverted = false;

        if (Angle.between(angleToEnd, angleToStart) < 0)
        {
            Angle.swap(angleToStart, angleToEnd);
            isInverted = true;
        }

        return new LineObjectProjection(angleToStart, angleToEnd, isInverted);
    }

    @Override
    public void drawOnMiniMap(IMiniMapFrame miniMapFrame)
    {
        miniMapFrame.setColor(color);
        miniMapFrame.drawLine(positionStart, positionEnd);
    }

    @Override
    public boolean isCollides(IPhysicsShape other)
    {
        if (!getBoundingBox().overlaps(other.getBoundingBox())) return false;

        if (other instanceof Line)
        {
            Line otherAsLine = (Line) other;

            return VectorUtils.linesIntersects(positionStart, positionEnd,
                    otherAsLine.positionStart, otherAsLine.positionEnd);
        }
        throw new RuntimeException("Not implemented shape types combination");
    }

    @Override
    public Rectangle getBoundingBox()
    {
        return new Rectangle(
                Math.min(positionStart.x, positionEnd.x), Math.min(positionStart.y, positionEnd.y),
                Math.abs(positionStart.x - positionEnd.x), Math.abs(positionStart.y - positionEnd.y));
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

    static private class LineObjectProjection extends ObjectProjection
    {
        private boolean isInverted;

        private LineObjectProjection(Angle angleStart, Angle angleEnd, boolean isInverted)
        {
            super(angleStart, angleEnd);
            this.isInverted = isInverted;
        }
    }
}
