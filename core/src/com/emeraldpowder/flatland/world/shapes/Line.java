package com.emeraldpowder.flatland.world.shapes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.emeraldpowder.flatland.data.Angle;
import com.emeraldpowder.flatland.view.WorldFrame;
import com.emeraldpowder.flatland.world.Camera;
import com.emeraldpowder.flatland.view.IMiniMapFrame;
import com.emeraldpowder.flatland.world.ObjectBounds;
import com.emeraldpowder.flatland.world.ObjectProjection;

public class Line implements IViewShape, IMiniMapShape
{
    private Vector2 positionStart;
    private Vector2 positionEnd;
    private Color color;

    public Line(Vector2 positionStart, Vector2 positionEnd, Color color)
    {
        this.positionStart = positionStart;
        this.positionEnd = positionEnd;
        this.color = color;
    }

    @Override
    public void drawOnView(WorldFrame worldFrame, Camera camera)
    {
        LineObjectProjection projection = getObjectProjection(camera.getPosition());

        ObjectBounds objectBounds = camera.getObjectBounds(projection);
        float distanceToStart = camera.getPosition().cpy().sub(positionStart).len();
        float distanceToEnd = camera.getPosition().cpy().sub(positionEnd).len();

        int pixelStart = (int) (objectBounds.getXStart() * camera.getScreenLength());
        int pixelEnd = (int) (objectBounds.getXEnd() * camera.getScreenLength());
        int pixelLength = pixelEnd - pixelStart;

        worldFrame.setColor(color);
        for (int i = Math.max(pixelStart, 0); i < Math.min(pixelEnd, camera.getScreenLength()); i++)
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

            worldFrame.drawPixel(i, 1f - distance / camera.getFarCullingLine());
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
