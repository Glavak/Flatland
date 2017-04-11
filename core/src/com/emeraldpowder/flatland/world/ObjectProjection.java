package com.emeraldpowder.flatland.world;

import com.emeraldpowder.flatland.data.Angle;

public class ObjectProjection
{
    private Angle angleStart;
    private Angle angleEnd;

    public ObjectProjection(Angle angleStart, Angle angleEnd)
    {
        this.angleStart = angleStart;
        this.angleEnd = angleEnd;
    }

    public Angle getAngleStart()
    {
        return angleStart;
    }

    public Angle getAngleEnd()
    {
        return angleEnd;
    }

    public Angle getAngleSize()
    {
        return new Angle(angleEnd.getRadians() - angleStart.getRadians());
    }

    @Override
    public String toString()
    {
        return "ObjectProjection(" + angleStart + " - " + angleEnd + ")";
    }
}
