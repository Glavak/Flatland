package com.emeraldpowder.flatland.world;

import com.badlogic.gdx.math.Vector2;
import com.emeraldpowder.flatland.data.Angle;

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

    /**
     * Converts absolute object projection to camera circle (which should be generated
     * depending on shape and camera position) to relative projection to 1d-screen
     * @return ObjectBounds object, containing start and end of object at 1d-screen
     */
    public ObjectBounds getObjectBounds(ObjectProjection projection)
    {
        Angle objectStartRelativeToViewingAngle = new Angle(
                -viewingAngle.getRadians() + projection.getAngleStart().getRadians()
        );
        Angle objectEndRelativeToViewingAngle = new Angle(
                -viewingAngle.getRadians() + projection.getAngleEnd().getRadians()
        );

        float xStart = 0.5f + objectStartRelativeToViewingAngle.getRadians() / fieldOfView;
        float xEnd = 0.5f + objectEndRelativeToViewingAngle.getRadians() / fieldOfView;
        return new ObjectBounds(xStart, xEnd);
    }
}
