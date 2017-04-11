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

    /**
     * @param direction vector, angle between which and 0X will be used
     */
    public Angle(Vector2 direction)
    {
        this.direction = direction.cpy();
    }

    /**
     * Counts angle between vectors
     *
     * @param angleA from this vector
     * @param angleB to this
     * @return angle between vectors in rads, (-Pi to Pi)
     */
    public static float between(Angle angleA, Angle angleB)
    {
        return angleB.direction.angle(angleA.direction);
    }

    /**
     * Swaps values of two angles
     */
    public static void swap(Angle angleA, Angle angleB)
    {
        Vector2 tmp = angleA.direction;
        angleA.direction = angleB.direction;
        angleB.direction = tmp;
    }

    public float getRadians()
    {
        return direction.angleRad();
    }

    public float getDegrees()
    {
        return direction.angle();
    }

    /**
     * @param angle angle in rads
     */
    public void set(float angle)
    {
        direction.set(MathUtils.cos(angle), MathUtils.sin(angle));
    }

    @Override
    public String toString()
    {
        return "Angle(" + getDegrees() + "deg)";
    }

    public Vector2 getDirection()
    {
        return direction.cpy();
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
}
