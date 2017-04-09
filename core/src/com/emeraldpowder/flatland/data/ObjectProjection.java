package com.emeraldpowder.flatland.data;

public class ObjectProjection
{
    private Angle angleStart;
    private Angle angleEnd;

    public Angle getAngleStart()
    {
        return angleStart;
    }

    public void setAngleStart(Angle angleStart)
    {
        this.angleStart = angleStart;
    }

    public Angle getAngleEnd()
    {
        return angleEnd;
    }

    public void setAngleEnd(Angle angleEnd)
    {
        this.angleEnd = angleEnd;
    }

    public Angle getAngleSize()
    {
        return new Angle(angleEnd.getRadians() - angleStart.getRadians());
    }

    public ObjectProjection(Angle angleStart, Angle angleEnd)
    {
        this.angleStart = angleStart;
        this.angleEnd = angleEnd;
    }
}
