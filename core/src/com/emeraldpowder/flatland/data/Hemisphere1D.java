package com.emeraldpowder.flatland.data;

import com.badlogic.gdx.math.MathUtils;

public class Hemisphere1D implements IShape1D
{
    /**
     * Start edge of shape in screen coordinates (0 to 1)
     */
    float xStart;
    /**
     * End edge of shape in screen coordinates (0 to 1)
     */
    float xEnd;
    /**
     * Distance from viewer to center of circle in world coordinates
     */
    float distanceToCenter;
    /**
     * Radius of circle in world coordinates
     */
    float radius;

    public Hemisphere1D(float xStart, float xEnd, float distanceToCenter, float radius)
    {
        this.xStart = xStart;
        this.xEnd = xEnd;
        this.distanceToCenter = distanceToCenter;
        this.radius = radius;
    }

    @Override
    public float getXStart()
    {
        return xStart;
    }

    @Override
    public float getXEnd()
    {
        return xEnd;
    }

    @Override
    public float getLength()
    {
        return xEnd - xStart;
    }

    @Override
    public float[] getDepth(int screenLength, float farCullingLine)
    {
        float[] result = new float[screenLength];

        int circleStart = (int) (xStart * screenLength);
        int circleEnd = (int) (xEnd * screenLength);
        System.out.println("Circle: "+circleStart+"-"+circleEnd);
        int circleLength = circleEnd - circleStart;
        float circleCenter = (xEnd + xStart) * screenLength / 2;
        for (int i = Math.max(circleStart, 0); i < Math.min(circleEnd, screenLength); i++)
        {
            float position0to1 = (float) (i - circleStart) / circleLength;
            float angle = (float) Math.acos(position0to1 * 2 - 1);
            float depth = MathUtils.sin(angle) * radius;
            //TODO: clamp
            result[i] = 1f-(distanceToCenter - depth) / farCullingLine;
        }

        return result;
    }
}
