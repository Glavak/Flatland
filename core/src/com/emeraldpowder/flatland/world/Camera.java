package com.emeraldpowder.flatland.world;

import com.badlogic.gdx.math.Vector2;
import com.emeraldpowder.flatland.data.Angle;

public class Camera
{
    private float fieldOfView = (float) Math.PI / 4;
    private float farCullingLine = 80;

    private Vector2 position = new Vector2(0, 0);
    private Angle viewingAngle = new Angle(0);

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
     * Converts absolute object projection to camera circle (which should be generated
     * depending on shape and camera position) to relative projection to 1d-screen
     *
     * @return ObjectBounds object, containing start and end of object at 1d-screen
     */
    public ObjectBounds getObjectBounds(ObjectProjection projection)
    {
        float objectStartRelativeToViewingAngle =
                -viewingAngle.getPositiveRadians() + projection.getAngleStart().getPositiveRadians();
        float objectEndRelativeToViewingAngle =
                -viewingAngle.getPositiveRadians() + projection.getAngleEnd().getPositiveRadians();

        if (objectStartRelativeToViewingAngle > objectEndRelativeToViewingAngle)
        {
            if (objectStartRelativeToViewingAngle > 0)
            {
                objectStartRelativeToViewingAngle -= Math.PI * 2;
            }
            else
            {
                objectEndRelativeToViewingAngle += Math.PI * 2;
            }
        }

        float xStart = 0.5f + objectStartRelativeToViewingAngle / fieldOfView;
        float xEnd = 0.5f + objectEndRelativeToViewingAngle / fieldOfView;
        return new ObjectBounds(xStart, xEnd);
    }
}
