package com.emeraldpowder.flatland.data;

import com.badlogic.gdx.math.Vector2;

public class Camera
{
    private float fieldOfView = (float) Math.PI / 4;
    private float farCullingLine = 50;

    private Vector2 position = new Vector2(0,0);
    private Angle viewingAngle = new Angle(0);
    private int screenLength;

    public Camera(int screenLength)
    {
        this.screenLength = screenLength;
    }

    public float getFieldOfView()
    {
        return fieldOfView;
    }

    public void setFieldOfView(float fieldOfView)
    {
        this.fieldOfView = fieldOfView;
    }

    public float getFarCullingLine()
    {
        return farCullingLine;
    }

    public void setFarCullingLine(float farCullingLine)
    {
        this.farCullingLine = farCullingLine;
    }

    public Vector2 getPosition()
    {
        return position;
    }

    public void setPosition(Vector2 position)
    {
        this.position = position;
    }

    public Angle getAngle()
    {
        return viewingAngle;
    }

    public void setViewingAngle(Angle viewingAngle)
    {
        this.viewingAngle = viewingAngle;
    }

    /**
     * @return screen length in pixels, for which camera is set up
     */
    public int getScreenLength()
    {
        return screenLength;
    }

    /**
     * Sets up camera screen length
     * @param screenLength screen length in pixels
     */
    public void setScreenLength(int screenLength)
    {
        this.screenLength = screenLength;
    }
}
