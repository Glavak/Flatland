package com.emeraldpowder.flatland.data;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Angle
{
    private Vector2 direction;

    /**
     * @param angle angle in rads
     */
    public Angle(float angle)
    {
        this.direction = new Vector2(MathUtils.cos(angle), MathUtils.sin(angle));
    }

    public float getRadians()
    {
        return direction.angleRad();
    }

    public float getDegrees()
    {
        return direction.angle();
    }

    @Override
    public String toString()
    {
        return "Angle(" + getDegrees() + "deg)";
    }

    public Vector2 getDirection()
    {
        return direction;
    }

    /**
     * Adds given rotation to angle
     *
     * @param angle angle in rads
     */
    public void addRotation(float angle)
    {
        direction.rotateRad(angle);
    }

    /*public static Angle angleDifference(Angle angleA, Angle angleB)
    {
        float phi = Math.abs(angleA.getRadians() - angleB.getRadians());
        if (phi > Math.PI * 2) phi -= Math.PI * 2;

        return phi
    }*/
}
